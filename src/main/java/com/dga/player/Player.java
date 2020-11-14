package com.dga.player;

import com.dga.BoardMap;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Player {

    String playerName;
    List<Pon> ponList = new ArrayList<>();
    int[] startPosition;
    List<int[]> winPositions;
    Map<Integer,int[]> playerMap = new HashMap<>();
    Map<Integer, Boolean> ponOnBoardMap = new HashMap<>();

    public Player(Image image, int[] startPosition, List<int[]> basePositions, List<int[]> winPositions){

        List<ImageView> imageViews = new ArrayList<>();
        for(int i=0;i<4;i++){
            imageViews.add(new ImageView(image));
            imageViews.get(i).setFitHeight(65);
            imageViews.get(i).setFitWidth(65);}
        this.startPosition = startPosition;
        this.winPositions = winPositions;
        for(int i=0;i<4;i++){ponList.add(new Pon(new Button(),startPosition,basePositions.get(i)));}
        for(int i=0;i<4;i++){ponList.get(i).getButton().setGraphic(imageViews.get(i));
            ponList.get(i).getButton().setStyle("-fx-border-color: transparent;-fx-background-color: transparent;");}

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

    }

    public void move(Pon pon, int tilesNumber, GridPane grid){

        grid.getChildren().remove(pon.getButton());
        grid.add(pon.getButton(),playerMap.get(pon.getPonPosition()+tilesNumber)[0],
                playerMap.get(pon.getPonPosition()+tilesNumber)[1]);
        pon.setPonPosition(pon.getPonPosition()+tilesNumber);
    }

    public void activate(Pon pon,GridPane grid){

        pon.getButton().setOnAction((e) -> {
            move(pon,1, grid);
        });
    }

    public void deactivate(Pon pon){
        pon.getButton().setOnAction((e) -> {});
    }



    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public List<Pon> getPonList() {
        return ponList;
    }

    public Map<Integer, int[]> getPlayerMap() {
        return playerMap;
    }

    public Map<Integer, Boolean> getPonOnBoardMap() {
        return ponOnBoardMap;
    }

    public void setPonOnBoardMap(Map<Integer, Boolean> ponOnBoardMap) {
        this.ponOnBoardMap = ponOnBoardMap;
    }
}
