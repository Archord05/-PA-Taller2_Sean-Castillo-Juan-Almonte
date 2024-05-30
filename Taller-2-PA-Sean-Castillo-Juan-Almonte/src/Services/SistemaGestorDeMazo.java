package Services;

import Model.*;
import ucn.*;

import java.io.IOException;

public class SistemaGestorDeMazo implements ISistemaGestorDeMazo {
    String landList = "Land_List.txt";
    String cardList = "Card_List_txt.txt";
    String basededatos="Base de datos 1.txt";
    String basededatos2="Base de datos 2.txt";
    String basededatos3="Base de datos 3.txt";
    String basededatos4="Base de datos 4.txt";
    String basededatosidedeck="Base de datos sidedeck.txt";
    String registrodeusuarios="registro de usuarios.txt";
    private String usuarioActual;
    private String mazoReferencia;
    private ListaCarta listaCarta;
    private ListaMazo listaMazo;
    private ListaMazo listaMazo2;
    private ListaMazo listaMazo3;
    private ListaMazo listaMazo4;
    private ListaMazo listaSidedeck;
    private ListaUsuario listaUsuario;
    private ListaTierra listaTierra;

    /**
     * Constructor del sistema SistemaGestorDeMazo
     * @throws IOException Excepción para la lectura de archivos
     */
    public SistemaGestorDeMazo() throws IOException {
        this.listaCarta = new ListaCarta(999);
        this.listaUsuario = new ListaUsuario(999);
        this.listaMazo = new ListaMazo(999);
        this.listaMazo2 = new ListaMazo(999);
        this.listaMazo3 = new ListaMazo(999);
        this.listaMazo4 = new ListaMazo(999);
        this.listaSidedeck=new ListaMazo(999);
        this.listaTierra = new ListaTierra(999);
        lecturaListaCartas();
        lecturaListaTierras();

    }

    /**
     * Metodo que permite el registro del usuario por parametro
     * @param nombreUsuario Variable del nombre de usuario unica
     * @param contrasenia Contrasenia unica del usuario
     * @return retorna un boleaon verdadero
     */


    @Override
    public boolean registrarUsuario(String nombreUsuario, String contrasenia) {
        return listaUsuario.agregarUsuario(new Usuario(nombreUsuario, contrasenia,0,0,0,0));
    }

    /**
     * Metodo que permite al usuario iniciar sesion
     * @param nombreUsuario String del nombre de usuario unico
     * @param contrasenia String de la contrasenia unica del usuario
     * @return retorna un boleano que verifica si el inicio fue exitoso
     */
    @Override
    public boolean iniciarSesion(String nombreUsuario, String contrasenia) {
        Usuario usuario;
        for (int i = 0; i < this.listaUsuario.getCantidadActual(); i++) {
            usuario = listaUsuario.obtenerPosicion(i);
            if (usuario.getNombreUsuario().equals(nombreUsuario)) {
                System.out.println("Bienvenido: " + nombreUsuario);
                if (usuario.getContrasenia().equals(contrasenia)) {
                    System.out.println("Logeo exitoso Bienvenido");
                    this.usuarioActual = nombreUsuario;
                    return true;
                }
            }
        }
        if (this.listaUsuario.esVacio()) {
            System.out.println(" Intente mas tarde,No hay usuarios registrados");
            return false;

        }
        System.out.println("Error en el nombre de usuario o contrasenia. Intente nuevamente");
        return false;
    }

    /**
     * Metodo que permite la lectura de un archivo de extension txt
     * @return Retorna un boleaon verdadero
     * @throws IOException Excepción que permite la lectura de archivos
     */
    @Override
    public boolean lecturaListaCartas() throws IOException {
        In archivoEntrada = new In(cardList);

        while (!archivoEntrada.isEmpty()) {
            String[] registro = archivoEntrada.readLine().split(";");
            if (!registro[0].equalsIgnoreCase("cardname")) {
                String cardName = registro[0];
                String description = registro[1];
                String manaCost = registro[2];
                String type = registro[3];
                int power = Integer.parseInt(registro[4]);
                int toughness = Integer.parseInt(registro[5]);
                int castingCost = Integer.parseInt(registro[6]);
                Carta2 carta = new Carta2(cardName, description, manaCost, type, power, toughness, castingCost);
                this.listaCarta.agregarCarta(carta);
            }
        }
        archivoEntrada.close();
        return true;
    }

    /**
     * Metodo que permite la lectura de un archivo de extension txt
     * @return Retorna un boleano verdadero
     * @throws IOException Excepción que permite la lectura de archivos
     */
    @Override
    public boolean lecturaListaTierras() throws IOException {
        ArchivoEntrada archivoEntrada = new ArchivoEntrada(landList);

        while (!archivoEntrada.isEndFile()) {
            Registro registro = archivoEntrada.getRegistro();
            String landCardName = registro.getString();
            String type = registro.getString();
            String color = registro.getString();
            Tierra2 tierra = new Tierra2(landCardName, type, color);
            this.listaTierra.agregarTierra(tierra);
        }
        archivoEntrada.close();
        return true;
    }

    /**
     * Metodo que permite la lectura de la lista mazos para cargarlos desde un txt aun si el programa se finaliza
     * @param lista Variable de la lista mazo
     * @param archivoCargar Nombre del mazo especifico de tipo String
     * @return Booleano que retorna verdadero
     * @throws IOException Excepción para la lectura de archivos
     */
    public boolean lecturaListaMazos(ListaMazo lista,String archivoCargar) throws IOException {

        ArchivoEntrada archivoEntrada = new ArchivoEntrada(archivoCargar);
    this.listaMazo=lista;

        while (!archivoEntrada.isEndFile()) {
            Registro registro = archivoEntrada.getRegistro();
            String usuarioActual = registro.getString();
            int cantidadDeCartas = registro.getInt();
            String cardname = registro.getString();
            Mazo mazo = new Mazo(usuarioActual, cantidadDeCartas, cardname);
            this.listaMazo.agregaraMazo(mazo);
        }
        archivoEntrada.close();
        return true;
        //**private String usuarioActual;
        //    private int cantidadDeCartas;
        //    private String cardname;
    }

    /**
     * Metodo que permite la lectura de un sidedeck asociado a un mazo especifico permitiendo cargalos de un txt aun si el programa se finaliza
     * @return Booleano que retorna verdadero
     * @throws IOException Excepción que permite la lectura de archivos
     */
    public boolean lecturasideDeck() throws IOException {

        ArchivoEntrada archivoEntrada = new ArchivoEntrada(basededatosidedeck);


    while (!archivoEntrada.isEndFile()) {
        Registro registro = archivoEntrada.getRegistro();
        String usuarioActual = registro.getString();
        int cantidadDeCartas = registro.getInt();
        String cardname = registro.getString();
        String masoAsociado = registro.getString();
        Sidedeck mazo = new Sidedeck(usuarioActual, cantidadDeCartas, cardname, masoAsociado);
        this.listaSidedeck.agregaraMazo(mazo);
    }

    archivoEntrada.close();
    return true;

    }

    /**
     * Metodo que permite la lectura de los usuarios registrados, cuales se guardan en un txt
     * @return Retorna un booleano verdadero
     * @throws IOException Excepción que permite la lectura de archivos
     */
    public boolean lecturasUsuario() throws IOException {

        ArchivoEntrada archivoEntrada = new ArchivoEntrada(registrodeusuarios);


        while (!archivoEntrada.isEndFile()) {
            Registro registro = archivoEntrada.getRegistro();
            String nombreUsuario = registro.getString();
            String contrasenia = registro.getString();
            int mazo1 = registro.getInt();
            int mazo2 = registro.getInt();
            int mazo3 = registro.getInt();
            int mazo4 = registro.getInt();
            Usuario usuario=new Usuario(nombreUsuario,contrasenia,mazo1,mazo2,mazo3,mazo4);
            this.listaUsuario.agregarUsuario(usuario);

        }

        archivoEntrada.close();
        return true;

    }

    /**
     * Metodo que permite cargar los datos de las listas
     * @throws IOException Excepción que permite la lectura de archivos
     */
    public void cargarDatos() throws IOException {
        lecturaListaMazos(listaMazo,basededatos);
        lecturaListaMazos(listaMazo2,basededatos2);
        lecturaListaMazos(listaMazo3,basededatos3);
        lecturaListaMazos(listaMazo4,basededatos4);
        lecturasideDeck();
        lecturasUsuario();

    }

    /**
     * Metodo que permite guardar el registro de un usuario en un documento de texto
     * @return Retorna un booleano verdadero
     * @throws IOException Excepción que permite la lectura de archivos
     */
    //Guarda las instancias de la listaUsuario y las escribe dentro de un documento de texto el cual queda dentro de
    //la carpeta del archivo
    @Override
    public boolean guardarRegistroUsuario() throws IOException {

        ArchivoSalida archivoSalida = new ArchivoSalida(registrodeusuarios);
        for (int i = 0; i < listaUsuario.getCantidadActual(); i++) {
            Registro registro = new Registro(6);
            registro.agregarCampo(listaUsuario.obtenerPosicion(i).getNombreUsuario());
            registro.agregarCampo(listaUsuario.obtenerPosicion(i).getContrasenia());
            registro.agregarCampo(listaUsuario.obtenerPosicion(i).getMazo());
            registro.agregarCampo(listaUsuario.obtenerPosicion(i).getMazo2());
            registro.agregarCampo(listaUsuario.obtenerPosicion(i).getMazo3());
            registro.agregarCampo(listaUsuario.obtenerPosicion(i).getMazo4());
            archivoSalida.writeRegistro(registro);
        }
        archivoSalida.close();
        return true;
    }

    /**
     * Metodo que permite guardar el registro de los mazos realizados por un usuario a un documento de texto
     * @param listaReferencia Variable de la lista mazo respecto a los mazos por referencia
     * @param archivoAGuardar Nombre del archivo de texto respecto al mazo
     * @return Retorna un booleano verdadero
     * @throws IOException Excepción que permite la lectura de archivos
     */
    public boolean guardarRegistroMazos(ListaMazo listaReferencia,String archivoAGuardar) throws IOException {
    this.listaMazo=listaReferencia;
        ArchivoSalida archivoSalida = new ArchivoSalida(archivoAGuardar);
        for (int i = 0; i < listaMazo.getCantidadActual(); i++) {
            Registro registro = new Registro(3);
            registro.agregarCampo(listaMazo.obtenerPoscion(i).getUsuarioActual());
            registro.agregarCampo(listaMazo.obtenerPoscion(i).getCantidadDeCartas());
            registro.agregarCampo(listaMazo.obtenerPoscion(i).getCardname());

            archivoSalida.writeRegistro(registro);
        }
        archivoSalida.close();
        return true;
    }

    /**
     * Metodo que permite guardar el registro de los sidecks asociados a los mazos por un usuario a un documento de texto
     * @return Retorna un booleano verdadero
     * @throws IOException Excepción que permite la lectura de archivos
     */
    public boolean guardarRegistrosideDeck() throws IOException {

        ArchivoSalida archivoSalida = new ArchivoSalida(basededatosidedeck);

        for (int i = 0; i < listaSidedeck.getCantidadActual(); i++) {
            Registro registro = new Registro(4);
            Object objeto = listaMazo.obtenerPoscion(i);
            try {
                Sidedeck sidedeck = (Sidedeck) objeto;
                if (objeto != null ) {
                    registro.agregarCampo(sidedeck.getUsuarioActual());
                    registro.agregarCampo(sidedeck.getCantidadDeCartas());
                    registro.agregarCampo(sidedeck.getCardname());
                    registro.agregarCampo(sidedeck.getMazoAsociado());
                    archivoSalida.writeRegistro(registro);
                }
            } catch (Exception e){
                return false;

            }
        }
        archivoSalida.close();
        return true;
    }

    /**
     * Metodo que permite la eliminación de una carta dentro de la lista mazo
     * @param carta nombre de la carta de tipo string ingresada por parametro
     * @return Retorna un booleano verdadero o falso dependiendo de si las condiciones se cumplen
     */
    @Override
    public boolean eliminarCarta(String carta) {
        int cantidad = 0; //25-05-2024 La variable cantidad se declara afuera para usar el try catch
        boolean EsTierra = false;
        boolean eliminar = true;
        if (!imprimirCarta(carta).equalsIgnoreCase("") || !imprimirtTierra(carta).equalsIgnoreCase("")) {
            StdOut.println("Carta encontrada en el sistema");
            StdOut.println("Ingrese el número de copias que desea eliminar");
            try {
                cantidad = StdIn.readInt(); //25-05-2024 Si la cantidad no es de tipo entera se atrapa la excepción
            } catch (Exception InputMismatchException) {
                System.out.println("Ingrese un valor numérico válido. Por razones cómicas se repetirá dos veces el siguiente mensaje");
                return false;
            }
            if (!imprimirtTierra(carta).equalsIgnoreCase("")) {
                EsTierra = true;
            }

            StdOut.println("");
            StdOut.println("╔═════════════════════════════════════════════╗");
            StdOut.println("║ Ingrese el mazo del que desea eliminar cartas ║"); //ni idea cómo agregar más mazos
            StdOut.println("╚═════════════════════════════════════════════╝");
            StdOut.println("╔══════════════════════════════════╗");
            StdOut.println("║       ¿Ingrese una opción?       ║");
            StdOut.println("║       1. Mazo 1                  ║");
            StdOut.println("║       2. Mazo 2                  ║");
            StdOut.println("║       3. Mazo 3                  ║");
            StdOut.println("║       4. Mazo 4                  ║");
            StdOut.println("║       5. No eliminar carta       ║");
            StdOut.println("╚══════════════════════════════════╝");
            String opcion = StdIn.readString();
            ListaMazo listaMazoSeleccionada = null;

            switch (opcion) {
                case "1":
                    listaMazoSeleccionada = listaMazo;
                    break;
                case "2":
                    listaMazoSeleccionada = listaMazo2;
                    break;
                case "3":
                    listaMazoSeleccionada = listaMazo3;
                    break;
                case "4":
                    listaMazoSeleccionada = listaMazo4;
                    break;
                case "5":
                    return false;
                default:
                    StdOut.println("Opción inválida");
                    return false;
            }

            int respuesta = listaMazoSeleccionada.buscarPosicion(carta, usuarioActual); //los métodos funcionan, pero no es adecuado el agregar

            if (respuesta != -1) {
                Mazo mazo = listaMazoSeleccionada.modificarCantidad(listaMazoSeleccionada.obtenerPoscion(respuesta), cantidad, eliminar);
                if (mazo.getCantidadDeCartas() <= 0) {
                    listaMazoSeleccionada.eliminarCarta(mazo, usuarioActual);
                    StdOut.println("╔═══════════════════════════════════════════════╗");
                    StdOut.println("║  Carta eliminada:                             ║");
                    StdOut.println("║ " + carta + "                                      ║");
                    StdOut.println("╚═══════════════════════════════════════════════╝");
                    return true;
                }
                StdOut.println("╔═══════════════════════════════════════════════╗");
                StdOut.println("║  Se han eliminado: " + cantidad + "                        ║");
                StdOut.println("║   La cantidad actual de " + carta + " es:            ║");
                StdOut.println("║   " + mazo.getCantidadDeCartas() + "                                         ║");
                StdOut.println("╚═══════════════════════════════════════════════╝");
                return true;
            }
        }

        return false;
    }


    /**
     * Metodo que permite la exportación de un mazo a un documento de texto.
     * @return Retorna un booleano verdadero
     * @throws IOException Excepción para la lectura de archivos
     */
    @Override
    public boolean exportarMazo() throws IOException {
        ArchivoSalida archivoSalida = new ArchivoSalida("Mazo 1");
        for (int i = 0; i < this.listaMazo.getCantidadActual(); i++) {
            Registro registro = new Registro(3);
            registro.agregarCampo(listaMazo.obtenerPoscion(i).getUsuarioActual());
            registro.agregarCampo(listaMazo.obtenerPoscion(i).getCantidadDeCartas());
            registro.agregarCampo(listaMazo.obtenerPoscion(i).getCardname());

            archivoSalida.writeRegistro(registro);
        }
        archivoSalida.close();
        return true;
    }

    /**
     * Metodo que permite agregar un Sidedeck con datos por parametro
     * @param cantidad cantidad de cartas
     * @param carta nombre de la carta
     * @param listaMazoSeleccionada Mazo n seleccionado asociado al sidedeck (n = 1,2,3,4)
     * @param respuesta Respuesta de verificación si el mazo asociado existe previamente en el programa
     * @param esSideck booleano que verifica que el mazo se arealmente un sidedeck
     * @return Retorna un booleano verdadero o falso dependiendo si las condiciones se cumplen
     */
    @Override
    public boolean agregarSliboard(int cantidad, String carta, ListaMazo listaMazoSeleccionada, String respuesta,boolean esSideck) {
            if (respuesta.equalsIgnoreCase("sin ingresar")) {
                Sidedeck sidedeck=new Sidedeck(this.usuarioActual,cantidad,carta,this.mazoReferencia);
                Mazo aux = sidedeck;
                listaMazoSeleccionada.agregaraMazo(aux);
                StdOut.println("Dato agregado: " + aux.getCardname() + " " + aux.getCantidadDeCartas());
                return true;
            }
            if (respuesta.equalsIgnoreCase("existente")) {
                int obtenerposicion = listaMazoSeleccionada.buscarPosicion(carta, usuarioActual);
                Mazo mazo = listaMazoSeleccionada.modificarCantidad(listaMazoSeleccionada.obtenerPoscion(obtenerposicion), cantidad);
                StdOut.println(mazo.empaquetarInformacion());
                return true;
            }
            return false;
    }

    /**
     * Metodo que permite eliminar una carta del sidedeck
     * @param carta nombre de la carta ingresada por parametro
     * @return Retorna un booleano verdadero o falso dependiendo si la condición se cumple
     */
    @Override
    public boolean eliminarCartaSidedeck(String carta)  {
        int cantidad = 0;
        boolean EsTierra = false;
        boolean eliminar = true;
        if (!imprimirCarta(carta).equalsIgnoreCase("") || !imprimirtTierra(carta).equalsIgnoreCase("")) {
            StdOut.println("Carta encontrada en el sistema");
            StdOut.println("Ingrese el número de copias que desea eliminar");
            try {
                cantidad = StdIn.readInt();
            } catch (Exception InputMismatchException) {
                System.out.println("Ingrese un valor numérico válido. ");
                return false;
            }
            if (!imprimirtTierra(carta).equalsIgnoreCase("")) {
                EsTierra = true;
            }

            int respuesta = listaSidedeck.buscarPosicion(carta, usuarioActual);

            if (respuesta != -1) {
                Mazo mazo = listaSidedeck.modificarCantidad(listaSidedeck.obtenerPoscion(respuesta), cantidad, eliminar);
                if (mazo.getCantidadDeCartas() <= 0) {
                    listaSidedeck.eliminarCarta(mazo, usuarioActual);
                    StdOut.println("Carta eliminada: " + carta);
                    return true;
                }
                StdOut.println("Se han eliminado: " + cantidad);
                StdOut.println("La cantidad actual de " + carta + " es: " + mazo.getCantidadDeCartas());
                return true;
            }
        }

        return false;
    }

    /**
     * Metodo que permite la exportación de los mazos
     * @throws IOException Excepción que permite la lectura de archivos de texto
     */
    @Override
    public void exportar() throws IOException {
    guardarRegistroMazos(listaMazo,basededatos);
    guardarRegistroMazos(listaMazo2,basededatos2);
    guardarRegistroMazos(listaMazo3,basededatos3);
    guardarRegistroMazos(listaMazo4,basededatos4);
    guardarRegistrosideDeck();
    }

    /**
     * Metodo que permite la impresion por pantalla de los datos de una carta solicitada
     * @param CardName Nombre de la carta ingresada por parametro
     * @return Retorna un String con los datos de la carta
     */
    @Override
    public String imprimirCarta(String CardName) {
        String imprimir = "";

        for (int i = 0; i < listaCarta.getCantidadActual(); i++) {
            if (this.listaCarta.obtenerPoscion(i).getCardname().equalsIgnoreCase(CardName)) {
                imprimir = this.listaCarta.obtenerPoscion(i).empaquetarInformacion();
                break;
            }
        }


        return imprimir;
    }

    /**
     * Metodo que permite la impresión por pantalla de los datos de una tierra solicitada
     * @param landCardName Nombre de la teirra ingresada por parametro
     * @return Retorna un String con los datos de la tierra
     */
    @Override
    public String imprimirtTierra(String landCardName) {
        String imprimir = "";

        for (int i = 0; i < listaTierra.getCantidadActual(); i++) {
            if (this.listaTierra.obtenerPoscion(i).getLandCardName().equalsIgnoreCase(landCardName)) {
                imprimir = this.listaTierra.obtenerPoscion(i).empaquetarInformacionTierra();
            }
        }

        return imprimir;
    }

    /**
     * Metodo que permite agregar una nueva carta o tierra a los mazos
     * @param carta nombre de la carta ingresda por parametro
     * @param origen booleano que verifica si el mazo existe previamente o es un sidedeck
     * @param esSidedeck booleano que verifica si el mazo es un sidedeck
     * @return Retorna un booleano verdadero o falso dependiendo si las condiciones se cumplen
     */
    @Override
    // Método principal que llama a los métodos auxiliares
    public boolean agregarnuevaCarta(String carta, boolean origen,boolean esSidedeck) {//nombre de la carta a agregar, el booleano para diferenciar si es de menu creado o modificado
        ListaMazo listaMazoSeleccionada = null;                            //
        int posicion=this.listaUsuario.buscarPosicionNombre(this.usuarioActual);
        Usuario usuarioAConsultar=this.listaUsuario.obtenerPosicion(posicion);
        boolean EsTierra = !imprimirtTierra(carta).equalsIgnoreCase("");
        int cantidad = leerCantidadDeCopias(EsTierra);
        if (cantidad == -1) return false;

               if(!origen&&!esSidedeck) {
                   listaMazoSeleccionada = seleccionarMazo(origen);
               } else if (origen&&!esSidedeck) {
                   listaMazoSeleccionada = seleccionarMazo(usuarioAConsultar.getMazo(),usuarioAConsultar.getMazo2(),usuarioAConsultar.getMazo3(),usuarioAConsultar.getMazo4());
               }
               if (esSidedeck) {
                   listaMazoSeleccionada = seleccionarMazo(origen,esSidedeck);
               }
               if (listaMazoSeleccionada == null){
                   return false;
               }
        if(esSidedeck) {
            String respuesta = comprobarCarta(cantidad, carta, listaMazoSeleccionada, EsTierra,esSidedeck);  // aqui se comprueba
            if (!comprobarCantidadSideDeck(cantidad, listaMazoSeleccionada)) return false;

            return agregarSliboard(cantidad, carta, listaMazoSeleccionada, respuesta,esSidedeck);
        }
        String respuesta = comprobarCarta(cantidad, carta, listaMazoSeleccionada, EsTierra,esSidedeck);  // aqui se comprueba
        if (!comprobarCantidad(cantidad, listaMazoSeleccionada)) return false;

        return procesarCarta(cantidad, carta, listaMazoSeleccionada, respuesta);

    }

    /**
     * Metodo que permite verificar y entregar la cantidad de copias de una carta
     * @param EsTierra booleano que verifica que la carta sea una tierra o carta
     * @return retorna la cantidad de copias correspondientes
     */
    // Método para leer la cantidad de copias del usuario
    private int leerCantidadDeCopias(boolean EsTierra) {
        StdOut.println("Ingrese el número de copias que desea agregar");
        int cantidad;
        try {
            cantidad = StdIn.readInt();
        } catch (Exception InputMismatchException) {
            System.out.println("Ingrese un valor numérico válido.");
            return -1;
        }

        // Mensaje de validación según el tipo de carta
        String mensaje;
        if (EsTierra) {
            mensaje = "Cantidad inválida. Ingrese una cantidad mayor a 0.";
        } else {
            mensaje = "Cantidad inválida. Ingrese una cantidad entre 1 y 4.";
        }

        // Validación del rango de la cantidad ingresada
        while (cantidad <= 0 || (!EsTierra && cantidad >= 5)) {
            StdOut.println(mensaje);
            cantidad = StdIn.readInt();
        }
        return cantidad;
    }


    // Método para seleccionar el mazo

    /**
     * Metodo que permite al usuario seleccionar un sidedeck
     * @param origen Booleano que permite verificar si el mazo existe previamente
     * @param Sidedeck Booleano que verifica si el mazo posee un sidedeck
     * @return Retorna nulo
     */
    private ListaMazo seleccionarMazo(boolean origen,boolean Sidedeck) { //mazo sidedeck
        while (true) {
            StdOut.println("╔══════════════════════════════════════════════╗");
            StdOut.println("║ Ingrese el sidedeck que desea agregar cartas ║");
            StdOut.println("╚══════════════════════════════════════════════╝");
            StdOut.println("╔══════════════════════════════════╗");
            StdOut.println("║       ¿Ingrese una opción?       ║");
            StdOut.println("║       1. Mazo 1                  ║");
            StdOut.println("║       2. Mazo 2                  ║");
            StdOut.println("║       3. Mazo 3                  ║");
            StdOut.println("║       4. Mazo 4                  ║");
            StdOut.println("║       5. No agregar carta        ║");
            StdOut.println("╚══════════════════════════════════╝");

            String opcion = StdIn.readString();

            boolean comprobar = this.listaMazo.creoMazo(this.usuarioActual); //verifica si el el mazo se creo
            boolean comprobar2 = this.listaMazo2.creoMazo(this.usuarioActual);
            boolean comprobar3 = this.listaMazo3.creoMazo(this.usuarioActual);
            boolean comprobar4 = this.listaMazo4.creoMazo(this.usuarioActual);
            switch (opcion) {


                case "1":
                    if (comprobar) {
                        StdOut.println("error vuelva a intentarlo");
                        StdOut.println(this.usuarioActual + " no ha creado el mazo 1");
                        return null;
                    }
                   this.mazoReferencia="mazo1";
                    return listaSidedeck;

                case "2":
                    if (comprobar2) {
                        StdOut.println("error vuelva a intentarlo");
                        StdOut.println(this.usuarioActual + " no ha creado el mazo 2");
                        return null;
                    }
                    this.mazoReferencia="mazo2";
                    return listaSidedeck;
                case "3":
                    if (comprobar3) {
                        StdOut.println("error vuelva a intentarlo");
                        StdOut.println(this.usuarioActual + " no ha creado el mazo 3");
                        return null;
                    }
                    this.mazoReferencia="mazo3";
                    return listaSidedeck;
                case "4":
                    if (comprobar4) {
                        StdOut.println("error vuelva a intentarlo");
                        StdOut.println(this.usuarioActual + " no ha creado el mazo 4");
                        return null;
                    }
                    this.mazoReferencia="mazo4";
                    return listaSidedeck;
                case "5":
                    return null;
                default:
                    StdOut.println("Opción inválida");
            }
        }
    }

    /**
     * Metodo que permite sleccionar especificamente un mazo
     * @param origen booleano que verifica si el mazo existe
     * @return Retorna nulo
     */
    private ListaMazo seleccionarMazo(boolean origen) {
        StdOut.println("╔═════════════════════════════════════════════╗");
        StdOut.println("║ Ingrese el mazo que desea agregar las cartas ║");
        StdOut.println("╚═════════════════════════════════════════════╝");
        StdOut.println("╔══════════════════════════════════╗");
        StdOut.println("║       ¿Ingrese una opción?       ║");
        StdOut.println("║       1. Mazo 1                  ║");
        StdOut.println("║       2. Mazo 2                  ║");
        StdOut.println("║       3. Mazo 3                  ║");
        StdOut.println("║       4. Mazo 4                  ║");
        StdOut.println("║       5. No agregar carta        ║");
        StdOut.println("╚══════════════════════════════════╝");

        String opcion = StdIn.readString();
        if (!origen) {
            boolean comprobar = this.listaMazo.creoMazo(this.usuarioActual); //verifica si el el mazo se creo
            boolean comprobar2 = this.listaMazo2.creoMazo(this.usuarioActual);
            boolean comprobar3 = this.listaMazo3.creoMazo(this.usuarioActual);
            boolean comprobar4 = this.listaMazo4.creoMazo(this.usuarioActual);
            switch (opcion) {


                case "1":
                    if (comprobar) {
                        StdOut.println("error vuelva a intentarlo");
                        StdOut.println(this.usuarioActual + " no ha creado el mazo 1");
                        return null;
                    }
                    return listaMazo;

                case "2":
                    if (comprobar2) {
                        StdOut.println("error vuelva a intentarlo");
                        StdOut.println(this.usuarioActual + " no ha creado el mazo 2");
                        return null;
                    }
                    return listaMazo2;
                case "3":
                    if (comprobar3) {
                        StdOut.println("error vuelva a intentarlo");
                        StdOut.println(this.usuarioActual + " no ha creado el mazo 3");
                        return null;
                    }
                    return listaMazo3;
                case "4":
                    if (comprobar4) {
                        StdOut.println("error vuelva a intentarlo");
                        StdOut.println(this.usuarioActual + " no ha creado el mazo 4");
                        return null;
                    }
                    return listaMazo4;
                case "5":
                    return null;
                default:
                    StdOut.println("Opción inválida");
                    return null;
            }
        }
      return null;
    }

    /**
     * Metodo que permite agregar cartas a un mazo especifico
     * @param mazo Mazo 1
     * @param mazo2 Mazo 2
     * @param mazo3 Mazo 3
     * @param mazo4 Mazo 4
     * @return retorna nulo
     */
    private ListaMazo seleccionarMazo(int mazo, int mazo2, int mazo3, int mazo4) {
        StdOut.println("╔═════════════════════════════════════════════╗");
        StdOut.println("║ Ingrese el mazo que desea agregar las cartas ║");
        StdOut.println("╚═════════════════════════════════════════════╝");
        StdOut.println("╔══════════════════════════════════╗");
        StdOut.println("║       ¿Ingrese una opción?       ║");
        StdOut.println("║       1. Mazo 1                  ║");
        StdOut.println("║       2. Mazo 2                  ║");
        StdOut.println("║       3. Mazo 3                  ║");
        StdOut.println("║       4. Mazo 4                  ║");
        StdOut.println("║       5. No agregar carta        ║");
        StdOut.println("╚══════════════════════════════════╝");

        String opcion = StdIn.readString();

        switch (opcion) {
            case "1":
                if (mazo != 0) {
                    StdOut.println("Error, vuelva a intentarlo. " + this.usuarioActual + " ya ha creado el mazo 1");
                    return null;
                }
                return listaMazo;
            case "2":
                if (mazo2 != 0) {
                    StdOut.println("Error, vuelva a intentarlo. " + this.usuarioActual + " ya ha creado el mazo 2");
                    return null;
                }
                return listaMazo2;
            case "3":
                if (mazo3 != 0) {
                    StdOut.println("Error, vuelva a intentarlo. " + this.usuarioActual + " ya ha creado el mazo 3");
                    return null;
                }
                return listaMazo3;
            case "4":
                if (mazo4 != 0) {
                    StdOut.println("Error, vuelva a intentarlo. " + this.usuarioActual + " ya ha creado el mazo 4");
                    return null;
                }
                return listaMazo4;
            case "5":
                return null;
            default:
                StdOut.println("Opción inválida");
                return null;
        }
    }





    // Método para procesar la carta y agregarla o modificarla en el mazo

    /**
     * Metodo que permite gestionar las cartas y agregarlas o modificarlas en el mazo
     * @param cantidad cantidad de cartas ingresada por parametro
     * @param carta nombre de la carta
     * @param listaMazoSeleccionada Lista de mazo n seleccionada (n = 1,2,3,4)
     * @param respuesta String que verifica si el mazo existe
     * @return Retorna un booleano verdadero o falso dependiendo si la condición se cumple
     */
    private boolean procesarCarta(int cantidad, String carta, ListaMazo listaMazoSeleccionada, String respuesta) {
        if (respuesta.equalsIgnoreCase("sin ingresar")) {
            Mazo aux = listaMazoSeleccionada.agregarMazo(usuarioActual, cantidad, carta);
            listaMazoSeleccionada.agregaraMazo(aux);
            StdOut.println("Dato agregado: " + aux.getCardname() + " " + aux.getCantidadDeCartas());
            return true;
        }
        if (respuesta.equalsIgnoreCase("existente")) {
            int obtenerposicion = listaMazoSeleccionada.buscarPosicion(carta, usuarioActual);
            Mazo mazo = listaMazoSeleccionada.modificarCantidad(listaMazoSeleccionada.obtenerPoscion(obtenerposicion), cantidad);
            StdOut.println(mazo.empaquetarInformacion());
            return true;
        }
        return false;
    }

    /**
     * Metodo que permite comprabar si la lista fue modificada
     * @return Retorna un booleano verdadero o falso dependiendo si la condición se cumple
     */
    @Override
public boolean comprobarListaModificada(){
    boolean comprobar=this.listaMazo.esVacio();
    boolean comprobar2=this.listaMazo2.esVacio();
    boolean comprobar3=this.listaMazo3.esVacio();
    boolean comprobar4=this.listaMazo4.esVacio();
    if(comprobar&&comprobar2&&comprobar3&&comprobar4){
    return true;
    }
        return false;
}

    /**
     * Metodo que comprueba si el mazo fue creado
     * @return Retorna un booleano verdadero o falso dependiendo si la condición se cumple
     */
     public boolean comprobarCreacionMazo(){
        boolean comprobar=this.listaMazo.creoMazo(this.usuarioActual); //verifica si el el mazo se creo
        boolean comprobar2=this.listaMazo2.creoMazo(this.usuarioActual);
        boolean comprobar3=this.listaMazo3.creoMazo(this.usuarioActual);
        boolean comprobar4=this.listaMazo4.creoMazo(this.usuarioActual);
        if(!comprobar&&!comprobar2&&!comprobar3&&!comprobar4){
            return true;
        }
        return false;
    }

    /**
     * Metodo que comprueba si el mazo fue creado
     * @return Retorna un booleano verdadero o falso dependiendo si la condición se cumple
     */
    public boolean comprobarMazosCreados(){
        boolean comprobar=this.listaMazo.creoMazo(this.usuarioActual);
        boolean comprobar2=this.listaMazo2.creoMazo(this.usuarioActual);
        boolean comprobar3=this.listaMazo3.creoMazo(this.usuarioActual);
        boolean comprobar4=this.listaMazo4.creoMazo(this.usuarioActual);
       int posicion=this.listaUsuario.buscarPosicionNombre(this.usuarioActual);
        if(!comprobar){
            boolean comprobacion=this.listaUsuario.comprobarMazo(comprobar,posicion,1);
        }
        if(!comprobar2){
            boolean comprobacion=this.listaUsuario.comprobarMazo(comprobar,posicion,2);
        }
        if(!comprobar3){
            boolean comprobacion=this.listaUsuario.comprobarMazo(comprobar,posicion,3);
        }
        if(!comprobar4){
            boolean comprobacion=this.listaUsuario.comprobarMazo(comprobar,posicion,4);
        }
        if(comprobar){
            boolean comprobacion=this.listaUsuario.comprobarMazo(comprobar,posicion,1);
        }
        if(comprobar2){
            boolean comprobacion=this.listaUsuario.comprobarMazo(comprobar,posicion,2);
        }
        if(comprobar3){
            boolean comprobacion=this.listaUsuario.comprobarMazo(comprobar,posicion,3);
        }
        if(comprobar4){
            boolean comprobacion=this.listaUsuario.comprobarMazo(comprobar,posicion,4);
        }
        return true;
    }

    /**
     * Metodo que comprueba si la carta ingresada supera la maxima cantidad dentro de un mazo
     * @param cantidad variable de cantidad
     * @param carta nombre de la carta
     * @param listaMazoseleccioada Mazo n seleccionado (n = 1,2,3,4)
     * @param Estierra booleano de Verificación si la carta es tierra o carta
     * @param esSidedeck booleano de Verificación si el mazo es un sidedeck o mazo
     * @return
     */
    public  String comprobarCarta(int cantidad, String carta, ListaMazo listaMazoseleccioada, boolean Estierra,boolean esSidedeck){   //metodo para comprobar si el limete de carta no fue superado
        int comprobar= listaMazoseleccioada.buscarPosicion(carta,usuarioActual);          // no es necesario comprobar si el dato no ha sido ingresado
        int aux;
        if(comprobar==-1){
            return "sin ingresar";
        }
        if(comprobar!=-1){                          //si se encuentra  se debe de comprobar que el dato ingresado no supere la cantidad maxima
           if(!esSidedeck) {
               Mazo comprobarcantidad = listaMazoseleccioada.obtenerPoscion(comprobar);
               aux=comprobarcantidad.getCantidadDeCartas();
           }else {
               Sidedeck comprobarCantidadSideDeck = (Sidedeck) listaMazoseleccioada.obtenerPoscion(comprobar); //revisar
               aux=comprobarCantidadSideDeck.getCantidadDeCartas();
           }

           if(aux+cantidad>=5&&!Estierra){              //metodo que comprueba que carta no supere 4
               StdOut.println("Cantidad supera el limite de cartas permitido");
               return "no valido";
           }
        }
        return "existente";                                              //se permite el ingreso
    }

    /**
     * Metodo que permite comprobar la cantidad limite de cartas en el sideck
     * @param cantidad cantidad de cartas dentro del sideck
     * @param listaMazo Lista relacionada al sideck
     * @return Retorna un booleano verdadero o falso dependiendo si la condición se cumple
     */
    public boolean comprobarCantidadSideDeck(int cantidad,ListaMazo listaMazo){
        this.listaMazo=listaMazo;
        int acumulador=listaMazo.contar60(usuarioActual);
        if(acumulador+cantidad>15){
            StdOut.println("Cantidad de cartas en el mazo supera el limite de cartas permitido");
            return false;
        }

        return true;
    }

    /**
     * Metodo que comprueba la cantidad de cartas dentro de un mazo
     * @param cantidad cantidad de cartas dentro del mazo
     * @param listaMazo lista de mazo n seleciconada (n = 1,2,3,4)
     * @return Retorna un booleano verdadero o falso dependiendo si la condición se cumple
     */
    public boolean comprobarCantidad(int cantidad,ListaMazo listaMazo){
    this.listaMazo=listaMazo;
    int acumulador=listaMazo.contar60(usuarioActual);
    if(acumulador+cantidad>60){
        StdOut.println("Cantidad de cartas en el mazo supera el limite de cartas permitido");
        return false;
    }

        return true;
    }

    /**
     * Metodo que muestra todas las cartas de un mazo
     */
    public void mostrarcarta(){
        StdOut.println("A continuacion se mostraran todas las cartas \n");
        for (int i = 0; i <this.listaCarta.getCantidadActual() ; i++) {
          Carta carta=this.listaCarta.obtenerPoscion(i);
            StdOut.println(carta.empaquetarInformacion());
        }
        StdOut.println("A continuacion se mostraran todas las tierras \n");
        for (int i = 0; i <this.listaTierra.getCantidadActual() ; i++) {
            Tierra tierra=this.listaTierra.obtenerPoscion(i);
            StdOut.println(tierra.empaquetarInformacionTierra());
        }
    }

    /**
     * metodo que despliega todos los mazos creados
     * @param opcion booleano que comprueba la opción elegida
     * @param referencia referencia del mazo
     */
    public void mostrarmazos(boolean opcion,String referencia) {

        ListaMazo listaelegida=null;
        if(referencia.equalsIgnoreCase("mazo1")){
            listaelegida=listaMazo;
        } if(referencia.equalsIgnoreCase("mazo2")){
            listaelegida=listaMazo2;
        } if(referencia.equalsIgnoreCase("mazo3")){
            listaelegida=listaMazo3;
        } if(referencia.equalsIgnoreCase("mazo4")){
            listaelegida=listaMazo4;
        }

        this.listaMazo = listaelegida;
        mazoReferencia=referencia;
        if (opcion) {
            StdOut.println("A continuacion su mazo seleccionado:\n");
            for (int i = 0; i < this.listaMazo.getCantidadActual(); i++) {
                Mazo mazo = this.listaMazo.obtenerPoscion(i);
                if (mazo.getUsuarioActual().equals(this.usuarioActual)) {
                    if (mazo.getCantidadDeCartas() < 3) {
                        StdOut.println(mazo.empaquetarInformacion());
                    }
                    if (mazo.getCantidadDeCartas() >= 3) {
                        StdOut.println(mazo.getCardname() + "|x" + mazo.getCantidadDeCartas());
                    }
                }

            }
        }
        if (!opcion) {
            StdOut.println("A continuacion se mostrara el sideDeck \n");
            for (int i = 0; i < this.listaSidedeck.getCantidadActual(); i++) {
                Sidedeck sidedeck = (Sidedeck) this.listaSidedeck.obtenerPoscion(i);
                if (sidedeck.getUsuarioActual().equals(this.usuarioActual) && sidedeck.getMazoAsociado().equalsIgnoreCase(mazoReferencia)) { //cambiar mazo referencia
                    if (sidedeck.getCantidadDeCartas() < 3) {
                        StdOut.println(sidedeck.empaquetarInformacion());
                    }
                    if (sidedeck.getCantidadDeCartas() >= 3) {
                        StdOut.println(sidedeck.getCardname() + "|x" + sidedeck.getCantidadDeCartas());
                    }
                }

            }
        }
    }
}


