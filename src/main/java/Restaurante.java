import java.util.ArrayList;
import java.util.List;

public class Restaurante {
    private String nombre;
    private String nit;
    private String direccion;
    private List<Producto> menu;
    private List<Cliente> listaClientes;
    private List<Pedido> listaPedidos;

    public Restaurante(String nombre, String nit, String direccion) {
        this.nombre = nombre;
        this.nit = nit;
        this.direccion = direccion;

        //inicializar listas
        menu = new ArrayList<>();
        listaClientes = new ArrayList<>();
        listaPedidos = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNit() {
        return nit;
    }
    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public List<Producto> getMenu() {
        return menu;
    }
    public void setMenu(List<Producto> menu) {
        this.menu = menu;
    }

    public List<Cliente> getListaClientes() {
        return listaClientes;
    }
    public void setListaClientes(List<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }

    public List<Pedido> getListaPedidos() {
        return listaPedidos;
    }
    public void setListaPedidos(List<Pedido> listaPedidos) {
        this.listaPedidos = listaPedidos;
    }

    @Override
    public String toString() {
        return "Restaurante{" +
                "nombre='" + nombre + '\'' +
                ", nit='" + nit + '\'' +
                ", direccion='" + direccion + '\'' +
                '}';
    }

    public boolean verificarProducto(String codigo) {
        for (Producto producto : menu) {
            if (producto.getCodigo().equals(codigo)) {
                return true;
            }
        }
        return false;
    }
    public boolean registrarProducto(Producto producto) {
        if (verificarProducto(producto.getCodigo())) {
            return false;
        }
        menu.add(producto);
        return true;
    }

    public boolean verificarCliente(String documento) {
        for (Cliente cliente : listaClientes) {
            if (cliente.getDocumento().equals(documento)) {
                return true;
            }
        }
        return false;
    }

    public boolean registrarCliente(Cliente cliente) {
        if (verificarCliente(cliente.getDocumento())) {
            return false;
        }
        listaClientes.add(cliente);
        return true;
    }

    public Cliente buscarCliente(String documento){
        for(Cliente cliente : listaClientes){
            if(cliente.getDocumento().equals(documento)){
                return cliente;
            }
        }
        System.out.println("Cliente no encontrado (se recomienda registrar el cliente)");
        return null;
    }

    public Producto buscarProducto(String codigo) {
        for (Producto producto : menu) {
            if (producto.getCodigo().equals(codigo)) {
                return producto;
            }
        }
        System.out.println("No se encontro el producto");
        return null;
    }

    public void consultarProductosDisponibles(){
        System.out.println("---------- Menu Productos Disponibles ----------");
        for(Producto producto: menu){
            System.out.println(producto.toString());
            System.out.println("\n\n");
        }
        System.out.println("--------------------------------------------");
    }

    public boolean verificarPedido(String codigo) {
        for (Pedido pedido : listaPedidos) {
            if (pedido.getCodigo().equals(codigo)) {
                return true;
            }
        }
        return false;
    }

    public boolean registrarPedido(Pedido pedido) {
        if (verificarPedido(pedido.getCodigo())) {
            return false;
        }
        listaPedidos.add(pedido);
        return true;
    }

    public Pedido buscarPedido(String codigo) {
        for (Cliente cliente : listaClientes) {
            if (cliente != null) {
                Pedido pedidoEncontrado = cliente.buscarPedido(codigo);
                if (pedidoEncontrado != null) {
                    return pedidoEncontrado;
                }
            }
        }
        return null; // No apareció en ningún lado
    }
    public void consultarPedidosCliente(){
        for(Pedido pedido : listaPedidos){
            System.out.println(pedido.getCodigo() + ": " + pedido.getEstadoPedido());
        }
    }
    public boolean validarRequisitosPedido(Pedido pedido, double distanciaKm, ServicioDomicilio empresa) {
        if (pedido == null) {
            return false;
        }
        if (pedido.getCliente() == null || buscarCliente(pedido.getCliente().getDocumento()) == null) {
            return false;
        }
        if (pedido.getListaDetallesPedidos() == null || pedido.getListaDetallesPedidos().isEmpty()) {
            return false;
        }

        for (DetallePedido detalle : pedido.getListaDetallesPedidos()) {
            if (detalle == null || detalle.getProducto() == null) {
                return false;
            }
            if (detalle.getCantidad() <= 0) {
                return false;
            }

            Producto productoMenu = buscarProducto(detalle.getProducto().getCodigo());

            if (productoMenu == null) {
                return false;
            }
            if (!detalle.getProducto().validarCondicionesCalidad()) {
                return false;
            }
        }
        if (pedido.getModalidadEntrega() == ModalidadEntrega.DOMICILIO) {
            if (empresa == null) {
                return false;
            }
            if (!empresa.aceptarRecorrido(distanciaKm)) {
                return false; // La empresa no acepta el recorrido por distancia exedida o inválida
            }
        }

        return true;
    }
    public boolean confirmarPedido(Pedido pedido, double distanciaKm) {
        if (!validarRequisitosPedido(pedido, distanciaKm, null)) {
            return false;
        }
        if (pedido.getEstadoPedido() != EstadoPedido.PENDIENTE) {
            return false;
        }
        if (verificarPedido(pedido.getCodigo())) {
            return false;
        }
        pedido.setEstadoPedido(EstadoPedido.CONFIRMADO);
        pedido.setEmpresaTransporte(null);

        this.listaPedidos.add(pedido);
        pedido.getCliente().registrarPedido(pedido);

        return true;
    }

    public boolean confirmarPedidoDomicilio(Pedido pedido, double distanciaKm, ServicioDomicilio empresa) {

        if (!validarRequisitosPedido(pedido, distanciaKm, empresa)) {
            return false;
        }
        if (pedido.getEstadoPedido() != EstadoPedido.PENDIENTE) {
            return false;
        }
        if (buscarPedido(pedido.getCodigo()) != null) {
            return false;
        }
        pedido.setEmpresaTransporte(empresa);
        pedido.setEstadoPedido(EstadoPedido.CONFIRMADO);

        this.listaPedidos.add(pedido);
        pedido.getCliente().registrarPedido(pedido);

        return true;
    }

    public double calcularIngresosTotales() {
        double ingresosProductos = 0.0;

        for (Pedido pedido : listaPedidos) {
            if (pedido.getEstadoPedido() == EstadoPedido.CONFIRMADO || pedido.getEstadoPedido() == EstadoPedido.ENTREGADO) {
                ingresosProductos += pedido.calcularValorProductos();
            }
        }

        return ingresosProductos;
    }

    public double calcularIngresosTotalesDomicilios() {
        double ingresosDomicilios = 0.0;

        for (Pedido pedido : listaPedidos) {
            if (pedido.getModalidadEntrega() == ModalidadEntrega.DOMICILIO &&
                    (pedido.getEstadoPedido() == EstadoPedido.CONFIRMADO || pedido.getEstadoPedido() == EstadoPedido.ENTREGADO)) {

                ingresosDomicilios += pedido.getCostoDomicilio();
            }
        }

        return ingresosDomicilios;
    }

    public Producto consultarProductoMasVendido() {
        Producto masVendido = null;
        int maxCantidad = 0;

        for (Producto producto : this.menu) {
            int cantidadTotalProducto = 0;

            for (Pedido pedido : listaPedidos) {
                if (pedido.getEstadoPedido() == EstadoPedido.CONFIRMADO || pedido.getEstadoPedido() == EstadoPedido.ENTREGADO) {
                    for (DetallePedido detalle : pedido.getListaDetallesPedidos()) {
                        if (detalle.getProducto().getCodigo().equals(producto.getCodigo())) {
                            cantidadTotalProducto += detalle.getCantidad();
                        }
                    }
                }
            }

            if (cantidadTotalProducto > maxCantidad) {
                maxCantidad = cantidadTotalProducto;
                masVendido = producto;
            }
        }

        return masVendido;
    }

    public String consultarCategoriaProductoMayorIngreso() {
        double ingresosPlatos = 0.0;
        double ingresosBebidas = 0.0;
        double ingresosPostres = 0.0;

        for (Pedido pedido : listaPedidos) {
            if (pedido.getEstadoPedido() == EstadoPedido.CONFIRMADO || pedido.getEstadoPedido() == EstadoPedido.ENTREGADO) {
                for (DetallePedido detalle : pedido.getListaDetallesPedidos()) {
                    Producto prod = detalle.getProducto();
                    double subtotal = detalle.calcularSubtotal();

                    if (prod instanceof PlatoFuerte) {
                        ingresosPlatos += subtotal;
                    } else if (prod instanceof Bebida) {
                        ingresosBebidas += subtotal;
                    } else if (prod instanceof Postre) {
                        ingresosPostres += subtotal;
                    }
                }
            }
        }

        if (ingresosPlatos == 0.0 && ingresosBebidas == 0.0 && ingresosPostres == 0.0) {
            return "Sin ventas registradas";
        }
        if (ingresosPlatos >= ingresosBebidas && ingresosPlatos >= ingresosPostres) {
            return "Platos Fuertes";
        } else if (ingresosBebidas >= ingresosPostres) {
            return "Bebidas";
        } else {
            return "Postres";
        }
    }







}
