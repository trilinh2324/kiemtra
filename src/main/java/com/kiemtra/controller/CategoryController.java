package com.kiemtra.controller;

import com.kiemtra.model.Category;
import com.kiemtra.service.impl.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/categori")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public Iterable<Category> getAllCategories() {
        return categoryService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Category>> getCategoryById(@PathVariable int id) {
        Optional<Category> category = categoryService.findById(id);
        return category.isPresent() ? ResponseEntity.ok().body(category) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        Category createdCategory = categoryService.save(category).getCategory();
        return ResponseEntity.created(URI.create("/categories/" + createdCategory.getId())).body(createdCategory);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable int id, @RequestBody Category category) {
        if (!categoryService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        category.setId(id);
        Category updatedCategory = categoryService.save(category).getCategory();
        return ResponseEntity.ok().body(updatedCategory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable int id) {
        categoryService.remove(id);
        return ResponseEntity.noContent().build();
    }
}
