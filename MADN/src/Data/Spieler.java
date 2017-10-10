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

    public boolean checkIfGameFinished() {
        int finishedFigures = 0;

        for(Spielfigur currentFigure : spielfigurs) {
            if(currentFigure.getPosition().equals("E1") ||
                    currentFigure.getPosition().equals("E2") ||
                    currentFigure.getPosition().equals("E3") ||
                    currentFigure.getPosition().equals("E4")) {

                finishedFigures++;
            }
        }

        if(finishedFigures == 4) {
            return true;
        } else {
            return false;
        }
    }

    public Spielfigur[] getSpielfigurs() {
        return spielfigurs;
    }

    @Override
    public String toString() {
        return this.name;
    }
}