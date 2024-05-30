import Services.ISistemaGestorDeMazo;
import Utils.Instalador;
import ucn.StdIn;
import ucn.StdOut;

import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException {
        ISistemaGestorDeMazo instaladorMazo = new Instalador().instalarSistema();
        instaladorMazo.cargarDatos();
        menuInicio(instaladorMazo);

    }

    public static void menuInicio(ISistemaGestorDeMazo instaladorMazo) throws IOException {

        while (true) {
            StdOut.println("╔══════════════════════════════════╗");
            StdOut.println("║     Bienvenido al deck builder   ║");
            StdOut.println("╚══════════════════════════════════╝");
            StdOut.println("╔══════════════════════════════════╗");
            StdOut.println("║         ¿Qué deseas hacer?       ║");
            StdOut.println("║       1. Iniciar sesión          ║");
            StdOut.println("║       2. Registrarse             ║");
            StdOut.println("║       3. Salir                   ║");
            StdOut.println("╚══════════════════════════════════╝");
            String opcion = StdIn.readString();
            switch (opcion) {
                case "1" -> iniciarSecion(instaladorMazo);
                case "2" -> registrarse(instaladorMazo);
                case "3" -> guardarRegistro(instaladorMazo);

                default -> System.out.println("Ingrese opcion valida");
            }
        }
    }
    public static void guardarRegistro(ISistemaGestorDeMazo instaladorMazo) throws IOException {
        instaladorMazo.guardarRegistroUsuario();
        instaladorMazo.exportar();
        System.exit(0);
    }
    public static void iniciarSecion(ISistemaGestorDeMazo instaladorMazo) throws IOException {
        System.out.println("Ingrese sus datos porfavor");
        System.out.print("Usuario: ");
        String nombreUsuario = StdIn.readLine();
        System.out.print("Contrasenia :");
        String contrasenia = StdIn.readLine();
        if (!instaladorMazo.iniciarSesion(nombreUsuario, contrasenia)) {
            menuInicio(instaladorMazo);
        }
        System.out.println("Hola denuevo " + nombreUsuario);
        menuPrincipal(instaladorMazo);
    }

    public static void registrarse(ISistemaGestorDeMazo instaladorMazo) throws IOException {
        System.out.println("Ingrese sus datos porfavor");
        System.out.print("Usuario: ");
        String nombreUsuario = StdIn.readLine();
        System.out.print("Contrasenia :");
        String contrasenia = StdIn.readLine();
        instaladorMazo.registrarUsuario(nombreUsuario, contrasenia);
        System.out.println("Gracias por registrarse " + nombreUsuario);
        menuInicio(instaladorMazo);
    }

    public static void menuPrincipal(ISistemaGestorDeMazo instaladorMazo) throws IOException {
        while (true) {
            StdOut.println("╔══════════════════════════════════╗");
            StdOut.println("║    Menu de construccion          ║");
            StdOut.println("╚══════════════════════════════════╝");
            StdOut.println("╔══════════════════════════════════╗");
            StdOut.println("║         ¿Qué deseas hacer?       ║");
            StdOut.println("║       1. Construir el mazo       ║");
            StdOut.println("║       2. ver mis mazos           ║");
            StdOut.println("║       3. Buscar Carta            ║");
            StdOut.println("║       5. Salir                   ║");
            StdOut.println("╚══════════════════════════════════╝");
            String opcion = StdIn.readString();
            switch (opcion) {
                case "1":
                    menuConstruirMazo(instaladorMazo);
                    break;
                case "2":
                    verMazos(instaladorMazo);
                    break;
                case "3":
                    buscardeCarta(instaladorMazo);
                    break;
                case "5":
                    menuInicio(instaladorMazo);
                    break;
                default:
                    StdOut.println("Ingrese opcion valida");
                    menuPrincipal(instaladorMazo);
                    break;
            }
        }
    }
    public static void menuConstruirMazo(ISistemaGestorDeMazo instaladorMazo) throws IOException {  //primero verificar la cantidad de masos del usuario
        while (true) {
            StdOut.println("╔══════════════════════════════════╗");                 //verificar si es primera vez que entra
            StdOut.println("║  Menu de construccion de Mazos   ║");                 //verificar si acaba de crear un mazo permitirle modificar ese mazo
            StdOut.println("╚══════════════════════════════════╝");                 //
            StdOut.println("╔══════════════════════════════════╗");
            StdOut.println("║       ¿Ingrese una opcion?       ║");
            StdOut.println("║       1. Crear mazo nuevo        ║");
            StdOut.println("║       2. Modificar uno existente ║");
            StdOut.println("║       3. volver                  ║");
            StdOut.println("╚══════════════════════════════════╝");

            String opcion = StdIn.readString();
            switch (opcion) {
                case "1":
                    validarConstruirmazo(instaladorMazo);
                    break;
                case "2":
                    modificarMazo(instaladorMazo);
                    break;
                case "3":
                    menuPrincipal(instaladorMazo);
                    break;
                default:
                    StdOut.println("Ingrese opcion valida");
                    menuConstruirMazo(instaladorMazo);
                    break;
            }
        }
    }
    public static void validarConstruirmazo(ISistemaGestorDeMazo instaladorMazo) throws IOException {
        if (instaladorMazo.comprobarCreacionMazo()) {
            StdOut.println("Estimado usuario,usted ha registrado sus cuatro mazo, por favor vaya a la seccion modificar mazo");
            menuConstruirMazo(instaladorMazo);
        } else {
            instaladorMazo.comprobarCreacionMazo();
            construirMazo(instaladorMazo);
        }
    }

    public static void construirMazo(ISistemaGestorDeMazo instaladorMazo) throws IOException {
        while (true) {
            StdOut.println("╔══════════════════════════════════╗");
            StdOut.println("║  Menu de construccion de Mazos   ║");
            StdOut.println("╚══════════════════════════════════╝");
            StdOut.println("╔══════════════════════════════════╗");
            StdOut.println("║       ¿Ingrese una opcion?       ║");
            StdOut.println("║       1. Añadir carta            ║");
            StdOut.println("║       2. Eliminar Carta          ║");
            StdOut.println("║       3. Buscar Carta            ║");
            StdOut.println("║       4. Modificar sideboard     ║");
            StdOut.println("║       5. volver                  ║");
            StdOut.println("╚══════════════════════════════════╝");
            boolean mazo = true;
            boolean esSidedeck=false;
            boolean menuAnterior = true;
            String opcion = StdIn.readString();
            switch (opcion) {
                case "1":
                    añadirCarta(instaladorMazo, mazo,esSidedeck);
                    break;
                case "2":
                    eliminarCarta(instaladorMazo,esSidedeck);
                    break;
                case "3":
                    buscardeCarta(instaladorMazo);
                    break;
                case "4":
                    modificarSidedeck(instaladorMazo, menuAnterior);
                    break;
                case "5":
                    menuConstruirMazo(instaladorMazo);
                    break;
                default:
                    StdOut.println("Ingrese opcion valida");
                    construirMazo(instaladorMazo);
            }
        }
    }
    public static void modificarMazo(ISistemaGestorDeMazo instaladorMazo) throws IOException {

        if (!instaladorMazo.comprobarListaModificada()) {
            while (true) {
                StdOut.println("╔══════════════════════════════════╗");
                StdOut.println("║  Menu de Modificacion de Mazos   ║");
                StdOut.println("╚══════════════════════════════════╝");
                StdOut.println("╔══════════════════════════════════╗");
                StdOut.println("║       ¿Ingrese una opcion?       ║");
                StdOut.println("║       1. Añadir carta            ║");
                StdOut.println("║       2. Eliminar Carta          ║");
                StdOut.println("║       3. Buscar Carta            ║");
                StdOut.println("║       4. Modificar sideboard     ║");
                StdOut.println("║       5. volver                  ║");
                StdOut.println("╚══════════════════════════════════╝");

                String opcion = StdIn.readString();
                boolean mazo = false;
                boolean esSidedeck=false;
                boolean menuAnterior = false;
                switch (opcion) {
                    case "1":
                        añadirCarta(instaladorMazo, mazo,esSidedeck);
                        break;
                    case "2":
                        eliminarCarta(instaladorMazo,esSidedeck);
                        break;
                    case "3":
                        buscardeCarta(instaladorMazo);
                        break;
                    case "4":
                        modificarSidedeck(instaladorMazo, menuAnterior);
                        break;
                    case "5":
                        menuConstruirMazo(instaladorMazo);
                        break;
                    default:
                        StdOut.println("Ingrese opcion valida");
                        break;
                }
            }
        }
        if (instaladorMazo.comprobarListaModificada()) {
            StdOut.println("No hay masos disponibles actualmente,intente en otro momento");
            StdOut.println("...Redirigiendo al menu crear mazo");
            StdOut.println("...");
            StdOut.println("...");
            StdOut.println("...");
            StdOut.println("...");
            validarConstruirmazo(instaladorMazo);
        }
    }

    public static void modificarSidedeck(ISistemaGestorDeMazo instaladorMazo,boolean menuAnterior) throws IOException {

        if (!instaladorMazo.comprobarListaModificada()) {
            while (true) {
                StdOut.println("╔══════════════════════════════════╗");
                StdOut.println("║  Menu de Sideboard               ║");
                StdOut.println("╚══════════════════════════════════╝");
                StdOut.println("╔══════════════════════════════════╗");
                StdOut.println("║       ¿Ingrese una opcion?       ║");
                StdOut.println("║       1. Añadir carta            ║");
                StdOut.println("║       2. Eliminar Carta          ║");
                StdOut.println("║       3. Buscar Carta            ║");
                StdOut.println("║       4. volver                  ║");
                StdOut.println("╚══════════════════════════════════╝");

                String opcion = StdIn.readString();
                boolean mazo = false;
                boolean esSidedeck=true;
                switch (opcion) {
                    case "1":
                        añadirCarta(instaladorMazo, mazo,esSidedeck);
                        break;
                    case "2":
                        eliminarCarta(instaladorMazo,esSidedeck);
                        break;
                    case "3":
                        buscardeCarta(instaladorMazo);
                        break;
                    case "4":
                       if(menuAnterior){
                           construirMazo(instaladorMazo);
                       }
                        if(!menuAnterior){
                            modificarMazo(instaladorMazo);
                        }

                        break;
                    default:
                        StdOut.println("Ingrese opcion valida");
                        break;
                }
            }
        }
        if (instaladorMazo.comprobarListaModificada()) {
            StdOut.println("No hay masos disponibles actualmente,intente en otro momento");
            StdOut.println("...Redirigiendo al menu crear mazo");
            StdOut.println("...");
            StdOut.println("...");
            StdOut.println("...");
            StdOut.println("...");
            validarConstruirmazo(instaladorMazo);
        }
    }

    public static void verMazos(ISistemaGestorDeMazo instaladorMazo) throws IOException {
        while (true) {
            StdOut.println("╔══════════════════════════════════╗");
            StdOut.println("║            Lista de mazos        ║"); //ni idea como agregar mas mazos
            StdOut.println("╚══════════════════════════════════╝");
            StdOut.println("╔══════════════════════════════════╗");
            StdOut.println("║       ¿Ingrese una opcion?       ║");
            StdOut.println("║       1. Mazo 1                  ║");
            StdOut.println("║       2. Mazo 2                  ║");
            StdOut.println("║       3. Mazo 3                  ║");
            StdOut.println("║       4. Mazo 4                  ║");
            StdOut.println("║       5. volver                  ║");
            StdOut.println("╚══════════════════════════════════╝");


            String mazoReferencia=" ";
            String mazoSeleccionado = StdIn.readString();
            switch (mazoSeleccionado) {
                case "1":
                    mazoReferencia = "mazo1";
                    break;
                case "2":
                    mazoReferencia = "mazo2";
                    break;
                case "3":
                    mazoReferencia = "mazo3";
                    break;
                case "4":
                    mazoReferencia = "mazo4";
                    break;
                default:
                    StdOut.println("Opción inválida");
                    break;
            }

            StdOut.println("╔══════════════════════════════════╗");
            StdOut.println("║ Desea ver el mazo o su sidedeck  ║");
            StdOut.println("╚══════════════════════════════════╝");
            StdOut.println("╔══════════════════════════════════╗");
            StdOut.println("║       ¿Ingrese una opcion?       ║");
            StdOut.println("║       1. mazo                    ║");
            StdOut.println("║       2. sidedeck                ║");
            StdOut.println("║       3. volver                  ║");
            StdOut.println("╚══════════════════════════════════╝");
            String eleccion = StdIn.readLine();
            boolean eleccionMS;
            switch (eleccion) {
                case "1":eleccionMS=true;
                instaladorMazo.mostrarmazos(eleccionMS,mazoReferencia);
                break;
                case "2":eleccionMS=false;
                    instaladorMazo.mostrarmazos(eleccionMS,mazoReferencia);
                    break;
                case "3":
                    menuPrincipal(instaladorMazo);
                    break;
                default:
                    StdOut.println("Opción inválida");
                    break;
            }
        }
    }

    public static void buscardeCarta(ISistemaGestorDeMazo instaladorMazo) throws IOException {
        while (true) {
            StdOut.println("╔══════════════════════════════════╗");
            StdOut.println("║          Busqueda de carta       ║"); //ni idea como agregar mas mazos
            StdOut.println("╚══════════════════════════════════╝");
            StdOut.println("╔══════════════════════════════════╗");
            StdOut.println("║       ¿Ingrese una opcion?       ║");
            StdOut.println("║       1. Mostrar  todas las      ║");
            StdOut.println("║          cartas                  ║");
            StdOut.println("║       2. Busqueda de cartas      ║");
            StdOut.println("║       3. Busqueda de tierras     ║");
            StdOut.println("║       5. volver                  ║");
            StdOut.println("╚══════════════════════════════════╝");
            String opcion = StdIn.readString();
            switch (opcion) {
                case "1":
                    instaladorMazo.mostrarcarta();
                    break;
                case "2":
                    buscarCarta(instaladorMazo);
                    break;
                case "3":
                    buscarTierra(instaladorMazo);
                    break;
                case "5":
                    menuInicio(instaladorMazo);
                    break;
                default:
                    StdOut.println("Ingrese opcion valida");
                    buscardeCarta(instaladorMazo);
                    break;
            }

        }
    }
    public static void buscarCarta(ISistemaGestorDeMazo instaladorMazo) throws IOException {

        StdOut.println("╔══════════════════════════════════╗");
        StdOut.println("║  Ingrese la carta buscada        ║");
        StdOut.println("╚══════════════════════════════════╝");
        String cardName = StdIn.readLine();
        String imprimir = instaladorMazo.imprimirCarta(cardName);
        System.out.println(imprimir);
        buscardeCarta(instaladorMazo);
    }

    public static void buscarTierra(ISistemaGestorDeMazo instaladorMazo) throws IOException {
        StdOut.println("╔═══════════════════════════════════╗");
        StdOut.println("║     Ingrese la Tierra buscada     ║");
        StdOut.println("╚═══════════════════════════════════╝");
        String landCardName = StdIn.readLine();
        String imprimir = instaladorMazo.imprimirtTierra(landCardName);
        System.out.println(imprimir);
        buscardeCarta(instaladorMazo);
    }

    public static void añadirCarta(ISistemaGestorDeMazo instaladorMazo, boolean mazo,boolean esSidedeck) {
        boolean aux = false;
        while (!aux) {
            StdOut.println("╔══════════════════════════════════╗");
            StdOut.println("║  Ingrese la carta a añadir        ║");
            StdOut.println("╚══════════════════════════════════╝");
            String carta = StdIn.readString();
            aux = instaladorMazo.agregarnuevaCarta(carta, mazo,esSidedeck);
            if (!aux) {
                StdOut.println("╔═══════════════════════════════════════════════╗");
                StdOut.println("║  carta no encontrada,intente nuevamente       ║");
                StdOut.println("╚═══════════════════════════════════════════════╝");
                // 25-05-2024 Para evitar que se repita mas de una vez se debe modificar la logica del sistema. sugiero dejarlo asi.

            }
        }
    }

    public static void eliminarCarta(ISistemaGestorDeMazo instaladorMazo,boolean sideDeck) throws IOException {
        boolean aux = false;
        if (!instaladorMazo.comprobarListaModificada()) {
            while (!aux) {
                StdOut.println("╔══════════════════════════════════╗");
                StdOut.println("║  Ingrese la carta a eliminar     ║");
                StdOut.println("╚══════════════════════════════════╝");
                String carta = StdIn.readString();
               if(!sideDeck) {
                   aux = instaladorMazo.eliminarCarta(carta);
               }
               if(sideDeck){
                   aux=instaladorMazo.eliminarCartaSidedeck(carta);
               }
                if (!aux) {
                    StdOut.println("╔═══════════════════════════════════════════════╗");
                    StdOut.println("║  carta no encontrada,intente nuevamente       ║");
                    StdOut.println("╚═══════════════════════════════════════════════╝");
                    // 25-05-2024 Para evitar que se repita mas de una vez se debe modificar la logica del sistema. sugiero dejarlo asi
                }
            }
        }
        if (instaladorMazo.comprobarListaModificada()) {
            StdOut.println("No hay masos disponibles actualmente,intente en otro momento");
            construirMazo(instaladorMazo);
        }
    }

}

