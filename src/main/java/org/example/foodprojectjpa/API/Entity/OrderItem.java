package org.example.foodprojectjpa.API.Entity;

import jakarta.persistence.*;
import org.example.foodprojectjpa.API.DTOs.Orders.OrderItemRequestDTO;


@Entity
@Table(name = "order_item")
public class OrderItem extends OrderItemRequestDTO {


        @Id
        @GeneratedValue
        private Long id;

        @ManyToOne
        private Order order;

        @ManyToOne
        private Food food;

        private Integer quantity;
        private Double price;
        private Integer subTotal;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Integer subTotal) {
        this.subTotal = subTotal;
    }
}
