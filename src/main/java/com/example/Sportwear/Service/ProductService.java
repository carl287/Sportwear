package com.example.Sportwear.Service;

import com.example.Sportwear.Model.Product;
import com.example.Sportwear.Repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    public List<Product> filtrarPorTalla(String talla) {
        return productRepository.findAll().stream()
                .filter(p -> p.getStockPorTalla().containsKey(talla) && p.getStockPorTalla().get(talla) > 0)
                .collect(Collectors.toList());
    }

    public Product create(Product product) {
        return productRepository.save(product);
    }

    public Product update(int id, Product updated) {
        Product existing = getById(id);

        existing.setName(updated.getName());
        existing.setDescription(updated.getDescription());
        existing.setPrice(updated.getPrice());
        existing.setStockPorTalla(updated.getStockPorTalla());

        return productRepository.save(existing);
    }

    public void delete(int id) {
        Product existing = getById(id);
        productRepository.delete(existing);
    }

    public Product updateStockByTalla(int id, Map<String, Integer> newStockMap) {
        Product p = getById(id);
        p.setStockPorTalla(newStockMap);
        return productRepository.save(p);
    }

    public Product increaseStock(int id, String size, int amount) {
        Product p = getById(id);
        Map<String, Integer> stockMap = p.getStockPorTalla();

        int currentStock = stockMap.getOrDefault(size, 0);

        stockMap.put(size, currentStock + amount);
        p.setStockPorTalla(stockMap);
        return productRepository.save(p);
    }

    public Product decreaseStock(int id, String size, int amount) {
        Product p = getById(id);
        Map<String, Integer> stockMap = p.getStockPorTalla();

        if (!stockMap.containsKey(size)) {
            throw new RuntimeException("Talla '" + size + "' no existe para este producto.");
        }

        int currentStock = stockMap.get(size);

        if (currentStock - amount < 0) {
            throw new RuntimeException("Stock insuficiente para la talla: " + size);
        }

        stockMap.put(size, currentStock - amount);
        p.setStockPorTalla(stockMap);
        return productRepository.save(p);
    }

    public List<Product> getLowStock(int threshold) {
        return productRepository.findAll().stream()
                .filter(p -> p.getStockPorTalla().values().stream()
                        .anyMatch(stock -> stock <= threshold)) // Alguna talla tiene stock bajo
                .collect(Collectors.toList());
    }

    public int calculateTotalStock(int id) {
        Product p = getById(id);
        return p.getStockPorTalla().values().stream()
                .mapToInt(Integer::intValue)
                .sum();
    }
}
