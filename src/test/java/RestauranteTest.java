import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class RestauranteTest {

    Restaurante restaurante = new Restaurante("Sabor y Tradicion", "21243222", "Armenia");

    Cliente cliente = new Cliente("Miguel Lopez", "1095552346", "3001234567", "miguel@gmail.com", "Calle 10 # 15-20", restaurante);

    Producto productoValido = new PlatoFuerte("PF01", "Bandeja Paisa", "Plato típico", 8000, 30, restaurante,450, TipoPreparacion.ASADO, true);
    Producto productoInvalido = new Bebida("BE01", "Jugo de Lulo", "Jugo natural", 4000, 5, restaurante, 300, 15.0, TipoTemperatura.FRIA, false);
    RapidoExpress rapidoExpress = new RapidoExpress();

    void implementarMetodosInicialesNecesarios(){

        restaurante.registrarCliente(cliente);
        restaurante.registrarProducto(productoValido);
        restaurante.registrarProducto(productoInvalido);

    }

    @Test
    void evitarElRegistroDeClientesDuplicados() {
        Cliente clienteDuplicado = new Cliente("Miguel Lopez", "1095552346", "3001234567", "miguel@gmail.com", "Calle 10 # 15-20", restaurante);

        boolean segundoRegistro = restaurante.registrarCliente(clienteDuplicado);

        assertTrue(segundoRegistro, "No debe registrar un cliente con un documento ya existente.");
        assertEquals(1, restaurante.getListaClientes().size());
    }

    @Test
    void confirmarPedidoConProductoRechazado() {
        Pedido pedido = new Pedido("PED00001", LocalDateTime.now(), ModalidadEntrega.CONSUMO_RESTAURANTE, cliente, rapidoExpress);
        pedido.agregarProducto(productoInvalido, 2); // Bebida no cumple control de calidad

        boolean resultado =

        assertFalse(resultado, "El pedido no debe confirmarse si contiene productos que no cumplen calidad.");
        assertEquals(EstadoPedido.PENDIENTE, pedido.getEstado());
        assertEquals(0, restaurante.getPedidos().size());
    }

}