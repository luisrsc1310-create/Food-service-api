package org.example.foodprojectjpa.API.DTOs.Food;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.example.foodprojectjpa.API.Entity.FoodType;

public class FoodRequestDTO {

    @NotBlank(message = "Name can't be blank")
    private String name;

    @Positive
    private Double price;

    @NotNull(message = "The type is null")
    private FoodType type;

    @NotNull(message = "Category is blank")
    private Long categoryId;


    public String getName() {
        return name;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public FoodType getType() {
        return type;
    }

    public void setType(FoodType type) {
        this.type = type;
    }
}
