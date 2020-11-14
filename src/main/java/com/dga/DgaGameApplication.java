package com.dga;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class DgaGameApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage){

        Engine engine = new Engine();
        engine.gridSet();
        engine.playersSet();
        engine.playersPonSet(4);

        Scene scene = new Scene(engine.getGrid(), 1000, 1000, Color.WHITE);

        primaryStage.setTitle("Don't Get Angry");
        primaryStage.setScene(scene);
        primaryStage.show();

        Boolean playerWins = true;

        Button start = new Button();
        start.setText("Start");
        start.setPrefSize(80,80);
        Button next = new Button();
        next.setText("Next");
        next.setPrefSize(80,80);

        start.setOnAction(e -> {
            engine.grid.getChildren().remove(start);

            while(engine.getNotEndOfGame()) {
                start.setText("Next");
                engine.grid.add(next,5,5);
                next.setOnAction(f->{
                    engine.startGame();
                    engine.grid.getChildren().remove(next);
                });
            }

        });

        engine.grid.add(start,5,5);



        /*BoardMap boardMap = new BoardMap();*/

        /*for(int i=1;i<=boardMap.getBoardMap().size();i++){
            System.out.println(Arrays.toString(boardMap.getBoardMap().get(i))+" -> "+i);}
        System.out.println("-------------");*/
        /*for(int i=1;i<=gameSet.getPlayerList().get(0).getPlayerMap().size();i++){
            System.out.println(Arrays.toString(gameSet.getPlayerList().get(0).getPlayerMap().get(i))+" -> "+i);}*/

        /*gameSet.getPlayerList().get(0).getPonList().get(0).start(gameSet.grid);
        gameSet.getPlayerList().get(0).activate(gameSet.getPlayerList().get(0).getPonList().get(0), gameSet.grid);
        gameSet.getPlayerList().get(1).getPonList().get(0).start(gameSet.grid);
        gameSet.getPlayerList().get(1).activate(gameSet.getPlayerList().get(1).getPonList().get(0), gameSet.grid);
        gameSet.getPlayerList().get(2).getPonList().get(0).start(gameSet.grid);
        gameSet.getPlayerList().get(2).activate(gameSet.getPlayerList().get(2).getPonList().get(0), gameSet.grid);
        gameSet.getPlayerList().get(3).getPonList().get(0).start(gameSet.grid);
        gameSet.getPlayerList().get(3).activate(gameSet.getPlayerList().get(3).getPonList().get(0), gameSet.grid);*/


    }
}