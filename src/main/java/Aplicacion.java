import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

;
public class Aplicacion {
    private static Pedido pedidoActual = null;


    public static void main(String[] args){

        Restaurante restaurante = new Restaurante("Sabor & Tradicion", "900123456-7", "Carrera 14 # 20-35, Armenia, Quindío");
        EntregaSegura entregaSegura = new EntregaSegura();
        RapidoExpress rapidoExpress = new RapidoExpress();
        Scanner sc = new Scanner(System.in);
        int opcion;
        do {
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

            switch (opcion) {
                case 1: {
                    //Registrar productos en el menú del restaurante, incluyendo platos fuertes,
                    // bebidas y postres, evitando códigos duplicados.
                    System.out.println("-----Registrar producto-----");
                    System.out.println("Ingresa el codigo del producto: ");
                    String codigoProducto = sc.nextLine();

                    System.out.println("Ingresa el nombre del producto: ");
                    String nombreProducto = sc.nextLine();

                    System.out.println("Ingresa la descripción del producto: ");
                    String descripcionProducto = sc.nextLine();

                    System.out.println("Ingresa el precio base producto: ");
                    double precioBaseProducto = sc.nextDouble();
                    sc.nextLine();

                    System.out.println("Ingresa el tiempo de preparación producto (en minutos): ");
                    int tiempoPreparacionProducto = sc.nextInt();
                    sc.nextLine();

                    System.out.println("Categoría: 1. Plato fuerte  2. Bebida  3. Postre");
                    int categoria = sc.nextInt();
                    sc.nextLine();

                    Producto producto = null;

                    switch (categoria) {
                        case 1:
                            System.out.println("Ingresa el peso en gramos: ");
                            double pesoPlato = sc.nextDouble();
                            sc.nextLine();

                            System.out.println("Elige el tipo de preparación: 1. Asado  2. Frito  3. Horneado  4. A la plancha");
                            int opcionPrep = sc.nextInt();
                            sc.nextLine();
                            if (opcionPrep < 1 || opcionPrep > 4) {
                                System.out.println("Tipo de preparación no válido.");
                                break;
                            }
                            TipoPreparacion tipoPrep = TipoPreparacion.values()[opcionPrep - 1];

                            System.out.println("¿Cocción aprobada? (true/false): ");
                            boolean coccionAprobada = sc.nextBoolean();
                            sc.nextLine();

                            producto = new PlatoFuerte(codigoProducto, nombreProducto, descripcionProducto,
                                    precioBaseProducto, tiempoPreparacionProducto, restaurante,
                                    pesoPlato, tipoPrep, coccionAprobada);

                            break;

                        case 2:
                            System.out.println("Ingresa el volumen en mililitros: ");
                            int volumen = sc.nextInt();
                            sc.nextLine();

                            System.out.println("Temperatura de servicio (°C): ");
                            double tempServicio = sc.nextDouble();
                            sc.nextLine();

                            System.out.println("Escoja el tipo de bebida: 1. Fría  2. Caliente");
                            int opcionTemp = sc.nextInt();
                            sc.nextLine();
                            if (opcionTemp < 1 || opcionTemp > 2) {
                                System.out.println("Tipo de temperatura no válido.");
                                break;
                            }
                            TipoTemperatura tipoTemp = TipoTemperatura.values()[opcionTemp - 1];

                            System.out.println("¿Requiere preparación especial? (true/false): ");
                            boolean prepEspecial = sc.nextBoolean();
                            sc.nextLine();

                            producto = new Bebida(codigoProducto, nombreProducto, descripcionProducto,
                                    precioBaseProducto, tiempoPreparacionProducto, restaurante,
                                    volumen, tempServicio, tipoTemp, prepEspecial);

                            break;

                        case 3:
                            System.out.println("Escriba el peso en gramos: ");
                            double pesoPostre = sc.nextDouble();
                            sc.nextLine();

                            System.out.println("Escriba la temperatura de almacenamiento (°C): ");
                            double tempAlmacenamiento = sc.nextDouble();
                            sc.nextLine();

                            System.out.println("¿Requiere refrigeración? (true/false): ");
                            boolean refrigeracion = sc.nextBoolean();
                            sc.nextLine();

                            producto = new Postre(codigoProducto, nombreProducto, descripcionProducto,
                                    precioBaseProducto, tiempoPreparacionProducto, restaurante,
                                    pesoPostre, tempAlmacenamiento, refrigeracion);
                            break;

                        default:
                            System.out.println("Categoría no válida.");
                    }

                    if (producto != null) {
                        if (restaurante.registrarProducto(producto)) {
                            System.out.println("Producto registrado correctamente.");
                        } else {
                            System.out.println("Error: ya existe un producto con ese código.");
                        }
                    }
                    break;
                }
                case 2: {
                    //Registrar clientes, verificando que no existan documentos de identidad repetidos.

                    System.out.println("-----Registrar cliente-----");

                    System.out.println("Ingresa el nombre del cliente: ");
                    String nombreCliente = sc.nextLine();

                    System.out.println("Ingresa el documento del cliente: ");
                    String documentoCliente = sc.nextLine();

                    System.out.println("Ingresa el teléfono del cliente: ");
                    String telefonoCliente = sc.nextLine();

                    System.out.println("Ingresa el correo del cliente: ");
                    String correoCliente = sc.nextLine();

                    System.out.println("Ingresa la dirección de residencia del cliente: ");
                    String direccionCliente = sc.nextLine();


                    Cliente cliente = new Cliente(nombreCliente, documentoCliente, telefonoCliente, correoCliente, direccionCliente, restaurante);

                    if (restaurante.registrarCliente(cliente)) {
                        System.out.println("Cliente registrado correctamente.");
                    } else {
                        System.out.println("Error: ya existe un cliente con ese documento.");
                    }

                    break;
                }
                case 3: {
                    //Calcular el precio de venta de los productos, aplicando los incrementos y recargos
                    // correspondientes según sus características.


                    System.out.println("-----Calcular precio de venta-----");
                    System.out.println("Ingresa el código del producto: ");
                    String codigoBuscado = sc.nextLine();

                    Producto encontrado = restaurante.buscarProducto(codigoBuscado);

                    if (encontrado != null) {
                        System.out.println("Precio base: $" + String.format("%.0f", encontrado.getPrecioBase()));
                        System.out.println("Precio de venta: $" + String.format("%.0f", encontrado.calcularPrecioVenta()));
                    } else {
                        System.out.println("No existe un producto con ese código.");
                    }
                    break;
                }
                case 4:
                    System.out.println("--- Verificar Condiciones de Calidad de Productos ---");

                    boolean hayIncumplidos = false;

                    for (Producto producto : restaurante.getMenu()) {

                        boolean cumple = producto.validarCondicionesCalidad();

                        if (cumple) {
                            System.out.println("[APROBADO] " + producto.getNombre() + " (" + producto.getCodigo() + ") cumple con las condiciones de calidad.");
                        } else {
                            System.out.println("[RECHAZADO] " + producto.getNombre() + " (" + producto.getCodigo() + ") NO cumple con las condiciones de calidad, se debe eliminar.");
                            hayIncumplidos = true;
                        }
                    }

                    if (!hayIncumplidos) {
                        System.out.println("\n¡Excelente! Todos los productos del menú cumplen con las condiciones de calidad.");
                    }
                    break;

                case 5: {
                    //Consultar los productos disponibles en el menú, mostrando su información y precio de venta.
                    System.out.println("--- Consultar Productos Disponibles ---");
                    restaurante.consultarProductosDisponibles();
                    break;
                }
                case 6: {
                    // Registrar pedidos de clientes, permitiendo seleccionar varios productos y sus
                    // respectivas cantidades, sin confirmar todavía la compra.
                    System.out.println("--- Registrar Pedidos ---");

                    System.out.println("Ingresa el numero de documento de identidad del cliente: ");
                    String documento = sc.nextLine();

                    Cliente cliente = restaurante.buscarCliente(documento);
                    if (cliente == null){
                        System.out.println("Cliente no encontrado.");
                        break;
                    }

                    System.out.println("¿Qué deseas pedir?(Ingresa el codigo de lo que desees)");
                    restaurante.consultarProductosDisponibles();
                    String codigo = sc.nextLine();

                    Producto producto = restaurante.buscarProducto(codigo);
                    if (producto == null){
                        System.out.println("Producto no encontrado.");
                        break;
                    }

                    System.out.println("¿Cuántos " + producto.getNombre() + " deseas?");
                    int cantidadProductos = sc.nextInt();
                    sc.nextLine();

                    if (cantidadProductos < 1) {
                        System.out.println("La cantidad de productos debe ser como minimo 1");
                        break;
                    }


                    DetallePedido detallePedido = new DetallePedido(producto, cantidadProductos);

                    String codigoPedido = "";
                    boolean codigoPedidoExiste = false;
                    do {
                        System.out.println("Ingresa el codigo del pedido (8 caracteres):");
                        codigoPedido = sc.nextLine();

                        if (codigoPedido.length() != 8){
                            System.out.println("El codigo debe de ser de 8 caracteres exactos");
                            codigoPedidoExiste = true;  continue;
                        }

                        codigoPedidoExiste = cliente.verificarPedido(codigoPedido);

                        if(codigoPedidoExiste){
                            System.out.println("El codigo que ingresaste ya existe, ingresa un codigo diferente");
                        }
                    } while(codigoPedidoExiste);

                    ModalidadEntrega modalidadEntrega = null;
                    int opcionEntrega = 0;
                    while(opcionEntrega < 1 || opcionEntrega > 2) {
                        System.out.println("Cual es la modalidad de entrega:\n1. Para consumo en el restaurante\n2. Para envio a domicilio");
                        opcionEntrega = sc.nextInt();
                        sc.nextLine();

                        switch (opcionEntrega) {
                            case 1: {
                                modalidadEntrega = ModalidadEntrega.CONSUMO_RESTAURANTE;
                                break;
                            }
                            case 2: {
                                modalidadEntrega = ModalidadEntrega.DOMICILIO;
                                break;
                            }
                            default: {
                                System.out.println("La opcion que elegiste no existe, ingresa una opcion disponible:");
                            }
                        }
                    }

                    ServicioDomicilio servicioDomicilio = null;
                    if(modalidadEntrega == ModalidadEntrega.DOMICILIO){
                        int opcionDomicilio = 0;
                        while(opcionDomicilio < 1 || opcionDomicilio > 2){
                            System.out.println("Elige una empresa para el domicilio:\n1. Entrega Segura\n2. Rapido Express");
                            opcionDomicilio = sc.nextInt();
                            sc.nextLine();

                            switch (opcionDomicilio) {
                                case 1: {
                                    servicioDomicilio = entregaSegura;
                                    break;
                                }
                                case 2: {
                                    servicioDomicilio = rapidoExpress;
                                    break;
                                }
                                default: {
                                    System.out.println("La opcion que elegiste no existe, ingresa una opcion disponible:");
                                }
                            }
                        }
                    }

                    Pedido pedido = new Pedido(codigoPedido, LocalDateTime.now(), modalidadEntrega, cliente, servicioDomicilio);
                    pedido.setEstadoPedido(EstadoPedido.PENDIENTE);

                    pedidoActual = pedido;
                    System.out.println("El pedido ha sido registrado correctamente.");

                    break;
                }
                case 7: {

                    if (pedidoActual == null) {
                        System.out.println("No hay ningún pedido activo. Debe crear un pedido primero en el menú correspondiente.");
                        break;
                    }

                    System.out.println("\n--- GESTIÓN DE DOMICILIO ---");
                    System.out.print("Ingrese la distancia del recorrido en kilómetros (km): ");
                    double distancia = sc.nextDouble();

                    ServicioDomicilio rapido = new RapidoExpress();
                    ServicioDomicilio segura = new EntregaSegura();
                    System.out.println("Cotizaciones disponibles para " + distancia + " km:");

                    boolean rapidoAcepta = rapido.aceptarRecorrido(distancia);
                    boolean seguraAcepta = segura.aceptarRecorrido(distancia);

                    if (rapidoAcepta) {
                        System.out.println("1. " + rapido.getNombreEmpresa() + " - Costo: $" + rapido.calcularCostoEnvio(distancia));
                    } else {
                        System.out.println("1. " + rapido.getNombreEmpresa() + " - NO disponible (supera el límite de 12 km)");
                    }

                    if (seguraAcepta) {
                        System.out.println("2. " + segura.getNombreEmpresa() + " - Costo: $" + segura.calcularCostoEnvio(distancia));
                    } else {
                        System.out.println("2. " + segura.getNombreEmpresa() + " - NO disponible (supera el límite de 20 km)");
                    }

                    if (!rapidoAcepta && !seguraAcepta) {
                        System.out.println("\n Error: Ninguna empresa de domicilio acepta recorridos para la distancia ingresada.");
                        break;
                    }

                    System.out.print("\nSeleccione la empresa de transporte (1 o 2): ");
                    int eleccion = sc.nextInt();

                    if (eleccion == 1 && rapidoAcepta) {
                        pedidoActual.setEmpresaTransporte(rapido);
                        pedidoActual.setCostoDomicilio(rapido.calcularCostoEnvio(distancia));
                        System.out.println("Se asignó " + rapido.getNombreEmpresa() + " al pedido.");
                    } else if (eleccion == 2 && seguraAcepta) {
                        pedidoActual.setEmpresaTransporte(segura);
                        pedidoActual.setCostoDomicilio(segura.calcularCostoEnvio(distancia));
                        System.out.println("Se asigno " + segura.getNombreEmpresa() + " al pedido.");
                    } else {
                        System.out.println("Selección inválida o la empresa seleccionada no acepta el servicio.");
                    }

                    break;
                }

                case 8: {
                    //Confirmar pedidos, verificando que el cliente esté registrado, los productos
                    // pertenezcan al menú, las cantidades sean válidas y todos los productos cumplan
                    // las condiciones de calidad. Para los domicilios, también se debe verificar la aceptación del servicio.

                        System.out.println("--- Confirmar Pedidos ---");

                        if (pedidoActual == null) {
                            System.out.println(" No hay ningún pedido activo registrado para confirmar.");
                            break;
                        }

                        if (pedidoActual.getCliente() == null) {
                            System.out.println(" Error: El pedido no tiene un cliente asociado válido.");
                            break;
                        }

                        if (pedidoActual.getListaDetallesPedidos().isEmpty()) {
                            System.out.println("Error: El pedido no contiene productos.");
                            break;
                        }

                        boolean todoValido = true;

                        for (DetallePedido detalle : pedidoActual.getListaDetallesPedidos()) {
                            Producto producto = detalle.getProducto();

                            if (detalle.getCantidad() < 1) {
                                System.out.println("Error: La cantidad para el producto " + producto.getNombre() + " no es válida.");
                                todoValido = false;
                                break;
                            }

                            boolean perteneceAlMenu = restaurante.getMenu().contains(producto);
                            if (!perteneceAlMenu) {
                                System.out.println(" Error: El producto " + producto.getNombre() + " ya no pertenece al menú del restaurante.");
                                todoValido = false;
                                break;
                            }

                            if (!producto.validarCondicionesCalidad()) {
                                System.out.println("Error de Calidad: El producto " + producto.getNombre() + " NO cumple con las condiciones de calidad requeridas.");
                                todoValido = false;
                                break;
                            }
                        }

                        if (!todoValido) {
                            System.out.println("El pedido NO pudo ser confirmado debido a incumplimientos en las validaciones.");
                            break;
                        }

                        if (pedidoActual.getModalidadEntrega() == ModalidadEntrega.DOMICILIO) {
                            if (pedidoActual.getEmpresaTransporte() == null) {
                                System.out.println(" Error: El pedido es a domicilio pero no tiene asignada ninguna empresa de transporte.");
                                System.out.println("Por favor, gestione el domicilio primero en la opción 7.");
                                break;
                            }

                            System.out.println(" Empresa de transporte asociada: " + pedidoActual.getEmpresaTransporte().getNombreEmpresa());
                        }

                        pedidoActual.setEstadoPedido(EstadoPedido.CONFIRMADO);
                        System.out.println("Pedido confirmado con éxito");
                        System.out.println(pedidoActual.toString());

                        break;
                }

                case 9: {
                    //Calcular el valor total de un pedido, considerando los productos
                    // solicitados y el costo del domicilio cuando corresponda.
                    System.out.println("--- Calcular Valor Total de un Pedido ---");

                    System.out.println("Ingresa el codigo del pedido: ");
                    String codigoPedido = sc.nextLine();

                    Pedido pedido = restaurante.buscarPedido(codigoPedido);
                    if (pedido == null){
                        System.out.println("Este pedido no existe");
                        break;
                    }
                    double valorTotal = pedido.calcularValorTotal();
                    System.out.println("El valor total de este pedido es de: " + valorTotal);
                }
                    break;
                case 10: {
                    //Consultar los pedidos realizados por un cliente, mostrando los productos adquiridos,
                    // las cantidades, los valores y el estado de cada pedido.
                    System.out.println("--- Consultar Pedidos ---");

                    System.out.println("Ingresa el documento del cliente: ");
                    String documento = sc.nextLine();

                    Cliente cliente = restaurante.buscarCliente(documento);
                    cliente.mostrarHistorialPedidos();
                    System.out.println("---------------------------------------------------------");
                }
                    break;
                case 11: {

                    //Actualizar el estado de los pedidos, considerando los estados pendiente,
                    // confirmado, entregado y cancelado, respetando las condiciones establecidas.

                    System.out.println("-----Estado de pedidos-----");
                    System.out.println("Ingresa el código del pedido: ");
                    String codigoPedido = sc.nextLine();

                    Pedido pedidoBuscado = restaurante.buscarPedido(codigoPedido);

                    if (pedidoBuscado == null) {
                        System.out.println("No se encontró ningún pedido con el código " + codigoPedido + ".");
                        break;
                    }

                    System.out.println("Estado actual: " + pedidoBuscado.getEstadoPedido());
                    System.out.println("1. Marcar como entregado  2. Cancelar  3. Solo consultar");
                    int opcionEstado = sc.nextInt();
                    sc.nextLine();

                    switch (opcionEstado) {
                        case 1:
                            if (pedidoBuscado.marcarComoEntregado()) {
                                System.out.println("Pedido entregado correctamente.");
                            } else {
                                System.out.println("No se puede entregar: el pedido debe estar confirmado.");
                            }
                            break;
                        case 2:
                            if (pedidoBuscado.cancelarPedido()) {
                                System.out.println("Pedido cancelado.");
                            } else {
                                System.out.println("No se puede cancelar: el pedido ya fue entregado o cancelado.");
                            }
                            break;
                        case 3:
                            System.out.println("Estado del pedido: " + pedidoBuscado.getEstadoPedido());
                            break;
                        default:
                            System.out.println("Opción no válida.");
                    }
                    break;
                }

                case 12: {
                    //Consultar el estado de los pedidos, considerando los estados pendiente,
                    // confirmado, entregado y cancelado, respetando las condiciones establecidas.
                    System.out.println("--- Consultar Estado Pedidos ---");
                    restaurante.consultarPedidosCliente();



                }
                    break;
                case 13: {
                    System.out.println("--- REPORTE DE VENTAS ---");

                    List<Pedido> pedidos = restaurante.getListaPedidos();

                    if (pedidos == null) {
                        System.out.println("No hay pedidos registrados para generar el reporte.");
                        break;
                    }

                    double ingresosTotalesRestaurante = 0.0;

                    double ingresosPlatoFuerte = 0.0;
                    double ingresosBebida = 0.0;
                    double ingresosPostre = 0.0;

                    List<String> nombresProductos = new ArrayList<>();
                    List<Integer> unidadesProductos = new ArrayList<>();

                    boolean hayPedidosProcesados = false;

                    for (Pedido pedido : pedidos) {
                        if (pedido == null || pedido.getEstadoPedido() == EstadoPedido.CANCELADO) {
                            continue;
                        }

                        List<DetallePedido> detalles = pedido.getListaDetallesPedidos();
                        if (detalles != null) {
                            for (DetallePedido detalle : detalles) {
                                if (detalle != null) {
                                    hayPedidosProcesados = true;
                                    Producto producto = detalle.getProducto();
                                    int cantidad = detalle.getCantidad();
                                    double subtotalProducto = detalle.calcularSubtotal();

                                    ingresosTotalesRestaurante += subtotalProducto;

                                    String nombreProd = producto.getNombre();
                                    int indexEncontrado = -1;
                                    int k = 0;

                                    for (String nombreExistente : nombresProductos) {
                                        if (nombreExistente.equals(nombreProd)) {
                                            indexEncontrado = k;
                                            break;
                                        }
                                        k++;
                                    }

                                    if (indexEncontrado != -1) {
                                        int totalActual = unidadesProductos.get(indexEncontrado);
                                        unidadesProductos.set(indexEncontrado, totalActual + cantidad);
                                    } else {
                                        nombresProductos.add(nombreProd);
                                        unidadesProductos.add(cantidad);
                                    }

                                    if (producto instanceof PlatoFuerte) {
                                        ingresosPlatoFuerte += subtotalProducto;
                                    } else if (producto instanceof Bebida) {
                                        ingresosBebida += subtotalProducto;
                                    } else if (producto instanceof Postre) {
                                        ingresosPostre += subtotalProducto;
                                    }
                                }
                            }
                        }
                    }

                    if (!hayPedidosProcesados) {
                        System.out.println(" No hay pedidos válidos registrados para generar el reporte.");
                        break;
                    }

                    String productoMasVendido = "Ninguno";
                    int maxUnidades = 0;
                    int i = 0;
                    for (String nombre : nombresProductos) {
                        int unidades = unidadesProductos.get(i);
                        if (unidades > maxUnidades) {
                            maxUnidades = unidades;
                            productoMasVendido = nombre;
                        }
                        i++;
                    }

                    String categoriaMayorIngresos = "Plato Fuerte";
                    double maxIngresosCat = ingresosPlatoFuerte;

                    if (ingresosBebida > maxIngresosCat) {
                        maxIngresosCat = ingresosBebida;
                        categoriaMayorIngresos = "Bebida";
                    }
                    if (ingresosPostre > maxIngresosCat) {
                        maxIngresosCat = ingresosPostre;
                        categoriaMayorIngresos = "Postre";
                    }

                    System.out.println("\n========================================");
                    System.out.println("          INFORME FINANCIERO            ");
                    System.out.println("========================================");
                    System.out.println("Ingresos totales del restaurante: $" + ingresosTotalesRestaurante);
                    System.out.println("Producto más vendido: " + productoMasVendido + " (" + maxUnidades + " unidades)");
                    System.out.println("Categoría con mayores ingresos: " + categoriaMayorIngresos + " ($" + maxIngresosCat + ")");
                    System.out.println("----------------------------------------");
                    System.out.println("Desglose por categoría:");
                    System.out.println(" - Plato Fuerte: $" + ingresosPlatoFuerte);
                    System.out.println(" - Bebida: $" + ingresosBebida);
                    System.out.println(" - Postre: $" + ingresosPostre);
                    System.out.println("========================================");

                    break;
                }
            }

        }while (opcion != 0);

    }
}
