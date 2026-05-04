package org.example.foodprojectjpa.API.Service;

import org.example.foodprojectjpa.API.DTOs.Food.FoodRequestDTO;
import org.example.foodprojectjpa.API.DTOs.Food.FoodResponseDTO;
import org.example.foodprojectjpa.API.Entity.Category;
import org.example.foodprojectjpa.API.Entity.Food;
import org.example.foodprojectjpa.API.ExceptionsHandlers.AlreadyExists;
import org.example.foodprojectjpa.API.ExceptionsHandlers.ResourceNotFoundException;
import org.example.foodprojectjpa.API.Mapper.FoodMapper;
import org.example.foodprojectjpa.API.Repository.CategoryRepository;
import org.example.foodprojectjpa.API.Repository.FoodRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;

import java.util.List;

@Service
public class FoodService {

    private final FoodRepository foodRepository;
    private final CategoryRepository categoryRepository;

    public FoodService(FoodRepository foodRepository, CategoryRepository categoryRepository) {
        this.foodRepository = foodRepository;
        this.categoryRepository = categoryRepository;
    }

    public FoodResponseDTO createFood(FoodRequestDTO dto) {
        Food food = FoodMapper.toFood(dto);

        if (foodRepository.existsByName(dto.getName())) {
            throw new AlreadyExists("The food already exists");
        }

        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));


        food.setCategory(category);

        return FoodMapper.toDTO(foodRepository.save(food));

    }

    public FoodResponseDTO updateFood(Long id, FoodRequestDTO dto) {

        Food food = foodRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Food not found"));

        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        food.setName(dto.getName());
        food.setType(dto.getType());
        food.setPrice(dto.getPrice());
        food.setCategory(category);

        Food updated = foodRepository.save(food);

        return FoodMapper.toDTO(updated);

    }

    public void deleteByid(Long id) {
        foodRepository.deleteById(id);
    }

    public Page<FoodResponseDTO> getFoods(Pageable pageable) {

        Page<Food> foodsPage = foodRepository.findAll(pageable);

        return foodsPage.map(FoodMapper::toDTO);
    }

    public Page<FoodResponseDTO> searchFood(String name, Double price, String categoryName, Pageable pageable) {

        Specification<Food> spec = (root, query, cb) -> cb.conjunction();

        if (name != null) {
            spec = spec.and(((root, query, cb) ->
                    cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%")));
        }

        if (price != null) {
            spec = spec.and((root, query, cb) ->
                    cb.lessThanOrEqualTo(root.get("price"), price));
        }

        if (categoryName != null) {
            spec = spec.and((root, query, cb) ->
                    cb.like(cb.lower(root.get("category").get("name")), "%" + categoryName.toLowerCase() + "%"));
        }

        Page<Food> foods = foodRepository.findAll(spec, pageable);


        return foods.map(FoodMapper::toDTO);

    }


}
