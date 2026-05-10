package org.example.foodprojectjpa.API.DTOs.Orders;


import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import org.example.foodprojectjpa.API.Entity.OrderItem;
import org.example.foodprojectjpa.API.Entity.StatusType;

import java.util.List;

public class OrderRequestDTO {

    private List<OrderItemRequestDTO> items;

    private StatusType status;

    private Double Total;

    public List<OrderItemRequestDTO> getItems() {
        return items;
    }

    public void setItems(List<OrderItemRequestDTO> items) {
        this.items = items;
    }

    public StatusType getStatus() {
        return status;
    }

    public void setStatus(StatusType status) {
        this.status = status;
    }

    public Double getTotal() {
        return Total;
    }

    public void setTotal(Double total) {
        Total = total;
    }
}
