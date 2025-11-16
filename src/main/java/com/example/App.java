package com.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class App extends Application {
    

    private GameController controller;
    private Board board;
    private Button buttons[][]= new Button[3][3];
    @Override
    public void start(Stage stage)
    {
        board = new Board();
        controller = new GameController(board);
        GridPane grid = new GridPane();
        grid.setHgap(5);
        grid.setVgap(5);
        for(int r=0;r<3;r++)
        {
            for(int c=0;c<3;c++)
            {
                Button btn = new Button(" ");
                btn.setPrefSize(100, 100);
                final int row = r;
                final int col = c;

                btn.setOnAction(e -> handleClick(row, col));
                buttons[r][c]=btn;
                grid.add(btn,r,c);
            }
        }
        Scene scene = new Scene(grid, 320, 350);
        stage.setScene(scene);       
        stage.setTitle("kryžiukai-nuliukai");
        stage.show();
    }
    private void updateUI()
    {
        for(int r=0;r<3;r++)
        {
            for(int c=0;c<3;c++)
            {
                buttons[r][c].setText(String.valueOf(board.getCell(r, c)));
            }
        }
    }
    private void showGameOverAlert(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("game over...");
        alert.setHeaderText(null);
        alert.setContentText(controller.getResultMessage());
        alert.showAndWait();
        controller.resetGame();
        updateUI();
    }
 
    private void handleClick(int row, int col)
    {
        if(!controller.handleMove(row,col)) return;

        updateUI();

        if(controller.isGameOver())
        {
            showGameOverAlert();
            return;
        }

        controller.handleAIMove();
        updateUI();

        if(controller.isGameOver())
        {
            showGameOverAlert();
        }
    }
   public static void main(String[] args)
    {
        launch();
    }
}