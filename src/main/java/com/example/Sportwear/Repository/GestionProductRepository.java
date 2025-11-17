package com.example.Sportwear.Repository;

import com.example.Sportwear.Model.GestionProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GestionProductRepository extends JpaRepository<GestionProduct, Integer>{
}
