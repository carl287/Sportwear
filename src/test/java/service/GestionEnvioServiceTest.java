package service;

import com.example.Sportwear.Model.GestionEnvio;
import com.example.Sportwear.Repository.GestionEnvioRepository;
import com.example.Sportwear.Service.GestionEnvioService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class GestionEnvioServiceTest {

    @Mock
    private GestionEnvioRepository repository;

    @InjectMocks
    private GestionEnvioService service;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreate() {
        GestionEnvio envio = new GestionEnvio();
        envio.setGestionName("Paula");
        envio.setGestionEmail("paula@mail.com");
        envio.setGestionPatente("ABC123");

        when(repository.save(envio)).thenReturn(envio);

        GestionEnvio result = service.create(envio);

        assertEquals("Paula", result.getGestionName());
        assertEquals("paula@mail.com", result.getGestionEmail());
        assertEquals("ABC123", result.getGestionPatente());
        verify(repository).save(envio);
    }

    @Test
    void testGetById() {
        GestionEnvio envio = new GestionEnvio(1, "Paula", "p@mail.com", "XYZ999");

        when(repository.findById(1)).thenReturn(Optional.of(envio));

        GestionEnvio result = service.getById(1);

        assertEquals(1, result.getId());
        assertEquals("Paula", result.getGestionName());
        verify(repository).findById(1);
    }

    @Test
    void testGetAll() {
        GestionEnvio e1 = new GestionEnvio(1, "Paula", "p@mail.com", "AAA111");
        GestionEnvio e2 = new GestionEnvio(2, "Carla", "c@mail.com", "BBB222");

        when(repository.findAll()).thenReturn(List.of(e1, e2));

        List<GestionEnvio> lista = service.getAll();

        assertEquals(2, lista.size());
        verify(repository).findAll();
    }

    @Test
    void testUpdate() {
        GestionEnvio original = new GestionEnvio(1, "Paula", "old@mail.com", "OLD111");
        GestionEnvio updated = new GestionEnvio(0, "Carla", "new@mail.com", "NEW999");

        when(repository.findById(1)).thenReturn(Optional.of(original));
        when(repository.save(any())).thenReturn(original);

        GestionEnvio result = service.update(1, updated);

        assertEquals("Carla", result.getGestionName());
        assertEquals("new@mail.com", result.getGestionEmail());
        assertEquals("NEW999", result.getGestionPatente());
        verify(repository).save(original);
    }

    @Test
    void testDelete() {
        when(repository.existsById(1)).thenReturn(true);
        doNothing().when(repository).deleteById(1);

        service.delete(1);

        verify(repository).deleteById(1);
    }

    @Test
    void testDelete_NotFound() {
        when(repository.existsById(1)).thenReturn(false);

        RuntimeException ex = assertThrows(
                RuntimeException.class,
                () -> service.delete(1)
        );

        assertEquals("Gestión de envío no encontrada", ex.getMessage());
    }
}
