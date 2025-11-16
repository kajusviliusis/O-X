package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AIPlayer extends Player {
    private Random random = new Random();

    public AIPlayer(char symbol)
    {
        super(symbol);
    }

    @Override
    public Move makeMove(Board board)
    {
        List<Move> availableMoves = new ArrayList<>();

        for(int r=0;r<3;r++)
        {
            for(int c=0;c<3;c++)
            {
                if(board.isEmpty(r, c)){
                    availableMoves.add(new Move(r, c));
                }
            }
        }
        if(availableMoves.isEmpty()) return null;
        return availableMoves.get(random.nextInt(availableMoves.size()));
    }
}
