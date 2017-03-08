package chess.engine.Move;


import chess.engine.Board.Board;
import chess.engine.Pieces.Piece;

public class Major_Move extends Move {

    /**
     * @param board                 The current board .
     * @param destinationCoordinate The destination that the piece wished to move to
     * @param movedPiece            The moved piece
     */
    public Major_Move(Board board, int destinationCoordinate, Piece movedPiece) {
        super(board, destinationCoordinate, movedPiece);
    }

    @Override
    public boolean equals(final Object other) {
        return this == other || (super.equals(other) && other instanceof Major_Move);
    }
}