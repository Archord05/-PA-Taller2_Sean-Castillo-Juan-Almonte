package Model;

public class Tierra2 extends Tierra {
    public Tierra2(String landCardName, String type, String color) {
        super(landCardName, type, color);
    }

    @Override
    public String empaquetarInformacionTierra() {
        return this.getLandCardName() + " "
                + this.getType() + " "
                + this.getColor()+ "\n";
    }
}

