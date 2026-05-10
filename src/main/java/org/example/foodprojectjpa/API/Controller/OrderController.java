package org.example.foodprojectjpa.API.Controller;

import jakarta.validation.Valid;
import org.example.foodprojectjpa.API.DTOs.Orders.OrderItemResponseDTO;
import org.example.foodprojectjpa.API.DTOs.Orders.OrderRequestDTO;
import org.example.foodprojectjpa.API.DTOs.Orders.OrderResponseDTO;
import org.example.foodprojectjpa.API.Entity.StatusType;
import org.example.foodprojectjpa.API.Service.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @GetMapping
    public Page<OrderResponseDTO> getOrders(@PageableDefault(size = 5) Pageable pageable) {

        return orderService.getAll(pageable);

    }

    @PostMapping()
    public OrderResponseDTO createOrder(@Valid @PathVariable Long id, @RequestBody OrderRequestDTO dto) {

        return orderService.createOrder(dto);

    }

    @PutMapping("/{id}")
    public OrderResponseDTO updateOrderStatus(@Valid @PathVariable Long id, @RequestParam StatusType status) {

        return orderService.updateOrderStatus(id, status);

    }

    @PutMapping("/{id}")
    public OrderItemResponseDTO updateOrderItem(@Valid @PathVariable Long id, @RequestParam Long orderItemId, @RequestParam Long newFoodId, @RequestParam Double price, @RequestParam Integer quantity) {

        return orderService.updateOrderItem(id, orderItemId, newFoodId, price, quantity);

    }

    @DeleteMapping("/{id}")
    public String deleteById(@Valid @PathVariable Long id) {
        orderService.deleteOrderById(id);

        return "Order Has been deleted";
    }
}
