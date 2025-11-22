package controller;

import com.example.Sportwear.Controller.UserController;
import com.example.Sportwear.Model.User;
import com.example.Sportwear.Repository.DTO.LoginRequest;
import com.example.Sportwear.Service.UserService;
import com.example.Sportwear.assemblers.UserModelAssembler;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class GestionUsuariosControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private UserModelAssembler assembler;

    private UserController controller;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

        controller = new UserController();

        // Inyectamos dependencias privadas con Reflection
        ReflectionTestUtils.setField(controller, "userService", userService);
        ReflectionTestUtils.setField(controller, "assembler", assembler);
    }

    @Test
    void testListarUsuarios() {
        User u1 = new User(1, "paula", "pass", "paula@mail.com", false, true);
        User u2 = new User(2, "carla", "pass", "carla@mail.com", false, true);

        List<User> lista = Arrays.asList(u1, u2);

        when(userService.listarUsuarios()).thenReturn(lista);
        when(assembler.toCollectionModel(lista)).thenReturn(CollectionModel.empty());

        ResponseEntity<?> respuesta = controller.getUsers();

        assertEquals(200, respuesta.getStatusCode().value());
        verify(userService).listarUsuarios();
    }

    @Test
    void testListarUsuariosVacio() {
        when(userService.listarUsuarios()).thenReturn(List.of());

        ResponseEntity<?> respuesta = controller.getUsers();

        assertEquals(204, respuesta.getStatusCode().value());
    }

    @Test
    void testGetUserById_ok() {
        User u = new User(10, "lucas", "123", "l@mail.com", false, true);

        when(userService.obtenerUsuarioPorId(10)).thenReturn(u);
        when(assembler.toModel(u)).thenReturn(EntityModel.of(u));

        ResponseEntity<?> respuesta = controller.getUserById(10);

        assertEquals(200, respuesta.getStatusCode().value());
    }

    @Test
    void testGetUserById_notFound() {
        when(userService.obtenerUsuarioPorId(10)).thenReturn(null);

        ResponseEntity<?> respuesta = controller.getUserById(10);

        assertEquals(404, respuesta.getStatusCode().value());
    }

    @Test
    void testLogin_exitoso() {
        // Mock LoginRequest SIN archivo
        LoginRequest login = mock(LoginRequest.class);
        when(login.getUsername()).thenReturn("paula");
        when(login.getPassword()).thenReturn("123");

        User u = new User(1, "paula", "123", "mail@mail.com", false, true);

        when(userService.login("paula", "123")).thenReturn(Optional.of(u));

        ResponseEntity<String> respuesta = controller.login(login);

        assertEquals(200, respuesta.getStatusCode().value());
        assertNotNull(respuesta.getBody());
        assertTrue(respuesta.getBody().contains("exitosamente"));
    }

    @Test
    void testLogin_invalido() {
        LoginRequest login = mock(LoginRequest.class);
        when(login.getUsername()).thenReturn("paula");
        when(login.getPassword()).thenReturn("xyz");

        when(userService.login("paula", "xyz")).thenReturn(Optional.empty());

        ResponseEntity<String> respuesta = controller.login(login);

        assertEquals(401, respuesta.getStatusCode().value());
        assertEquals("Credenciales inválidas", respuesta.getBody());
    }
}
