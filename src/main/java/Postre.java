public class Postre extends Producto{
    private double peso;
    private double temperaturaAlmacenamiento;
    private boolean requiereRefrigeracion;

    public Postre(String codigo, String nombre, String descripcion, double precioBase, int tiempoPreparacion, Restaurante restaurante, double peso, double temperaturaAlmacenamiento, boolean requiereRefrigeracion) {
        super(codigo, nombre, descripcion, precioBase, tiempoPreparacion, restaurante);
        this.peso = peso;
        this.temperaturaAlmacenamiento = temperaturaAlmacenamiento;
        this.requiereRefrigeracion = requiereRefrigeracion;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getTemperaturaAlmacenamiento() {
        return temperaturaAlmacenamiento;
    }

    public void setTemperaturaAlmacenamiento(double temperaturaAlmacenamiento) {
        this.temperaturaAlmacenamiento = temperaturaAlmacenamiento;
    }

    public boolean isRequiereRefrigeracion() {
        return requiereRefrigeracion;
    }

    public void setRequiereRefrigeracion(boolean requiereRefrigeracion) {
        this.requiereRefrigeracion = requiereRefrigeracion;
    }

    @Override
    public String toString() {
        return "Postre{" +super.toString()+
                "peso=" + peso +
                ", temperaturaAlmacenamiento=" + temperaturaAlmacenamiento +
                ", requiereRefrigeracion=" + requiereRefrigeracion +
                '}';
    }

    @Override
    public double calcularPrecioVenta() {
        double precio = getPrecioBase() * 1.15;
        if (requiereRefrigeracion) {
            precio += getPrecioBase() * 0.05;
        }
        return precio;
    }

    @Override
    public boolean validarCondicionesCalidad() {
        boolean pesoValido = (peso >= 80 && peso <= 500);
        boolean temperaturaValida = false;

        if (requiereRefrigeracion) {
            temperaturaValida = (temperaturaAlmacenamiento >= 0 && temperaturaAlmacenamiento <= 6);
        } else {
            temperaturaValida = (temperaturaAlmacenamiento >= 15 && temperaturaAlmacenamiento <= 30);
        }

        return pesoValido && temperaturaValida;
    }
}
