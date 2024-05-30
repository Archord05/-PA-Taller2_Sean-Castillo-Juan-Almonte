package Model;


public class ListaTierra {
    private Tierra[] listaTierra;
    private int cantidadMaxima;
    private int cantidadActual;
    public ListaTierra(int cantidadMaxima) {
        this.cantidadMaxima = cantidadMaxima;
        this.listaTierra = new Tierra[cantidadMaxima];
        this.cantidadActual = 0;
    }
    public int buscarPosicion(String cardName){
        for (int i = 0; i <this.cantidadActual ; i++) {
            if(this.listaTierra[i].getLandCardName().equalsIgnoreCase(cardName)){
                return i;
            }
        }
        return -1;
    }
    public Tierra obtenerPoscion(int posicion){
        return this.listaTierra[posicion];
    }

    public boolean agregarTierra(Tierra nuevaTierra){
        this.listaTierra[this.cantidadActual] = nuevaTierra;
        cantidadActual++;
        return true;
    }
    public boolean eliminarTierra(Tierra tierra){
        //TODO
        return false;
    }

    public Tierra[] getListaTierra() {
        return listaTierra;
    }

    public int getCantidadMaxima() {
        return cantidadMaxima;
    }

    public int getCantidadActual() {
        return cantidadActual;
    }

    public void setListaTierra(Tierra[] listaTierra) {
        this.listaTierra = listaTierra;
    }

    public void setCantidadMaxima(int cantidadMaxima) {
        this.cantidadMaxima = cantidadMaxima;
    }

    public void setCantidadActual(int cantidadActual) {
        this.cantidadActual = cantidadActual;
    }
}

