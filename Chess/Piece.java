import java.awt.*;
import java.util.*;

/**
 * Class for game pieces.
 *
 * @author Kai
 * @version June 1, 2018
 */
public abstract class Piece
{
	//the board this piece is on
	private Board board;

	//the location of this piece on the board
	private Location location;

	//the color of the piece
	private Color color;

	//the file used to display this piece
	private String imageFileName;

	//the approximate value of this piece in a game of chess
	private int value;

	/**
	 * constructs a new Piece with the given attributes.
	 * @param col the color of the piece
	 * @param fileName the name of the image file to be displayed for the piece
	 * @param val the value given to the piece, used for computing the best move
	 */
	public Piece(Color col, String fileName, int val)
	{
		color = col;
		imageFileName = fileName;
		value = val;
	}

	/**
	 * returns the board this piece is on
	 * @return this piece's board
	 */
	public Board getBoard()
	{
		return board;
	}

	/**
	 * returns the location of this piece on the board
	 * @return this piece's location
	 */
	public Location getLocation()
	{
		return location;
	}

	/**
	 * returns the color of this piece
	 * @return this piece's color
	 */
	public Color getColor()
	{
		return color;
	}

	/**
	 * Gets the name of this piece's image file
	 * @return this piece's image file
	 */
	public String getImageFileName()
	{
		return imageFileName;
	}

	/**
	 * Gets this piece's value
	 * @return the value of this piece
	 */
	public int getValue()
	{
		return value;
	}

    /**
     * Puts this piece into a board. If there is another piece at the given
     * location, it is removed. <br />
     * Precondition: (1) This piece is not contained in a grid (2)
     * <code>loc</code> is valid in <code>gr</code>
     * @param brd the board into which this piece should be placed
     * @param loc the location into which the piece should be placed
     */
    public void putSelfInGrid(Board brd, Location loc)
    {
        if (board != null)
            throw new IllegalStateException(
                    "This piece is already contained in a board.");

        Piece piece = brd.get(loc);
        if (piece != null)
            piece.removeSelfFromGrid();
        brd.put(loc, this);
        board = brd;
        location = loc;
    }

    /**
     * Removes this piece from its board. <br />
     * Precondition: This piece is contained in a board
     */
    public void removeSelfFromGrid()
    {
        if (board == null)
            throw new IllegalStateException(
                    "This piece is not contained in a board.");
        if (board.get(location) != this)
            throw new IllegalStateException(
                    "The board contains a different piece at location "
                            + location + ".");

        board.remove(location);
        board = null;
        location = null;
    }

    /**
     * Moves this piece to a new location. If there is another piece at the
     * given location, it is removed. <br />
     * Precondition: (1) This piece is contained in a grid (2)
     * <code>newLocation</code> is valid in the grid of this piece
     * @param newLocation the new location
     */
    public void moveTo(Location newLocation)
    {
        if (board == null)
            throw new IllegalStateException("This piece is not on a board.");
        if (board.get(location) != this)
            throw new IllegalStateException(
                    "The board contains a different piece at location "
                            + location + ".");
        if (!board.isValid(newLocation))
            throw new IllegalArgumentException("Location " + newLocation
                    + " is not valid.");

        if (newLocation.equals(location))
            return;
        board.remove(location);
        Piece other = board.get(newLocation);
        if (other != null)
            other.removeSelfFromGrid();
        location = newLocation;
        board.put(location, this);
    }

	/**
	 * Checks if the given location is a valid destination
	 * @param dest the location to check
	 * @return true if the given location is inside the board and it is either empty or contains an enemy piece;
	 *         otherwise,
	 * 		   false
	 */
	public boolean isValidDestination(Location dest)
	{
		return dest.getRow() >= 0 && dest.getRow() < 8 && dest.getCol() >= 0 && dest.getCol() < 8 &&
				(board.get(dest) == null || !board.get(dest).getColor().equals(color));
	}

	/**
	 * Gets the possible destinations that the piece can move to
	 * @return an ArrayList containing all of the locations that the piece is able to move to
	 */
	public abstract ArrayList<Location> destinations();

	/**
	 * Gets all of the locations in a line in a given direction from the piece's location
	 * @param dests the list to add the destinations to
	 * @param direction the direction to check
	 */
    public void sweep(ArrayList<Location> dests, int direction)
	{
		Location loc = location.getAdjacentLocation(direction);
		while (loc != null && isValidDestination(loc) && board.get(loc) == null)
		{
			dests.add(loc);
			loc = loc.getAdjacentLocation(direction);
		}
		if (loc != null && isValidDestination(loc) && board.get(loc) != null && !board.get(loc).getColor().equals(color))
		{
			dests.add(loc);
		}
	}


}