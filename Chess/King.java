import java.awt.*;
import java.util.ArrayList;

public class King extends Piece
{
    public King(Color col, String fileName)
    {
        super(col, fileName, 1000);
    }

    public ArrayList<Location> destinations()
    {
        ArrayList<Location> result = new ArrayList<Location>();
        for( int i = 0; i < Location.FULL_CIRCLE; i += Location.HALF_RIGHT)
        {
            Location possibleLocation = getLocation();
            possibleLocation = possibleLocation.getAdjacentLocation(i);
            if(isValidDestination(possibleLocation))
            {
                result.add(possibleLocation);
            }
        }
        return result;
    }
}