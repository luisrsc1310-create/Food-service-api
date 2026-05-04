package org.example.foodprojectjpa.API.DTOs.Food;


import org.example.foodprojectjpa.API.Entity.FoodType;

public class FoodResponseDTO {

    private final Long id;
    private final String name;
    private final Double price;
    private final FoodType type;

    private final String categoryName;

    public FoodResponseDTO(Long id, String name, FoodType type, Double price, String categoryName ) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.type = type;
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public FoodType getType() {
        return type;
    }
}
