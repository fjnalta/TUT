package Data;

public class Spieler {

    private String name;
    private Spielfigur[] spielfigurs;
    private Würfel würfel;
    private FarbEnum color;

    public Spieler(String name, FarbEnum color) {
        this.name = name;
        this.color = color;
        this.spielfigurs = new Spielfigur[4];
        this.würfel = new Würfel();

    }

    public FarbEnum getColor() {
        return color;
    }
}