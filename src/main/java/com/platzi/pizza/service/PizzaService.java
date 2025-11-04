package com.platzi.pizza.service;

import com.platzi.pizza.persistence.entity.PizzaEntity;
import com.platzi.pizza.persistence.repository.PizzaPagSortRepository;
import com.platzi.pizza.persistence.repository.PizzaRepository;
import com.platzi.pizza.service.dto.UpdatePizzaPriceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
public class PizzaService {
//    para tener consultas dentro del servicio vamos a usar JDBC TEMPLATE

/*    vamos a remplazar JdbcTemplate jdbcTemplate la la Interface
      PizzaRepository que extiende de ListCrudRepository<PizzaEntity,Integer>
*/
//    private final JdbcTemplate jdbcTemplate;
    private final PizzaRepository pizzaRepository;
//    importamos el repositorio de PaginandSortingRepository
    private final PizzaPagSortRepository pizzaPagSortRepository;

    @Autowired //se encarga de inyeccion de dependencias de todos los componentes
    public PizzaService(PizzaRepository pizzaRepository, PizzaPagSortRepository pizzaPagSortRepository) {
        this.pizzaRepository = pizzaRepository;
        this.pizzaPagSortRepository = pizzaPagSortRepository;
    }
 /*   public PizzaService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }*/

//    Nos permite retornar todas las entidades que se encuentran en la tabla Pizza
    public Page<PizzaEntity> getAll(int page, int size) {
//        return this.jdbcTemplate.query("select * from pizza order by id_pizza DESC",new BeanPropertyRowMapper<>(PizzaEntity.class));
        //return pizzaRepository.findAll(); vamos a modificar el retur para que sea paginada
        Pageable pageRequest = PageRequest.of(page, size);
        return this.pizzaPagSortRepository.findAll(pageRequest);
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

    public void delete(int idPizza) {
        this.pizzaRepository.deleteById(idPizza);
    }

    public Page<PizzaEntity> findAllByavailable(int page, int size, String sortBy, String sortDirection) {
        System.out.println(this.pizzaRepository.countByVeganTrue());
//      return this.pizzaRepository.findAllByAvailableTrueOrderByPriceDesc();

        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageRequest = PageRequest.of(page, size, sort);
        return this.pizzaPagSortRepository.findByAvailableTrue(pageRequest);
    }

    public PizzaEntity findbyname(String name) {
//        return this.pizzaRepository.findAllByAvailableTrueAndNameIgnoreCase(name);
        return this.pizzaRepository.findFirstByAvailableTrueAndNameIgnoreCase(name).orElseThrow(()->new RuntimeException("La pizza no existe"));
    }

    public List<PizzaEntity> getWith(String ingredient) {
        return this.pizzaRepository.findAllByAvailableTrueAndDescriptionContainingIgnoreCase(ingredient);
    }

    public List<PizzaEntity> getWithOut(String ingredient) {
        return this.pizzaRepository.findAllByAvailableTrueAndDescriptionNotContainingIgnoreCase(ingredient);
    }

    public List<PizzaEntity> getCheapest(double price) {
        return this.pizzaRepository.findTop3ByAvailableTrueAndPriceLessThanEqualOrderByPriceAsc(price);
    }

  /*  La anotación @Transactional garantiza que las transacciones sean atómicas incluso con
    una sola operación. No solo es para métodos con dos o más operaciones;
    asegura integridad y rollback en errores*/
    @Transactional
    public void updatePrice(UpdatePizzaPriceDto dto){
        this.pizzaRepository.updatePrice(dto);
    }
}


/*
JdbcTemplate como JpaRepository sirven para acceder a bases de datos en aplicaciones Java (Spring),
pero funcionan a niveles muy diferentes de abstracción.
Vamos a ver sus diferencias principales
*/
