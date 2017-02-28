package chess.engine.Move;

import chess.engine.Board.Board;
import chess.engine.Pieces.Piece;


public class AttackMove extends Move {

    private Piece attackedPiece ;

    /**
     *
     * @param board                     The current board .
     * @param destinationCoordinate     The destination that the piece wished to move to
     * @param movedPiece                The moved piece
     * @param attackedPiece             The piece on the destination coordinate .
     */
    public AttackMove(Board board, int destinationCoordinate, Piece movedPiece , Piece attackedPiece) {
        super(board, destinationCoordinate, movedPiece);
        this.attackedPiece = attackedPiece ;
    }

    @Override
    public Piece getAttackedPiece() {
        return attackedPiece;
    }

    @Override
    public boolean isAttack() {
        return true;
    }


    @Override
    public boolean equals(final Object other) {
        if (other == this)
            return true ;

        if (!(other instanceof AttackMove))
            return false ;

        AttackMove comparedAttackMove = (AttackMove) other ;
        return super.equals(other) && attackedPiece.equals(comparedAttackMove.getAttackedPiece()) ;
    }

    @Override
    public int hashCode() {
        return super.hashCode() + attackedPiece.hashCode() ;
    }

}
