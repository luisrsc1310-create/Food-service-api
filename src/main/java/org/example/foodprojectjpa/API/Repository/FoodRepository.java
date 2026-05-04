package org.example.foodprojectjpa.API.Repository;

import org.example.foodprojectjpa.API.Entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


@Repository
public interface FoodRepository extends JpaRepository<Food, Long>,
        JpaSpecificationExecutor<Food> {


    boolean existsByName(String name);
}
