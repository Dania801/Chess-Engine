package chess.engine.Move;

import chess.engine.Board.Board;
import chess.engine.Board.Board_Builder;
import chess.engine.Pieces.Pawn;
import chess.engine.Pieces.Piece;

/**
 * Created by dania on 2/18/17.
 */
public class EnPassant_Move extends PawnAttack_Move {
    /**
     * @param board                 The current board .
     * @param destinationCoordinate The destination that the piece wished to move to
     * @param movedPiece            The moved piece
     * @param attackedPiece         The piece on the destination coordinate .
     */
    public EnPassant_Move(Board board, int destinationCoordinate, Piece movedPiece, Piece attackedPiece) {
        super(board, destinationCoordinate, movedPiece, attackedPiece);
    }

    @Override
    public Board executeMove()
    {
        Board_Builder builder = new Board_Builder() ;
        for(Piece piece : board.getCurrentPlayer().getActivePieces())
            if(!piece.equals(movedPiece))
                builder.setPiece(piece) ;

        for(Piece piece : board.getCurrentPlayer().getOpponent().getActivePieces())
            builder.setPiece(piece) ;

        Pawn newPosition = (Pawn) movedPiece.movePiece(this) ;
        builder.setPiece(newPosition) ;
        builder.setEnPassantPawn(newPosition) ;
        builder.setMoveMaker(board.getCurrentPlayer().getOpponent().getAlliance()) ;

        return builder.build() ;
    }

    @Override
    public boolean equals(final Object other)
    {
        return other == this || other instanceof EnPassant_Move && super.equals(other) ;
    }
}
