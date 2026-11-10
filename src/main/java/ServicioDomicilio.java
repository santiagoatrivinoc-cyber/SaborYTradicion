public interface ServicioDomicilio {
    boolean aceptarRecorrido(double distanciaKm);
    double calcularCostoEnvio(double distanciaKm);
    String getNombreEmpresa();
}