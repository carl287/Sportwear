package com.example.Sportwear.Model;

import jakarta.persistence.*;
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
    private int price;

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
}
