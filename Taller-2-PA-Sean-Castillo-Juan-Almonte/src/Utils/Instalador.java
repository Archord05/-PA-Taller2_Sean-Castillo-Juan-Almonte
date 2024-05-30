package Utils;

import Services.ISistemaGestorDeMazo;
import Services.SistemaGestorDeMazo;

import java.io.IOException;

public class Instalador {
    private ISistemaGestorDeMazo instaladorMazos;

    public Instalador() throws IOException {
        this.instaladorMazos =new SistemaGestorDeMazo();
    }
    public ISistemaGestorDeMazo instalarSistema(){

        return this.instaladorMazos;
    }

}
