package service;

import com.example.Sportwear.Model.Product;
import com.example.Sportwear.Repository.ProductRepository;
import com.example.Sportwear.Service.GestionProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class GestionProductosServiceTest {

    @Mock
    private ProductRepository repository;

    @InjectMocks
    private GestionProductService service;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testUpdateStock() {
        Product p = new Product();
        p.setId(1);
        p.setStock(10);

        when(repository.findById(1)).thenReturn(Optional.of(p));
        when(repository.save(p)).thenReturn(p);

        Product result = service.updateStock(1, 30);

        assertEquals(30, result.getStock());
        verify(repository).save(p);
    }

    @Test
    void testIncreaseStock() {
        Product p = new Product();
        p.setId(1);
        p.setStock(10);

        when(repository.findById(1)).thenReturn(Optional.of(p));
        when(repository.save(p)).thenReturn(p);

        Product result = service.increaseStock(1, 5);

        assertEquals(15, result.getStock());
        verify(repository).save(p);
    }

    @Test
    void testDecreaseStock() {
        Product p = new Product();
        p.setId(1);
        p.setStock(20);

        when(repository.findById(1)).thenReturn(Optional.of(p));
        when(repository.save(p)).thenReturn(p);

        Product result = service.decreaseStock(1, 5);

        assertEquals(15, result.getStock());
        verify(repository).save(p);
    }

    @Test
    void testDecreaseStock_Throws() {
        Product p = new Product();
        p.setId(1);
        p.setStock(3);

        when(repository.findById(1)).thenReturn(Optional.of(p));

        RuntimeException ex = assertThrows(
                RuntimeException.class,
                () -> service.decreaseStock(1, 10)
        );

        assertEquals("Stock insuficiente", ex.getMessage());
    }

    @Test
    void testGetLowStock() {
        Product p1 = new Product();
        Product p2 = new Product();
        p1.setStock(3);
        p2.setStock(10);

        when(repository.findAll()).thenReturn(List.of(p1, p2));

        List<Product> result = service.getLowStock(5);

        assertEquals(1, result.size());
        assertEquals(3, result.get(0).getStock());
    }
}
