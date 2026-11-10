import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ClienteTest {

    private Cliente cliente;
    private Restaurante restaurante;
    private Pedido pedidoPrueba;

    @BeforeEach
    void setUp() {
        restaurante = new Restaurante("Sabor & Tradicion", "900123456-7", "Carrera 14 # 20-35, Armenia, Quindío");

        cliente = new Cliente("Ana Gómez", "10987654", "3104445566", "ana@mail.com", "Calle 50", restaurante);

        pedidoPrueba = new Pedido("ABC12345", LocalDateTime.now(), ModalidadEntrega.CONSUMO_RESTAURANTE,cliente,null);
    }

    @Test
    void testRegistrarPedidoExitoso() {
        cliente.registrarPedido(pedidoPrueba);
        boolean pedidoEncontrado = false;
        for (Pedido p : cliente.getListaPedidos()) {
            if (p.getCodigo().equals("ABC12345")) {
                pedidoEncontrado = true;
                break;
            }
        }

        assertTrue(pedidoEncontrado, "El pedido debería estar registrado en la lista del cliente.");
    }

    @Test
    void testRegistrarPedidoDuplicado() {

        cliente.registrarPedido(pedidoPrueba);

        Pedido pedidoDuplicado = new Pedido("ABC12345", LocalDateTime.now(), ModalidadEntrega.CONSUMO_RESTAURANTE, cliente, null);
        cliente.registrarPedido(pedidoDuplicado);

        int contador = 0;
        for (Pedido p : cliente.getListaPedidos()) {
            contador++;
        }

        assertEquals(1, contador, "No debería permitir registrar dos pedidos con el mismo código.");
    }

    @Test
    void testVerificarPedidoExistenteYNoExistente() {
        assertFalse(cliente.verificarPedido("XYZ99999"), "Debería retornar false si el pedido no ha sido registrado.");
        cliente.registrarPedido(pedidoPrueba);

        assertTrue(cliente.verificarPedido("ABC12345"), "Debería retornar true si el código del pedido ya está registrado.");
    }

    @Test
    void testHistorialPedidosBusqueda() {
        cliente.registrarPedido(pedidoPrueba);

        Pedido encontrado = cliente.buscarPedido("ABC12345");
        assertNotNull(encontrado, "Debería encontrar y retornar el objeto pedido por su código.");
        assertEquals("ABC12345", encontrado.getCodigo(), "El código del pedido encontrado debe coincidir.");

        Pedido noEncontrado = cliente.buscarPedido("FALSO123");
        assertNull(noEncontrado, "Debería retornar null si el pedido no existe en el historial del cliente.");
    }
}