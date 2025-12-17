package com.example.Sportwear.Controller;

import com.example.Sportwear.Model.Product;
import com.example.Sportwear.Service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping
    public List<Product> findAll() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product findById(@PathVariable int id) {
        return productService.getById(id);
    }

    @PostMapping
    public Product create(@RequestBody Product product) {
        // DEBUG: Esto te dirá exactamente qué está llegando desde Android
        if (product.getImagenUrl() != null) {
            System.out.println("IMAGEN RECIBIDA: El String empieza con: " + product.getImagenUrl().substring(0, Math.min(product.getImagenUrl().length(), 30)));
            System.out.println("LONGITUD TOTAL: " + product.getImagenUrl().length());
        } else {
            System.out.println("ERROR: La imagen llegó NULL al controlador. Revisa el nombre del campo en Java y Android.");
        }
        return productService.create(product);
    }

    @PutMapping("/{id}")
    public Product update(@PathVariable int id, @RequestBody Product product) {
        return productService.update(id, product);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        productService.delete(id);
        return "Producto eliminado.";
    }

    @GetMapping("/talla/{talla}")
    public List<Product> getByTalla(@PathVariable String talla) {
        return productService.filtrarPorTalla(talla);
    }

    @PutMapping("/stock/{id}")
    public Product updateStockByTalla(@PathVariable int id, @RequestBody Map<String, Integer> newStockMap) {
        // Llama al nuevo método en el servicio
        return productService.updateStockByTalla(id, newStockMap);
    }

    @PutMapping("/stock/increase/{id}")
    public Product increaseStock(
            @PathVariable int id,
            @RequestParam String talla,
            @RequestParam int amount) {

        // Llama al nuevo método en el servicio, pasando la talla
        return productService.increaseStock(id, talla, amount);
    }

    @PutMapping("/stock/decrease/{id}")
    public Product decreaseStock(
            @PathVariable int id,
            @RequestParam String talla,
            @RequestParam int amount) {

        // Llama al nuevo método en el servicio, pasando la talla
        return productService.decreaseStock(id, talla, amount);
    }

    /**
     * Endpoint para obtener productos con al menos una talla con stock bajo.
     * GET /api/products/low-stock?threshold=5
     */
    @GetMapping("/low-stock")
    public List<Product> lowStock(@RequestParam(defaultValue = "5") int threshold) {
        // El servicio maneja la lógica de buscar stock bajo en el mapa
        return productService.getLowStock(threshold);
    }
}
