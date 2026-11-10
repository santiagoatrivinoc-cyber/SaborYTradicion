import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Pedido {

    private String codigo;
    private LocalDateTime fecha;
    private ModalidadEntrega modalidadEntrega;
    private EstadoPedido estadoPedido;
    private double costoDomicilio;
    private Cliente cliente;
    private ServicioDomicilio empresaTransporte;
    private List<DetallePedido> listaDetallesPedidos;

    public Pedido(String codigo, LocalDateTime fecha, ModalidadEntrega modalidadEntrega, Cliente cliente, ServicioDomicilio empresaTransporte) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.modalidadEntrega = modalidadEntrega;
        this.costoDomicilio = 0;
        this.cliente = cliente;
        this.empresaTransporte = empresaTransporte; // ¡Faltaba esta línea en tu constructor!
        this.listaDetallesPedidos = new ArrayList<>();
    }


    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }
    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public ModalidadEntrega getModalidadEntrega() {
        return modalidadEntrega;
    }
    public void setModalidadEntrega(ModalidadEntrega modalidadEntrega) {
        this.modalidadEntrega = modalidadEntrega;
    }

    public EstadoPedido getEstadoPedido() {
        return estadoPedido;
    }
    public void setEstadoPedido(EstadoPedido estadoPedido) {
        this.estadoPedido = estadoPedido;
    }

    public double getCostoDomicilio() {
        return costoDomicilio;
    }
    public void setCostoDomicilio(double costoDomicilio) {
        this.costoDomicilio = costoDomicilio;
    }

    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public ServicioDomicilio getEmpresaTransporte() {
        return empresaTransporte;
    }

    public void setEmpresaTransporte(ServicioDomicilio empresaTransporte) {
        this.empresaTransporte = empresaTransporte;
    }

    public List<DetallePedido> getListaDetallesPedidos() {
        return listaDetallesPedidos;
    }

    public void setListaDetallesPedidos(List<DetallePedido> listaDetallesPedidos) {
        this.listaDetallesPedidos = listaDetallesPedidos;
    }

    public void agregarProducto(Producto producto, int cantidad){
        for(DetallePedido detalle : listaDetallesPedidos) {
            if(detalle.getProducto().getCodigo().equals(producto.getCodigo())){
                detalle.setCantidad(detalle.getCantidad() + cantidad);
                return;
            }

        }
        this.listaDetallesPedidos.add(new DetallePedido(producto, cantidad));
    }

    public double calcularValorProductos(){
        double totalProductos = 0;
        for (DetallePedido detalle : listaDetallesPedidos) {
            totalProductos += detalle.calcularSubtotal();
        }
        return totalProductos;
    }

    public double calcularValorTotal(){
        return calcularValorProductos() + costoDomicilio;
    }

    public void calcularCostoDomicilio(double distancia){
        if (modalidadEntrega == ModalidadEntrega.CONSUMO_RESTAURANTE){
            System.out.println("Este pedido no es un domicilio, no se puede calcular un costo domicilio");
            return;
        }
        if (empresaTransporte == null){
            System.out.println("No se ha determinado una empresa de transporte, no se puede calcular un costo domicilio");
            return;
        }
        costoDomicilio = empresaTransporte.calcularCostoEnvio(distancia);
    }

    @Override
    public String toString() {
        return "----- Pedido -----" +
                "\ncodigo: " + codigo +
                "\nfecha: " + fecha +
                "\nmodalidadEntrega: " + modalidadEntrega +
                "\nestadoPedido: " + estadoPedido +
                "\ncostoDomicilio: " + costoDomicilio +
                "\nvalor total: $" + calcularValorTotal();
    }

    //método para marcar como entregado
    public boolean marcarComoEntregado() {
        if (estadoPedido == EstadoPedido.CONFIRMADO) {
            estadoPedido = EstadoPedido.ENTREGADO;
            return true;
        }
        return false;
    }

//método para cancelar
    public boolean cancelarPedido() {
        if (estadoPedido == EstadoPedido.PENDIENTE || estadoPedido == EstadoPedido.CONFIRMADO) {
            estadoPedido = EstadoPedido.CANCELADO;
            return true;
        }
        return false;
    }
}
