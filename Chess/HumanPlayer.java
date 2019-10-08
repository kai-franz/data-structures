import java.awt.*;

/**
 * HumanPlayer represents a human chess player.
 */
public class HumanPlayer extends Player
{
    private BoardDisplay display;

    /**
     * Constructs HumanPlayer objects
     * @param d the board display object to use for the human to select its next move
     * @param b board that the game is being played on
     * @param n the name of the player
     * @param c color that the player is playing
     */
    public HumanPlayer(BoardDisplay d, Board b, String n, Color c)
    {
        super(b, n, c);
        display = d;
    }

    /**
     * Returns the next move that the human chooses
     * @return the next move that is chosen by the human
     */
    public Move nextMove()
    {
        while (true) {
            Move m = display.selectMove();
            for (Move move : getBoard().allMoves(getColor())) {
                if (move.equals(m)) return move;
            }
        }
    }

}
