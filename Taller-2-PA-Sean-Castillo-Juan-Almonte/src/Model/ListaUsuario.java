package Model;

import ucn.StdOut;

public class ListaUsuario {
    private Usuario[] listaUsuario;
    private int cantidadMaxima;
    private int cantidadActual;

    public ListaUsuario(int cantidadMaxima) {
        this.cantidadMaxima = cantidadMaxima;
        this.listaUsuario = new Usuario[this.cantidadMaxima];
        this.cantidadActual = 0;
    }

    public boolean esVacio() {
        try {
            this.listaUsuario[0].equals(null);
        } catch (NullPointerException e) {
            return true;
        }
        return false;
    }

    public int buscarPosicionNombre(String nombre){
        for (int i = 0; i <this.cantidadActual ; i++) {
            if(this.listaUsuario[i].getNombreUsuario().equals(nombre)){
                return i;
            }
        }
        return -1;
    }
    public int buscarPosicionContraseña(String contrasena,int posicion) {
        if(this.listaUsuario[posicion].getContrasenia().equals(contrasena)){
            return posicion;
        }

        return -1;
    }
    public Usuario obtenerPosicion(int posicion){
        if(posicion>cantidadActual||posicion<0){
            StdOut.println("Ingrese una posicion valida");

        }
        return this.listaUsuario[posicion];
    }
    public boolean agregarUsuario(Usuario usuario){
        this.listaUsuario[this.cantidadActual]=usuario;
        this.cantidadActual++;
        return true;
    }
    public boolean comprobarMazo(boolean dato, int posicion,int validacion){
        if(!dato& validacion==1) {
            this.listaUsuario[posicion].setMazo(1);
        }
        if(dato& validacion!=1) {
            this.listaUsuario[posicion].setMazo(0);
        }
        if(!dato& validacion==2) {
            this.listaUsuario[posicion].setMazo(1);
        }
        if(dato& validacion!=2) {
            this.listaUsuario[posicion].setMazo(0);
        }

        if(!dato& validacion==3) {
            this.listaUsuario[posicion].setMazo(1);
        }
        if(dato& validacion!=3) {
            this.listaUsuario[posicion].setMazo(0);
        }
        if(!dato& validacion==4) {
            this.listaUsuario[posicion].setMazo(1);
        }
        if(dato& validacion!=4) {
            this.listaUsuario[posicion].setMazo(0);
        }
        return true;
    }

    public Usuario[] getListaUsuario() {
        return listaUsuario;
    }

    public int getCantidadMaxima() {
        return cantidadMaxima;
    }

    public int getCantidadActual() {
        return cantidadActual;
    }
}

