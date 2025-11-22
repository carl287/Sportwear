package repository;
import com.example.Sportwear.Repository.GestionProductRepository;
import com.example.Sportwear.Model.Product;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class GestionProductosRepositoryTest {

    @Test
    void testRepositoryMock() {
        // repo correcto (cambia el nombre si en tu proyecto se llama distinto)
        GestionProductRepository repo = mock(GestionProductRepository.class);

        // si Product tiene constructor vacío, esto sirve
        Product p = new Product();

        // OJO: cambia setName por el setter REAL de tu Product
        p.setName("Chaqueta");

        repo.save(p);

        verify(repo).save(p);
    }
}
