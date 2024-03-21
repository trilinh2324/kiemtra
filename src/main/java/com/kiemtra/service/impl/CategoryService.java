package com.kiemtra.service.impl;

import com.kiemtra.model.Category;
import com.kiemtra.model.Product;
import com.kiemtra.repository.ICategoryRepository;
import com.kiemtra.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService implements ICategoryService {
    @Autowired
    private ICategoryRepository iCategoryRepository;

    @Override
    public Iterable<Category> findAll() {
        return iCategoryRepository.findAll();
    }

    @Override
    public Optional<Category> findById(int id) {
        return iCategoryRepository.findById(id);
    }

    @Override
    public Product save(Category category) {
        iCategoryRepository.save(category);
        return null;
    }

    @Override
    public void remove(int id) {
        iCategoryRepository.deleteById(id);
    }
}
