package Data;

public class Spielfeld {

    private int id;
    private boolean hasPlayer = false;
    private String name;

    public Spielfeld(int id) {
        this.id = id;
        this.hasPlayer = false;
    }

    public Spielfeld(String name) {
        this.name = name;
        this.hasPlayer = false;
    }

    public int getId() {
        return id;
    }

    public boolean isHasPlayer() {
        return hasPlayer;
    }

    public void setHasPlayer(boolean hasPlayer) {
        this.hasPlayer = hasPlayer;
    }

    @Override
    public String toString() {
        if(id != 0) {
            return String.valueOf(this.id);
        } else {
            return this.name;
        }
    }
}