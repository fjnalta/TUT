import Data.FarbEnum;
import Data.Spielbrett;
import Data.Spieler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Spiel {

    // mandatory fields
    private Spielbrett game;
    private List<Spieler> players = new ArrayList<Spieler>();
    private long whosTurn;



    public Spiel(){

        createGame();

        System.out.println(this.players.size());

    }

    // Helper Methods

    private void createGame() {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int players = 0;

        System.out.println("Bitte geben Sie die Anzahl an Spielern ein");

        try {
            players = Integer.parseInt(br.readLine());

            if(players < 1 || players > 4) {

                System.out.println("Bitte geben Sie eine gültige Anzahl an Spielern ein");

            } else {

                // create Players as long as the chosen amount is more than the list of players
                while(this.players.size() < players) {
                    createPlayer(setPlayerName(),setPlayerColor());
                }
            }

        } catch (IOException e) {

            System.out.println("Bitte geben Sie eine gültige Zahl ein");
            e.printStackTrace();
        }

        // create Game
        this.game = new Spielbrett();
    }

    private String setPlayerName() {
        String name = null;

        try {
            System.out.println("Bitte geben Sie ihren Namen ein");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            name = br.readLine();

        } catch (IOException e) {
            System.out.println("Bitte geben Sie einen gültigen Namen ein");
        }

        return name;
    }

    private FarbEnum setPlayerColor() {
        // choose Player color
        System.out.println("Bitte wählen Sie Ihre Farbe:");
        System.out.println("1 = red; 2 = yellow; 3 = blue; 4 = green");

        // hint chosen colors
        if (this.players.size() > 0) {
            System.out.println("Folgende Farben sind bereits vergeben: ");

            for (int i = 0; i < this.players.size(); i++) {
                System.out.println(this.players.get(i).getColor());
            }
        }

        // start reading the color
        FarbEnum curColor = null;

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int color = Integer.parseInt(br.readLine());

            // TODO - more exceptions

            switch (color) {
                case 1:
                    curColor = FarbEnum.red;
                    break;
                case 2:
                    curColor = FarbEnum.yellow;
                    break;
                case 3:
                    curColor = FarbEnum.blue;
                    break;
                case 4:
                    curColor = FarbEnum.green;
                    break;
            }

        } catch (IOException e) {
            System.out.println("Bitte wählen Sie eine gültige Farbe");
        }

        return curColor;
    }

    private Spieler createPlayer(String name, FarbEnum color) {
        Spieler currentPlayer = new Spieler(name,color);
        this.players.add(currentPlayer);

        return currentPlayer;
    }
}
