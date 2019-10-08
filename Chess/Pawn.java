import java.awt.*;
import java.util.ArrayList;

/**
 * This class represents Pawn pieces in a chess game.
 *
 * @author Kai
 * @version June 1, 2018
 */
public class Pawn extends Piece
{
    /**
     * Constructor for Pawn objects
     * @param col the color of the pawn
     * @param fileName the name of the image file to be displayed
     */
    public Pawn(Color col, String fileName)
    {
        super(col, fileName, 1);
    }

    /**
     * Returns the possible destinations that the pawn can be moved to. On their first move, pawns can move two spaces
     * forward given that they are not blocked. Otherwise, they can move one space ahead, if the space is not occupied,
     * or they can move forward diagonally if they capture a piece while doing so.
     * @return
     */
    public ArrayList<Location> destinations()
    {
        ArrayList<Location> result = new ArrayList<Location>();
        int direction = Location.NORTH;
        if (getColor().equals(Color.BLACK))
        {
            if (isValidDestination(getLocation().getAdjacentLocation(Location.SOUTH)) &&
                    getBoard().get(getLocation().getAdjacentLocation(Location.SOUTH)) == null)
            {
                result.add(getLocation().getAdjacentLocation(Location.SOUTH));
            }
            if (isValidDestination(getLocation().getAdjacentLocation(Location.SOUTH + Location.HALF_RIGHT)) &&
                    getBoard().get(getLocation().getAdjacentLocation(Location.SOUTH + Location.HALF_RIGHT)) != null)
            {
                result.add(getLocation().getAdjacentLocation(Location.SOUTH + Location.HALF_RIGHT));
            }
            if (isValidDestination(getLocation().getAdjacentLocation(Location.SOUTH + Location.HALF_LEFT)) &&
                    getBoard().get(getLocation().getAdjacentLocation(Location.SOUTH + Location.HALF_LEFT)) != null)
            {
                result.add(getLocation().getAdjacentLocation(Location.SOUTH + Location.HALF_LEFT));
            }
            if (getLocation().getRow() == 1 && isValidDestination(getLocation().getAdjacentLocation(Location.SOUTH)
                    .getAdjacentLocation(Location.SOUTH)) && getBoard().get(getLocation().getAdjacentLocation(Location.SOUTH)
                    .getAdjacentLocation(Location.SOUTH)) == null)
            {
                result.add(getLocation().getAdjacentLocation(Location.SOUTH)
                        .getAdjacentLocation(Location.SOUTH));
            }

        }
        else
        {
            if (isValidDestination(getLocation().getAdjacentLocation(Location.NORTH)) &&
                    getBoard().get(getLocation().getAdjacentLocation(Location.NORTH)) == null)
            {
                result.add(getLocation().getAdjacentLocation(Location.NORTH));
            }
            if (isValidDestination(getLocation().getAdjacentLocation(Location.NORTH + Location.HALF_RIGHT)) &&
                    getBoard().get(getLocation().getAdjacentLocation(Location.NORTH + Location.HALF_RIGHT)) != null)
            {
                result.add(getLocation().getAdjacentLocation(Location.NORTH + Location.HALF_RIGHT));
            }
            if (isValidDestination(getLocation().getAdjacentLocation(Location.NORTH + Location.HALF_LEFT)) &&
                    getBoard().get(getLocation().getAdjacentLocation(Location.NORTH + Location.HALF_LEFT)) != null)
            {
                result.add(getLocation().getAdjacentLocation(Location.NORTH + Location.HALF_LEFT));
            }
            if (getLocation().getRow() == 6 && isValidDestination(getLocation().getAdjacentLocation(Location.NORTH)
                    .getAdjacentLocation(Location.NORTH)) &&
                    getBoard().get(getLocation().getAdjacentLocation(Location.NORTH)
                            .getAdjacentLocation(Location.NORTH)) == null)
            {
                result.add(getLocation().getAdjacentLocation(Location.NORTH)
                        .getAdjacentLocation(Location.NORTH));
            }
        }
        return result;
    }
}