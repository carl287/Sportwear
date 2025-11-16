package com.example.Sportwear.Service;


import com.example.Sportwear.Model.User;
import com.example.Sportwear.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class UserService {

    @Autowired
    UserRepository userRepository;

    public String agregarUser(User user) {
        userRepository.save(user);
        return "User agregado usuario con id" + user.getId();
    }

    public List<User> listarUsuarios() {return userRepository.findAll();}

    public User obtenerUsuarioPorId(int id) {
        if (userRepository.existsById(id)) {
            return userRepository.findById(id).get();
        }
        return null;
    }

    public String eliminarUsuario(int id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return "User eliminado usuario con id" + id;
        }else  {
            return "Usuario no encontrado";
        }
    }

    public String actualizarUsuario(int id, User user) {
        if (userRepository.existsById(id)) {
            User buscado= userRepository.findById(id).get();
            buscado.setUsername(user.getUsername());
            buscado.setPassword(user.getPassword());
            userRepository.save(buscado);
            return "Usuario actualizado con id" + id;
        }else  {
            return "Usuario no encontrado";
        }
    }

    public Optional<User> login(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }

}
