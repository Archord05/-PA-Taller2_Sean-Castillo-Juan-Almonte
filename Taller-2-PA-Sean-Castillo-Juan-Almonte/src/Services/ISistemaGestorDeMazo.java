package Services;


import Model.ListaMazo;

import java.io.IOException;

public interface ISistemaGestorDeMazo {

    boolean  registrarUsuario(String nombreUsuario, String contrasenia);
    boolean iniciarSesion(String nombreUsuario, String contrasenia);
    boolean  lecturaListaCartas() throws IOException;
    boolean  lecturaListaTierras() throws IOException;
    boolean  guardarRegistroUsuario() throws IOException;
    boolean  exportarMazo() throws IOException;
    void  cargarDatos() throws IOException;
    boolean  agregarSliboard(int cantidad, String carta, ListaMazo listaMazoSeleccionada, String respuesta, boolean esSideck);
   boolean eliminarCartaSidedeck(String carta);
    void exportar() throws IOException;
    String  imprimirCarta(String cardName);
    String imprimirtTierra(String landCardName);
    boolean agregarnuevaCarta(String carta,boolean origen,boolean esSidedeck);
boolean eliminarCarta(String cardName);
    boolean comprobarListaModificada();
    boolean comprobarCreacionMazo();
    void mostrarcarta();
    public void mostrarmazos(boolean opcion,String referencia);
}
