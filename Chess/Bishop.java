import java.awt.*;
import java.util.ArrayList;

public class Bishop extends Piece
{
    public Bishop(Color col, String fileName)
    {
        super(col, fileName, 3);
    }

    public ArrayList<Location> destinations()
    {
        ArrayList<Location> result = new ArrayList<Location>();
        for( int i = Location.HALF_RIGHT; i < Location.FULL_CIRCLE; i += Location.RIGHT) {
            sweep(result, i);
        }
        return result;
    }
}
