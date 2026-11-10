import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PlatoFuerteTest {

    private Restaurante restaurante;

    @BeforeEach
    void setUp() {
        restaurante = new Restaurante("Sabor & Tradicion", "900123456-7", "Carrera 14 # 20-35, Armenia, Quindío");
    }

    @Test
    void precioVentaAplicaIncremento20() {
        PlatoFuerte plato = new PlatoFuerte("P001", "Bandeja", "Plato típico", 10000, 30,
                restaurante, 400, TipoPreparacion.ASADO, true);
        assertEquals(12000, plato.calcularPrecioVenta(), 0.01);
    }

    @Test
    void cumpleCalidadConValoresValidos() {
        PlatoFuerte plato = new PlatoFuerte("P002", "Bandeja", "Plato típico", 10000, 30,
                restaurante, 400, TipoPreparacion.FRITO, true);
        assertTrue(plato.validarCondicionesCalidad());
    }

    @Test
    void rechazaPesoBajoElMinimo() {
        PlatoFuerte plato = new PlatoFuerte("P003", "Bandeja", "Plato típico", 10000, 30,
                restaurante, 150, TipoPreparacion.ASADO, true);
        assertFalse(plato.validarCondicionesCalidad());
    }

    @Test
    void rechazaCoccionNoAprobada() {
        PlatoFuerte plato = new PlatoFuerte("P004", "Bandeja", "Plato típico", 10000, 30,
                restaurante, 400, TipoPreparacion.HORNEADO, false);
        assertFalse(plato.validarCondicionesCalidad());
    }

    @Test
    void lanzaExcepcionConPrecioNegativo() {
        assertThrows(IllegalArgumentException.class, () ->
                new PlatoFuerte("P005", "Bandeja", "Plato típico", -5000, 30,
                        restaurante, 400, TipoPreparacion.ASADO, true));
    }
}