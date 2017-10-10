package Data;

public class Spielfeld {

    private String id;
    private boolean hasPlayer = false;

    public Spielfeld(String id) {
        this.id = id;
        this.hasPlayer = false;
    }

    public String getId() {
        return id;
    }

    public boolean isHasPlayer() {
        return hasPlayer;
    }

    public void setHasPlayer(boolean hasPlayer) {
        this.hasPlayer = hasPlayer;
    }
}