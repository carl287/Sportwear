package com.example.Sportwear.Controller;

import com.example.Sportwear.Model.Product;
import com.example.Sportwear.Service.GestionProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/gestion-product")
@CrossOrigin("*")
public class GestionProductController {

    private final GestionProductService gestionProductService;

    public GestionProductController(GestionProductService gestionProductService) {
        this.gestionProductService = gestionProductService;
    }

    @PutMapping("/stock/{id}")
    public Product updateStock(@PathVariable int id, @RequestParam int stock) {
        return gestionProductService.updateStock(id, stock);
    }

    @PutMapping("/stock/increase/{id}")
    public Product increaseStock(@PathVariable int id, @RequestParam int amount) {
        return gestionProductService.increaseStock(id, amount);
    }

    @PutMapping("/stock/decrease/{id}")
    public Product decreaseStock(@PathVariable int id, @RequestParam int amount) {
        return gestionProductService.decreaseStock(id, amount);
    }

    @GetMapping("/low-stock")
    public List<Product> lowStock(@RequestParam(defaultValue = "5") int threshold) {
        return gestionProductService.getLowStock(threshold);
    }
}
