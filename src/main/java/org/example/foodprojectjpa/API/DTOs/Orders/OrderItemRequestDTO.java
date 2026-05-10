package org.example.foodprojectjpa.API.DTOs.Orders;


import org.springframework.stereotype.Component;

@Component
public class OrderItemRequestDTO {


    private Long foodId;
    private Integer quantity;
    private Double subtotal;

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public Long getFoodId() {
        return foodId;
    }

    public void setFoodId(Long foodId) {
        this.foodId = foodId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }




}
