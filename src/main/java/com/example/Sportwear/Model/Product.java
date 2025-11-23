package com.example.Sportwear.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "PRODUCT") // opcional pero recomendado
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
}



