package com.example.Sportwear.Service;

import com.example.Sportwear.Model.User;
import com.example.Sportwear.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // ---------------------------------------------------------
    //   REGISTRAR USUARIO (Corregido con región/comuna/dirección)
    // ---------------------------------------------------------
    public User registrar(User user) {

        // Validación de duplicados
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("El email ya está registrado");
        }

        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new RuntimeException("El nombre de usuario ya está en uso");
        }

        // Validación de campos obligatorios para Oracle
        if (user.getRegion() == null || user.getRegion().isBlank()) {
            throw new RuntimeException("La región es obligatoria");
        }
        if (user.getComuna() == null || user.getComuna().isBlank()) {
            throw new RuntimeException("La comuna es obligatoria");
        }
        if (user.getDireccion() == null || user.getDireccion().isBlank()) {
            throw new RuntimeException("La dirección es obligatoria");
        }

        // Construcción del usuario final asegurando que no haya nulls
        User nuevo = new User();
        nuevo.setUsername(user.getUsername());
        nuevo.setPassword(user.getPassword());
        nuevo.setEmail(user.getEmail());
        nuevo.setRegion(user.getRegion());
        nuevo.setComuna(user.getComuna());
        nuevo.setDireccion(user.getDireccion());
        nuevo.setEsAdmin(false);
        nuevo.setActive(true);

        return userRepository.save(nuevo);
    }

    // ---------------------------------------------------------
    //   LISTAR TODOS LOS USUARIOS
    // ---------------------------------------------------------
    public List<User> listarUsuarios() {
        return userRepository.findAll();
    }

    // ---------------------------------------------------------
    //   OBTENER POR ID
    // ---------------------------------------------------------
    public User obtenerUsuarioPorId(int id) {
        return userRepository.findById(id)
                .orElse(null);
    }

    // ---------------------------------------------------------
    //   ELIMINAR USUARIO
    // ---------------------------------------------------------
    public String eliminarUsuario(int id) {
        if (!userRepository.existsById(id)) {
            return "Usuario no encontrado";
        }

        userRepository.deleteById(id);
        return "Usuario eliminado con id " + id;
    }

    // ---------------------------------------------------------
    //   ACTUALIZAR USUARIO
    // ---------------------------------------------------------
    public String actualizarUsuario(int id, User user) {
        if (!userRepository.existsById(id)) {
            return "Usuario no encontrado";
        }

        User buscado = userRepository.findById(id).get();

        buscado.setUsername(user.getUsername());
        buscado.setPassword(user.getPassword());
        buscado.setEmail(user.getEmail());
        buscado.setRegion(user.getRegion());
        buscado.setComuna(user.getComuna());
        buscado.setDireccion(user.getDireccion());
        buscado.setEsAdmin(user.isEsAdmin());
        buscado.setActive(user.isActive());

        userRepository.save(buscado);

        return "Usuario actualizado con id " + id;
    }

    // ---------------------------------------------------------
    //   LOGIN
    // ---------------------------------------------------------
    public Optional<User> login(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }
}
