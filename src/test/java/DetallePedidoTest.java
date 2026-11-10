import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DetallePedidoTest {
    private Restaurante restaurante;

    @BeforeEach
    void setUp() {
        restaurante = new Restaurante("Sabor & Tradicion", "900123456-7", "Carrera 14 # 20-35, Armenia, Quindío");
    }
        @Test
    void testCalcularSubtotalCorrecto() {

        Producto producto = new PlatoFuerte("PF01", "Hamburguesa", "Comida raída", 20000,
                15, restaurante, 425, TipoPreparacion.A_LA_PLANCHA, true);

        int cantidad = 3;
        DetallePedido detalle = new DetallePedido(producto, cantidad);

        double subtotalEsperado = 60000.0;
        double subtotalObtenido = detalle.calcularSubtotal();

       //El tercer parámetro 0.001 es el margen de tolerancia para números decimales tipo double
        assertEquals(subtotalEsperado, subtotalObtenido, 0.001,
                "El subtotal del detalle debe ser igual al precio del producto multiplicado por la cantidad.");
    }
}