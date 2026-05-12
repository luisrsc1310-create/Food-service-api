package org.example.foodprojectjpa.API.Mapper;

import org.example.foodprojectjpa.API.DTOs.Orders.OrderItemRequestDTO;
import org.example.foodprojectjpa.API.DTOs.Orders.OrderItemResponseDTO;
import org.example.foodprojectjpa.API.DTOs.Orders.OrderResponseDTO;
import org.example.foodprojectjpa.API.Entity.Food;
import org.example.foodprojectjpa.API.Entity.Order;
import org.example.foodprojectjpa.API.Entity.OrderItem;
import org.example.foodprojectjpa.API.Entity.StatusType;
import org.example.foodprojectjpa.API.Repository.FoodRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderMapper {


    public OrderItemResponseDTO toItemDTO(OrderItem item) {
        return new OrderItemResponseDTO(
                item.getId(),
                item.getFood(),
                item.getQuantity(),
                item.getPrice(),
                Double.valueOf(item.getSubTotal())
        );

    }

    public OrderResponseDTO toDTO(Order order) {
        OrderResponseDTO dto = new OrderResponseDTO();

        dto.setId(order.getId());
        dto.setStatus(order.getStatus());

        List<OrderItemResponseDTO> items = order.getItems()
                .stream()
                .map(this::toItemDTO)
                .toList();

        dto.setItems(items);

        double total = items.stream()
                .mapToDouble(i -> i.getPrice() * i.getQuantity())
                .sum();

        dto.setTotal(total);

        return dto;
    }
}
