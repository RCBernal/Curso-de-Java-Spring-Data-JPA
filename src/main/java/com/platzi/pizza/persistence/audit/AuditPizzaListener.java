package com.platzi.pizza.persistence.audit;

import com.platzi.pizza.persistence.entity.PizzaEntity;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PreRemove;
import org.springframework.util.SerializationUtils;

public class AuditPizzaListener {

    private PizzaEntity currentValue;

    @PostLoad
    public void postLoad(PizzaEntity pizzaEntity) {
        System.out.println("Entro a PostLoad");
        this.currentValue = SerializationUtils.clone(pizzaEntity);
    }



//    Este método se ejecuta justo después de que la entidad ha sido guardada por primera vez con EntityManager.persist() o con un save() de Spring Data JPA.
    @PostPersist
//    Este método se dispara automáticamente después de que se hace una actualización de esa entidad.
    @PostUpdate
    public void onPostPersist(PizzaEntity pizzaEntity) {
        System.out.println("Entro a PostPersist y PostUpdate");
        System.out.println("OLD VALUE " + this.currentValue.toString());
        System.out.println("NEW VALUE " + pizzaEntity.toString());
    }



    @PreRemove
    public void onPreDelete(PizzaEntity pizzaEntity) {
        System.out.println(pizzaEntity.toString());
        System.out.println("Entro a PreRemove");
    }



}


/*
Anotación	       Momento en que se ejecuta
@PrePersist	 --->  Antes de insertar (INSERT)
@PostPersist --->  Después de insertar
@PreUpdate	 --->  Antes de actualizar
@PostUpdate	 --->  Después de actualizar
@PreRemove	 --->  Antes de eliminar
@PostRemove	 --->  Después de eliminar
@PostLoad	 --->  Después de cargar la entidad desde la BD
*/
