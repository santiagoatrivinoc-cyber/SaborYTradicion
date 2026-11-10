import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class PedidoTest {

    private Pedido pedido;
    private Producto producto1;
    private Producto producto2;
    private Restaurante restaurante;
    @BeforeEach
    void setUp() {
        pedido = new Pedido("PED001", LocalDateTime.now(), ModalidadEntrega.DOMICILIO, null, null);
        restaurante = new Restaurante("Sabor & Tradicion", "900123456-7", "Carrera 14 # 20-35, Armenia, Quindío");

        producto1 = new PlatoFuerte("PF01", "Bandeja Paisa", "Almuerzo", 25000.0, 10,
                restaurante,500,TipoPreparacion.FRITO, true);
        producto2 = new Bebida("BE01", "Jugo Natural", "Bebida", 6000.0, 7,restaurante,500,10,
                TipoTemperatura.FRIA,false);
    }

    @Test
    void testAgregarProductoNuevoYAcumularCantidad() {

        pedido.agregarProducto(producto1, 2); // 2 Bandejas Paisas

        assertEquals(1, pedido.getListaDetallesPedidos().size(), "Debe haber 1 detalle en la lista.");
        assertEquals(2, pedido.getListaDetallesPedidos().get(0).getCantidad(), "La cantidad inicial debe ser 2.");


        pedido.agregarProducto(producto1, 3); // 3 más

        assertEquals(1, pedido.getListaDetallesPedidos().size(), "No debe duplicar el detalle, debe acumular.");
        assertEquals(5, pedido.getListaDetallesPedidos().get(0).getCantidad(), "La cantidad total debe acumularse a 5.");

        pedido.agregarProducto(producto2, 1); // 1 Jugo
        assertEquals(2, pedido.getListaDetallesPedidos().size(), "Debe agregar el nuevo producto como un segundo detalle.");
    }

    @Test
    void testCalcularValorTotalConProductosYDomicilio() {

        pedido.agregarProducto(producto1, 2);
        pedido.agregarProducto(producto2, 1);

        pedido.setCostoDomicilio(5000.0);

        double valorEsperado = (50000.0 * 1.2) + (6000.0 * 1.1) + 5000.0;
        double valorObtenido = pedido.calcularValorTotal();

        assertEquals(valorEsperado, valorObtenido, 0.001, "El valor total debe incluir la suma de los productos más el costo del domicilio.");
    }

    @Test
    void testFlujoYCambioDeEstados() {
        assertEquals(EstadoPedido.PENDIENTE, pedido.getEstadoPedido(), "El estado inicial del pedido debe ser PENDIENTE.");

        boolean entregadoFallido = pedido.marcarComoEntregado();
        assertFalse(entregadoFallido, "No se puede entregar un pedido que no ha sido confirmado.");
        assertEquals(EstadoPedido.PENDIENTE, pedido.getEstadoPedido(), "El estado no debe cambiar si la transición es inválida.");

        pedido.setEstadoPedido(EstadoPedido.CONFIRMADO);

        boolean entregadoExitoso = pedido.marcarComoEntregado();
        assertTrue(entregadoExitoso, "El pedido confirmado debe poder marcarse como entregado.");
        assertEquals(EstadoPedido.ENTREGADO, pedido.getEstadoPedido(), "El estado final debe ser ENTREGADO.");
    }

    @Test
    void testCancelarPedidoValido() {
        assertTrue(pedido.cancelarPedido(), "Un pedido pendiente debe poder cancelarse.");
        assertEquals(EstadoPedido.CANCELADO, pedido.getEstadoPedido(), "El estado debe cambiar a CANCELADO.");
    }
}