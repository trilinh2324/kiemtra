package com.kiemtra.repository;

import com.kiemtra.model.Category;
import com.kiemtra.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByPriceBetween(double minPrice, double maxPrice);
    List<Product> findByOrderByAmountAsc();
    List<Product> findTop3ByOrderByPriceDesc();
    List<Product> findByCategory(Category category);
}
