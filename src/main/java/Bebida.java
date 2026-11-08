public class Bebida extends Producto{
    private int volumen;
    private double temperaturaServicio;
    private TipoTemperatura tipoTemperatura;

    public Bebida(String codigo, String nombre, String descripcion, double precioBase, int tiempoPreparacion, int volumen, double temperaturaServicio, TipoTemperatura tipoTemperatura) {
        super(codigo, nombre, descripcion, precioBase, tiempoPreparacion);
        this.volumen = volumen;
        this.temperaturaServicio = temperaturaServicio;
        this.tipoTemperatura = tipoTemperatura;
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

    @Override
    public String toString() {
        return "Bebida{" +super.toString()+
                "volumen=" + volumen +
                ", temperaturaServicio=" + temperaturaServicio +
                ", tipoTemperatura=" + tipoTemperatura +
                '}';
    }
}
