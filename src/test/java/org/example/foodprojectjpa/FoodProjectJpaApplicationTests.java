package org.example.foodprojectjpa;

import org.example.foodprojectjpa.API.Entity.OrderItem;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class OrderItemTest  {

    @Test
    void shouldCalculateSubtotal() {
        OrderItem item = new OrderItem();
        item.setPrice(10.0);
        item.setQuantity(2);

        double result = item.getSubTotal();

        assertEquals(20.0, result);
    }

}
