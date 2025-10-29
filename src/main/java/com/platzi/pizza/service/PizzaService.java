package com.platzi.pizza.service;

import com.platzi.pizza.persistence.entity.PizzaEntity;
import com.platzi.pizza.persistence.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PizzaService {
//    para tener consultas dentro del servicio vamos a usar JDBC TEMPLATE

/*    vamos a remplazar JdbcTemplate jdbcTemplate la la Interface
      PizzaRepository que extiende de ListCrudRepository<PizzaEntity,Integer>
*/
//    private final JdbcTemplate jdbcTemplate;
    private final PizzaRepository pizzaRepository;

    @Autowired //se encarga de inyeccion de dependencias de todos los componentes
    public PizzaService(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }
 /*   public PizzaService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }*/

//    Nos permite retornar todas las entidades que se encuentran en la tabla Pizza
    public List<PizzaEntity> getAll() {
//        return this.jdbcTemplate.query("select * from pizza order by id_pizza DESC",new BeanPropertyRowMapper<>(PizzaEntity.class));
        return pizzaRepository.findAll();
    }

    public PizzaEntity getById(int id) {
//        return this.jdbcTemplate.query("select * from pizza where id_pizza = ?",new BeanPropertyRowMapper<>(PizzaEntity.class),id);
        return pizzaRepository.findById(id).orElse(null);
    }

    public PizzaEntity save(PizzaEntity pizza) {
        return pizzaRepository.save(pizza);
    }
    public boolean exists(int idPizza) {
        return pizzaRepository.existsById(idPizza);
    }
}


/*
JdbcTemplate como JpaRepository sirven para acceder a bases de datos en aplicaciones Java (Spring),
pero funcionan a niveles muy diferentes de abstracción.
Vamos a ver sus diferencias principales
*/
