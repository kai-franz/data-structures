import java.awt.*;
import java.util.ArrayList;

/**
 * Represents a single move, in which a piece moves to a destination location.
 * Since a move can be undone, also keeps track of the source location and any captured victim.
 *
 * @author Kai
 * @version June 1, 2018
 */
public class Move
{
	private Piece piece;          //the piece being moved
	private Location source;      //the location being moved from
	private Location destination; //the location being moved to
	private Piece victim;         //any captured piece at the destination

	/**Constructs a new move for moving the given piece to the given destination.
	 *
	 * @param piece the piece that is being moved
	 * @param destination The location that the piece is being moved to
	 */
	public Move(Piece piece, Location destination)
	{
		this.piece = piece;
		this.source = piece.getLocation();
		this.destination = destination;
		this.victim = piece.getBoard().get(destination);

		if (source.equals(destination))
			throw new IllegalArgumentException("Both source and dest are " + source);
	}

	/**
	 * 	Returns the piece being moved
	 * 	@return  the piece that is being moved
	 */
	public Piece getPiece()
	{
		return piece;
	}

	/**
	 * 	Returns the location being moved from
	 * 	@return  the original location of the piece
	 */
	public Location getSource()
	{
		return source;
	}

	/**
	 * Returns the location being moved to
	 * @return the location that the piece is being moved to
	 */
	public Location getDestination()
	{
		return destination;
	}

	/**
	 * Returns the piece being captured at the destination, if any
	 * @return the piece that is captured (or null)
	 */
	public Piece getVictim()
	{
		return victim;
	}

	/**
	 * Returns a string description of the move
	 * @return A description of the move as a string
	 */
	public String toString()
	{
		return piece + " from " + source + " to " + destination + " containing " + victim;
	}

	/**
	 * Returns true if this move is equivalent to the given one.
	 * @param x object to be compared to
	 * @return Returns true if this move is equivalent to the given one; otherwise,
	 * 				   false
	 */
	public boolean equals(Object x)
	{
		Move other = (Move)x;
		return piece == other.getPiece() && source.equals(other.getSource()) &&
			destination.equals(other.getDestination()) && victim == other.getVictim();
	}

	/**
	 * Returns a hash code for this move, such that equivalent moves have the same hash code.
	 * @return the hash code for this particular move
	 */
	public int hashCode()
	{
		return piece.hashCode() + source.hashCode() + destination.hashCode();
	}



}