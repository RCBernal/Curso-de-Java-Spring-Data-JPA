package com.platzi.pizza.web.controller;

import com.platzi.pizza.persistence.entity.PizzaEntity;
import com.platzi.pizza.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;



import java.util.List;
import java.util.Optional;

//@RestController lo que indica a Spring que esta clase va a ser un controlador de una API Rest
@RestController
@RequestMapping("/api/pizzas")
public class PizzaController {
    private final PizzaService pizzaService;

//    Se crea el constructor
    @Autowired
    public PizzaController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @GetMapping("/all")
    public ResponseEntity<Page<PizzaEntity>> getAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "4") int size) {
//        return  new ResponseEntity<>(pizzaService.getAll(), HttpStatus.OK);
        return ResponseEntity.ok( this.pizzaService.getAll(page, size));
    }

    @GetMapping("/{idp}")
    public ResponseEntity <PizzaEntity> getById(@PathVariable int idp) {
        return ResponseEntity.ok(this.pizzaService.getById(idp));
    }

    @PostMapping("/save")
    public ResponseEntity<PizzaEntity> save(@RequestBody PizzaEntity pizzaEntity) {
        if (pizzaEntity.getIdPizza() == null || !this.pizzaService.exists(pizzaEntity.getIdPizza())) {
            return ResponseEntity.ok(this.pizzaService.save(pizzaEntity));
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/update")
    public ResponseEntity<PizzaEntity> update(@RequestBody PizzaEntity pizzaEntity) {
        if (pizzaEntity.getIdPizza() != null && this.pizzaService.exists(pizzaEntity.getIdPizza())) {
            return ResponseEntity.ok(this.pizzaService.save(pizzaEntity));
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/delete/{idPizza}")
    public ResponseEntity<Void> deleteById(@PathVariable int idPizza) {
        if (this.pizzaService.exists(idPizza)) {
            this.pizzaService.delete(idPizza);
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/available")
    public ResponseEntity<List<PizzaEntity>> findAllByavailable() {
        return ResponseEntity.ok(this.pizzaService.findAllByavailable());
    }

    @GetMapping("/pizzaname/{name}")
    public ResponseEntity<PizzaEntity> findAllByName(@PathVariable String name) {
        return ResponseEntity.ok(this.pizzaService.findbyname(name));
    }

    @GetMapping("/ingredient/{ingredient}")
    public ResponseEntity <List<PizzaEntity>> getWith(@PathVariable String ingredient){
        return ResponseEntity.ok(this.pizzaService.getWith(ingredient));
    }

    @GetMapping("/ingredientout/{ingredient}")
    public ResponseEntity <List<PizzaEntity>> getWithout(@PathVariable String ingredient){
        return ResponseEntity.ok(this.pizzaService.getWithOut(ingredient));
    }

    @GetMapping("/cheapest/{price}")
    public ResponseEntity<List<PizzaEntity>> getCheapest(@PathVariable double price){
        return ResponseEntity.ok(this.pizzaService.getCheapest(price));
    }
}
