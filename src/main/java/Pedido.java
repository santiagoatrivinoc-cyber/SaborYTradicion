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

    public Pedido(String codigo, LocalDateTime fecha, ModalidadEntrega modalidadEntrega, EstadoEntrega estadoEntrega, double costoDomicilio, Cliente cliente, ServicioDomicilio empresaTransporte) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.modalidadEntrega = modalidadEntrega;
        this.estadoEntrega = estadoEntrega;
        this.costoDomicilio = costoDomicilio;
        this.cliente = cliente;
        listaDetallesPedidos = new ArrayList<>();
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
                detalle.setCantidad(detalle.getCantidad()+cantidad);
                return;
            }

        }
        this.listaDetallesPedidos.add(new DetallePedido(producto,cantidad));
    }

    public double calcularValorProductos(){
        return 0;
    }

    public double calcularValorTotal(){
        return 0;
    }

    public void marcarEntrega(){

    }

    public void marcarCancelacion(){

    }

    @Override
    public String toString() {
        return "----- Pedido -----" +
                "\ncodigo: " + codigo +
                "\nfecha: " + fecha +
                "\nmodalidadEntrega: " + modalidadEntrega +
                "\nestadoEntrega: " + estadoEntrega +
                "\ncostoDomicilio: " + costoDomicilio;
    }
}
