package com.example;

public class Board {
    private char[][] grid;
    private final int SIZE = 3;

    public Board()
    {
        grid = new char[SIZE][SIZE];
        for(int r=0;r<SIZE;r++)
        {
            for(int c=0;c<SIZE;c++)
            {
                grid[r][c] = ' ';
            }
        }
    }
    public boolean isEmpty(int row, int col)
    {
        return grid[row][col] == ' ';
    }
    public void placeMove(int row, int col, char symbol)
    {
        grid[row][col] = symbol;
    }
    public char getCell(int row, int col)
    {
        return grid[row][col];
    }
    public char checkWinner()
    {
        for (int r = 0; r < SIZE; r++) {
            if (grid[r][0] != ' ' &&
                grid[r][0] == grid[r][1] &&
                grid[r][1] == grid[r][2]) {
                return grid[r][0];
            }
        }

        for (int c = 0; c < SIZE; c++) {
            if (grid[0][c] != ' ' &&
                grid[0][c] == grid[1][c] &&
                grid[1][c] == grid[2][c]) {
                return grid[0][c];
            }
        }

        if (grid[0][0] != ' ' &&
            grid[0][0] == grid[1][1] &&
            grid[1][1] == grid[2][2]) {
            return grid[0][0];
        }

        if (grid[0][2] != ' ' &&
            grid[0][2] == grid[1][1] &&
            grid[1][1] == grid[2][0]) {
            return grid[0][2];
        }
        return ' ';
    }
    public boolean isFull()
    {
        for(int r=0;r<SIZE;r++)
        {
            for(int c=0;c<SIZE;c++)
            {
                if(grid[r][c]==' '){
                    return false;
                }
            }
        }
        return true;
    }
}
