package com.dga;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
        primaryStage.initStyle(StageStyle.UTILITY);
        primaryStage.show();

        Button start = new Button();
        start.setText("Start");
        start.setPrefSize(80,80);

        engine.setPlayerOrComputerButton(engine.blue, 1,1);
        engine.setPlayerOrComputerButton(engine.orange, 1,9);
        engine.setPlayerOrComputerButton(engine.turquoise, 9,9);
        engine.setPlayerOrComputerButton(engine.violet, 9,1);

        start.setOnAction(e -> {
            engine.grid.getChildren().remove(16,21);
            engine.playerRound();
        });

        engine.grid.add(start,5,5);
    }
}