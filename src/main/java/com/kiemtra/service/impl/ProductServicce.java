package com.kiemtra.service.impl;

import com.kiemtra.model.Category;
import com.kiemtra.model.Product;
import com.kiemtra.repository.IProductRepository;
import com.kiemtra.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServicce implements IProductService {
    @Autowired
    private IProductRepository iProductRepository;

    public List<Product> getProductsByPriceRange(double minPrice, double maxPrice) {
        return iProductRepository.findByPriceBetween(minPrice, maxPrice);
    }

    public List<Product> getProductsSortedByAmount() {
        return iProductRepository.findByOrderByAmountAsc();
    }

    public List<Product> getTop3ExpensiveProducts() {
        return iProductRepository.findTop3ByOrderByPriceDesc();
    }

    public List<Product> getProductsByCategory(Category category) {
        return iProductRepository.findByCategory(category);
    }


    @Override
    public Iterable<Product> findAll() {
        return iProductRepository.findAll();
    }

    @Override
    public Optional<Product> findById(int id) {
        return iProductRepository.findById(id);
    }

    @Override
    public Product save(Product product) {
        iProductRepository.save(product);
        return product;
    }

    @Override
    public void remove(int id) {
        iProductRepository.deleteById(id);
    }
}
