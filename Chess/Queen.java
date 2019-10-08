import java.awt.*;
import java.util.ArrayList;

/**
 * Class representing the queen in chess.
 *
 * @author Kai
 * @version June 1, 2018
 */
public class Queen extends Piece
{
    /**
     * Constructor for Queen objects
     * @param col the color of the queen
     * @param fileName the name of the image file to be displayed
     */
    public Queen(Color col, String fileName)
    {
        super(col, fileName, 9);
    }

    /**
     * Gets the possible destinations for the piece if it were to make a move.
     * @return an ArrayList containing the possible destinations for this piece
     */
    public ArrayList<Location> destinations()
    {
        ArrayList<Location> result = new ArrayList<Location>();
        for( int i = 0; i < Location.FULL_CIRCLE; i += Location.HALF_RIGHT) {
            sweep(result, i);
        }
        return result;
    }
}
