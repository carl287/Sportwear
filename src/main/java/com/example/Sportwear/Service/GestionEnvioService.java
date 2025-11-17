package com.example.Sportwear.Service;

import com.example.Sportwear.Model.GestionEnvio;
import com.example.Sportwear.Repository.GestionEnvioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GestionEnvioService {

    private final GestionEnvioRepository repo;

    public GestionEnvioService(GestionEnvioRepository repo) {
        this.repo = repo;
    }

    public GestionEnvio create(GestionEnvio gestionEnvio) {
        return repo.save(gestionEnvio);
    }

    public GestionEnvio getById(int id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Gestión de envío no encontrada"));
    }

    public List<GestionEnvio> getAll() {
        return repo.findAll();
    }

    public GestionEnvio update(int id, GestionEnvio updated) {
        GestionEnvio existing = getById(id);

        existing.setGestionName(updated.getGestionName());
        existing.setGestionEmail(updated.getGe
