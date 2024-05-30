package Model;

public class Sidedeck extends Mazo {
    private String mazoAsociado;

    public Sidedeck(String usuarioActual, int cantidadDeCartas, String cardname, String mazoAsociado) {
        super(usuarioActual, cantidadDeCartas, cardname);
        this.mazoAsociado = mazoAsociado;
    }

    public String getMazoAsociado() {
        return mazoAsociado;
    }

    public void setMazoAsociado(String mazoAsociado) {
        this.mazoAsociado = mazoAsociado;
    }
}
