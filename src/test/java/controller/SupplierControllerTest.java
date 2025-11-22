package controller;



import com.example.Sportwear.Controller.SupplierController;
import com.example.Sportwear.Model.Suppliers;
import com.example.Sportwear.Service.SupplierService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class SupplierControllerTest {

    @Mock
    private SupplierService service;

    private SupplierController controller;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        controller = new SupplierController(service);
    }

    @Test
    void testCreate() {
        Suppliers s = new Suppliers();
        s.setSupplier_name("Paula");

        when(service.create(s)).thenReturn(s);

        Suppliers result = controller.create(s);

        assertEquals("Paula", result.getSupplier_name());
    }

    @Test
    void testGetAll() {
        when(service.getAll()).thenReturn(List.of(new Suppliers(), new Suppliers()));

        List<Suppliers> result = controller.getAll();

        assertEquals(2, result.size());
    }

    @Test
    void testGetById() {
        Suppliers s = new Suppliers();
        s.setId(1);

        when(service.getById(1)).thenReturn(s);

        Suppliers result = controller.getById(1);

        assertEquals(1, result.getId());
    }

    @Test
    void testUpdate() {
        Suppliers s = new Suppliers();
        s.setSupplier_name("Nuevo");

        when(service.update(1, s)).thenReturn(s);

        Suppliers result = controller.update(1, s);

        assertEquals("Nuevo", result.getSupplier_name());
    }

    @Test
    void testDelete() {
        String msg = controller.delete(1);

        verify(service).delete(1);
        assertEquals("Proveedor eliminado correctamente.", msg);
    }
}
