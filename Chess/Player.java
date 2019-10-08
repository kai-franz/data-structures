import java.awt.*;

/**
 * Abstract class representing a player.
 *
 * @author Kai
 * @version June 1, 2018
 */
public abstract class Player
{
    private Board board;
    private String name;
    private Color color;

    /**
     * Constructor for Player objects
     * @param b Board for the player
     * @param n Player's name
     * @param c Player's color
     */
    public Player(Board b, String n, Color c)
    {
        board = b;
        name = n;
        color = c;
    }

    /**
     * Gets the board
     * @return the board for the player
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Gets the color
     * @return the player's color
     */
    public Color getColor() {
        return color;
    }

    /**
     * Gets the name
     * @return the player's name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the player's next move
     * @return the next move the player will make
     */
    public abstract Move nextMove();
}
