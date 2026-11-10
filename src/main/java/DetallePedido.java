public class DetallePedido {
    private Producto producto;
    private int cantidad;


    public DetallePedido(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public double calcularSubtotal() {
        if (producto != null) {
            return producto.calcularPrecioVenta() * cantidad;
        }
        return 0.0;
    }

    public Producto getProducto() {
        return producto;
    }
    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }



    @Override
    public String toString() {
        return cantidad + " x " + producto.getNombre() + " = Subtotal: $" + calcularSubtotal()+"\n";
    }
}