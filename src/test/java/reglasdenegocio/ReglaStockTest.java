package reglasdenegocio;

import com.example.Sportwear.Model.Product;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ReglaStockTest {

    @Test
    public void testNoPermitirAgregarSinStock() {
        Product p = new Product();

        p.setId(1);
        p.setName("Zapatillas");  // ← setter correcto
        p.setStock(0);            // ← viene de GestionProduct

        boolean sePuedeAgregar = p.getStock() > 0;

        assertFalse(sePuedeAgregar);
    }
}
