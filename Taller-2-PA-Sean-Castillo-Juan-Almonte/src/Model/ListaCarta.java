package Model;

public class ListaCarta {
    private Carta[] listaCarta;
    private int cantidadMaxima;
    private int cantidadActual;
    public ListaCarta(int cantidadMaxima) {
        this.cantidadMaxima = cantidadMaxima;
        this.listaCarta=new Carta2[this.cantidadMaxima];
        this.cantidadActual = 0;
    }
    public int buscarCarta(String cardName){
        for (int i = 0; i <this.cantidadActual ; i++) {
            if(this.listaCarta[i].getCardname().equalsIgnoreCase(cardName)){
                return i;
            }
        }
        return -1;
    }
    public Carta obtenerPoscion(int posicion){
        return this.listaCarta[posicion];
    }

    public boolean agregarCarta(Carta nuevaCarta){
        this.listaCarta[this.cantidadActual] = nuevaCarta;
        cantidadActual++;
        return true;
    }
    public boolean eliminarCarta(Carta carta){
        //TODO
        return false;
    }

    public Carta[] getListaCarta() {
        return listaCarta;
    }

    public void setListaCarta(Carta[] listaCarta) {
        this.listaCarta = listaCarta;
    }

    public int getCantidadMaxima() {
        return cantidadMaxima;
    }

    public void setCantidadMaxima(int cantidadMaxima) {
        this.cantidadMaxima = cantidadMaxima;
    }

    public int getCantidadActual() {
        return cantidadActual;
    }

    public void setCantidadActual(int cantidadActual) {
        this.cantidadActual = cantidadActual;
    }

}

