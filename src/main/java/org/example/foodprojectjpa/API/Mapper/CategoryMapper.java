package org.example.foodprojectjpa.API.Mapper;

import org.example.foodprojectjpa.API.DTOs.Category.CategoryRequestDTO;
import org.example.foodprojectjpa.API.DTOs.Category.CategoryResponseDTO;
import org.example.foodprojectjpa.API.Entity.Category;

import java.util.List;
import java.util.stream.Collectors;

public class    CategoryMapper {

    public static Category toCategory(CategoryRequestDTO dto) {

        Category category = new Category();

        category.setName(dto.getCategoryName());

        return category;

    }

    public static CategoryResponseDTO toDTO(Category category) {

        return new CategoryResponseDTO(
                category.getId(),
                category.getName()
        );
    }

    public static List<CategoryResponseDTO> toDTOList(List<Category> categories) {
        return categories.stream()
                .map(CategoryMapper::toDTO)
                .collect(Collectors.toList());
    }


}
