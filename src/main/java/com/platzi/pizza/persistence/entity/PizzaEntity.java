package com.platzi.pizza.persistence.entity;

import com.platzi.pizza.persistence.audit.AuditPizzaListener;
import com.platzi.pizza.persistence.audit.AuditableEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;

@Entity
@Table(name="pizza")
@EntityListeners({AuditingEntityListener.class, AuditPizzaListener.class}) //ya con esto el Entity sabra que tiene la capacidad de ser auditado con fecha de creacion y fecha de modificacion
@Getter
@Setter
@NoArgsConstructor
public class PizzaEntity extends AuditableEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_pizza", nullable=false)
    private Integer idPizza;

    @Column(nullable=false, unique=true,length=30)
    private String name;

    @Column(nullable=false, length=150, unique=true)
    private String description;

    @Column(nullable=false, columnDefinition = "Decimal(5,2)")
    private Double price;

    @Column(columnDefinition = "TINYINT")
    private Boolean vegetarian;

    @Column(columnDefinition = "TINYINT")
    private Boolean vegan;

    @Column(columnDefinition = "TINYINT",nullable = false)
    private Boolean available;

/*//    Para que la auditoria funcione se tiene que agregar  createdDate y LastModifiedDate
    @Column(name = "created_date")
    @CreatedDate
//    @JsonIgnore si no desamos que se muestren los datos
    private LocalDateTime createdDate;

    @Column(name = "modified_date")
    @LastModifiedDate
    private LocalDateTime modifiedDate;*/


    @Override
    public String toString() {
        return "PizzaEntity{" +
                "idPizza=" + idPizza +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", vegetarian=" + vegetarian +
                ", vegan=" + vegan +
                ", available=" + available +
                '}';
    }
}
