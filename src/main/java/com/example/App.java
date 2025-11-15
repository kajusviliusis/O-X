package com.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class App extends Application {
    
    private Button[][] buttons = new Button[3][3];
    private char currentPlayer = 'X';

    @Override
    public void start(Stage stage)
    {
        GridPane grid = new GridPane();
        for(int row=0;row<3;row++)
        {
            for(int col=0;col<3;col++)
            {
                Button btn = new Button(" ");
                btn.setPrefSize(100,100);
                buttons[row][col] = btn;
                final int r=row;
                final int c=col;
                btn.setOnAction(e -> handleClick(r, c));
                grid.add(btn, col, row);
            }
        }

        Scene scene = new Scene(grid);
        stage.setScene(scene);       
        stage.setTitle("kryžiukai-nuliukai");
        stage.show();
    }

    private void handleClick(int row, int col)
    {
        Button btn = buttons[row][col];
        if(btn.getText().equals(" "))
        {
            btn.setText(String.valueOf(currentPlayer));
            btn.setDisable(true);
            
            for(int r=0;r<3;r++)
            {
                String b1 = buttons[r][0].getText();
                String b2 = buttons[r][1].getText();
                String b3 = buttons[r][2].getText();

                if(!b1.equals(" ") && b1.equals(b2) && b2.equals(b3))
                {
                    showWinnerAlert(b1);
                    return;
                }
            }
            for(int c=0;c<3;c++)
            {
                String b1 = buttons[0][c].getText();
                String b2 = buttons[1][c].getText();
                String b3 = buttons[2][c].getText();

                if(!b1.equals(" ") && b1.equals(b2) && b2.equals(b3))
                {
                    showWinnerAlert(b1);
                    return;
                }
            }
                String d1 = buttons[0][0].getText();
                String d2 = buttons[1][1].getText();
                String d3 = buttons[2][2].getText();

                if(!d1.equals(" ") && d1.equals(d2) && d2.equals(d3))
                {
                    showWinnerAlert(d1);
                    return;
                }
                d1 = buttons[0][2].getText();
                d2 = buttons[1][1].getText();
                d3 = buttons[2][0].getText();

                if(!d1.equals(" ") && d1.equals(d2) && d2.equals(d3))
                {
                    showWinnerAlert(d1);
                    return;
                }

                boolean draw=true;
                for(int r=0;r<3;r++)
                {
                    for(int c=0;c<3;c++)
                    {
                        if(buttons[r][c].getText().equals(" "))
                        {
                            draw = false;
                        }
                    }
                }
                if(draw)
                {
                    showDrawAlert();
                    return;
                }

            currentPlayer = (currentPlayer=='X') ? 'O' : 'X';
        }
    }
    private void showWinnerAlert(String winner)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("game over...");
        alert.setHeaderText(null);
        alert.setContentText("laimėjo " + winner);
        alert.showAndWait();
        resetBoard();

    }
    private void showDrawAlert()
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("game over...");
        alert.setHeaderText(null);
        alert.setContentText("lygiosios");
        alert.showAndWait();

    }
    private void resetBoard()
    {
        for(int r=0;r<3;r++)
        {
            for(int c=0;c<3;c++)
            {
                buttons[r][c].setText(" ");
                buttons[r][c].setDisable(false);
            }
        }
    }

    public static void main(String[] args)
    {
        launch();
    }
}