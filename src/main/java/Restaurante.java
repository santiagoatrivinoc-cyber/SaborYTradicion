import java.util.ArrayList;
import java.util.List;

public class Restaurante {
    private String nombre;
    private String nit;
    private String direccion;
    private List<Producto> menu;
    private List<Cliente> listaClientes;

    public Restaurante(String nombre, String nit, String direccion) {
        this.nombre = nombre;
        this.nit = nit;
        this.direccion = direccion;

        //inicializar listas
        menu = new ArrayList<>();
        listaClientes = new ArrayList<>();
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






}
