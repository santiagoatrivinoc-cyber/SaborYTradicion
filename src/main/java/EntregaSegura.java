public class EntregaSegura implements ServicioDomicilio {

    @Override
    public boolean aceptarRecorrido(double distanciaKm) {
        return distanciaKm > 0 && distanciaKm <= 20.0;
    }

    @Override
    public double calcularCostoEnvio(double distanciaKm) {
        if (!aceptarRecorrido(distanciaKm)) {
            throw new IllegalArgumentException("EntregaSegura no acepta recorridos superiores a 20 km.");
        }

        double costo = 7000.0 + (1000.0 * distanciaKm);
        if (distanciaKm > 5.0) {
            costo += 3000.0;
        }
        return costo;
    }

    @Override
    public String getNombreEmpresa() {
        return "EntregaSegura";
    }
}