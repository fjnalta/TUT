package Data;

public class Spielbrett {

    private Spielfeld[] gameFields = new Spielfeld[40];
    private Spielfeld[] endFields = { new Spielfeld("E1"), new Spielfeld("E2"), new Spielfeld("E3"), new Spielfeld("E4") };
    private Spielfeld[] startFields = { new Spielfeld("S1"), new Spielfeld("S2"), new Spielfeld("S3"), new Spielfeld("S4") };

    public Spielbrett() {

        createGameFields();

    }

    private void createGameFields() {
        for (int i = 0; i < 40; i++) {
            gameFields[i] = new Spielfeld(String.valueOf(i+1));
        }
    }

    public Spielfeld[] getGameFields() {
        return gameFields;
    }

    public Spielfeld[] getEndFields() {
        return endFields;
    }

    public Spielfeld[] getStartFields() {
        return startFields;
    }

}