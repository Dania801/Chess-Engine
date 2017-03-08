package chess.engine.Move;

import chess.engine.Board.Board;
import chess.engine.Pieces.Piece;
import chess.engine.Pieces.Rook;


public class QueenCastle_Move extends Castle_Move {
    /**
     * A generic constructor for Move class
     *
     * @param board                 The current board
     * @param destinationCoordinate The destination that the piece wishes to move to
     * @param movedPiece            The piece in the current coordinate
     */
    public QueenCastle_Move(Board board,
                            int destinationCoordinate,
                            Piece movedPiece,
                            Rook castleRook,
                            int castleRookStart,
                            int castleRookDest) {
        super(board, destinationCoordinate, movedPiece, castleRookStart, castleRookDest, castleRook);
    }

    @Override
    public String toString()
    {
        return "0-0-0" ;
    }
}
