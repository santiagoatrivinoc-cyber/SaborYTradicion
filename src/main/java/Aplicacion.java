import java.util.Scanner;

public class Aplicacion {
    public static void main(String[] args){

        Restaurante SaborYTradicion = new Restaurante("Sabor & Tradicion", "900123456-7", "Carrera 14 # 20-35, Armenia, Quindío");
        Scanner sc = new Scanner(System.in);
        int opcion;
        do{
            System.out.println("\n======        MENU        ======");
            System.out.println("====== Sabor & Tradición ======");


            System.out.println("\n--- Escoja una de las siguientes opciones:  ---");

            System.out.println("1.  Registrar Productos");
            System.out.println("2.  Registrar Clientes");
            System.out.println("3.  Calcular  Precio de Venta de los Productos");
            System.out.println("4.  Verificar Condiciones de Calidad de Productos");
            System.out.println("5.  Consultar Productos Disponibles");
            System.out.println("6.  Registrar Pedidos");
            System.out.println("7.  Gestionar Domicilios");
            System.out.println("8.  Confirmar Pedidos");
            System.out.println("9.  Calcular Valor Total de un Pedido");
            System.out.println("10. Consultar Pedidos");
            System.out.println("11. Actualizar Estado Pedidos");
            System.out.println("12. Consultar Estado Pedidos");
            System.out.println("13. Generar Reporte de Ventas");

            System.out.println("\n0. Salir");

            System.out.print("\nSeleccione la opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion){
                case 1:
                    //Registrar productos en el menú del restaurante, incluyendo platos fuertes,
                    // bebidas y postres, evitando códigos duplicados.


                    break;
                case 2:
                    //Registrar clientes, verificando que no existan documentos de identidad repetidos.

                    break;
                case 3:
                    //Calcular el precio de venta de los productos, aplicando los incrementos y recargos
                    // correspondientes según sus características.

                    break;
                case 4:
                    //Verificar las condiciones de calidad de los productos, determinando cuáles cumplen
                    // los requisitos establecidos y cuáles son rechazados.

                    break;
                case 5:
                    //Consultar los productos disponibles en el menú, mostrando su información y precio de venta.

                    break;
                case 6:
                    //Registrar pedidos de clientes, permitiendo seleccionar varios productos y sus
                    // respectivas cantidades, sin confirmar todavía la compra.

                    break;
                case 7:
                    //Gestionar servicios de domicilio, seleccionando RápidoExpress o EntregaSegura,
                    // verificando la distancia permitida y calculando el costo correspondiente.

                    break;
                case 8:
                    //Confirmar pedidos, verificando que el cliente esté registrado, los productos
                    // pertenezcan al menú, las cantidades sean válidas y todos los productos cumplan
                    // las condiciones de calidad. Para los domicilios, también se debe verificar la aceptación del servicio.

                    break;
                case 9:
                    //Calcular el valor total de un pedido, considerando los productos
                    // solicitados y el costo del domicilio cuando corresponda.

                    break;
                case 10:
                    //Consultar los pedidos realizados por un cliente, mostrando los productos adquiridos,
                    // las cantidades, los valores y el estado de cada pedido.

                    break;
                case 11:
                    //Actualizar el estado de los pedidos, considerando los estados pendiente,
                    // confirmado, entregado y cancelado, respetando las condiciones establecidas.

                    break;
                case 12:
                    //Consultar el estado de los pedidos, considerando los estados pendiente,
                    // confirmado, entregado y cancelado, respetando las condiciones establecidas.

                    break;
                case 13:
                    //Generar reportes de ventas, calculando los ingresos del restaurante, identificando
                    // el producto con mayor cantidad de unidades vendidas y la categoría que genera
                    // mayores ingresos, sin incluir los valores cobrados por los domicilios.

                    break;



            }

        }while (opcion != 0);

    }
}
