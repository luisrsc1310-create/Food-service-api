package org.example.foodprojectjpa.API.DTOs.Orders;


import org.example.foodprojectjpa.API.Entity.Food;



public class OrderItemResponseDTO {

    private Food food;
    private Integer quantity;
    private Double price;
    private Double subTotal;


    public OrderItemResponseDTO(Food food, Integer quantity, Double price, Integer subTotal) {
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
}
