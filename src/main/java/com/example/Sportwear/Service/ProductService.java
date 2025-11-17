package com.example.Sportwear.Service;

import com.example.Sportwear.Model.Product;
import com.example.Sportwear.Repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getById(int id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));
    }

    public Product create(Product product) {
        return productRepository.save(product);
    }

    public Product update(int id, Product updated) {
        Product existing = getById(id);

        existing.setName(updated.getName());
        existing.setDescription(updated.getDescription());
        existing.setPrice(updated.getPrice());

        return productRepository.save(existing);
    }

    public void delete(int id) {
        Product existing = getById(id);
        productRepository.delete(existing);
    }
}
