package com.platzi.pizza.persistence.projection;

import java.time.LocalDateTime;

//las projecciones son DTO que definimos a nuestra medida ya que solo necesitamos crear una interfaz con los atributos que deseamos
public interface OrderSUmmary {

    Integer getIdOrder();
    String getCostumerName();
    LocalDateTime getDateOrder();
    Double getOrderTotal();
    String getPizzaNames();


}
