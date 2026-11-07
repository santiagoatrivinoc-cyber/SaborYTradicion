import java.time.LocalDate;

public class Compra {
    private String codigoCompra;
    private LocalDate fechaCompra;
    private double valorTotal;
    private MetodoPago metodoPago;

    public Compra(String codigoCompra, LocalDate fechaCompra, double valorTotal, MetodoPago metodoPago) {
        this.codigoCompra = codigoCompra;
        this.fechaCompra = fechaCompra;
        this.valorTotal = valorTotal;
        this.metodoPago = metodoPago;
    }

    public String getCodigoCompra() {
        return codigoCompra;
    }

    public void setCodigoCompra(String codigoCompra) {
        this.codigoCompra = codigoCompra;
    }

    public LocalDate getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(LocalDate fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public MetodoPago getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }

    @Override
    public String toString() {
        return "Compra{" +
                "codigoCompra='" + codigoCompra + '\'' +
                ", fechaCompra=" + fechaCompra +
                ", valorTotal=" + valorTotal +
                ", metodoPago=" + metodoPago +
                '}';
    }
}
