package org.example.foodprojectjpa.API.Repository;

import org.example.foodprojectjpa.API.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
