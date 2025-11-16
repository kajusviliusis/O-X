package com.example;

public class GameController {
    private Board board;
    private Player playerX;
    private Player playerO;
    private Player currentPlayer;    
    private boolean gameOver;

    public GameController(Board board){
        this.board = board;
        this.playerX = new HumanPlayer('X'); 
        this.playerO = new AIPlayer('O'); 
        this.currentPlayer = playerX;
        this.gameOver = false;
    }

    boolean handleMove(int row, int col)
    {
        if(gameOver || !board.isEmpty(row, col)){
            return false;
        }
        
        board.placeMove(row, col, currentPlayer.getSymbol());
        char winner = board.checkWinner();
        if(winner != ' ')
        {
            gameOver = true;
            return true;
        }

        if(board.isFull())
        {
            gameOver=true;
            return true;
        }

        currentPlayer = (currentPlayer==playerX)? playerO : playerX;

        return true;
    }
    public void handleAIMove()
    {
        if (gameOver) return;
        if(!(currentPlayer instanceof AIPlayer)) return;

        Move move = currentPlayer.makeMove(board);
        if(move!=null)
        {
            board.placeMove(move.row,move.col,currentPlayer.getSymbol());
            checkGameOverAndSwitch();
        }
    }
    public boolean isGameOver()
    {
        return gameOver;
    }
    public Player getCurrentPlayer(){
        return currentPlayer;
    }
    public String getResultMessage()
    {
        char winner = board.checkWinner();
        if(winner!=' ')
        {
            return "laimėjo " + winner;
        }
        else if(board.isFull())
        {
            return "lygiosios";
        }
        else{
            return "";
        }
    }
    public void checkGameOverAndSwitch(){
        char winner = board.checkWinner();
        if (winner != ' ') {
            gameOver = true;
            return;
        }

        if (board.isFull()) {
            gameOver = true;
            return;
        }
        currentPlayer = (currentPlayer == playerX) ? playerO : playerX;
    }
    public void resetGame()
    {
        for(int r=0;r<3;r++)
        {
            for(int c=0;c<3;c++)
            {
                board.placeMove(r, c, ' ');
            }
        }
        currentPlayer = playerX;
        gameOver = false;
    }
}
