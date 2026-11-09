import java.util.ArrayList;
import java.util.List;

public abstract class Producto {
    private String codigo;
    private String nombre;
    private String descripcion;
    private double precioBase;
    private int tiempoPreparacion;
    private Restaurante restaurante;
    private List<DetallePedido> listaDetallesPedido;


    public Producto(String codigo, String nombre, String descripcion, double precioBase, int tiempoPreparacion, Restaurante restaurante) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precioBase = precioBase;
        this.tiempoPreparacion = tiempoPreparacion;
        this.restaurante = restaurante;
        listaDetallesPedido = new ArrayList<>();
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecioBase() {
        return precioBase;
    }

    public void setPrecioBase(double precioBase) {
        this.precioBase = precioBase;
    }

    public int getTiempoPreparacion() {
        return tiempoPreparacion;
    }

    public void setTiempoPreparacion(int tiempoPreparacion) {
        this.tiempoPreparacion = tiempoPreparacion;
    }

    public Restaurante getRestaurante() {
        return restaurante;
    }
    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }

    public List<DetallePedido> getListaDetallesPedido() {
        return listaDetallesPedido;
    }
    public void setListaDetallesPedido(List<DetallePedido> listaDetallesPedido) {
        this.listaDetallesPedido = listaDetallesPedido;
    }

    @Override
    public String toString() {
        return "---- " + nombre + " ----" +
                "\ncodigo: " + codigo +
                "\ndescripcion:'" + descripcion +
                "\nprecioBase:" + precioBase +
                "\ntiempoPreparacion:" + tiempoPreparacion +
                "\nlistaDetallesPedido=" + listaDetallesPedido;
    }

    public abstract double calcularPrecioVenta();

    public abstract boolean validarCondicionesCalidad();
}
