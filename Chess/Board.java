import java.awt.*;
import java.util.*;

// Represesents a rectangular game board, containing Piece objects.
public class Board extends BoundedGrid<Piece>
{
	// Constructs a new Board with the given dimensions
	public Board()
	{
		super(8, 8);
	}

	// Precondition:  move has already been made on the board
	// Postcondition: piece has moved back to its source,
	//                and any captured piece is returned to its location
	public void undoMove(Move move)
	{
		Piece piece = move.getPiece();
		Location source = move.getSource();
		Location dest = move.getDestination();
		Piece victim = move.getVictim();

		piece.moveTo(source);

		if (victim != null)
			victim.putSelfInGrid(piece.getBoard(), dest);
	}

	/**
	 * Gets all of the moves that the given color is able to make
	 * @param color the color to check
	 * @return an ArrayList of all the moves this color is able to mae.
	 */
	public ArrayList<Move> allMoves(Color color)
	{
		ArrayList<Move> moves = new ArrayList<>();
		for (int row = 0; row < 8; row++)
		{
			for (int col = 0; col < 8; col++)
			{
				if (get(new Location(row, col)) != null && get(new Location(row, col)).getColor().equals(color))
				{
					for (Location loc : get(new Location(row, col)).destinations())
					{
						moves.add(new Move(get(new Location(row, col)), loc));
					}
				}
			}
		}
		return moves;
	}

	/**
	 * Executes the given move.
	 * @param move the move to execute
	 */
	public void executeMove(Move move)
	{
		if (move.getVictim() != null) move.getVictim().removeSelfFromGrid();
		Piece piece = move.getPiece();
		Board board = piece.getBoard();
		piece.removeSelfFromGrid();
		piece.putSelfInGrid(board, move.getDestination());
	}

}