package com.example.Sportwear.Repository;

import com.example.Sportwear.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("SELECT p FROM Product p JOIN p.stockPorTalla stockMap " +
            "WHERE KEY(stockMap) = :talla AND stockMap >= 1")
    List<Product> findByTallaAndStockAvailable(@Param("talla") String talla);
    @Query("SELECT DISTINCT p FROM Product p JOIN p.stockPorTalla stockMap " +
            "WHERE stockMap <= :threshold")
    List<Product> findByLowStockThreshold(@Param("threshold") int threshold);
}