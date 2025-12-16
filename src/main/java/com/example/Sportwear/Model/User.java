package com.example.Sportwear.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonProperty; // Asegúrate de importar esto

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "APP_USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;
    private String password;
    private String email;
    private String region;
    private String comuna;
    private String direccion;

    @JsonProperty("esAdmin")
    private boolean esAdmin;

    private boolean active = true;
}


