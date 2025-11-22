package controller;

import com.example.Sportwear.Controller.GestionProductController;
import com.example.Sportwear.Model.Product;
import com.example.Sportwear.Service.GestionProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class GestionProductosControllerTest {

    @Mock
    private GestionProductService service;

    private GestionProductController controller;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        controller = new GestionProductController(service);
    }

    @Test
    void testUpdateStock() {
        Product p = new Product();
        p.setId(10);
        p.setStock(50);

        when(service.updateStock(10, 50)).thenReturn(p);

        Product result = controller.updateStock(10, 50);

        assertEquals(50, result.getStock());
        verify(service).updateStock(10, 50);
    }

    @Test
    void testIncreaseStock() {
        Product p = new Product();
        p.setId(1);
        p.setStock(30);

        when(service.increaseStock(1, 10)).thenReturn(p);

        Product result = controller.increaseStock(1, 10);

        assertEquals(30, result.getStock());
        verify(service).increaseStock(1, 10);
    }

    @Test
    void testDecreaseStock() {
        Product p = new Product();
        p.setId(5);
        p.setStock(20);

        when(service.decreaseStock(5, 5)).thenReturn(p);

        Product result = controller.decreaseStock(5, 5);

        assertEquals(20, result.getStock());
        verify(service).decreaseStock(5, 5);
    }

    @Test
    void testLowStock() {
        Product p1 = new Product();
        p1.setStock(2);

        Product p2 = new Product();
        p2.setStock(3);

        List<Product> list = Arrays.asList(p1, p2);

        when(service.getLowStock(5)).thenReturn(list);

        List<Product> result = controller.lowStock(5);

        assertEquals(2, result.size());
        verify(service).getLowStock(5);
    }
}
