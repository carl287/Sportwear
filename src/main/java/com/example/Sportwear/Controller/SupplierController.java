package com.example.Sportwear.Controller;

import com.example.Sportwear.Model.Suppliers;
import com.example.Sportwear.Service.SupplierService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/supplier")
@CrossOrigin("*")
public class SupplierController {

    private final SupplierService service;

    public SupplierController(SupplierService service) {
        this.service = service;
    }

    @PostMapping
    public Suppliers create(@RequestBody Suppliers suppliers) {
        return service.create(suppliers);
    }

    @GetMapping
    public List<Suppliers> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Suppliers getById(@PathVariable int id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public Suppliers update(@PathVariable int id, @RequestBody Suppliers suppliers) {
        return service.update(id, suppliers);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        service.delete(id);
        return "Proveedor eliminado correctamente.";
    }
}
