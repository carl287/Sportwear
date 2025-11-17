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

    // Registrar usuario
    public String agregarUser(User user) {

        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            return "El email ya está registrado";
        }

        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            return "El nombre de usuario ya está en uso";
        }

        userRepository.save(user);
        return "Usuario agregado con id " + user.getId();
    }

    // Listar todos
    public List<User> listarUsuarios() {
        return userRepository.findAll();
    }

    // Buscar por ID
    public User obtenerUsuarioPorId(int id) {
        return userRepository.findById(id)
                .orElse(null);
    }

    // Eliminar
    public String eliminarUsuario(int id) {
        if (!userRepository.existsById(id)) {
            return "Usuario no encontrado";
        }

        userRepository.deleteById(id);
        return "Usuario eliminado con id " + id;
    }

    // Actualizar usuario
    public String actualizarUsuario(int id, User user) {
        if (!userRepository.existsById(id)) {
            return "Usuario no encontrado";
        }

        User buscado = userRepository.findById(id).get();

        buscado.setUsername(user.getUsername());
        buscado.setPassword(user.getPassword());
        buscado.setEmail(user.getEmail());
        buscado.setEsAdmin(user.isEsAdmin());
        buscado.setActive(user.isActive());

        userRepository.save(buscado);

        return "Usuario actualizado con id " + id;
    }

    // Login básico
    public Optional<User> login(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }
}

