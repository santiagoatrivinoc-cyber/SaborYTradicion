import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private String nombre;
    private String documento;
    private String telefono;
    private String correo;
    private String direccion;
    private Restaurante restaurante;
    private List<Pedido> listaPedidos;

    public Cliente(String nombre, String documento, String telefono, String correo, String direccion, Restaurante restaurante) {
        this.nombre = nombre;
        this.documento = documento;
        this.telefono = telefono;
        this.correo = correo;
        this.direccion = direccion;
        this.restaurante = restaurante;
        listaPedidos = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Restaurante getRestaurante() {
        return restaurante;
    }
    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }

    public List<Pedido> getListaPedidos() {
        return listaPedidos;
    }
    public void setListaPedidos(List<Pedido> listaPedidos) {
        this.listaPedidos = listaPedidos;
    }

    public boolean verificarPedido(String codigo){
        boolean existePedido = false;
        for(Pedido pedido : listaPedidos){
            if (pedido.getCodigo().equals(codigo)) {
                existePedido = true;
                break;
            }
        }
        return existePedido;
    }

    public void registrarPedido(Pedido pedido){
        boolean existePedido = verificarPedido(pedido.getCodigo());
        if (existePedido){
            System.out.println("Este pedido ya se registro anterirmente, no se puede volver a registrar.");
            return;
        }
        listaPedidos.add(pedido);
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

    public void mostrarHistorialPedidos(){
        for(Pedido pedido : listaPedidos){
            System.out.println(pedido.toString() + "\n--------------------\n");
        }
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "nombre='" + nombre + '\'' +
                ", documento='" + documento + '\'' +
                ", telefono='" + telefono + '\'' +
                ", correo='" + correo + '\'' +
                ", direccion='" + direccion + '\'' +
                '}';
    }
}
