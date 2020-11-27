package com.dga;

import com.dga.player.Player;

import com.dga.player.Pon;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.*;


public class Engine {

    Player orange;
    Player turquoise;
    Player blue;
    Player violet;
    LinkedList<Player> playerList = new LinkedList<>();
    GridPane grid = new GridPane();
    Dice dice = new Dice();
    Map<Integer, Player> occupationMap = new HashMap<>();
    BoardMap boardMap = new BoardMap();

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
                orangeBasePositions, orangeWinPositions, new int[]{1,9},63);
        orange.setPlayerName("orange");

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
                blueBasePositions, blueWinPositions, new int[]{1,1},73);
        blue.setPlayerName("blue");

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
                turquoiseBasePositions, turquoiseWinPositions, new int[]{9,9},53);
        turquoise.setPlayerName("turquoise");

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
                violetBasePositions, violetWinPositions, new int[]{9,1},43);
        violet.setPlayerName("violet");

        playerList.add(0,blue);
        playerList.add(1,violet);
        playerList.add(2,orange);
        playerList.add(3,turquoise);

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
    }

    public void playersPonSet(int numberOfPlayers){

        for(int i=0;i<numberOfPlayers;i++){
            for(int j = 0; j<playerList.get(i).getPonInBaseList().size(); j++){
                grid.add(playerList.get(i).getPonInBaseList().get(j).getButton(),playerList.get(i).
                        getPonInBaseList().get(j).getBasePosition()[0],playerList.get(i).
                        getPonInBaseList().get(j).getBasePosition()[1]);
            }
        }

    }

    public GridPane getGrid() {
        return grid;
    }

    public void baseRound(Player player){

        int[] dicePosition = {player.getDicePosition()[0],
                player.getDicePosition()[1]};

        dice.activateDice();
        dice.rollDice();
        reRollDice(player);
        grid.add(dice.getDice(),dicePosition[0],dicePosition[1]);

    }

    public void computerBaseRound(Player player){
        int[] dicePosition = {player.getDicePosition()[0],
                player.getDicePosition()[1]};

        dice.rollDice();
        grid.add(dice.getDice(),dicePosition[0],dicePosition[1]);
        computerReRollDice(player);
    }

    public void normalRound(Player player){

        grid.add(dice.getDice(), player.getDicePosition()[0], player.getDicePosition()[1]);
        dice.rollDice();
        if(checkIfAnyPonCanMove(player)){
            allowToMove(player,dice.getRandomInt());
            if(dice.getRandomInt()==6){ activateAllBasePons(player);}
        } else {
            if(dice.getRandomInt()==6){ activateAllBasePons(player);}
            if(!player.getPonInBaseList().isEmpty()){
                reRollDice(player);
            } else { reRollDiceEndOfGame(player); }
        }
    }

    public void moveFurthestComputerPon(Player player){
        LinkedList<Pon> temporaryPonList = new LinkedList<>();
        Pon temporaryPon = null;
        temporaryPonList.addAll(player.getPonOnBoardList());

        temporaryPon=furthestComputerPon(temporaryPonList);
        while(!temporaryPon.getCanMove()){
            temporaryPonList.remove(temporaryPon);
            temporaryPon=furthestComputerPon(temporaryPonList);
        }

        move(player,temporaryPon, dice.getRandomInt());
    }

    public Pon furthestComputerPon(List<Pon> list) {
        Pon pon = list.get(0);

        if (list.size() > 1) {
            for (int i = 0; i < list.size() - 1; i++) {
                if (list.get(i).getNumberOfFieldsTraveled() <
                        list.get(i + 1).getNumberOfFieldsTraveled()) {
                    pon = list.get(i + 1);
                }
            }
        }

            return pon;
    }

    public void computerNormalRound(Player player){

        grid.add(dice.getDice(), player.getDicePosition()[0], player.getDicePosition()[1]);
        dice.rollDice();
        int startBoardPosition = boardMap.getKey(new int[]{player.getStartPosition()[0], player.getStartPosition()[1]}).get();
        if(checkIfAnyPonCanMove(player)){
            if(dice.getRandomInt()==6){
                if(occupationMap.get(startBoardPosition)==player){ moveFurthestComputerPon(player); }
                else { computerMoveBasePon(player); }
            } else { moveFurthestComputerPon(player); }
        } else {
            if(dice.getRandomInt()==6){ computerMoveBasePon(player); }
            else { computerReRollDice(player);}
        }
    }


    public void playerRound(){

        dice.setRandomInt(0);
        Player player = playerList.getFirst();

        if(player.isPlayedByComputer()){

            if(!occupationMap.containsValue(player)){ computerBaseRound(player); }
            else { computerNormalRound(player); }

        } else {

            if(!occupationMap.containsValue(player)){ baseRound(player); }
            else { normalRound(player); }
        }
    }

    public void activateAllBasePons(Player player) {
        int startBoardPosition = boardMap.getKey(new int[]{player.getStartPosition()[0], player.getStartPosition()[1]}).get();
        if (occupationMap.get(startBoardPosition) != player) {
            if (!occupationMap.containsKey(startBoardPosition)) {
                for (int j = 0; j < player.getPonInBaseList().size(); j++) {
                    int finalJ = j;
                    player.getPonInBaseList().get(j).getButton().setOnAction(e -> {
                            player.sendPonOnBoard(player.getPonInBaseList().get(finalJ), grid);
                            occupationMap.put(startBoardPosition, player);
                            endTurn();
                            deactivateAllPons(player);
                        });
                    }
                }
            } else {
                Player enemyPlayer = occupationMap.get(startBoardPosition);
                for (int j = 0; j < player.getPonInBaseList().size(); j++) {
                    int finalJ = j;
                    player.getPonInBaseList().get(j).getButton().setOnAction(e -> {
                            if (enemyPlayer.getPonOnBoardList().size() == 1) {
                                occupationMap.remove(startBoardPosition);
                                enemyPlayer.sendPonToBase(enemyPlayer.getPonOnBoardList().get(0), grid);
                            } else if (enemyPlayer.getPonOnBoardList().size() > 1) {
                                remove(startBoardPosition);
                            }
                            player.sendPonOnBoard(player.getPonInBaseList().get(finalJ), grid);
                            occupationMap.put(startBoardPosition, player);
                            endTurn();
                            deactivateAllPons(player);
                    });
                }
            }
    }

    public void computerMoveBasePon(Player player) {
        int startBoardPosition = player.getStartBoardPosition();
        if(occupationMap.containsKey(startBoardPosition)){
            if(occupationMap.get(startBoardPosition)!=player){
                remove(startBoardPosition);
            }
        }

        if(!player.getPonInBaseList().isEmpty()){
            player.sendPonOnBoard(player.getPonInBaseList().getFirst(),grid);
            occupationMap.put(startBoardPosition, player);
        }
        endTurn();
    }

    public void endTurn(){

        Button nextTurn = new Button("Next turn");
        nextTurn.setPrefSize(80,80);

        Player player = playerList.getFirst();


        if(occupationMap.get(player.getBoardWinNumber())==player &&
                occupationMap.get(player.getBoardWinNumber()+1)==player &&
                occupationMap.get(player.getBoardWinNumber()+2)==player &&
                occupationMap.get(player.getBoardWinNumber()+3)==player) {

            deactivateAllPons(player);
            dice.deactivateDice();


            Text label = new Text(player.toString() + " won!");
            label.setFont(new Font(20));
            label.setFill(Color.WHITE);
            GridPane.setHalignment(label,HPos.CENTER);

            Rectangle r = new Rectangle();
            r.setWidth(240);
            r.setHeight(80);
            r.setArcWidth(20);
            r.setArcHeight(20);
            r.setStyle("-fx-fill: black; -fx-stroke: red; -fx-stroke-width: 5;");
            grid.add(r,4,5);
            grid.add(label,5,5);

        } else {
            playerList.addLast(player);
            playerList.removeFirst();

            deactivateAllPons(player);
            dice.deactivateDice();
            grid.add(nextTurn,5,5);
            nextTurn.setOnAction(e->{
                grid.getChildren().remove(dice.getDice());
                if(!grid.getChildren().contains(dice)){
                    playerRound();
                    grid.getChildren().remove(nextTurn);
                }
            });

        }
    }

    public void move(Player player, Pon pon, int tilesNumber){

        int newBoardPosition = getRealBoardPosition(player,pon,tilesNumber);
        if(occupationMap.containsKey(newBoardPosition)){
            if (occupationMap.get(newBoardPosition)==player){
                if(player.isPlayedByComputer()){ endTurn(); }
            } else if(pon.getNumberOfFieldsTraveled()+tilesNumber>=40) { replacePon(player,pon,tilesNumber);
            } else {
                Player enemyPlayer = occupationMap.get(newBoardPosition);
                if(enemyPlayer.getPonOnBoardList().size()==1){
                    occupationMap.remove(newBoardPosition);
                    enemyPlayer.sendPonToBase(enemyPlayer.getPonOnBoardList().get(0),grid);
                    replacePon(player,pon,tilesNumber);
                } else if(enemyPlayer.getPonOnBoardList().size() > 1){
                    remove(newBoardPosition);
                    replacePon(player,pon,tilesNumber);
                }
            }

        } else { replacePon(player,pon,tilesNumber); }
    }

    public void replacePon(Player player, Pon pon, int tilesNumber){

        pon.setNumberOfFieldsTraveled(pon.getNumberOfFieldsTraveled()+tilesNumber);
        int newXPosition = player.getPlayerMap().get(pon.getPlayerMapPonPosition()+tilesNumber)[0];
        int newYPosition = player.getPlayerMap().get(pon.getPlayerMapPonPosition()+tilesNumber)[1];

        grid.getChildren().remove(pon.getButton());
        grid.add(pon.getButton(),newXPosition,newYPosition);
        occupationMap.remove(pon.getBoardPosition());
        occupationMap.put(boardMap.getKey(new int[]{newXPosition,newYPosition}).get(),player);

        pon.setPlayerMapPonPosition(pon.getPlayerMapPonPosition()+tilesNumber);
        pon.setBoardPosition(boardMap.getKey(new int[]{newXPosition,newYPosition}).get());
        endTurn();

    }

    public void allowToMove(Player player, int tilesNumber){

        for(Pon pon: player.getPonOnBoardList()){
            if(pon.getCanMove()){
                if(player.getPlayerMap().containsKey(pon.getPlayerMapPonPosition()+tilesNumber)){
                    pon.getButton().setOnAction((e) -> {
                        move(player, pon, tilesNumber);
                    });
                }
            }
        }
    }

    public void deactivateAllPons(Player player){
        if(!player.getPonOnBoardList().isEmpty()){
            if(player.getPonOnBoardList().size()==1){
                player.getPonOnBoardList().get(0).getButton().setOnAction((e) -> {});
                player.getPonOnBoardList().get(0).setCanMove(false);
            } else {
                for(int i=0; i<player.getPonOnBoardList().size();i++){
                    player.getPonOnBoardList().get(i).getButton().setOnAction((e) -> {});
                    player.getPonOnBoardList().get(i).setCanMove(false);
                }
            }
        }

        if(!player.getPonInBaseList().isEmpty()){
            if(player.getPonInBaseList().size()==1){
                player.getPonInBaseList().get(0).getButton().setOnAction((e) -> {});
            } else {
                for(int i=0; i<player.getPonInBaseList().size();i++){
                    player.getPonInBaseList().get(i).getButton().setOnAction((e) -> {});
                }
            }
        }
    }

    public void remove(int newPosition){
        Player enemyPlayer = occupationMap.get(newPosition);
        Pon enemyPon = null;
        for (Pon listPon : enemyPlayer.getPonOnBoardList()) {
            if (listPon.getBoardPosition() == newPosition) {
                enemyPon = listPon;
            }
        }
        occupationMap.remove(newPosition);
        enemyPlayer.sendPonToBase(enemyPon, grid);
    }

    public void reRollDice(Player player) {

        if(dice.getRandomInt()!=6){

            dice.getDice().setOnAction(e->{
                dice.rollDice();
                if (dice.getRandomInt() != 6) {

                    dice.getDice().setOnAction(f -> {

                        dice.rollDice();

                        if (dice.getRandomInt() != 6) {
                            endTurn();
                        } else {
                            activateAllBasePons(player);
                            dice.deactivateDice();
                        }
                    });
                } else {
                    activateAllBasePons(player);
                    dice.deactivateDice();
                }
            });

        } else { activateAllBasePons(player);
            dice.deactivateDice(); }

    }

    public void reRollDiceEndOfGame(Player player) {

        if(dice.getRandomInt()!=6){

            dice.getDice().setOnAction(e->{
                dice.rollDice();
                if (dice.getRandomInt() != 6) {

                    dice.getDice().setOnAction(f -> {

                        dice.rollDice();

                        if (dice.getRandomInt() != 6) {

                            endTurn();

                        } else {
                            if(checkIfAnyPonCanMove(player)){
                                allowToMove(player, dice.getRandomInt());
                            }
                            dice.deactivateDice();
                        }
                    });
                } else {
                    if(checkIfAnyPonCanMove(player)){
                        allowToMove(player, dice.getRandomInt());
                    }
                    dice.deactivateDice();
                }
            });

        } else {
            if(checkIfAnyPonCanMove(player)){
                allowToMove(player, dice.getRandomInt());
            }
            dice.deactivateDice(); }
    }


    public void computerReRollDice(Player player){

        if(dice.getRandomInt()!=6){
            dice.rollDice();
            if (dice.getRandomInt() != 6) {
                dice.rollDice();
                if (dice.getRandomInt() != 6) {
                    endTurn();
                } else { computerMoveBasePon(player); }
            } else { computerMoveBasePon(player); }
        } else { computerMoveBasePon(player);;}

    }


    public void checkIfPonCanMove(Player player, Pon pon){

        if(occupationMap.get(getRealBoardPosition(player,pon, dice.getRandomInt()))==player){
            pon.setCanMove(false);
        } else if(!player.getPlayerMap().containsKey(pon.getPlayerMapPonPosition()+dice.getRandomInt())){
            pon.setCanMove(false);
        } else { pon.setCanMove(true); }
    }

    public boolean checkIfAnyPonCanMove(Player player){

        for(Pon pon: player.getPonOnBoardList()) { checkIfPonCanMove(player,pon); }

        boolean canMove = false;

        for (Pon pon : player.getPonOnBoardList()) {
            if (pon.getCanMove()) {
                canMove = true;
                break;
            }
        }
        return canMove;
    }

    public void setPlayerOrComputerButton(Player player,int x, int y){

        Button playerButton = new Button("Player");
        playerButton.setPrefSize(80,80);
        Button computerButton = new Button("Computer");
        computerButton.setPrefSize(80,80);
        playerButton.setOnAction(e->{
            player.setPlayedByComputer(true);
            grid.getChildren().remove(playerButton);
            grid.add(computerButton,x,y);
        });

        computerButton.setOnAction(f->{
            player.setPlayedByComputer(false);
            grid.getChildren().remove(computerButton);
            grid.add(playerButton,x,y);
        });
        grid.add(playerButton,x,y);
    }

    public int getRealBoardPosition(Player player, Pon pon, int tilesNumber){

        int newBoardPosition = pon.getBoardPosition()+tilesNumber;

        if(pon.getBoardPosition()<40){
            if(pon.getNumberOfFieldsTraveled()+tilesNumber<=40){
                if(newBoardPosition>40){ newBoardPosition=newBoardPosition-40; }
            } else {
                newBoardPosition=newBoardPosition+40;
            }
        }

        return newBoardPosition;
    }

}
