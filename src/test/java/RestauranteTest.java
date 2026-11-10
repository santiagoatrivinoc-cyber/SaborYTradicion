import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class RestauranteTest {

    Restaurante restaurante = new Restaurante("Sabor y Tradicion", "21243222", "Armenia");

    Cliente cliente = new Cliente("Miguel Lopez", "1095552346", "3001234567", "miguel@gmail.com", "Calle 10 # 15-20", restaurante);

    Producto productoValido = new PlatoFuerte("PF01", "Bandeja Paisa", "Plato típico", 8000, 30, restaurante,450, TipoPreparacion.ASADO, true);
    Producto productoInvalido = new Bebida("BE01", "Jugo de Lulo", "Jugo natural", 4000, 5, restaurante, 300, 15.0, TipoTemperatura.FRIA, false);
    RapidoExpress rapidoExpress = new RapidoExpress();

    @BeforeEach
    void implementarMetodosInicialesNecesarios(){

        restaurante.registrarCliente(cliente);
        restaurante.registrarProducto(productoValido);
        restaurante.registrarProducto(productoInvalido);

    }

    @Test
    void evitarElRegistroDeClientesDuplicados() {
        Cliente clienteDuplicado = new Cliente("Miguel Lopez", "1095552346", "3001234567", "miguel@gmail.com", "Calle 10 # 15-20", restaurante);

        boolean segundoRegistro = restaurante.registrarCliente(clienteDuplicado);

        assertFalse(segundoRegistro, "No debe registrar un cliente con un documento ya existente.");
        assertEquals(1, restaurante.getListaClientes().size());
    }

    @Test
    void confirmarPedidoConProductoRechazado() {
        Pedido pedido = new Pedido("PED00001", LocalDateTime.now(), ModalidadEntrega.CONSUMO_RESTAURANTE, cliente, null);
        pedido.setEstadoPedido(EstadoPedido.PENDIENTE);
        pedido.agregarProducto(productoInvalido, 2); // Bebida no cumple control de calidad

        boolean resultado = restaurante.confirmarPedido(pedido, 0);

        assertFalse(resultado, "El pedido no debe confirmarse si contiene productos que no cumplen calidad.");
        assertEquals(EstadoPedido.PENDIENTE, pedido.getEstadoPedido());
        assertEquals(0, restaurante.getListaPedidos().size());
    }

    @Test
    void confirmarPedidoConDomicilioExitoso(){
        Pedido pedido = new Pedido("PED00002", LocalDateTime.now(), ModalidadEntrega.DOMICILIO, cliente, rapidoExpress);
        pedido.setEstadoPedido(EstadoPedido.PENDIENTE);
        pedido.agregarProducto(productoValido, 2); // 2 * $9.600 = $19.200
        double distancia = 5;
        boolean resultado = restaurante.confirmarPedidoDomicilio(pedido, distancia, rapidoExpress);
        assertTrue(resultado, "El pedido a domicilio con datos válidos debe confirmarse.");
        assertEquals(EstadoPedido.CONFIRMADO, pedido.getEstadoPedido());
        assertEquals(19200, pedido.calcularValorTotal());

        assertTrue(restaurante.getListaPedidos().contains(pedido));
        assertTrue(cliente.getListaPedidos().contains(pedido));
    }

    @Test
    void calculoIngresosTotalesRestaurante() {
        // Pedido 1 Confirmado: 2 platos ($19.200) + Domicilio ($12.500)
        Pedido pedido1 = new Pedido("PED00003", LocalDateTime.now(), ModalidadEntrega.DOMICILIO, cliente, rapidoExpress);
        pedido1.calcularCostoDomicilio(5);
        pedido1.setEstadoPedido(EstadoPedido.PENDIENTE);
        pedido1.agregarProducto(productoValido, 2);
        restaurante.confirmarPedidoDomicilio(pedido1, 5, rapidoExpress);

        // Pedido 2 Confirmado y Cancelado
        Pedido pedido2 = new Pedido("PED00004", LocalDateTime.now(), ModalidadEntrega.CONSUMO_RESTAURANTE, cliente, null);
        pedido2.setEstadoPedido(EstadoPedido.PENDIENTE);
        pedido2.agregarProducto(productoValido, 1);
        restaurante.confirmarPedido(pedido2, 0);
        pedido2.cancelarPedido(); // Estado: CANCELADO

        System.out.println(pedido1.getEstadoPedido());
        System.out.println(pedido2.getEstadoPedido());

        double ingresosRestaurante = restaurante.calcularIngresosTotales();
        double ingresosDomicilios = restaurante.calcularIngresosTotalesDomicilios();

        System.out.println(pedido1.getCostoDomicilio());

        System.out.println(ingresosRestaurante);
        System.out.println(ingresosDomicilios);

        assertEquals(19200.0, ingresosRestaurante);
        assertEquals(12500.0, ingresosDomicilios);
    }

    @Test
    void obtenerCategoriaConMayoresIngresos() {
        Postre postreValido = new Postre("PO01", "Tres Leches", "Postre", 5000, 15, restaurante, 200, 4.0, true); // Refrigerado (+5% base = +$250) -> $6.000
        restaurante.registrarProducto(postreValido);

        Bebida bebidaValida = new Bebida("B001", "Chocolisto", "Bebida de chocolate", 6000, 5, restaurante, 500, 10, TipoTemperatura.FRIA, false);

        Pedido pedido1 = new Pedido("PED00005", LocalDateTime.now(), ModalidadEntrega.CONSUMO_RESTAURANTE, cliente, null);
        pedido1.agregarProducto(productoValido, 1);
        restaurante.confirmarPedido(pedido1, 0);
        pedido1.setEstadoPedido(EstadoPedido.CONFIRMADO);
        restaurante.registrarPedido(pedido1);

        Pedido pedido2 = new Pedido("PED00006", LocalDateTime.now(), ModalidadEntrega.CONSUMO_RESTAURANTE, cliente, null);
        pedido2.agregarProducto(postreValido, 3);
        restaurante.confirmarPedido(pedido2, 0);
        pedido2.setEstadoPedido(EstadoPedido.CONFIRMADO);
        restaurante.registrarPedido(pedido2);

        Pedido pedido3 = new Pedido("PED00007", LocalDateTime.now(), ModalidadEntrega.CONSUMO_RESTAURANTE, cliente, null);
        pedido3.agregarProducto(bebidaValida, 6);
        restaurante.confirmarPedido(pedido3, 0);
        pedido3.setEstadoPedido(EstadoPedido.CONFIRMADO);
        restaurante.registrarPedido(pedido3);

        String categoriaLider = restaurante.consultarCategoriaProductoMayorIngreso();
        System.out.println(categoriaLider);
        assertEquals("Bebidas", categoriaLider);
    }

}