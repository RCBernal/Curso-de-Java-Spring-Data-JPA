package com.platzi.pizza.persistence.repository;

import com.platzi.pizza.persistence.entity.CustomerEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends ListCrudRepository<CustomerEntity,String> {

//    Base de Datos con JPQL en Spring Boot
    @Query(value = "SELECT c FROM CustomerEntity c Where c.phone =:phone")
   CustomerEntity findByPhone(@Param("phone") String phone);
}

//Nos va  a permitir hacer consultas  la base de datos a traves de Entitys en lugar de usar SQL