package org.example.foodprojectjpa.API.Service;

import org.example.foodprojectjpa.API.DTOs.Orders.OrderItemRequestDTO;
import org.example.foodprojectjpa.API.DTOs.Orders.OrderRequestDTO;
import org.example.foodprojectjpa.API.DTOs.Orders.OrderResponseDTO;
import org.example.foodprojectjpa.API.Entity.Food;
import org.example.foodprojectjpa.API.Entity.Order;
import org.example.foodprojectjpa.API.Entity.OrderItem;
import org.example.foodprojectjpa.API.ExceptionsHandlers.ResourceNotFoundException;
import org.example.foodprojectjpa.API.Mapper.OrderMapper;
import org.example.foodprojectjpa.API.Repository.FoodRepository;
import org.example.foodprojectjpa.API.Repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final FoodRepository foodRepository;


    public OrderService(OrderRepository orderRepository, FoodRepository foodRepository) {
        this.orderRepository = orderRepository;
        this.foodRepository = foodRepository;
    }


    public OrderResponseDTO createOrder(OrderRequestDTO dto) {

        OrderMapper orderMapper = new OrderMapper();


        Order order = new Order();

        List<OrderItem> items = new ArrayList<>();

        for(OrderItemRequestDTO itemDTO : dto.getItems()) {
            Food food = foodRepository.findById(itemDTO.getFoodId())
                    .orElseThrow(() -> new ResourceNotFoundException("Food not found"));

            OrderItem item = new OrderItem();

            item.setFood(food);
            item.setOrder(order);
            item.setPrice(food.getPrice());
            item.setQuantity(itemDTO.getQuantity());

            items.add(item);
        }

        order.setItems(items);

        return orderMapper.toDTO(orderRepository.save(order));




    }

}
