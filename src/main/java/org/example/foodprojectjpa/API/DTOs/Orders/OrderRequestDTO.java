package org.example.foodprojectjpa.API.DTOs.Orders;


import org.example.foodprojectjpa.API.Entity.OrderItem;
import org.example.foodprojectjpa.API.Entity.StatusType;

import java.util.List;

public class OrderRequestDTO {

    private List<OrderItem> items;

    private StatusType status;

    private Double Total;

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
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
