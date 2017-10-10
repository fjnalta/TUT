public class Auto {

    private String color = "";
    private int kmh;
    private int tires;

    public Auto() {
        this.tires = 4;

//        if(this.kmh == 0) {
//            this.kmh = 500;
//        }
    }

    public Auto(String color) {
        this();
        this.color = color;
    }

    public Auto(int kmh) {
        this();
        this.kmh = kmh;
    }


    public Auto(String color, int kmh) {
        this();
        this.color = color;
        this.kmh = kmh;
    }


    @Override
    public String toString() {
        return "Tires: " + this.tires + "; Color: " + this.color + "; Km/h: " + this.kmh;
    }
}
