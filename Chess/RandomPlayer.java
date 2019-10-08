import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Class representing a player that moves randomly.
 *
 * @author Kai
 * @version June 1, 2018
 */
public class RandomPlayer extends Player
{
    /**
     * Constructor for RandomPlayer objects
     * @param b the board for the RandomPlayer
     * @param n the player's name
     * @param c the color of the player
     */
    public RandomPlayer(Board b, String n, Color c) {
        super(b, n, c);
    }

    /**
     * The next move that the player will make is determined randomly.
     * @return The player's next move
     */
    @Override
    public Move nextMove() {
        ArrayList<Move> moves = getBoard().allMoves(getColor());
        return moves.get((int) (Math.random() * moves.size()));
    }
}
