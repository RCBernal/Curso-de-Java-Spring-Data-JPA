package com.platzi.pizza.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "customer")
@Getter
@Setter
@NoArgsConstructor
public class CustomerEntity {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_customer",nullable = false,length = 15)
    private String idCustomer;

    @Column(unique = true, nullable = false,length = 60)
    private String name;

    @Column(nullable = false,length = 100)
    private String address;

    @Column(unique = true, nullable = false,length = 50)
    private String email;

    @Column(name = "phone_number", unique = true, nullable = false,length = 20)
    private String phone;
}
