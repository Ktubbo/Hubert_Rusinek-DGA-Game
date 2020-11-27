package com.dga.player;

import com.dga.BoardMap;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.util.*;

public class Player {

    String playerName;
    LinkedList<Pon> ponInBaseList = new LinkedList<>();
    LinkedList<Pon> ponOnBoardList = new LinkedList<>();
    int[] startPosition;
    int[] dicePosition;
    List<int[]> winPositions;
    Map<Integer,int[]> playerMap = new HashMap<>();
    int boardWinNumber;
    boolean playedByComputer=false;
    int startBoardPosition;

    public Player(Image image, int[] startPosition, List<int[]> basePositions, List<int[]> winPositions, int[] dicePosition,
                  int boardWinNumber){

        List<ImageView> imageViews = new ArrayList<>();
        for(int i=0;i<4;i++){
            imageViews.add(new ImageView(image));
            imageViews.get(i).setFitHeight(65);
            imageViews.get(i).setFitWidth(65);}
        this.startPosition = startPosition;
        this.winPositions = winPositions;
        this.dicePosition = dicePosition;
        this.boardWinNumber = boardWinNumber;
        for(int i=0;i<4;i++){
            ponInBaseList.add(new Pon(new Button(),startPosition,basePositions.get(i)));}
        for(int i=0;i<4;i++){
            ponInBaseList.get(i).getButton().setGraphic(imageViews.get(i));
            ponInBaseList.get(i).getButton().setStyle("-fx-border-color: transparent;-fx-background-color: transparent;");}

        BoardMap boardMap = new BoardMap();
        int key = boardMap.getKey(startPosition).get();

        playerMap.put(1,startPosition);
        for(int i=1; i<=41;i++){

            if(key+i<=40){
                playerMap.put(i+1,boardMap.getBoardMap().get(key+i));
            } else {
                playerMap.put(i+1,boardMap.getBoardMap().get(i-(40-key)));
            }
        }

        for(int i=1;i<=winPositions.size();i++){

            playerMap.put(40+i,winPositions.get(i-1));
        }

        startBoardPosition = boardMap.getKey(new int[]{getStartPosition()[0], getStartPosition()[1]}).get();
    }

    public LinkedList<Pon> getPonOnBoardList() {
        return ponOnBoardList;
    }

    public int[] getStartPosition() {
        return startPosition;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int[] getDicePosition() {
        return dicePosition;
    }

    public LinkedList<Pon> getPonInBaseList() {
        return ponInBaseList;
    }

    public Map<Integer, int[]> getPlayerMap() {
        return playerMap;
    }

    public void sendPonOnBoard(Pon pon, GridPane grid){

        BoardMap boardMap = new BoardMap();
        ponInBaseList.remove(pon);
        ponOnBoardList.add(pon);
        grid.getChildren().remove(pon.getButton());
        grid.add(pon.getButton(),pon.getStartPosition()[0],pon.getStartPosition()[1]);
        pon.setPlayerMapPonPosition(1);
        pon.setBoardPosition(boardMap.getKey(new int[]{pon.getStartPosition()[0],
        pon.getStartPosition()[1]}).get());
        pon.getButton().setOnAction(e->{});

    }

    public void sendPonToBase(Pon pon, GridPane grid){
        ponOnBoardList.remove(pon);
        ponInBaseList.add(pon);
        grid.getChildren().remove(pon.getButton());
        grid.add(pon.getButton(),pon.getBasePosition()[0],pon.getBasePosition()[1]);
        pon.setNumberOfFieldsTraveled(0);
        pon.setPlayerMapPonPosition(0);
        pon.setBoardPosition(0);
    }

    @Override
    public String toString() {
        return "Player " + playerName;
    }

    public int getBoardWinNumber() {
        return boardWinNumber;
    }

    public boolean isPlayedByComputer() {
        return playedByComputer;
    }

    public void setPlayedByComputer(boolean playedByComputer) {
        this.playedByComputer = playedByComputer;
    }

    public int getStartBoardPosition() {
        return startBoardPosition;
    }
}
