package org.example.foodprojectjpa.API.DTOs.Orders;


import org.example.foodprojectjpa.API.Entity.Food;



public class OrderItemResponseDTO {

    private Long id;
    private Food food;
    private Integer quantity;
    private Double price;
    private Double subTotal;


    public OrderItemResponseDTO(Long id, Food food, Integer quantity, Double price, Double subTotal) {
        this.id = id;
        this.food = food;
        this.quantity = quantity;
        this.price = price;
        this.subTotal = subTotal;
    }

    public Food getFood() {
        return food;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Double getPrice() {
        return price;
    }

    public Double getSubTotal() {
        return getQuantity() * getPrice();
    }

    public Long getId() {
        return id;
    }
}
