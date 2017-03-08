package chess.engine.Pieces;

import chess.engine.Alliance;
import chess.engine.Board.Board;
import chess.engine.Board.BoardUtils;
import chess.engine.Move.*;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece{

    private final static int[] candidateOffset = {7 , 8 , 9 , 16} ;

    public Pawn(int piecePosition, Alliance pieceAlliance) {
        super(piecePosition, pieceAlliance , Piece_Type.PAWN);
    }

    public List<Move> calculateLegalMoves(Board board) {
        List<Move> legalMoves = new ArrayList<Move>() ;
        for(int currentCandidate : candidateOffset)
        {
            int candidateDestinationCoordinate = currentCandidate + (this.getPiecePosition() * this.getAlliance().getDirection()) ;
            if(!BoardUtils.isValidCoordinate(candidateDestinationCoordinate))
                continue;
            if(currentCandidate == 8 && !board.getTile(candidateDestinationCoordinate).isTileOccupied())
            {
                if(this.getAlliance().isPromotionSquare(candidateDestinationCoordinate))
                    legalMoves.add(new Promotion_Move(new Pawn_Move(board , candidateDestinationCoordinate , this))) ;
                else
                    legalMoves.add(new Major_Move(board , candidateDestinationCoordinate , this)) ;
            }
            //Jump Move
            else if(currentCandidate == 16 && this.isFirstMove()
                    && ((BoardUtils.SECOND_ROW[this.getPiecePosition()] && this.getAlliance().isBlack())
                    || (BoardUtils.SEVENTH_ROW[this.getPiecePosition()] && this.getAlliance().isWhite())))
            {
                //Making sure that the tile number 8 is not occupied !
                int behindCandidateDestination = this.getPiecePosition() + (this.getAlliance().getDirection() * 8) ;
                if(!board.getTile(behindCandidateDestination).isTileOccupied() &&
                   !board.getTile(candidateDestinationCoordinate).isTileOccupied())
                    //The move should be updated .
                    legalMoves.add(new Major_Move(board , candidateDestinationCoordinate , this)) ;
            }
            else if( currentCandidate == 7
                    && !((this.getAlliance().isWhite() && BoardUtils.EIGHTH_COLUMN[this.getPiecePosition()])
                    || (this.getAlliance().isBlack() && BoardUtils.FIRST_COLUMN[this.getPiecePosition()])))
            {
                if(board.getTile(candidateDestinationCoordinate).isTileOccupied()
                   && this.getAlliance() != board.getTile(candidateDestinationCoordinate).getPiece().getAlliance())
                    //TODO more work
                    //The move should be updated .
                    legalMoves.add(new Major_Move(board , candidateDestinationCoordinate , this)) ;
                else if(board.getEnPassantPawn() != null)
                {
                    int enPassantPawnCoodinate = board.getEnPassantPawn().getPiecePosition() ;
                    if(enPassantPawnCoodinate == this.getPiecePosition() + this.getAlliance().getOppositeDirection())
                    {
                        Piece piece = board.getTile(candidateDestinationCoordinate).getPiece() ;
                        if(piece.getAlliance() != this.getAlliance())
                            legalMoves.add(new EnPassant_Move(board , candidateDestinationCoordinate, this , piece)) ;
                    }
                }
            }
            else if(currentCandidate == 9
                    && !((this.getAlliance().isWhite() && BoardUtils.FIRST_COLUMN[this.getPiecePosition()])
                    || (this.getAlliance().isBlack() && BoardUtils.EIGHTH_COLUMN[this.getPiecePosition()])))
            {
                if(board.getTile(candidateDestinationCoordinate).isTileOccupied()
                        && this.getAlliance() != board.getTile(candidateDestinationCoordinate).getPiece().getAlliance())
                    //The move should be updated .
                    legalMoves.add(new Major_Move(board , candidateDestinationCoordinate , this)) ;
                else if(board.getEnPassantPawn() != null)
                {
                    int enPassantPawnCoodinate = board.getEnPassantPawn().getPiecePosition() ;
                    if(enPassantPawnCoodinate == this.getPiecePosition() - this.getAlliance().getOppositeDirection())
                    {
                        Piece piece = board.getTile(candidateDestinationCoordinate).getPiece() ;
                        if(piece.getAlliance() != this.getAlliance())
                            legalMoves.add(new EnPassant_Move(board , candidateDestinationCoordinate, this , piece)) ;
                    }
                }
            }
        }

        if(legalMoves != null)
            return ImmutableList.copyOf(legalMoves);
        throw new NullPointerException("Error in Pawn's legal moves") ;
    }

    public Piece movePiece(final Move move) {
        return new Pawn(move.getDestinationCoordinate() , move.getMovedPiece().getAlliance());
    }

    @Override
    public String toString() {
        return Piece_Type.PAWN.toString();
    }
}
