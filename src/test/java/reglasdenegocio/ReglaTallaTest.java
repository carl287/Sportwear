package reglasdenegocio;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ReglaTallaTest {

    @Test
    public void testValidarTallaSeleccionada() {
        String tallaSeleccionada = "M";

        boolean esValida = tallaSeleccionada != null && !tallaSeleccionada.isEmpty();

        assertTrue(esValida);
    }
}
