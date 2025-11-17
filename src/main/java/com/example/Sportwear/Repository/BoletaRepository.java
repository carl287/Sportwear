package com.example.Sportwear.Repository;


import com.example.Sportwear.Model.Boleta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BoletaRepository extends JpaRepository<Boleta, Integer>{
}
