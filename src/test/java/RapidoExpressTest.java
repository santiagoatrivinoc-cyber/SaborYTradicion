import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RapidoExpressTest {

    RapidoExpress rapidoExpress = new RapidoExpress();

    @Test
    void aceptaLaDistanciaValida() {
        assertTrue(rapidoExpress.aceptarRecorrido(7));
        assertTrue(rapidoExpress.aceptarRecorrido(12));
        assertTrue(rapidoExpress.aceptarRecorrido(10.5));
    }
    @Test
    void rechazaLaDistanciaInvalida() {
        assertFalse(rapidoExpress.aceptarRecorrido(-3));
        assertFalse(rapidoExpress.aceptarRecorrido(13));
        assertFalse(rapidoExpress.aceptarRecorrido(0));
    }

    @Test
    void calculaELCostoDeEnvioDeUnReccoridoCorto() {
        //5000 + (11 * 1500) = 21500
        double costo1 = rapidoExpress.calcularCostoEnvio(11);
        assertEquals(21500, costo1);

        //5000 + (11.47 * 1500) = 22205
        double costo2 = rapidoExpress.calcularCostoEnvio(11.47);
        assertEquals(22205, costo2);

        //5000 + (12 * 1500) = 23000
        double costo3 = rapidoExpress.calcularCostoEnvio(12);
        assertEquals(23000, costo3);
    }

    @Test
    void getNombreEmpresa() {
        assertEquals(rapidoExpress.getNombreEmpresa(), "RapidoExpress");
    }

    //Solo fueron necesarios 4 Test
}