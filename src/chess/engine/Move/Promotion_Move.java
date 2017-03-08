package chess.engine.Move;

import chess.engine.Board.Board;
import chess.engine.Pieces.Piece;

/**
 * Created by dania on 3/6/17.
 */
public class Promotion_Move extends Move{
    Move decoratedMove ;
    Piece promotedPawn ;

    /**
     *
     * @param dicoratedMove
     */
    public Promotion_Move(final Move dicoratedMove) {
        super(dicoratedMove.board, dicoratedMove.destinationCoordinate, dicoratedMove.movedPiece);
        this.decoratedMove = dicoratedMove ;
        this.promotedPawn = dicoratedMove.getBoard().getEnPassantPawn() ;
    }

    @Override
    public boolean isAttack()
    {
         return this.decoratedMove.isAttack() ;
    }

    @Override
    public Piece getAttackedPiece()
    {
        return this.decoratedMove.getAttackedPiece() ;
    }

    @Override
    public Board executeMove()
    {
        return null ;
    }

    @Override
    public boolean equals(final Object other)
    {
        return false ;
    }

    @Override
    public int hashCode()
    {
        return 0 ;
    }

}
