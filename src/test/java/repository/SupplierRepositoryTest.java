package repository;

import com.example.Sportwear.Repository.SupplierRepository;
import com.example.Sportwear.Model.Suppliers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class SupplierRepositoryTest {

    @Test
    void testRepoMock() {
        SupplierRepository repo = Mockito.mock(SupplierRepository.class);

        Suppliers s = new Suppliers();
        s.setSupplier_name("test");

        repo.save(s);

        Mockito.verify(repo).save(s);
    }
}
