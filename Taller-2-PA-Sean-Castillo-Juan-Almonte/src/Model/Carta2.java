package Model;

public class Carta2 extends Carta{

    public Carta2(String cardname, String description, String manaCost, String type, int power, int tougness, int castingCost) {
        super(cardname, description, manaCost, type, power, tougness, castingCost);
    }

    @Override
    public String empaquetarInformacion() {
        return this.getCardname() + " "
                + this.getDescription() + " "
                + this.getManaCost() + " "
                + this.getType() + " "
                + this.getPower() + " "
                + this.getToughness() + " "
                + this.getCastingCost()+ "\n";
    }
}
