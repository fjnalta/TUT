public class Main {

    // Data
    public static int zahl = 0;
    public static int zahl2 = 0;


    public static void main(String[] args) {
        // create GUICentralized
        new GUICentralized();
        new GUIDecentralized();
    }

    // setter and getter
    public static int getZahl() {
        return zahl;
    }

    public static void setZahl(int zahl) {
        Main.zahl = zahl;
    }
}
