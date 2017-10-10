package Data;

public class Spielfigur {

    private Spielfeld position;
    private boolean validGameFieldPosition = false;
    private FarbEnum color;

    public Spielfigur(FarbEnum color, Spielfeld position) {
        this.position = position;
        this.color = color;
    }

    public Spielfeld getPosition() {
        return position;
    }

    public void setPosition(Spielfeld position) {
        this.position = position;
    }


    public boolean isValidGameFieldPosition() {
        return validGameFieldPosition;
    }

    public void setValidGameFieldPosition(boolean validGameFieldPosition) {
        this.validGameFieldPosition = validGameFieldPosition;
    }

    public FarbEnum getColor() {
        return color;
    }

    public void setColor(FarbEnum color) {
        this.color = color;
    }


}