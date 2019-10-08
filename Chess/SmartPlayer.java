import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Class that represents a "smart player" that attempts to analyze the efficacy of its moves using a decision tree.
 * The SmartPlayer assigns each piece a value, ranging from 1 for pawns to 9 for the king, and makes the move that
 * will give it the best possible outcome n moves ahead, assuming that the opponent uses the same strategy.
 */
public class SmartPlayer extends Player
{
    public SmartPlayer(Board b, String n, Color c) {
        super(b, n, c);
    }

    public int score()
    {
        int score = 0;
        for (int row = 0; row < 8; row++)
        {
            for (int col = 0; col < 8; col++)
            {
                if (getBoard().get(new Location(row, col)) != null &&
                        getBoard().get(new Location(row, col)).getColor().equals(getColor()))
                {
                    score += getBoard().get(new Location(row, col)).getValue();
                }
                else if (getBoard().get(new Location(row, col)) != null)
                {
                    score -= getBoard().get(new Location(row, col)).getValue();
                }
            }
        }
        return score;
    }

    /**
     * Gets the next move the smart player will make
     * @return the next move to be made
     */
    @Override
    public Move nextMove() {
        int highest = Integer.MIN_VALUE;
        int index = 0;
        int i = 0;
        for (Move move : getBoard().allMoves(getColor()))
        {
            getBoard().executeMove(move);
            int v = valueOfMeanestResponse(10);
            if (score() + v > highest)
            {
                highest = score() + v;
                index = i;
            }
            getBoard().undoMove(move);
            i++;
        }
        return getBoard().allMoves(getColor()).get(index);
    }

    /**
     * Gets the meanest response that the smart player's opponent could make against it
     * @param moves the number of moves to look ahead
     * @return the score that the meanest response from the opponent would result in
     */
    private int valueOfMeanestResponse(int moves)
    {
        if (moves == 0) return score();
        int lowest = Integer.MAX_VALUE;
        Color c = Color.BLACK;
        if (getColor().equals(Color.BLACK))
        {
            c = Color.WHITE;
        }
        for (Move move : getBoard().allMoves(c))
        {
            getBoard().executeMove(move);
            if (valueOfBestMove(moves - 1) < lowest)
            {
                lowest = score();
            }
            getBoard().undoMove(move);
        }
        return lowest;
    }

    /**
     * Gets the value of the best possible move that the smart player could make, looking n moves ahead
     * @param moves the number of moves to look ahead
     * @return the value of the best possible move that the smart player can make
     */
    private int valueOfBestMove(int moves)
    {
        if (moves == 0) return score();
        int highest = Integer.MIN_VALUE;
        for (Move move : getBoard().allMoves(getColor()))
        {
            getBoard().executeMove(move);
            int v = valueOfMeanestResponse(moves - 1);
            if (score() + v > highest)
            {
                highest = score() + v;
            }
            getBoard().undoMove(move);
        }
        return highest;
    }
}
