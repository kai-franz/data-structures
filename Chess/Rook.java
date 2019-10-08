import java.awt.*;
import java.util.ArrayList;

/**
 * This class represents the rook piece in chess.
 *
 * @author Kai
 * @version June 1, 2018
 */
public class Rook extends Piece
{
    /**
     * Constructs Rook objects
     * @param col the color of the room
     * @param fileName the name of the image file to be displayed
     */
    public Rook(Color col, String fileName)
    {
        super(col, fileName, 5);
    }

    /**
     * Gets the destinations that the rook can move to
     * @return the list of destinations that this piece is able to move to
     */
    public ArrayList<Location> destinations()
    {
        ArrayList<Location> result = new ArrayList<Location>();
        for( int i = 0; i < Location.FULL_CIRCLE; i += Location.RIGHT) {
            sweep(result, i);
        }
        return result;
    }
}