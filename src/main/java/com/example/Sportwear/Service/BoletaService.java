package com.example.Sportwear.Service;

import com.example.Sportwear.Model.Boleta;
import com.example.Sportwear.Repository.BoletaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoletaService {

    private final BoletaRepository boletaRepository;

    public BoletaService(BoletaRepository boletaRepository) {
        this.boletaRepository = boletaRepository;
    }

    public Boleta create(Boleta boleta) {
        return boletaRepository.save(boleta);
    }

    public Boleta getById(int id) {
        return boletaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Boleta no encontrada"));
    }

    public List<Boleta> getAll() {
        return boletaRepository.findAll();
    }

    public List<Boleta> getByUser(int idUser) {
        return boletaRepository.findByIdUser(idUser);
    }

    public void delete(int id) {
        boletaRepository.deleteById(id);
    }
}
