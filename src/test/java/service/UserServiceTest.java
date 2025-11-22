package service;

import com.example.Sportwear.Model.User;
import com.example.Sportwear.Repository.UserRepository;
import com.example.Sportwear.Service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {

    @Mock
    private UserRepository repo;

    @InjectMocks
    private UserService service;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAgregarUser_EmailDuplicado() {
        User u = new User();
        u.setEmail("paula@mail.com");

        when(repo.findByEmail("paula@mail.com")).thenReturn(Optional.of(u));

        String result = service.agregarUser(u);

        assertEquals("El email ya está registrado", result);
    }

    @Test
    void testAgregarUser_UsernameDuplicado() {
        User u = new User();
        u.setEmail("nuevo@mail.com");
        u.setUsername("paula");

        when(repo.findByEmail("nuevo@mail.com")).thenReturn(Optional.empty());
        when(repo.findByUsername("paula")).thenReturn(Optional.of(u));

        String result = service.agregarUser(u);

        assertEquals("El nombre de usuario ya está en uso", result);
    }

    @Test
    void testAgregarUser_OK() {
        User u = new User();
        u.setId(10);
        u.setEmail("nuevo@mail.com");
        u.setUsername("paula");

        when(repo.findByEmail("nuevo@mail.com")).thenReturn(Optional.empty());
        when(repo.findByUsername("paula")).thenReturn(Optional.empty());
        when(repo.save(u)).thenReturn(u);

        String result = service.agregarUser(u);

        assertEquals("Usuario agregado con id 10", result);
    }

    @Test
    void testListarUsuarios() {
        when(repo.findAll()).thenReturn(List.of(new User(), new User()));

        List<User> lista = service.listarUsuarios();

        assertEquals(2, lista.size());
    }

    @Test
    void testObtenerUsuarioPorId_OK() {
        User u = new User();
        u.setId(1);

        when(repo.findById(1)).thenReturn(Optional.of(u));

        User result = service.obtenerUsuarioPorId(1);

        assertEquals(1, result.getId());
    }

    @Test
    void testObtenerUsuarioPorId_NotFound() {
        when(repo.findById(2)).thenReturn(Optional.empty());

        User result = service.obtenerUsuarioPorId(2);

        assertNull(result);
    }

    @Test
    void testEliminarUsuario_NoExiste() {
        when(repo.existsById(5)).thenReturn(false);

        String result = service.eliminarUsuario(5);

        assertEquals("Usuario no encontrado", result);
    }

    @Test
    void testEliminarUsuario_OK() {
        when(repo.existsById(5)).thenReturn(true);

        String result = service.eliminarUsuario(5);

        assertEquals("Usuario eliminado con id 5", result);
        verify(repo).deleteById(5);
    }

    @Test
    void testActualizarUsuario_NoExiste() {
        when(repo.existsById(3)).thenReturn(false);
    }

}