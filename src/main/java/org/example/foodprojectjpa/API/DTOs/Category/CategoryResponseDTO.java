package org.example.foodprojectjpa.API.DTOs.Category;

public class CategoryResponseDTO {

    private Long id;
    private String categoryName;

    public CategoryResponseDTO(Long id, String categoryName) {
        this.id = id;
        this.categoryName = categoryName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
