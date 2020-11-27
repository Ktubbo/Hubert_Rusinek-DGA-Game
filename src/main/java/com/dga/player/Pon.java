package com.dga.player;

import javafx.scene.control.Button;

public class Pon {

    int numberOfFieldsTraveled=0;
    int playerMapPonPosition;
    int boardPosition;
    int[] startPosition;
    int[] basePosition;
    Button button;
    Boolean canMove=false;

    public Pon(Button button,int[] startPosition, int[] basePosition) {
        this.button = button;
        this.startPosition = startPosition;
        this.basePosition = basePosition;
    }

    public int getNumberOfFieldsTraveled() {
        return numberOfFieldsTraveled;
    }

    public void setNumberOfFieldsTraveled(int numberOfFieldsTraveled) {
        this.numberOfFieldsTraveled = numberOfFieldsTraveled;
    }

    public int getPlayerMapPonPosition() {
        return playerMapPonPosition;
    }

    public void setPlayerMapPonPosition(int playerMapPonPosition) {
        this.playerMapPonPosition = playerMapPonPosition;
    }

    public Button getButton() {
        return button;
    }

    public int[] getStartPosition() {
        return startPosition;
    }

    public int[] getBasePosition() {
        return basePosition;
    }


    public int getBoardPosition() {
        return boardPosition;
    }

    public void setBoardPosition(int boardPosition) {
        this.boardPosition = boardPosition;
    }

    public Boolean getCanMove() { return canMove; }

    public void setCanMove(Boolean canMove) { this.canMove = canMove; }

    @Override
    public String toString() {
        return "Pon{" +
                "numberOfFieldsTraveled=" + numberOfFieldsTraveled +
                ", boardPosition=" + boardPosition +
                '}';
    }
}

