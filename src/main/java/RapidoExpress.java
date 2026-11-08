public class RapidoExpress implements ServicioDomicilio{

    @Override
    public boolean aprobarDistancia(double distanciaKm) {
        if (distanciaKm <= 12){
            return true;
        } else{
            return false;
        }
    }

    @Override
    public double calcularCostoEnvio(double distanciaKm) {
        return 5000+(1500*distanciaKm);
    }
}
