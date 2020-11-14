package com.dga;

import com.dga.player.Player;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class Engine {

    Player orange;
    Player turquoise;
    Player blue;
    Player violet;
    LinkedList<Player> playerList = new LinkedList<>();
    GridPane grid = new GridPane();
    Dice dice = new Dice();
    Boolean notEndOfGame = true;

    public void playersSet() {

        List<int[]> orangeBasePositions = new ArrayList<>();
        orangeBasePositions.add(new int[]{0, 8});
        orangeBasePositions.add(new int[]{2, 8});
        orangeBasePositions.add(new int[]{0, 10});
        orangeBasePositions.add(new int[]{2, 10});

        List<int[]> orangeWinPositions = new ArrayList<>();
        orangeWinPositions.add(new int[]{5, 9});
        orangeWinPositions.add(new int[]{5, 8});
        orangeWinPositions.add(new int[]{5, 7});
        orangeWinPositions.add(new int[]{5, 6});
        orange = new Player(new Image("file:src/main/resources/OrngPon.png"), new int[]{4, 10},
                orangeBasePositions, orangeWinPositions);

        List<int[]> blueBasePositions = new ArrayList<>();
        blueBasePositions.add(new int[]{0, 0});
        blueBasePositions.add(new int[]{2, 0});
        blueBasePositions.add(new int[]{0, 2});
        blueBasePositions.add(new int[]{2, 2});

        List<int[]> blueWinPositions = new ArrayList<>();
        blueWinPositions.add(new int[]{1, 5});
        blueWinPositions.add(new int[]{2, 5});
        blueWinPositions.add(new int[]{3, 5});
        blueWinPositions.add(new int[]{4, 5});
        blue = new Player(new Image("file:src/main/resources/BluePon.png"), new int[]{0, 4},
                blueBasePositions, blueWinPositions);

        List<int[]> turquoiseBasePositions = new ArrayList<>();
        turquoiseBasePositions.add(new int[]{8, 8});
        turquoiseBasePositions.add(new int[]{10, 8});
        turquoiseBasePositions.add(new int[]{8, 10});
        turquoiseBasePositions.add(new int[]{10, 10});

        List<int[]> turquoiseWinPositions = new ArrayList<>();
        turquoiseWinPositions.add(new int[]{9, 5});
        turquoiseWinPositions.add(new int[]{8, 5});
        turquoiseWinPositions.add(new int[]{7, 5});
        turquoiseWinPositions.add(new int[]{6, 5});
        turquoise = new Player(new Image("file:src/main/resources/TurqPon.png"), new int[]{10, 6},
                turquoiseBasePositions, turquoiseWinPositions);

        List<int[]> violetBasePositions = new ArrayList<>();
        violetBasePositions.add(new int[]{8, 0});
        violetBasePositions.add(new int[]{10, 0});
        violetBasePositions.add(new int[]{8, 2});
        violetBasePositions.add(new int[]{10, 2});

        List<int[]> violetWinPositions = new ArrayList<>();
        violetWinPositions.add(new int[]{5, 1});
        violetWinPositions.add(new int[]{5, 2});
        violetWinPositions.add(new int[]{5, 3});
        violetWinPositions.add(new int[]{5, 4});
        violet = new Player(new Image("file:src/main/resources/VioletPon.png"), new int[]{6, 0},
                violetBasePositions, violetWinPositions);

        playerList.add(0,blue);
        playerList.add(1,violet);
        playerList.add(2,orange);
        playerList.add(3,turquoise);

        /*BoardMap boardMap = new BoardMap();

        System.out.println(boardMap.getKey(orange.getPonList().get(0).getStartPosition()).get());
        for (int i = 1; i <= orange.getPlayerMap().size(); i++) {
            System.out.println(Arrays.toString(orange.getPlayerMap().get(i)) + " -> " + i);
        }

        System.out.println(boardMap.getKey(blue.getPonList().get(0).getStartPosition()).get());
        for (int i = 1; i <= blue.getPlayerMap().size(); i++) {
            System.out.println(Arrays.toString(blue.getPlayerMap().get(i)) + " -> " + i);
        }*/

    }

    public void gridSet(){

        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(new Image("file:src/main/resources/Table.png"),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);

        grid.setAlignment(Pos.CENTER);
        grid.setBackground(background);
        int gridSize = 80;
        for(int i=1; i<=11; i++){
            grid.getColumnConstraints().add(new ColumnConstraints(gridSize));
        }
        for(int i=1; i<=11; i++){
            grid.getRowConstraints().add(new RowConstraints(gridSize));
        }
        grid.setGridLinesVisible(true);

    }

    public void playersPonSet(int numberOfPlayers){

        for(int i=0;i<numberOfPlayers;i++){
            for(int j=0;j<playerList.get(i).getPonList().size();j++){
                grid.add(playerList.get(i).getPonList().get(j).getButton(),playerList.get(i).
                        getPonList().get(j).getBasePosition()[0],playerList.get(i).
                        getPonList().get(j).getBasePosition()[1]);
            }
        }

    }

    public GridPane getGrid() {
        return grid;
    }

    public void baseRound(Player player){

        int[] dicePosition = {player.getPonList().get(0).getBasePosition()[0]+1,
                player.getPonList().get(0).getBasePosition()[1]+1};

        dice.rollDice();

        dice.getDice().setOnAction(e->{

            dice.rollDice();

            if(dice.getRandomInt()!=6){

                dice.getDice().setOnAction(f->{

                    dice.rollDice();

                    if(dice.getRandomInt()!=6){

                        if(dice.getRandomInt()!=6){
                            dice.getDice().setOnAction(g->{
                                endTurn(dice);
                            });
                        } else {activateAllPons(player);}

                } else {
                        activateAllPons(player);
                    }
            });
            } else {activateAllPons(player);}

        });

        grid.add(dice.getDice(),dicePosition[0],dicePosition[1]);


    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    public void startGame(){

        //PLAYER 1
        dice.setRandomInt(0);
        Player player = playerList.getFirst();
        if(player.getPonOnBoardMap().isEmpty()){
            
            baseRound(player);
        } else {
            System.out.println("It works!");
        }
    }

    public void activateAllPons(Player player) {
        for (int j = 0; j < player.getPonList().size(); j++) {
            int finalJ = j;
            player.getPonList().get(j).getButton().setOnAction(e -> {
                grid.getChildren().remove(player.getPonList().get(finalJ).getButton());
                grid.add(player.getPonList().get(finalJ).getButton(),
                        player.getPonList().get(finalJ).getStartPosition()[0],
                        player.getPonList().get(finalJ).getStartPosition()[1]);
                player.getPonOnBoardMap().put(1,true);
                endTurn(dice);
            });
        }
    }

    public void endTurn(Dice dice){

        grid.getChildren().remove(dice.getDice());
        playerList.addLast(playerList.getFirst());
        playerList.removeFirst();
    }

    public Boolean getNotEndOfGame() {
        return notEndOfGame;
    }

    public void setNotEndOfGame(Boolean notEndOfGame) {
        this.notEndOfGame = notEndOfGame;
    }
}
