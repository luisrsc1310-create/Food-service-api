package org.example.foodprojectjpa.API.DTOs.Category;

import jakarta.validation.constraints.NotBlank;

public class CategoryRequestDTO {

    @NotBlank(message = "Name is blank")
    private String categoryName;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
