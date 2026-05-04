package org.example.foodprojectjpa.API.Service;

import org.example.foodprojectjpa.API.DTOs.Category.CategoryRequestDTO;
import org.example.foodprojectjpa.API.DTOs.Category.CategoryResponseDTO;
import org.example.foodprojectjpa.API.Entity.Category;
import org.example.foodprojectjpa.API.ExceptionsHandlers.ResourceNotFoundException;
import org.example.foodprojectjpa.API.Mapper.CategoryMapper;
import org.example.foodprojectjpa.API.Repository.CategoryRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;



@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;


    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Page<CategoryResponseDTO> getCategories(Pageable pageable) {

        Page<Category> categoriesPages = categoryRepository.findAll(pageable);

        return categoriesPages.map(CategoryMapper::toDTO);
    }

    public CategoryResponseDTO getCategoryById(Long id) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));


        return CategoryMapper.toDTO(category);

    }

    public CategoryResponseDTO createCategory(CategoryRequestDTO dto) {

        Category category = CategoryMapper.toCategory(dto);

        categoryRepository.save(category);

        return CategoryMapper.toDTO(category);
    }

    public CategoryResponseDTO updateCategoryById(Long id, CategoryRequestDTO dto) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        category.setName(dto.getCategoryName());

        return CategoryMapper.toDTO(category);

    }

    public void deleteById(Long id) {
        categoryRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        categoryRepository.deleteById(id);
    }

}
