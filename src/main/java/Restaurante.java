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
        if (verificarCliente(pedido.getCodigo())) {
            return false;
        }
        listaPedidos.add(pedido);
        return true;
    }

    public Pedido buscarPedido(String codigo){
        for(Pedido pedido : listaPedidos){
            if(pedido.getCodigo().equals(codigo)){
                return pedido;
            }
        }
        System.out.println("Pedido no encontrado");
        return null;
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

        // Verificar que el pedido tenga al menos un producto
        if (pedido.getListaDetallesPedidos() == null || pedido.getListaDetallesPedidos().isEmpty()) {
            return false;
        }

        // 2, 3 y 4. Verificar productos en el menú, cantidades y condiciones de calidad
        for (DetallePedido detalle : pedido.getListaDetallesPedidos()) {
            if (detalle == null || detalle.getProducto() == null) {
                return false;
            }

            // Validar que la cantidad sea mayor a cero
            if (detalle.getCantidad() <= 0) {
                return false;
            }

            // Validar que el producto pertenezca al menú del restaurante
            Producto productoMenu = buscarProducto(detalle.getProducto().getCodigo());
            if (productoMenu == null) {
                return false;
            }

            // Validar que el producto cumpla con los estándares de calidad
            if (!detalle.getProducto().validarCondicionesCalidad()) {
                return false;
            }
        }

        // 5. Si la modalidad es a domicilio, validar la empresa y la distancia
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
    public double confirmarPedido(Pedido pedido, double distanciaKm) {
        // Validar requisitos (para consumo en restaurante no se requiere empresa de domicilio)
        if (!validarRequisitosPedido(pedido, distanciaKm, null)) {
            return -1.0;
        }

        // Evitar confirmaciones repetidas
        if (pedido.getEstadoPedido() != EstadoPedido.PENDIENTE) {
            return -1.0;
        }

        // Evitar códigos de pedido duplicados en el restaurante
        if (buscarPedido(pedido.getCodigo()) != null) {
            return -1.0;
        }

        // Cambiar estado a CONFIRMADO
        pedido.setEstadoPedido(EstadoPedido.CONFIRMADO);
        pedido.setCostoDomicilio(0.0);
        pedido.setEmpresaTransporte(null);

        // Registrar en la lista global del restaurante y en el historial del cliente
        this.listaPedidos.add(pedido);
        pedido.getCliente().registrarPedido(pedido);

        // Retornar el valor total del pedido
        return pedido.calcularValorTotal();
    }







}
