import java.awt.*;

/**
 * The Game class represents a chess game.
 *
 * @author Kai
 * @version June 1, 2018
 */
public class Game {
    public static void main(String[] args)
    {
        Board board = new Board();

        Piece blackKing = new King(Color.BLACK,
                "src/black_king.gif");
        blackKing.putSelfInGrid(board, new Location(0, 4));

        Piece whiteKing = new King(Color.WHITE,
                "src/white_king.gif");
        whiteKing.putSelfInGrid(board, new Location(7, 4));


        Piece blackRook1 = new Rook(Color.BLACK, "src/black_rook.gif");
        blackRook1.putSelfInGrid(board, new Location(0, 0));
        Piece blackRook2 = new Rook(Color.BLACK, "src/black_rook.gif");
        blackRook2.putSelfInGrid(board, new Location(0, 7));

        Piece whiteRook1 = new Rook(Color.WHITE, "src/white_rook.gif");
        whiteRook1.putSelfInGrid(board, new Location(7, 0));
        Piece whiteRook2 = new Rook(Color.WHITE, "src/white_rook.gif");
        whiteRook2.putSelfInGrid(board, new Location(7, 7));


        Piece blackPawn1 = new Pawn(Color.BLACK, "src/black_pawn.gif");
        blackPawn1.putSelfInGrid(board, new Location(1, 0));
        Piece blackPawn2 = new Pawn(Color.BLACK, "src/black_pawn.gif");
        blackPawn2.putSelfInGrid(board, new Location(1, 1));
        Piece blackPawn3 = new Pawn(Color.BLACK, "src/black_pawn.gif");
        blackPawn3.putSelfInGrid(board, new Location(1, 2));
        Piece blackPawn4 = new Pawn(Color.BLACK, "src/black_pawn.gif");
        blackPawn4.putSelfInGrid(board, new Location(1, 3));
        Piece blackPawn5 = new Pawn(Color.BLACK, "src/black_pawn.gif");
        blackPawn5.putSelfInGrid(board, new Location(1, 4));
        Piece blackPawn6 = new Pawn(Color.BLACK, "src/black_pawn.gif");
        blackPawn6.putSelfInGrid(board, new Location(1, 5));
        Piece blackPawn7 = new Pawn(Color.BLACK, "src/black_pawn.gif");
        blackPawn7.putSelfInGrid(board, new Location(1, 6));
        Piece blackPawn8 = new Pawn(Color.BLACK, "src/black_pawn.gif");
        blackPawn8.putSelfInGrid(board, new Location(1, 7));

        Piece whitePawn1 = new Pawn(Color.white, "src/white_pawn.gif");
        whitePawn1.putSelfInGrid(board, new Location(6, 0));
        Piece whitePawn2 = new Pawn(Color.white, "src/white_pawn.gif");
        whitePawn2.putSelfInGrid(board, new Location(6, 1));
        Piece whitePawn3 = new Pawn(Color.white, "src/white_pawn.gif");
        whitePawn3.putSelfInGrid(board, new Location(6, 2));
        Piece whitePawn4 = new Pawn(Color.white, "src/white_pawn.gif");
        whitePawn4.putSelfInGrid(board, new Location(6, 3));
        Piece whitePawn5 = new Pawn(Color.white, "src/white_pawn.gif");
        whitePawn5.putSelfInGrid(board, new Location(6, 4));
        Piece whitePawn6 = new Pawn(Color.white, "src/white_pawn.gif");
        whitePawn6.putSelfInGrid(board, new Location(6, 5));
        Piece whitePawn7 = new Pawn(Color.white, "src/white_pawn.gif");
        whitePawn7.putSelfInGrid(board, new Location(6, 6));
        Piece whitePawn8 = new Pawn(Color.white, "src/white_pawn.gif");
        whitePawn8.putSelfInGrid(board, new Location(6, 7));


        Piece blackKnight1 = new Knight(Color.BLACK, "src/black_knight.gif");
        blackKnight1.putSelfInGrid(board, new Location(0, 1));
        Piece blackKnight2 = new Knight(Color.BLACK, "src/black_knight.gif");
        blackKnight2.putSelfInGrid(board, new Location(0, 6));

        Piece whiteKnight1 = new Knight(Color.WHITE, "src/white_knight.gif");
        whiteKnight1.putSelfInGrid(board, new Location(7, 1));
        Piece whiteKnight2 = new Knight(Color.WHITE, "src/white_knight.gif");
        whiteKnight2.putSelfInGrid(board, new Location(7, 6));


        Piece blackBishop1 = new Bishop(Color.BLACK, "src/black_bishop.gif");
        blackBishop1.putSelfInGrid(board, new Location(0, 2));
        Piece blackBishop2 = new Bishop(Color.BLACK, "src/black_bishop.gif");
        blackBishop2.putSelfInGrid(board, new Location(0, 5));

        Piece whiteBishop1 = new Bishop(Color.WHITE, "src/white_bishop.gif");
        whiteBishop1.putSelfInGrid(board, new Location(7, 2));
        Piece whiteBishop2 = new Bishop(Color.WHITE, "src/white_bishop.gif");
        whiteBishop2.putSelfInGrid(board, new Location(7, 5));


        Piece blackQueen1 = new Queen(Color.BLACK, "src/black_queen.gif");
        blackQueen1.putSelfInGrid(board, new Location(0, 3));

        Piece whiteQueen1 = new Queen(Color.WHITE, "src/white_queen.gif");
        whiteQueen1.putSelfInGrid(board, new Location(7, 3));


        BoardDisplay display = new BoardDisplay(board);
        Player h = new HumanPlayer(display, board, "human", Color.WHITE);
        Player r = new SmartPlayer(board, "computer", Color.BLACK);
        play(board, display, h, r);
    }

    /**
     * Executes the next turn in the game, setting the title of the window to the player's name
     * @param board the board to use
     * @param display BoardDisplay to be used
     * @param player the next player to make a move
     */
    private static void nextTurn(Board board, BoardDisplay display, Player player)
    {
        display.setTitle(player.getName());
        Move move = player.nextMove();
        board.executeMove(move);
        display.clearColors();
        display.setColor(move.getDestination(), Color.GREEN);
        display.setColor(move.getSource(), Color.BLUE);
        try {Thread.sleep(500);} catch(InterruptedException e) {}
    }

    /**
     * Executes an entire game of chess.
     * @param board the board to use
     * @param display the display to be used to display the game
     * @param white the Player object that is controlling white
     * @param black the Player object that controls black
     */
    public static void play(Board board, BoardDisplay display, Player white, Player black)
    {
        while (true)
        {
            nextTurn(board, display, white);
            nextTurn(board, display, black);
        }
    }
}
