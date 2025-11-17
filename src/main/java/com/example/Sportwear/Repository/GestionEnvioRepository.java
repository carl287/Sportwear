package com.example.Sportwear.Repository;

import com.example.Sportwear.Model.GestionEnvio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GestionEnvioRepository extends JpaRepository<GestionEnvio, Integer> {
}
