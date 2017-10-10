import Data.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Spiel {

    // mandatory fields
    private Spielbrett game;
    private List<Spieler> players = new ArrayList<Spieler>();
    private int whosTurn;



    public Spiel(){

        createGame();



        System.out.println(this.players.size());

        setPlayerStartPositions();

        gameLoop();

        // End the game
        System.out.println("ENDE! - Spieler " + whoWonTheGame().toString() + " hat gewonnen");

    }

    // Main Loop
    // ---------

    private void gameLoop() {
        // Create the dice
        Würfel dice = new Würfel();

        // Randomize who starts the game
        whosTurn = (int)(Math.random() * this.players.size() + 1);

        // Keep playing
        while(!gameFinished()) {
            System.out.println("Spieler " + whosTurn + " ist an der Reihe!");

            int move = dice.werfen();
            System.out.println("Es wurde " + move + " gewürfelt!");
            System.out.println("-------------------------------");
            System.out.println("Die Spielfiguren stehen auf:");

            // count the figures
            int counter = 1;

            for(Spielfigur curFigure : this.players.get(whosTurn - 1).getSpielfigurs()) {
                System.out.println(counter + ": " + curFigure.getPosition());
                counter++;
            }

            System.out.println("-------------------------------");

            // check if there is the first 6
            if(move == 6 && !checkIfPlayerCanMove()) {
                int startNumber = 999;

                Spielfigur[] current = this.players.get(whosTurn -1).getSpielfigurs();
                current[0].setValidGameFieldPosition(true);

                if(this.players.get(whosTurn -1).getColor().equals(FarbEnum.blue)) {
                    startNumber = 11 - 1;
                } else if(this.players.get(whosTurn -1).getColor().equals(FarbEnum.green)) {
                    startNumber = 21 - 1;
                } else if(this.players.get(whosTurn -1).getColor().equals(FarbEnum.yellow)) {
                    startNumber = 31 - 1;
                } else if(this.players.get(whosTurn -1).getColor().equals(FarbEnum.red)) {
                    startNumber = 1 - 1;
                }

                // strike player if there is one
                if (game.getGameFields()[startNumber].isHasPlayer()) {

                    // TODO - search for the player on field and move him back to start
                } else {

                    current[0].setPosition(game.getGameFields()[startNumber]);
                }

            }

            // check if the player can move
            if(checkIfPlayerCanMove()) {

                System.out.println("Bitte wählen Sie die zu rückende Figur aus");

                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

                try {
                    String figureInput = br.readLine();

                    // check if the figure is already in game
                    boolean isInGame = this.players.get(whosTurn -1).getSpielfigurs()[Integer.parseInt(figureInput) - 1].isValidGameFieldPosition();

                    if(isInGame) {
                        //
                        Spielfigur current = this.players.get(whosTurn -1).getSpielfigurs()[Integer.parseInt(figureInput) - 1];

                        Spielfeld currentField = current.getPosition();


                    // no Figure in Game
                    } else {
                            // TODO
                    }

                } catch (IOException e) {
                    // TODO - exception handling -> wrong figure input
                    e.printStackTrace();
                }



            } else {

                System.out.println("Sie können nichts machen! Der nächste Spieler ist an der Reihe.");
                System.out.println("---------------------------------------------------------------");
                System.out.println("---------------------------------------------------------------");
            }

            // change player
            nextPlayersTurn();
        }
    }


    // Helper Methods
    // --------------

    // Managing the Game

    private void setPlayerStartPositions() {
        for(Spieler curPlayer : this.players) {
            for(int i = 0; i < 4; i++) {
                curPlayer.getSpielfigurs()[i].setColor(curPlayer.getColor());
            }
        }
    }

    // Managing Turns

    private void nextPlayersTurn() {
        whosTurn++;
        if(whosTurn > this.players.size()) {
            whosTurn = 1;
        }
    }

    private boolean checkIfPlayerCanMove() {
        for(Spielfigur curFigure : this.players.get(whosTurn - 1).getSpielfigurs()) {
            if(curFigure.isValidGameFieldPosition()) {
                return true;
            }
        }

        return false;
    }


    // Managing the Game status

    private boolean gameFinished() {

        // Check if a player has all figures in endfields

        for (Spieler curPlayer : this.players) {

            if(curPlayer.checkIfGameFinished()) {

                return true;
            }
        }

        return false;
    }

    private Spieler whoWonTheGame() {
        return null;
    }

    // Creating the Game

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
