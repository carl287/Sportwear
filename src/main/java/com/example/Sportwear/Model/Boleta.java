package com.example.Sportwear.Model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Boleta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;
    private int cantidad;
    private int precio;
    private int idUser;
    private int idProduct;

}
