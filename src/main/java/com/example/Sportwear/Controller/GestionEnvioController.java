package com.example.Sportwear.Controller;

import com.example.Sportwear.Model.GestionEnvio;
import com.example.Sportwear.Service.GestionEnvioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/gestion-envio")
@CrossOrigin("*")
public class GestionEnvioController {

    private final GestionEnvioService service;

    public GestionEnvioController(GestionEnvioService service) {
        this.service = service;
    }

    @PostMapping
    public GestionEnvio create(@RequestBody GestionEnvio gestionEnvio) {
        return service.create(gestionEnvio);
    }

    @GetMapping("/{id}")
    public GestionEnvio getById(@PathVariable int id) {
        return service.getById(id);
    }

    @GetMapping
    public List<GestionEnvio> getAll() {
        return service.getAll();
    }

    @PutMapping("/{id}")
    public GestionEnvio update(@PathVariable int id, @RequestBody GestionEnvio updated) {
        return service.update(id, updated);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        service.delete(id);
        return "Gestión de envío eliminada correctamente.";
    }
}

