package com.platzi.pizza.service;

import com.platzi.pizza.persistence.entity.OrderEntity;
import com.platzi.pizza.persistence.projection.OrderSUmmary;
import com.platzi.pizza.persistence.repository.OrderRepository;
import com.platzi.pizza.service.dto.RandomOrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class OrderService {
//    vamos a inyectar el repositorio
    private final OrderRepository orderRepository;

    private static final String DELIVERY ="D";
    private static final String CARRYOUT ="C";
    private static final String ON_SITE ="S";


    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<OrderEntity> findAll() {
        return orderRepository.findAll();
    }

    public List<OrderEntity>getToDayOrders(){
        LocalDateTime today = LocalDate.now() .atTime(0,0);
        return  this.orderRepository.findAllByDateAfter(today);
    }

    public List<OrderEntity>getOutsideOrders(){
        List<String> methods= Arrays.asList(DELIVERY,CARRYOUT);
        return this.orderRepository.findAllByMethodIn(methods);
    }

    public List<OrderEntity> getCustomerOrders(String idCustomer){
        List<OrderEntity> order= this.orderRepository.findCustomerOrders(idCustomer);
        if(order.isEmpty()){
            throw new RuntimeException("El cliente no tiene pedidios");
        }else {
            return order;
        }
    }

    public OrderSUmmary getSummary(int orderId){
        return this.orderRepository.findSummary(orderId);
    }

    @Transactional
    public boolean saveRandomOrder(RandomOrderDto  randomOrder){
        return this.orderRepository.saveRandomOrder(randomOrder.getIdCustomer(),randomOrder.getMethod());
    }

}
