package model;

import com.example.Sportwear.Model.Product;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProductModelTest {

    @Test
    void testSettersYGetters() {
        Product p = new Product();

        // Heredados de GestionProduct
        p.setId(5);
        p.setStock(20);

        // Propios de Product
        p.setName("Zapatilla");
        p.setDescription("Zapatilla deportiva");
        p.setPrice(29990);

        assertEquals(5, p.getId());
        assertEquals(20, p.getStock());
        assertEquals("Zapatilla", p.getName());
        assertEquals("Zapatilla deportiva", p.getDescription());
        assertEquals(29990, p.getPrice());
    }

    @Test
    void testConstructorCompleto() {
        Product p = new Product(
                "Polera",
                "Polera de algodón",
                9990
        );


        assertEquals("Polera", p.getName());
        assertEquals("Polera de algodón", p.getDescription());
        assertEquals(9990, p.getPrice());
    }
}
