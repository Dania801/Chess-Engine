package chess.engine.Move;


import chess.engine.Board.Board;
import chess.engine.Board.Board_Builder;
import chess.engine.Pieces.Piece;

public class MajorMove extends Move {

    /**
     * @param board                 The current board .
     * @param destinationCoordinate The destination that the piece wished to move to
     * @param movedPiece            The moved piece
     */
    public MajorMove(Board board, int destinationCoordinate, Piece movedPiece) {
        super(board, destinationCoordinate, movedPiece);
    }

    @Override
    public boolean equals(final Object other) {
        return this == other || (super.equals(other) && other instanceof MajorMove);
    }
}