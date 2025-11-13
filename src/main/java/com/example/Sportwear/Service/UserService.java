package com.example.Sportwear.Service;


import com.example.Sportwear.Model.Users;
import com.example.Sportwear.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class UserService {

    @Autowired
    UserRepository userRepository;

    public String agregarUser(Users users) {
        userRepository.save(users);
        return "User agregado usuario con id" + users.getId();
    }

    public List<Users> listarUsuarios() {return userRepository.findAll();}

    public Users obtenerUsuarioPorId(int id) {
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

    public String actualizarUsuario(int id, Users users) {
        if (userRepository.existsById(id)) {
            Users buscado= userRepository.findById(id).get();
            buscado.setUsername(users.getUsername());
            buscado.setPassword(users.getPassword());
            userRepository.save(buscado);
            return "Usuario actualizado con id" + id;
        }else  {
            return "Usuario no encontrado";
        }
    }

    public Optional<Users> login(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }

}
