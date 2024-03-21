package com.kiemtra.controller;

import com.kiemtra.model.Category;
import com.kiemtra.model.Product;
import com.kiemtra.service.impl.ProductServicce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductServicce productService;

    @GetMapping
    public Iterable<Product> getAllProducts() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Product>> getProductById(@PathVariable int id) {
        Optional<Product> product = productService.findById(id);
        return product.isPresent() ? ResponseEntity.ok().body(product) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product createdProduct = productService.save(product);
        return ResponseEntity.created(URI.create("/products/" + createdProduct.getId())).body(createdProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable int id, @RequestBody Product product) {
        if (!productService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        product.setId(id);
        Product updatedProduct = productService.save(product);
        return ResponseEntity.ok().body(updatedProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable int id) {
        productService.remove(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/search/price")
    public List<Product> searchByPriceRange(@RequestParam double minPrice, @RequestParam double maxPrice) {
        return productService.getProductsByPriceRange(minPrice, maxPrice);
    }

    @GetMapping("/sort/amount")
    public List<Product> sortByAmount() {
        return productService.getProductsSortedByAmount();
    }

    @GetMapping("/top3")
    public List<Product> getTop3ExpensiveProducts() {
        return productService.getTop3ExpensiveProducts();
    }

    @GetMapping("/category/{categoryId}")
    public List<Product> getProductsByCategory(@PathVariable Integer categoryId) {
        Category category = new Category();
        category.setId(categoryId);
        return productService.getProductsByCategory(category);
    }
}