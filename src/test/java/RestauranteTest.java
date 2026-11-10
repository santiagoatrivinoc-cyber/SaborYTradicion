import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RestauranteTest {

    Restaurante restaurante = new Restaurante("Sabor y Tradicion", "21243222", "Armenia");

    Cliente cliente = new Cliente("Miguel Lopez", "1095552346", "3001234567", "miguel@gmail.com", "Calle 10 # 15-20", restaurante);

    Producto productoValido = new PlatoFuerte("PF01", "Bandeja Paisa", "Plato típico", 8000, 30, restaurante,450, TipoPreparacion.ASADO, true);
    Producto productoInvalido = new Bebida("BE01", "Jugo de Lulo", "Jugo natural", 4000, 5, restaurante, 300, 15.0, TipoTemperatura.FRIA, false);
    RapidoExpress rapidoExpress = new RapidoExpress();

    void implementarMetodosInicialesNecesarios(){
        restaurante.registrarCliente(cliente);
        restaurante.registrarProducto(productoValido);
        restaurante.registrarProducto(productoInvalido);
    }

    @Test
    void

    @Test
    void pruDeTest(){

    }

}