package com.example.Sportwear.Model;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;

import java.util.Map;
import java.util.HashMap;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "PRODUCT")
public class Product extends GestionProduct {

    @Column(name = "PRODUCT_NAME")
    private String name;

    @Column(name = "PRODUCT_DESCRIPTION")
    private String description;

    @Column(name = "PRODUCT_PRICE")
    private double price;

    @Column(name = "PRODUCT_CATEGORY")
    private String category;

    @Column(name = "PRODUCT_SIZE")
    private String size;

    @Column(name = "PRODUCT_COLOR")
    private String color;

    @Column(name = "PRODUCT_IMAGE_URL")
    private String imageUrl;

    @ElementCollection
    @CollectionTable(
            name = "PRODUCT_STOCK_BY_SIZE",
            joinColumns = @JoinColumn(name = "PRODUCT_ID")
    )
    @MapKeyColumn(name = "SIZE_KEY")
    @Column(name = "STOCK_QUANTITY")
    private Map<String, Integer> stockPorTalla = new HashMap<>();


    public void decreaseStock(String sizeKey, int quantity) {
        if (this.stockPorTalla.containsKey(sizeKey)) {
            int currentStock = this.stockPorTalla.get(sizeKey);
            if (currentStock >= quantity) {
                this.stockPorTalla.put(sizeKey, currentStock - quantity);
            } else {
                throw new IllegalStateException("Stock insuficiente para la talla: " + sizeKey);
            }
        } else {
            throw new IllegalArgumentException("Talla no válida: " + sizeKey);
        }
    }
}



