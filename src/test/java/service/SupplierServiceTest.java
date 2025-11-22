package service;



import com.example.Sportwear.Model.Suppliers;
import com.example.Sportwear.Repository.SupplierRepository;
import com.example.Sportwear.Service.SupplierService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class SupplierServiceTest {

    @Mock
    private SupplierRepository repo;

    @InjectMocks
    private SupplierService service;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreate() {
        Suppliers s = new Suppliers();
        s.setSupplier_name("Paula");

        when(repo.save(s)).thenReturn(s);

        Suppliers result = service.create(s);

        assertEquals("Paula", result.getSupplier_name());
    }

    @Test
    void testGetById_OK() {
        Suppliers s = new Suppliers();
        s.setId(1);

        when(repo.findById(1)).thenReturn(Optional.of(s));

        Suppliers result = service.getById(1);

        assertEquals(1, result.getId());
    }

    @Test
    void testGetById_NotFound() {
        when(repo.findById(99)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> service.getById(99));

        assertEquals("Proveedor no encontrado", ex.getMessage());
    }

    @Test
    void testGetAll() {
        when(repo.findAll()).thenReturn(List.of(new Suppliers(), new Suppliers()));

        List<Suppliers> result = service.getAll();

        assertEquals(2, result.size());
    }

    @Test
    void testUpdate() {
        Suppliers original = new Suppliers();
        original.setId(1);

        Suppliers updated = new Suppliers();
        updated.setSupplier_name("Nuevo");
        updated.setSupplier_email("nuevo@mail.com");

        when(repo.findById(1)).thenReturn(Optional.of(original));
        when(repo.save(original)).thenReturn(original);

        Suppliers result = service.update(1, updated);

        assertEquals("Nuevo", result.getSupplier_name());
        assertEquals("nuevo@mail.com", result.getSupplier_email());
    }

    @Test
    void testDelete() {
        doNothing().when(repo).deleteById(1);

        service.delete(1);

        verify(repo).deleteById(1);
    }
}
