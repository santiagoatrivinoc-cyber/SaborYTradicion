import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Pedido {

    private String codigo;
    private LocalDateTime fecha;
    private ModalidadEntrega modalidadEntrega;
    private EstadoEntrega estadoEntrega;
    private double costoDomicilio;
    private Cliente cliente;
    private ServicioDomicilio empresaTransporte;
    private List<DetallePedido> listaDetallesPedidos;

    public Pedido(String codigo, LocalDateTime fecha, ModalidadEntrega modalidadEntrega, EstadoEntrega estadoEntrega, Cliente cliente, ServicioDomicilio empresaTransporte) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.modalidadEntrega = modalidadEntrega;
        this.estadoEntrega = estadoEntrega;
        this.cliente = cliente;
        this.empresaTransporte = empresaTransporte; // 👈 ¡Faltaba esta línea en tu constructor!
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

    public EstadoEntrega getEstadoEntrega() {
        return estadoEntrega;
    }
    public void setEstadoEntrega(EstadoEntrega estadoEntrega) {
        this.estadoEntrega = estadoEntrega;
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

    public void marcarEntrega(){
        this.estadoEntrega = EstadoEntrega.ENTREGADO;
    }

    public void marcarCancelacion(){
        this.estadoEntrega = EstadoEntrega.CANCELADO;
    }

    @Override
    public String toString() {
        return "----- Pedido -----" +
                "\ncodigo: " + codigo +
                "\nfecha: " + fecha +
                "\nmodalidadEntrega: " + modalidadEntrega +
                "\nestadoEntrega: " + estadoEntrega +
                "\ncostoDomicilio: " + costoDomicilio +
                "\nvalor total: $" + calcularValorTotal();
    }
}
