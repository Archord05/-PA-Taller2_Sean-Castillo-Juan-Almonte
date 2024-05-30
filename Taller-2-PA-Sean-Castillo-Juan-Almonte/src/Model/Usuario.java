package Model;

public class Usuario {
    private String nombreUsuario;
    private String contrasenia;
    private int mazo;
    private int mazo2;
    private int mazo3;
    private int mazo4;

    public Usuario(String nombreUsuario, String contrasenia) {
        this.nombreUsuario = nombreUsuario;
        this.contrasenia = contrasenia;
    }

    public Usuario(String nombreUsuario, String contrasenia, int mazo, int mazo2, int mazo3, int mazo4) {
        this.nombreUsuario = nombreUsuario;
        this.contrasenia = contrasenia;
        this.mazo = mazo;
        this.mazo2 = mazo2;
        this.mazo3 = mazo3;
        this.mazo4 = mazo4;
    }

    public int getMazo() {
        return mazo;
    }

    public void setMazo(int mazo) {
        this.mazo = mazo;
    }

    public int getMazo2() {
        return mazo2;
    }

    public void setMazo2(int mazo2) {
        this.mazo2 = mazo2;
    }

    public int getMazo3() {
        return mazo3;
    }

    public void setMazo3(int mazo3) {
        this.mazo3 = mazo3;
    }

    public int getMazo4() {
        return mazo4;
    }

    public void setMazo4(int mazo4) {
        this.mazo4 = mazo4;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

}
