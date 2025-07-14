package com.portfolio.portfolio.controller;

import com.portfolio.portfolio.model.Category;
import com.portfolio.portfolio.response.ApiResponse;
import com.portfolio.portfolio.service.category.ICategoryService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/categories")
public class CategoryController {

    private final ICategoryService categoryService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllCategories() {

            List<Category> categories = categoryService.getAllCategories();
            return ResponseEntity.ok(new ApiResponse("Found", categories));

    }

    @GetMapping("/category/{id}/category")
    public ResponseEntity<ApiResponse> getCategoryById(@PathVariable  Long id) {

            Category theCategory = categoryService.findCategoryById(id);
            return ResponseEntity.ok(new ApiResponse("Found", theCategory));

    }

    @GetMapping("/category/{name}/category")
    public ResponseEntity<ApiResponse> getCategoryByName(@PathVariable  String name) {

            Category theCategory = categoryService.findCategoryByName(name);
            return ResponseEntity.ok(new ApiResponse("Found", theCategory));

    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addCategory(@RequestBody Category category) {

            Category theCategory = categoryService.addCategory(category);
            return ResponseEntity.ok(new ApiResponse("Added", theCategory));

    }

    @PutMapping("/category/{id}/update")
    public ResponseEntity<ApiResponse> updateCategory(@RequestBody Category category, @PathVariable Long id) {

            Category theCategory = categoryService.updateCategory(category, id);
            return ResponseEntity.ok(new ApiResponse("Updated", theCategory));

    }

    @DeleteMapping("category/{id}/delete")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Long id) {

            categoryService.deleteCategory(id);
            return ResponseEntity.ok(new ApiResponse("Deleted", id));

    }
}
