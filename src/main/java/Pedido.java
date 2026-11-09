import java.time.LocalDate;

public class Pedido {

    private String codigo;
    private LocalDate fecha;
    private ModalidadEntrega modalidadEntrega;
    private EstadoEntrega estadoEntrega;
    private double costoDomicilio;

    public Pedido(String codigo, LocalDate fecha, ModalidadEntrega modalidadEntrega, EstadoEntrega estadoEntrega, double costoDomicilio) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.modalidadEntrega = modalidadEntrega;
        this.estadoEntrega = estadoEntrega;
        this.costoDomicilio = costoDomicilio;
    }

    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public LocalDate getFecha() {
        return fecha;
    }
    public void setFecha(LocalDate fecha) {
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
