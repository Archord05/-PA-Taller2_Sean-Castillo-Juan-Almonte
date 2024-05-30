package Model;

    public  abstract class  Carta {

        private String cardname;
        private String description;
        private String manaCost;
        private String type;
        private int power;
        private int toughness;
        private int castingCost;

        public Carta(String cardname, String description, String manaCost, String type, int power, int tougness, int castingCost) {
            this.cardname = cardname;
            this.description = description;
            this.manaCost = manaCost;
            this.type = type;
            this.power = power;
            this.toughness = tougness;
            this.castingCost = castingCost;
        }

        public abstract String empaquetarInformacion();

        public String getCardname() {
            return cardname;
        }

        public void setCardname(String cardname) {
            this.cardname = cardname;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getManaCost() {
            return manaCost;
        }

        public void setManaCost(String manaCost) {
            this.manaCost = manaCost;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getPower() {
            return power;
        }

        public void setPower(int power) {
            this.power = power;
        }

        public int getToughness() {
            return toughness;
        }

        public void setToughness(int toughness) {
            this.toughness = toughness;
        }

        public int getCastingCost() {
            return castingCost;
        }

        public void setCastingCost(int castingCost) {
            this.castingCost = castingCost;
        }
        //- castingCost; int
        //+ Carta(cardName; String, description; String, manaCost; int,
        //  type; String, power; int, tougness; int, castingCost; int)
        //+ empaquetarInformacion();
        //+ get´s()
        //+ set´s()
        //}
    }


