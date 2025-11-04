package com.platzi.pizza.web.controller;


import com.platzi.pizza.persistence.entity.OrderEntity;
import com.platzi.pizza.persistence.projection.OrderSUmmary;
import com.platzi.pizza.service.OrderService;
import com.platzi.pizza.service.dto.RandomOrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    private OrderService  orderService;

//    Se crea el constructor
    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<OrderEntity>> finAllOrders() {
        List<OrderEntity> orders = orderService.findAll();
        orders.forEach(order -> System.out.println(order.getCustomer().getName()));
        return  ResponseEntity.ok(orders);
    }

    @GetMapping("/today")
    public ResponseEntity<List<OrderEntity>> getTodayOrder() {
        return  ResponseEntity.ok(orderService.getToDayOrders());
    }

    @GetMapping("/outside")
    public  ResponseEntity<List<OrderEntity>> getOutSideOrders(){
        return  ResponseEntity.ok(orderService.getOutsideOrders());
    }

    @GetMapping("/customer/{idCustomer}")
    public ResponseEntity<List<OrderEntity>> getCustomerOrders(@PathVariable String idCustomer){
        return ResponseEntity.ok(orderService.getCustomerOrders(idCustomer));
    }

    @GetMapping("/summary/{orderId}")
    public ResponseEntity<OrderSUmmary> getSummary(@PathVariable int orderId){
        return ResponseEntity.ok(orderService.getSummary(orderId));
    }

    @PostMapping("/random")
    public ResponseEntity<Boolean> randomOrder(@RequestBody RandomOrderDto dto){
        return ResponseEntity.ok(orderService.saveRandomOrder(dto));
    }
}
