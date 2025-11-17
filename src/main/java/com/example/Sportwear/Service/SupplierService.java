package com.example.Sportwear.Service;

import com.example.Sportwear.Model.Suppliers;
import com.example.Sportwear.Repository.SuppliersRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SuppliersService {

    private final SuppliersRepository repo;

    public SuppliersService(SuppliersRepository repo) {
        this.repo = repo;
    }

    public Suppliers create(Suppliers suppliers) {
        return repo.save(suppliers);
    }

    public Suppliers getById(int id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado"));
    }

    public List<Suppliers> getAll() {
        return repo.findAll();
    }

    public Suppliers update(int id, Suppliers updated) {
        Suppliers existing = getById(id);

        existing.setSupplier_name(updated.getSupplier_name());
        existing.setSupplier_email(updated.getSupplier_email());

        return repo.save(existing);
    }

    public void delete(int id) {
        repo.deleteById(id);
    }
}

