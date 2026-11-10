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







}
