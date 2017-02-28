package chess.engine.Pieces;

import chess.engine.Alliance;
import chess.engine.Board.Board;
import chess.engine.Move.Move;

import java.util.List;

public abstract class Piece {
    private int piecePosition;
    private Alliance pieceAlliance;
    private boolean isFirstMove;
    private Piece_Type pieceType;

    /**
     * A constructor for a piece that determines the position , alliance , and isFirstMove .
     * @param piecePosition an integer that determines what tile the piece occupies in the board .
     * @param pieceAlliance WHITE or BLACK alliance .
     */
    public Piece(final int piecePosition,final Alliance pieceAlliance,final Piece_Type pieceType) {
        this.pieceAlliance = pieceAlliance;
        this.piecePosition = piecePosition;
        this.isFirstMove = false;
        this.pieceType = pieceType;
    }

    /**
     * @return the current position of the piece ,
     */
    public int getPiecePosition() {
        return this.piecePosition;
    }

    /**
     * @return The current position of the piece in the board.
     */
    public Alliance getAlliance() {
        return this.pieceAlliance;
    }

    /**
     * @return      the type of the piece .
     */
    public Piece_Type getPieceType() {
        return this.pieceType;
    }

    /**
     * @return true if it's the first move for the piece , and false otherwise .
     */
    public boolean isFirstMove() {
        return this.isFirstMove;
    }

    /**
     * A method that calculates the legal moves that a specific type of piece can takes .
     * @param board The current board with the current position of pieces in board .
     * @return an immutable list of legal moves .
     */
    public abstract List<Move> calculateLegalMoves(final Board board);

    /**
     * @param move      The mve that should be performed on the piece .
     * @return          A piece after performing the moved on it .
     */
    public abstract Piece movePiece(final Move move) ;

    /**
     * The overridden method of equals for Piece class
     * Used because we need instance type equality to logical equality .
     * @param other     The compared object .
     * @return          True if the compared object belongs to Piece class and False otherwise .
     */
    @Override
    public boolean equals(final Object other)
    {
        if(other == this)
            return true ;
        if (! (other instanceof Piece))
            return false ;

        Piece comparedPiece = (Piece) other ;
        return (comparedPiece.piecePosition == piecePosition
                && comparedPiece.pieceAlliance == pieceAlliance
                && comparedPiece.pieceType == pieceType
                && comparedPiece.isFirstMove == isFirstMove) ;
    }

    /**
     * The overridden method of hashCode for Piece Class
     * done as a part of the contract of override both equals and hashCode .
     * @return
     */
    @Override
    public int hashCode()
    {
        int result = pieceType.hashCode() ;
        result = 31 * result + pieceAlliance.hashCode() ;
        result = 31 * result + piecePosition ;
        result = 31 * (isFirstMove? 1 :0 ) ;
        return result ;
    }

}