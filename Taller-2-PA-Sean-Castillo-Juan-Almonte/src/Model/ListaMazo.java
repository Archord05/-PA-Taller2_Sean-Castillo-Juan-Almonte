package Model;

public class ListaMazo {

    private  Mazo[] listaMazo;
    private int cantidadMaxima;
    private int cantidadActual;

    public ListaMazo(int cantidadMaxima) {
        this.cantidadMaxima = cantidadMaxima;
        this.listaMazo=new Mazo[this.cantidadMaxima];
        this.cantidadActual = 0;
    }
    public boolean esVacio() {
        try {
            this.listaMazo[0].equals(null);
        } catch (NullPointerException e) {
            return true;
        }
        return false;
    }
    public boolean creoMazo(String usuario) {
        for (int i = 0; i <this.cantidadActual; i++) {
            if(this.listaMazo[i].getUsuarioActual().equals(usuario)){
                return false;
            }
        }
        return true;
    }
    public int buscarPosicion(String nombreMazo,String usuarioActual){
        for (int i = 0; i <this.cantidadActual ; i++) {
            if(usuarioActual.equals(this.listaMazo[i].getUsuarioActual())) {
                if (this.listaMazo[i].getCardname().equalsIgnoreCase(nombreMazo)) {
                    return i;
                }
            }
        }
        return -1;
    }
    public Mazo agregarMazo(String usuarioActual,int cantidad,String cartaAgregar){
        return new Mazo(usuarioActual,cantidad,cartaAgregar);
    }
    public Mazo obtenerPoscion(int posicion){
        return this.listaMazo[posicion];
    }
    public boolean agregaraMazo(Mazo nuevoMazo){
        this.listaMazo[this.cantidadActual] = nuevoMazo;
        this.cantidadActual++;
        return true;
    }
    public Mazo modificarCantidad(Mazo nuevoMazo,int cantidad){

        nuevoMazo.setCantidadDeCartas(nuevoMazo.getCantidadDeCartas()+cantidad);
        return nuevoMazo;
    }
    public Mazo modificarCantidad(Mazo nuevoMazo,int cantidad,boolean eliminar){

        nuevoMazo.setCantidadDeCartas(nuevoMazo.getCantidadDeCartas()-cantidad);
        return nuevoMazo;
    }
    public boolean eliminarCarta(Mazo mazo,String usuarioActual){
        String eliminar=mazo.getCardname();
        int posicionAeliminar=buscarPosicion(eliminar,usuarioActual);
        for (int i = posicionAeliminar; i <this.cantidadActual ; i++) {
            this.listaMazo[i]=this.listaMazo[(i+1)];
        }

        this.cantidadActual--;
        return true;
    }

    public int contar60(String usuarioActual){
       int aux=0;
        for (int i = 0; i <this.cantidadActual ; i++) {
           if(usuarioActual.equals(listaMazo[i].getUsuarioActual())) {
               aux = this.listaMazo[i].getCantidadDeCartas() + aux;
           }
        }
        return aux;
    }
    public Mazo[] getListaMazo() {
        return this.listaMazo;
    }

    public void setListaMazo(Mazo[] listaMazo) {
        this.listaMazo = listaMazo;
    }

    public int getCantidadMaxima() {
        return this.cantidadMaxima;
    }

    public void setCantidadMaxima(int cantidadMaxima) {
        this.cantidadMaxima = cantidadMaxima;
    }

    public int getCantidadActual() {
        return this.cantidadActual;
    }

    public void setCantidadActual(int cantidadActual) {
        this.cantidadActual = cantidadActual;
    }

}
