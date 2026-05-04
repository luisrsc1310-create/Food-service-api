package org.example.foodprojectjpa.API.DTOs.Orders;

import org.example.foodprojectjpa.API.Entity.OrderItem;
import org.example.foodprojectjpa.API.Entity.StatusType;

import java.util.List;

public class OrderResponseDTO {


    private Long id;

    private List<OrderItemResponseDTO> items;

    private StatusType status;

    private Double Total;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<OrderItemResponseDTO> getItems() {
        return items;
    }

    public void setItems(List<OrderItemResponseDTO> items) {
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
