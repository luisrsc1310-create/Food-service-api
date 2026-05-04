package org.example.foodprojectjpa.API.Controller;

import jakarta.validation.Valid;
import org.example.foodprojectjpa.API.DTOs.Category.CategoryRequestDTO;
import org.example.foodprojectjpa.API.DTOs.Category.CategoryResponseDTO;
import org.example.foodprojectjpa.API.Service.CategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;


    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public Page<CategoryResponseDTO> getAll(@PageableDefault(size = 5) Pageable page) {

        return categoryService.getCategories(page);
    }

    @PostMapping
    public CategoryResponseDTO saveCategory(@Valid @RequestBody CategoryRequestDTO dto) {

        return categoryService.createCategory(dto);

    }

    @PutMapping("/{id}")
    public CategoryResponseDTO updateCategory(@Valid @PathVariable Long id, @RequestBody CategoryRequestDTO dto) {
       return categoryService.updateCategoryById(id, dto);
    }

    @DeleteMapping("/{id}")
    public String deleteCategoryById(@Valid @PathVariable Long id) {
        categoryService.deleteById(id);

        return "Category has been deleted";
    }

}
