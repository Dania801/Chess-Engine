package chess.engine.Move;

import chess.engine.Board.Board;
import chess.engine.Pieces.Piece;
import chess.engine.Pieces.Rook;

/**
 * Created by dania on 2/18/17.
 */
public class KingSideCastleMove extends CastleMove {
    /**
     * A generic constructor for Move class
     *
     * @param board                 The current board
     * @param destinationCoordinate The destination that the piece wishes to move to
     * @param movedPiece            The piece in the current coordinate
     */
    public KingSideCastleMove(final Board board,
                              final int destinationCoordinate,
                              final Piece movedPiece ,
                              final Rook castleRook ,
                              final int castleRookStart ,
                              final int castleRookDest) {
        super(board, destinationCoordinate, movedPiece , castleRookStart , castleRookDest , castleRook);
    }

    @Override
    public Board executeMove() {
        return null;
    }

    @Override
    public Piece getAttackedPiece() {
        return null;
    }

    @Override
    public boolean isAttack() {
        return false;
    }

    @Override
    public String toString()
    {
        return "0-0" ;
    }
}
