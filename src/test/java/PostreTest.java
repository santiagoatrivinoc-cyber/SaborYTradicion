import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PostreTest {

    private Restaurante restaurante;

    @BeforeEach
    void setUp() {
        restaurante = new Restaurante("Sabor & Tradicion", "900123456-7", "Carrera 14 # 20-35, Armenia, Quindío");
    }

    @Test
    void precioVentaSinRefrigeracion() {
        Postre postre = new Postre("D001", "Brownie", "Chocolate", 10000, 10,
                restaurante, 200, 20, false);
        assertEquals(11500, postre.calcularPrecioVenta(), 0.01);
    }

    @Test
    void precioVentaRefrigeradoSumaCincoPorCiento() {
        Postre postre = new Postre("D002", "Tres leches", "Cremoso", 10000, 10,
                restaurante, 200, 4, true);
        assertEquals(12000, postre.calcularPrecioVenta(), 0.01);
    }

    @Test
    void cumpleCalidadRefrigeradoEnRango() {
        Postre postre = new Postre("D003", "Tres leches", "Cremoso", 10000, 10,
                restaurante, 200, 4, true);
        assertTrue(postre.validarCondicionesCalidad());
    }

    @Test
    void rechazaRefrigeradoConTemperaturaAmbiente() {
        Postre postre = new Postre("D004", "Tres leches", "Cremoso", 10000, 10,
                restaurante, 200, 20, true);
        assertFalse(postre.validarCondicionesCalidad());
    }

    @Test
    void lanzaExcepcionConPrecioCero() {
        assertThrows(IllegalArgumentException.class, () ->
                new Postre("D005", "Brownie", "Chocolate", 0, 10,
                        restaurante, 200, 20, false));
    }
}
