package com.dga;

import javafx.scene.control.Button;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Dice {

    Button dice = new Button();
    ImageView one = new ImageView(new Image("file:src/main/resources/1.png"));
    ImageView two = new ImageView(new Image("file:src/main/resources/2.png"));
    ImageView three = new ImageView(new Image("file:src/main/resources/3.png"));
    ImageView four = new ImageView(new Image("file:src/main/resources/4.png"));
    ImageView five = new ImageView(new Image("file:src/main/resources/5.png"));
    ImageView six = new ImageView(new Image("file:src/main/resources/6.png"));

    List<ImageView> diceViewList = new ArrayList<>();

    int randomInt = 1;

    public Dice(){


        diceViewList.add(0,new ImageView());
        diceViewList.add(1,one);
        diceViewList.add(2,two);
        diceViewList.add(3,three);
        diceViewList.add(4,four);
        diceViewList.add(5,five);
        diceViewList.add(6,six);

        for(int i=0;i<=6;i++){
            diceViewList.get(i).setFitHeight(65);
            diceViewList.get(i).setFitWidth(65);}

        dice.setGraphic(one);
        dice.setStyle("-fx-border-color: transparent;-fx-background-color: transparent;");
    }

    public int rollDice(){
        ColorAdjust colorAdjust = new ColorAdjust();
        Random random = new Random();
        int i = random.nextInt(6)+1;
        setRandomInt(i);
        ImageView imageView = getDiceViewList().get(getRandomInt());
        colorAdjust.setHue(-1+(2*random.nextDouble()));
        imageView.setEffect(colorAdjust);
        dice.setGraphic(getDiceViewList().get(getRandomInt()));

        return i;
    }

    public Button getDice() {
        return dice;
    }

    public int getRandomInt() {
        return randomInt;
    }

    public void setRandomInt(int randomInt) {
        this.randomInt = randomInt;
    }

    public List<ImageView> getDiceViewList() {
        return diceViewList;
    }

    public void activateDice(){
        dice.setOnAction((e) -> {
            randomInt = rollDice();
            dice.setGraphic(diceViewList.get(randomInt));
        });
    }

    public void deactivateDice(){
        dice.setOnAction((e) -> { });
    }
}
