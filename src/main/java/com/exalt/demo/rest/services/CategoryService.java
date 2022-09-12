package com.exalt.demo.rest.services;

import com.exalt.demo.rest.models.Category;
import com.exalt.demo.rest.models.Course;
import com.exalt.demo.rest.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories() {
        return (List<Category>) categoryRepository.findAll();
    }

    public Optional<Category> findCategoryById(Long categoryId) {
        return categoryRepository.findById(categoryId);
    }

    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }
    public Category updateCategory(Long categoryId, Category category) {
        if (findCategoryById(categoryId).isPresent()) {
            Category existingCategory = findCategoryById(categoryId).get();
            existingCategory.setName(category.getName());
            existingCategory.setCourses(category.getCourses());
            return categoryRepository.save(existingCategory);
        }
        return null;
    }

    public List<Course> getCategoryCourses(Long categoryId) {
        if (findCategoryById(categoryId).isPresent()) {
            Category category = findCategoryById(categoryId).get();
            return category.getCourses();
        }
        return null;
    }
}
