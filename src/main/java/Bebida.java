public class Bebida extends Producto{
    private int volumen;
    private double temperaturaServicio;
    private TipoTemperatura tipoTemperatura;
    private boolean preparacionEspecial;

    public Bebida(String codigo, String nombre, String descripcion, double precioBase, int tiempoPreparacion, Restaurante restaurante, int volumen, double temperaturaServicio, TipoTemperatura tipoTemperatura, boolean preparacionEspecial) {
        super(codigo, nombre, descripcion, precioBase, tiempoPreparacion, restaurante);
        this.volumen = volumen;
        this.temperaturaServicio = temperaturaServicio;
        this.tipoTemperatura = tipoTemperatura;
        this.preparacionEspecial = preparacionEspecial;
    }

    public int getVolumen() {
        return volumen;
    }

    public void setVolumen(int volumen) {
        this.volumen = volumen;
    }

    public double getTemperaturaServicio() {
        return temperaturaServicio;
    }

    public void setTemperaturaServicio(double temperaturaServicio) {
        this.temperaturaServicio = temperaturaServicio;
    }

    public TipoTemperatura getTipoTemperatura() {
        return tipoTemperatura;
    }

    public void setTipoTemperatura(TipoTemperatura tipoTemperatura) {
        this.tipoTemperatura = tipoTemperatura;
    }

    public boolean isPreparacionEspecial() {
        return preparacionEspecial;
    }

    public void setPreparacionEspecial(boolean preparaciónEspecial) {
        this.preparacionEspecial = preparaciónEspecial;
    }

    @Override
    public String toString() {
        return "Bebida{" +super.toString()+
                "volumen=" + volumen +
                ", temperaturaServicio=" + temperaturaServicio +
                ", tipoTemperatura=" + tipoTemperatura +
                ", preparaciónEspecial=" + preparacionEspecial +
                '}';
    }

    @Override
    public double calcularPrecioVenta() {
        double precio = getPrecioBase() * 1.10;
        if (preparacionEspecial) {
            precio += 3000;
        }
        return precio;
    }

    @Override
    public boolean validarCondicionesCalidad() {
        return false;
    }
}
