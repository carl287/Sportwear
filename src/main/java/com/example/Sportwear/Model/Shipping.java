package com.example.Sportwear.Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Shipping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;

    @Column(name = "shipping_name")
    private String shipping_name;

    @Column(name = "shipping_email")
    private String shipping_email;

    @Column(name = "patente")
    private String patente;
}
