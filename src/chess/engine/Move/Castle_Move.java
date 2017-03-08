package chess.engine.Move;

import chess.engine.Board.Board;
import chess.engine.Board.Board_Builder;
import chess.engine.Pieces.King;
import chess.engine.Pieces.Piece;
import chess.engine.Pieces.Rook;

/**
 * Created by dania on 2/18/17.
 */
public class Castle_Move extends Move{

    final protected Rook castleRook ;
    final protected int castleRookStart ;
    final protected int castleRookDest ;

    /**
     * A generic constructor for Move class
     *
     * @param board                 The current board
     * @param destinationCoordinate The destination that the piece wishes to move to
     * @param movedPiece            The piece in the current coordinate
     */
    public Castle_Move(Board board, int destinationCoordinate, Piece movedPiece , int castleRookStart , int castleRookDest , Rook castleRook) {
        super(board, destinationCoordinate, movedPiece);
        this.castleRookStart = castleRookStart ;
        this.castleRookDest = castleRookDest ;
        this.castleRook = castleRook ;
    }

    @Override
    public Board executeMove() {
        final Board_Builder builder = new Board_Builder() ;
        for(Piece piece : this.board.getCurrentPlayer().getActivePieces())
            if(!piece.equals(movedPiece) && !piece.equals(castleRook))
                builder.setPiece(piece) ;

        for(Piece piece : this.board.getCurrentPlayer().getOpponent().getActivePieces())
            builder.setPiece(piece) ;

        King newKingPostion = (King) movedPiece.movePiece(this) ;
        Rook newRookPosition = new Rook(castleRookDest , this.board.getCurrentPlayer().getAlliance()) ;
        builder.setPiece(newKingPostion) ;
        builder.setPiece(newRookPosition) ;
        builder.setMoveMaker(this.board.getCurrentPlayer().getOpponent().getAlliance()) ;

        return builder.build() ;
    }

    public Rook getCastleRook()
    {
        return castleRook ;
    }

    @Override
    public boolean isCastling()
    {
        return true ;
    }

    @Override
    public int hashCode()
    {
        int result = super.hashCode() ;
        result = 31 * result + castleRook.hashCode() ;
        result = 31 * result + castleRookStart ;
        result = 31 * result + castleRookDest ;

        return result ;
    }

    @Override
    public boolean equals(final Object other)
    {
        if(this == other)
            return true ;

        if(!(other instanceof Major_Move))
            return false ;

        Castle_Move comparedCastle = (Castle_Move) other ;
        return (comparedCastle.castleRook.equals(this.castleRook) &&
                comparedCastle.castleRookStart == this.castleRookStart &&
                comparedCastle.castleRookDest == this.castleRookDest &&
                super.equals(comparedCastle)) ;
    }
}
