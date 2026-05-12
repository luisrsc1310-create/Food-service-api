package org.example.foodprojectjpa.API.Controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
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

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public Page<OrderResponseDTO> getOrders(@PageableDefault(size = 12) Pageable pageable) {

        return orderService.getAll(pageable);

    }

    @PostMapping()
    public OrderResponseDTO createOrder(@Valid @RequestBody OrderRequestDTO dto) {

        return orderService.createOrder(dto);

    }

    @PutMapping("/{orderId}")
    public OrderResponseDTO updateOrderStatus(@PathVariable Long orderId, @RequestParam StatusType status) {

        return orderService.updateOrderStatus(orderId, status);

    }

    @PutMapping("/{orderId}/items/{itemId}")
    public OrderItemResponseDTO updateOrderItem( @PathVariable Long orderId, @PathVariable Long itemId, @RequestBody @Valid OrderItemRequestDTO dto) {

        return orderService.updateOrderItem(orderId, itemId, dto);
    }

    @PostMapping("/{orderId}/items")
    public OrderResponseDTO addOrderItem(@PathVariable Long orderId, @RequestBody     OrderItemRequestDTO dto) {

        return orderService.addFoodToOrder(orderId, dto);
    }


    @DeleteMapping("/{id}")
    public String deleteById(@Valid @PathVariable Long id) {
        orderService.deleteOrderById(id);

        return "Order Has been deleted";
    }
}
