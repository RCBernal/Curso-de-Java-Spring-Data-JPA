package com.platzi.pizza.persistence.repository;

import com.platzi.pizza.persistence.entity.OrderEntity;
import com.platzi.pizza.persistence.entity.PizzaEntity;
import com.platzi.pizza.persistence.projection.OrderSUmmary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends ListCrudRepository<OrderEntity,Integer> {
    List<OrderEntity> findAllByDateAfter(LocalDateTime date);
    List<OrderEntity> findAllByMethodIn(List<String> methods);

//    Consultas SQL nativas y ordenación con Hibernate en Spring Data
//    Con el nativeQuery le estamos indicando a spring data que es cogigo nativo de sql
//@Query(value = "SELECT * FROM pizza_order WHERE id_customer= :id", nativeQuery = true)
    @Query(value = "select p.* from pizza_order as p inner join order_item as o on p.id_order=o.id_order where p.id_customer=:id", nativeQuery = true)
    List<OrderEntity> findCustomerOrders(@Param("id") String idCustomer);

    @Query(value = """
      select po.id_order as idOrder,cu.name as costumerName,po.date as dateOrder,po.total as orderTotal, GROUP_CONCAT(pi.name) as pizzaNames
      from pizza_order as po
      inner join customer as cu on po.id_customer = cu.id_customer
      inner join order_item as oi on po.id_order = oi.id_order
      inner join pizza as pi on oi.id_pizza = pi.id_pizza
      where po.id_order=:orderId
      GROUP BY po.id_order,cu.name,po.date,po.total
     """, nativeQuery = true)
    OrderSUmmary findSummary(@Param("orderId") Integer orderId);


    @Procedure(value = "take_random_pizza_order",outputParameterName ="order_taken" )
    boolean saveRandomOrder(@Param("id_customer") String idCustomer,@Param("method")String method);
}
