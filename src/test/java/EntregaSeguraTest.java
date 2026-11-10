import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EntregaSeguraTest {

    EntregaSegura entregaSegura = new EntregaSegura();
    @Test
    void aceptaLaDistanciaValida() {
        assertTrue(entregaSegura.aceptarRecorrido(12));
        assertTrue(entregaSegura.aceptarRecorrido(4));
        assertTrue(entregaSegura.aceptarRecorrido(20));
    }
    @Test
    void rechazaLaDistanciaInvalida() {
        assertFalse(entregaSegura.aceptarRecorrido(-3));
        assertFalse(entregaSegura.aceptarRecorrido(21));
        assertFalse(entregaSegura.aceptarRecorrido(0));
    }

    @Test
    void calculaELCostoDeEnvioDeUnReccoridoSinRecargaAdicional() {
        //7000 + (1000 * 2) = 9000
        double costo1 = entregaSegura.calcularCostoEnvio(2);
        assertEquals(9000, costo1);

        //7000 + (1000 * 3.67) = 10670
        double costo2 = entregaSegura.calcularCostoEnvio(3.67);
        assertEquals(10670, costo2);

        //7000 + (1000 * 5) = 12000
        double costo3 = entregaSegura.calcularCostoEnvio(5);
        assertEquals(12000, costo3);
    }
    @Test
    void calculaELCostoDeEnvioDeUnReccoridoConRecargaAdicional() {
        //7000 + (1000 * 6) + 3000 = 16000
        double costo1 = entregaSegura.calcularCostoEnvio(6);
        assertEquals(16000, costo1);

        //7000 + (1000 * 14.79) + 3000 = 24790
        double costo2 = entregaSegura.calcularCostoEnvio(14.79);
        assertEquals(24790, costo2);

        //7000 + (1000 * 20) + 3000 =
        double costo3 = entregaSegura.calcularCostoEnvio(20);
        assertEquals(30000, costo3);
    }

    @Test
    void getNombreEmpresa() {
        assertEquals(entregaSegura.getNombreEmpresa(), "EntregaSegura");
    }

}