package com.example.Sportwear.Service;

import com.example.Sportwear.Model.Shipping;
import com.example.Sportwear.Repository.ShippingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShippingService {

    private final ShippingRepository shippingRepository;

    public ShippingService(ShippingRepository shippingRepository) {
        this.shippingRepository = shippingRepository;
    }

    public Shipping create(Shipping shipping) {
        return shippingRepository.save(shipping);
    }

    public Shipping getById(int id) {
        return shippingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Shipping no encontrado"));
    }

    public List<Shipping> getAll() {
        return shippingRepository.findAll();
    }

    public Shipping update(int id, Shipping updated) {
        Shipping existing = getById(id);
