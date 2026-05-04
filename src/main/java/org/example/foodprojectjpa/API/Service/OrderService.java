package org.example.foodprojectjpa.API.Service;

import org.aspectj.weaver.ast.Or;
import org.example.foodprojectjpa.API.DTOs.Orders.OrderItemRequestDTO;
import org.example.foodprojectjpa.API.DTOs.Orders.OrderItemResponseDTO;
import org.example.foodprojectjpa.API.DTOs.Orders.OrderRequestDTO;
import org.example.foodprojectjpa.API.DTOs.Orders.OrderResponseDTO;
import org.example.foodprojectjpa.API.Entity.Food;
import org.example.foodprojectjpa.API.Entity.Order;
import org.example.foodprojectjpa.API.Entity.OrderItem;
import org.example.foodprojectjpa.API.Entity.StatusType;
import org.example.foodprojectjpa.API.ExceptionsHandlers.ResourceNotFoundException;
import org.example.foodprojectjpa.API.Mapper.OrderMapper;
import org.example.foodprojectjpa.API.Repository.FoodRepository;
import org.example.foodprojectjpa.API.Repository.OrderRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final FoodRepository foodRepository;
    private final OrderMapper orderMapper;

    public OrderService(OrderRepository orderRepository, FoodRepository foodRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.foodRepository = foodRepository;
        this.orderMapper = orderMapper;
    }


    public OrderResponseDTO createOrder(OrderRequestDTO dto) {


        Order order = new Order();

        List<OrderItem> items = new ArrayList<>();

        for (OrderItemRequestDTO itemDTO : dto.getItems()) {
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

    public Page<OrderResponseDTO> getAll(Pageable pageable) {

        Page<Order> orderPage = orderRepository.findAll(pageable);

        return orderPage.map(orderMapper::toDTO);

    }

    public void deleteOrderById(Long id) {

        orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));

        orderRepository.deleteById(id);

    }

    public OrderResponseDTO UpdateOrderStatus(Long id, StatusType status) {

        Order orderToUpdate = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));

        orderToUpdate.setStatus(status);

        return orderMapper.toDTO(orderToUpdate);


    }

    public OrderItemResponseDTO UpdateOrderItem(Long orderId, Long orderItemId, Long newFoodId, Double price, Integer quantity) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));

        OrderItem item = order.getItems().stream()
                .filter(i -> i.getId().equals(orderItemId))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Item not found"));

        Food food = foodRepository.findById(newFoodId)
                .orElseThrow(() -> new ResourceNotFoundException("Food not found"));

        item.setFood(food);
        item.setPrice(price);
        item.setQuantity(quantity);

        orderRepository.save(order); // 🔥 VERY IMPORTANT


        return orderMapper.toItemDTO(item);


    }

}
