import java.awt.*;
import java.util.ArrayList;

/**
 * The Knight class represents the knight in chess.
 */
public class Knight extends Piece
{
    /**
     * Constructs Knight objects
     * @param col the color of the knight
     * @param fileName the name of the image file that the knight displays
     */
    public Knight(Color col, String fileName)
    {
        super(col, fileName, 3);
    }

    /**
     * Gets the possible destinations that the knight can move to
     * @return an ArrayList containing all of the possible locations that this piece is able to move to
     */
    public ArrayList<Location> destinations()
    {
        ArrayList<Location> result = new ArrayList<Location>();
        Location possibleLocation1, possibleLocation2;
        for( int i = 0; i < Location.FULL_CIRCLE; i+= Location.RIGHT)
        {
            Location baseLocation = getLocation();
            for (int counter = 0; counter < 2; counter++)
            {
                baseLocation = baseLocation.getAdjacentLocation(i);
            }
            possibleLocation1 = baseLocation.getAdjacentLocation(i + Location.LEFT);
            if( getBoard().isValid(possibleLocation1) )
            {
                result.add(possibleLocation1);
                if( getBoard().get(possibleLocation1) != null
                        && getBoard().get(possibleLocation1).getColor().equals(getColor()))
                {
                    result.remove(possibleLocation1);
                }
            }
            possibleLocation2 = baseLocation.getAdjacentLocation(i + Location.RIGHT);
            if( getBoard().isValid(possibleLocation2) )
            {
                result.add(possibleLocation2);
                if( getBoard().get(possibleLocation2) != null
                        && getBoard().get(possibleLocation2).getColor().equals(getColor()))
                {
                    result.remove(possibleLocation2);
                }
            }
        }
        return result;
    }
}
