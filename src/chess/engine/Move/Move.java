package chess.engine.Move;

import chess.engine.Board.Board;
import chess.engine.Board.Board_Builder;
import chess.engine.Pieces.Piece;

public abstract class Move {
    protected Board board ;
    protected int destinationCoordinate ;
    protected Piece movedPiece ;


    /**
     * A generic constructor for Move class
     * @param board                     The current board
     * @param destinationCoordinate     The destination that the piece wishes to move to
     * @param movedPiece                The piece in the current coordinate
     */
    public Move(final Board board ,final int destinationCoordinate ,final Piece movedPiece )
    {
        this.board = board ;
        this.destinationCoordinate = destinationCoordinate ;
        this.movedPiece = movedPiece ;
    }

    public int getDestinationCoordinate(){return this.destinationCoordinate ;}
    public Piece getMovedPiece(){return this.movedPiece ;}
    public Board getBoard(){return this.board ;}

    public Board executeMove() {
        final Board_Builder boardAfterExecution = new Board_Builder() ;
        for (final Piece piece : this.board.getCurrentPlayer().getActivePieces())
            if(!piece.equals(movedPiece))
                boardAfterExecution.setPiece(piece) ;

        for(final Piece piece : this.board.getCurrentPlayer().getOpponent().getActivePieces())
            boardAfterExecution.setPiece(piece) ;

        //TODO more work
        Piece movedPieceNewPos = this.movedPiece.movePiece(this) ;
        boardAfterExecution.setPiece(movedPieceNewPos) ;
        boardAfterExecution.setMoveMaker(this.board.getCurrentPlayer().getOpponent().getAlliance()) ;

        return boardAfterExecution.build();
    }

    public Piece getAttackedPiece() {return null ;}
    public boolean isAttack() {return false ;}
    public boolean isCastling() {return true ;}

    /**
     * The overridden method of equals for Move class
     * PS : it's possible to consider the board in the comparison .
     * @param other
     * @return
     */
    @Override
    public boolean equals(final Object other) {
        if(other == this)
            return true ;
        if (!(other instanceof Move))
            return false ;

        Move comparedMove = (Move) other ;
        return (/*comparedMove.board == board
                && */comparedMove.destinationCoordinate == destinationCoordinate
                && movedPiece.equals(comparedMove.getMovedPiece())) ;
    }

    /**
     * The overridden method of hashCode for Move class
     * PS : it's possible to consider the board in the comparison .
     * @return
     */
    @Override
    public int hashCode() {
        int result = 1 ;
        //result = 31 * result + board.hashCode() ;
        result = 31 * result + destinationCoordinate ;
        result = 31 * result + movedPiece.hashCode() ;

        return result ;
    }
}
