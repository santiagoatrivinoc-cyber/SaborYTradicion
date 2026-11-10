public class RapidoExpress implements ServicioDomicilio {

    @Override
    public boolean aceptarRecorrido(double distanciaKm) {
        return distanciaKm > 0 && distanciaKm <= 12.0;
    }

    @Override
    public double calcularCostoEnvio(double distanciaKm) {
        if (!aceptarRecorrido(distanciaKm)) {
            throw new IllegalArgumentException("RapidoExpress no acepta recorridos superiores a 12 km.");
        }
        return 5000.0 + (1500.0 * distanciaKm);
    }

    @Override
    public String getNombreEmpresa() {
        return "RapidoExpress";
    }
}