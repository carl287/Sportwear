package com.example.Sportwear.Model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
public class Product extends GestionProduct {

    private String name;
    private String description;
    private int price;

    private String category;   // Ej: "polera", "zapatilla"
    private String size;       // Ej: "M", "L", "38", "40"
    private String color;      // Ej: "Negro", "Blanco"
    private String imageUrl;   // Link de imagen
}

