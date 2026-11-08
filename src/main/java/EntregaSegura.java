public class EntregaSegura implements ServicioDomicilio{

    @Override
    public boolean aprobarDistancia(double distanciaKm) {
        if (distanciaKm <= 20){
            return true;
        } else{
            return false;
        }
    }

    @Override
    public double calcularCostoEnvio(double distanciaKm) {
        double costoEnvio = 7000+(1000*distanciaKm);
        if(distanciaKm > 5){
            costoEnvio += 3000;
        }
        return costoEnvio;
    }
}
