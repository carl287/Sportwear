package reglasdenegocio;

import com.example.Sportwear.Model.Product;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ReglaPrecioTest {

    @Test
    public void testCalculoPrecioFinal() {
        Product p = new Product();
        p.setPrice(20000);  // ← setter correcto

        int cantidad = 3;

        int total = p.getPrice() * cantidad;  // ← getter correcto

        assertEquals(60000, total);
    }
}
