package org.example.foodprojectjpa.API.Mapper;



import org.example.foodprojectjpa.API.DTOs.Food.FoodRequestDTO;
import org.example.foodprojectjpa.API.DTOs.Food.FoodResponseDTO;
import org.example.foodprojectjpa.API.Entity.Food;

import java.util.List;
import java.util.stream.Collectors;

public class FoodMapper {

    public static Food toFood(FoodRequestDTO dto) {
        Food food = new Food();

        food.setName(dto.getName());
        food.setPrice(dto.getPrice());
        food.setType(dto.getType());


        return food;

    }

    public static FoodResponseDTO toDTO (Food food) {
        String categoryName = null;

        if (food.getCategory() != null) {
            categoryName = food.getCategory().getName();
        }

        return new FoodResponseDTO(
                food.getId(),
                food.getName(),
                food.getType(),
                food.getPrice(),
                categoryName
        );
    }

    public static List<FoodResponseDTO> toDTOList(List<Food> foods) {
        return foods.stream()
                .map(FoodMapper::toDTO)
                .collect(Collectors.toList());
    }

}
