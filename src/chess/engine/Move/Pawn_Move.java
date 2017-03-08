package chess.engine.Move;

import chess.engine.Board.Board;
import chess.engine.Pieces.Piece;


public class Pawn_Move extends Move {
    /**
     * A generic constructor for Move class
     *
     * @param board                 The current board
     * @param destinationCoordinate The destination that the piece wishes to move to
     * @param movedPiece            The piece in the current coordinate
     */
    public Pawn_Move(Board board, int destinationCoordinate, Piece movedPiece) {
        super(board, destinationCoordinate, movedPiece);
    }

    @Override
    public boolean equals(final Object other)
    {
        return this == other || (super.equals(other) && other instanceof Pawn_Move) ;
    }
}
