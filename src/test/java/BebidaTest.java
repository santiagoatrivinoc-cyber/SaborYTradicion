import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BebidaTest {

    private Restaurante restaurante;

    @BeforeEach
    void setUp() {
        restaurante = new Restaurante("Sabor & Tradicion", "900123456-7", "Carrera 14 # 20-35, Armenia, Quindío");
    }

    @Test
    void precioVentaSinPreparacionEspecial() {
        Bebida bebida = new Bebida("B001", "Limonada", "Natural", 10000, 5,
                restaurante, 500, 5, TipoTemperatura.FRIA, false);
        assertEquals(11000, bebida.calcularPrecioVenta(), 0.01);
    }

    @Test
    void precioVentaConPreparacionEspecial() {
        Bebida bebida = new Bebida("B002", "Cóctel", "De la casa", 10000, 10,
                restaurante, 500, 5, TipoTemperatura.FRIA, true);
        assertEquals(14000, bebida.calcularPrecioVenta(), 0.01);
    }

    @Test
    void cumpleCalidadBebidaFria() {
        Bebida bebida = new Bebida("B003", "Limonada", "Natural", 10000, 5,
                restaurante, 500, 5, TipoTemperatura.FRIA, false);
        assertTrue(bebida.validarCondicionesCalidad());
    }

    @Test
    void rechazaBebidaFriaConTemperaturaErronea() {
        Bebida bebida = new Bebida("B004", "Limonada", "Natural", 10000, 5,
                restaurante, 500, 70, TipoTemperatura.FRIA, false);
        assertFalse(bebida.validarCondicionesCalidad());
    }

    @Test
    void muestraExcepcionConCodigoVacio() {
        assertThrows(IllegalArgumentException.class, () ->
                new Bebida("", "Limonada", "Natural", 10000, 5,
                        restaurante, 500, 5, TipoTemperatura.FRIA, false));
    }
}
