package Model;

public class Mazo  {
    private String usuarioActual;
    private int cantidadDeCartas;
    private String cardname;


    public Mazo(String usuarioActual,int cantidadDeCartas, String cardname) {
        this.usuarioActual=usuarioActual;
        this.cantidadDeCartas = cantidadDeCartas;
        this.cardname = cardname;
    }

    public String getUsuarioActual() {
        return usuarioActual;
    }

    public int getCantidadDeCartas() {
        return cantidadDeCartas;
    }

    public void setCantidadDeCartas(int cantidadDeCartas) {
        this.cantidadDeCartas = cantidadDeCartas;
    }

    public String getCardname() {
        return cardname;
    }

    public void setCardname(String cardname) {
        this.cardname = cardname;
    }


    public String empaquetarInformacion() {
        return this.cantidadDeCartas + " "
                + this.cardname+ "\n";
    }

}

