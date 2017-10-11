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

        // create Game
        this.game = new Spielbrett();
        createGame();

        System.out.println(this.players.size());

        setPlayerStartPositions();


        Spieler winner = gameLoop();

        // End the game
        System.out.println("ENDE! - Spieler " + winner.toString() + " hat gewonnen!!111");

    }

    // Main Loop
    // ---------

    private Spieler gameLoop() {
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
                System.out.println(counter + ": " + curFigure.getPosition().toString());
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
            }

            // check if the player can move
            if(checkIfPlayerCanMove() || move == 6) {

                System.out.println("Bitte wählen Sie die zu rückende Figur aus");

                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

                try {
                    String figureInput = br.readLine();

                    // check if the figure is already in game
                    boolean isInGame = this.players.get(whosTurn -1).getSpielfigurs()[Integer.parseInt(figureInput) - 1].isValidGameFieldPosition();


                    if(isInGame) {
                        Spielfigur current = this.players.get(whosTurn -1).getSpielfigurs()[Integer.parseInt(figureInput) - 1];

                        // strike player if there is one
                        if (Spielbrett.getGameFieldPosition(current.getPosition().getId() + move).isHasPlayer()) {
                            // TODO - search for the player on field and move him back to start

                        } else {
                            current.setPosition(Spielbrett.getGameFieldPosition(current.getPosition().getId() + move));
                            System.out.println("Die Figur" + figureInput + " steht nun auf " + current.getPosition());

                        }

                    // no Figure in Game
                    } else {
                        // if there is a 6 - start the game
                        if(move == 6) {

                            Spielfigur current = this.players.get(whosTurn -1).getSpielfigurs()[Integer.parseInt(figureInput) - 1];

                            // different starting zones
                            if(current.getColor() == FarbEnum.green) {
                                if(Spielbrett.getGameFieldPosition(21).isHasPlayer()) {

                                    if(searchFigureWithPosition(21).getColor() == FarbEnum.green) {
                                        Spielfigur deadFigure = searchFigureWithPosition(21);
                                        backToStart(deadFigure);
                                    }

                                } else {

                                    current.setPosition(Spielbrett.getGameFieldPosition(21));
                                    current.setValidGameFieldPosition(true);
                                    Spielbrett.getGameFieldPosition(21).setHasPlayer(true);
                                }
                            } else if(current.getColor() == FarbEnum.blue) {
                                if(Spielbrett.getGameFieldPosition(11).isHasPlayer()) {

                                    if(searchFigureWithPosition(11).getColor() == FarbEnum.blue) {
                                        Spielfigur deadFigure = searchFigureWithPosition(11);
                                        backToStart(deadFigure);
                                    }

                                } else {

                                    current.setPosition(Spielbrett.getGameFieldPosition(11));
                                    current.setValidGameFieldPosition(true);
                                    Spielbrett.getGameFieldPosition(11).setHasPlayer(true);
                                }
                            } else if(current.getColor() == FarbEnum.red) {
                                if(Spielbrett.getGameFieldPosition(1).isHasPlayer()) {

                                    if(searchFigureWithPosition(1).getColor() != FarbEnum.red) {
                                        Spielfigur deadFigure = searchFigureWithPosition(1);
                                        backToStart(deadFigure);
                                    }

                                } else {

                                    current.setPosition(Spielbrett.getGameFieldPosition(1));
                                    current.setValidGameFieldPosition(true);
                                    Spielbrett.getGameFieldPosition(1).setHasPlayer(true);
                                }
                            } else if(current.getColor() == FarbEnum.yellow) {
                                if(Spielbrett.getGameFieldPosition(31).isHasPlayer()) {

                                    if(searchFigureWithPosition(31).getColor() != FarbEnum.yellow) {
                                        Spielfigur deadFigure = searchFigureWithPosition(31);
                                        backToStart(deadFigure);
                                    }

                                } else {

                                    current.setPosition(Spielbrett.getGameFieldPosition(31));
                                    current.setValidGameFieldPosition(true);
                                    Spielbrett.getGameFieldPosition(31).setHasPlayer(true);
                                }
                            }
                        }
                    }

                } catch (IOException e) {
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

        return this.players.get(whosTurn -1);
    }


    // Helper Methods
    // --------------

    // Managing the Game

    private void configureFigures() {

    }

    private void setPlayerStartPositions() {
        for(Spieler curPlayer : this.players) {
            for(int i = 0; i < 4; i++) {
                curPlayer.getSpielfigurs()[i].setPosition(game.getStartFields()[i]);

                System.out.println("Start Positions " + curPlayer.toString() + " is " + curPlayer.getSpielfigurs()[i].getPosition().toString());
            }
        }
    }

    private Spielfigur searchFigureWithPosition(int i) {
        for(Spieler players : this.players) {
            for(Spielfigur figure : players.getSpielfigurs()) {
                if(figure.getPosition().equals(Spielbrett.getGameFieldPosition(i))) {
                    return figure;
                }
            }
        }

        return null;
    }

    private void backToStart(Spielfigur current) {
        for(Spieler player : this.players) {
            if(player.getColor() == current.getColor()) {
                for(Spielfigur figure : player.getSpielfigurs()) {
                    if(figure.getPosition() == current.getPosition()) {
                        for(Spielfeld startField : game.getStartFields()) {
                            if(!startField.isHasPlayer()) {
                                figure.setPosition(startField);
                                figure.setValidGameFieldPosition(false);
                            }
                        }
                    }
                }
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

    private boolean checkIfPlayerHasFiguresInBank() {
        boolean check = false;

        for(Spielfigur curFigure : this.players.get(whosTurn -1).getSpielfigurs()) {
            if(curFigure.isValidGameFieldPosition()) {
                continue;
            } else {
                check = true;
            }
        }
        return check;
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

    // Creating the Game

    private void createGame() {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int players = 0;

        System.out.println("Bitte geben Sie die Anzahl der Spieler ein");

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
        for(int i = 0; i < currentPlayer.getSpielfigurs().length; i ++) {

            currentPlayer.getSpielfigurs()[i] = new Spielfigur(currentPlayer.getColor(),game.getStartFields()[i]);
        }
        this.players.add(currentPlayer);

        return currentPlayer;
    }
}
