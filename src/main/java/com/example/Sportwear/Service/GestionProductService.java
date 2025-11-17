package com.example.Sportwear.Service;

import com.example.Sportwear.Model.Product;
import com.example.Sportwear.Repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GestionProductService {

    private final ProductRepository productRepository;

    public GestionProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product updateStock(int id, int newStock) {
        Product p = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        p.setStock(newStock);
        return productRepository.save(p);
    }

    public List<Product> getLowStock(int threshold) {
        return productRepository.findAll().stream()
                .filter(p -> p.getStock() <= threshold)
                .collect(Collectors.toList());
    }

    public Product increaseStock(int id, int amount) {
        Product p = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        p.setStock(p.getStock() + amount);
        return productRepository.save(p);
    }

    public Product decreaseStock(int id, int amount) {
        Product p = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        if (p.getStock() - amount < 0) {
            throw new RuntimeException("Stock insuficiente");
        }

        p.setStock(p.getStock() - amount);
        return productRepository.save(p);
    }
}
