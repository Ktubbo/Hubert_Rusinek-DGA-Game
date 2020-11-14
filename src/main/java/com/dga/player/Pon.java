package com.dga.player;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class Pon {

    int numberOfFieldsTraveled=0;
    int ponPosition;
    int[] startPosition;
    int[] basePosition;
    Button button;


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

    public int getPonPosition() {
        return ponPosition;
    }

    public void setPonPosition(int ponPosition) {
        this.ponPosition = ponPosition;
    }

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }

    public int[] getStartPosition() {
        return startPosition;
    }

    public int[] getBasePosition() {
        return basePosition;
    }


    public void start(GridPane grid){

        grid.getChildren().remove(button);
        grid.add(button,startPosition[0],startPosition[1]);
        setPonPosition(1);

    }

}

