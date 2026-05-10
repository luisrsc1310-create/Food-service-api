package org.example.foodprojectjpa.API.Controller;

import jakarta.validation.Valid;
import org.example.foodprojectjpa.API.DTOs.Food.FoodRequestDTO;
import org.example.foodprojectjpa.API.DTOs.Orders.OrderItemRequestDTO;
import org.example.foodprojectjpa.API.DTOs.Orders.OrderItemResponseDTO;
import org.example.foodprojectjpa.API.DTOs.Orders.OrderRequestDTO;
import org.example.foodprojectjpa.API.DTOs.Orders.OrderResponseDTO;
import org.example.foodprojectjpa.API.Entity.StatusType;
import org.example.foodprojectjpa.API.Service.FoodService;
import org.example.foodprojectjpa.API.Service.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;
    private final FoodService foodService;

    public OrderController(OrderService orderService, FoodService foodService) {
        this.orderService = orderService;
        this.foodService = foodService;
    }

    @GetMapping
    public Page<OrderResponseDTO> getOrders(@PageableDefault(size = 5) Pageable pageable) {

        return orderService.getAll(pageable);

    }

    @PostMapping()
    public OrderResponseDTO createOrder(@Valid @RequestBody OrderRequestDTO dto) {

        return orderService.createOrder(dto);

    }

    @PutMapping("/{id}")
    public OrderResponseDTO updateOrderStatus(@PathVariable Long id, @RequestParam StatusType status) {

        return orderService.updateOrderStatus(id, status);

    }

    @PutMapping("/{orderId}/items/{itemId}")
    public OrderItemResponseDTO updateOrderItem( @PathVariable Long orderId, @PathVariable Long itemId, @RequestBody @Valid OrderItemRequestDTO dto) {

        return orderService.updateOrderItem(orderId, itemId, dto);
    }



    @DeleteMapping("/{id}")
    public String deleteById(@Valid @PathVariable Long id) {
        orderService.deleteOrderById(id);

        return "Order Has been deleted";
    }
}
