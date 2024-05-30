package Model;

public abstract class Tierra {

    private String landCardName;
    private String type;
    private String color;

    public Tierra(String landCardName, String type, String color) {
        this.landCardName = landCardName;
        this.type = type;
        this.color = color;
    }

    public abstract String empaquetarInformacionTierra();

    public String getLandCardName() {
        return landCardName;
    }

    public String getType() {
        return type;
    }

    public String getColor() {
        return color;
    }

    public void setLandCardName(String landCardName) {
        this.landCardName = landCardName;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
