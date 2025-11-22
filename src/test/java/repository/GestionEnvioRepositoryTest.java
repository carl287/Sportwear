package repository;



import com.example.Sportwear.Repository.GestionEnvioRepository;
import com.example.Sportwear.Model.GestionEnvio;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class GestionEnvioRepositoryTest {

    @Test
    void testRepoMock() {
        GestionEnvioRepository repo = Mockito.mock(GestionEnvioRepository.class);

        GestionEnvio g = new GestionEnvio();
        g.setGestionName("Paula");

        repo.save(g);

        Mockito.verify(repo).save(g);
    }
}
