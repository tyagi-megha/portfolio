package com.portfolio.portfolio.service.category;

import com.portfolio.portfolio.model.Category;

import java.util.List;

public interface ICategoryService {
    Category addCategory(Category category);
    Category updateCategory(Category category, Long categoryId);
    void deleteCategory(Long categoryId);
    List<Category> getAllCategories();
    Category findCategoryById(Long categoryId);
    Category findCategoryByName(String name);
}
