package com.platzi.pizza.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@IdClass(OrderItemId.class)
@Table(name = "order_item")
public class OrderItemEntity {


    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_item",nullable = false)
    private Integer idItem;

    @Id
    @Column(name = "id_order",nullable = false)
    private Integer idOrder;


    @Column(name = "id_pizza",nullable = false)
    private Integer idPizza;

    @Column(nullable = false, columnDefinition = "DECIMAL(2,1)")
    private Double quantity;

    @Column(nullable = false, columnDefinition = "DECIMAL(5,2)")
    private Double price;

//    vamos a crear las relaciones entre tablas

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_pizza",referencedColumnName = "id_pizza",insertable = false,updatable = false)
    private PizzaEntity pizza;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_order",referencedColumnName = "id_order",insertable = false,updatable = false)
    private OrderEntity  order;

}
