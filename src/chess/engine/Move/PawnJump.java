package chess.engine.Move;

import chess.engine.Board.Board;
import chess.engine.Board.Board_Builder;
import chess.engine.Pieces.Pawn;
import chess.engine.Pieces.Piece;

public class PawnJump extends Move {
    /**
     * A generic constructor for Move class
     *
     * @param board                 The current board
     * @param destinationCoordinate The destination that the piece wishes to move to
     * @param movedPiece            The piece in the current coordinate
     */
    public PawnJump(Board board, int destinationCoordinate, Piece movedPiece) {
        super(board, destinationCoordinate, movedPiece);
    }

    @Override
    public Board executeMove()
    {
        Board_Builder builder = new Board_Builder() ;
        for (Piece piece : this.board.getCurrentPlayer().getActivePieces())
            if(!piece.equals(movedPiece))
                builder.setPiece(piece) ;

        for (Piece piece : this.board.getCurrentPlayer().getOpponent().getActivePieces())
            builder.setPiece(piece) ;

        Pawn movedPawn = (Pawn) this.movedPiece.movePiece(this) ;
        builder.setPiece(movedPawn) ;
        builder.setEnPassantPawn(movedPawn);
        builder.setMoveMaker(this.board.getCurrentPlayer().getOpponent().getAlliance()) ;
        // Move transition must be added

        return builder.build() ;
    }

    @Override
    public boolean equals(final Object other)
    {
        return this == other || (other instanceof PawnJump && super.equals(other)) ;
    }
}
