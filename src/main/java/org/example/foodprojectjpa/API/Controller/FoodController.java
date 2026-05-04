package org.example.foodprojectjpa.API.Controller;

import jakarta.validation.Valid;
import org.example.foodprojectjpa.API.DTOs.Food.FoodRequestDTO;
import org.example.foodprojectjpa.API.DTOs.Food.FoodResponseDTO;
import org.example.foodprojectjpa.API.Service.FoodService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/foods")
public class FoodController {

    private final FoodService foodService;


    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @PostMapping
    public FoodResponseDTO saveFood(@Valid @RequestBody FoodRequestDTO dto) {

        return foodService.createFood(dto);
    }

    @PutMapping("/{id}")
    public FoodResponseDTO updateFood(@Valid @PathVariable Long id, @RequestBody FoodRequestDTO dto) {
        return foodService.updateFood(id, dto);
    }

    @DeleteMapping("/{id}")
    public String deleteFood(@PathVariable Long id) {

        foodService.deleteByid(id);
        return "Food has been deleted";
    }

    @GetMapping
    public Page<FoodResponseDTO> getFoods(
            @PageableDefault(size = 5) Pageable pageable) {

        return foodService.getFoods(pageable);
    }

    @GetMapping("/search")
    public Page<FoodResponseDTO> searchFood(@RequestParam String name, @RequestParam Double price, @RequestParam String categoryName, Pageable pageable) {


        return foodService.searchFood(name, price, categoryName, pageable);



    }

}
