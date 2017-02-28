package chess.engine.Pieces;

import chess.engine.Alliance;
import chess.engine.Board.Board;
import chess.engine.Board.BoardUtils;
import chess.engine.Move.MajorMove;
import chess.engine.Move.Move;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece{

    private final static int[] candidateDestination = {7 , 8 , 9 , 16} ;

    public Pawn(int piecePosition, Alliance pieceAlliance) {
        super(piecePosition, pieceAlliance , Piece_Type.PAWN);
    }

    public List<Move> calculateLegalMoves(Board board) {
        List<Move> legalMoves = new ArrayList<Move>() ;
        for(int currentCandidate : candidateDestination)
        {
            int candidateDestinationCoordinate = currentCandidate + (this.getPiecePosition() * this.getAlliance().getDirection()) ;
            if(!BoardUtils.isValidCoordinate(candidateDestinationCoordinate))
                continue;
            if(currentCandidate == 8 && !board.getTile(candidateDestinationCoordinate).isTileOccupied())
            {
                //TODO more work
                //The move should be updated .
                legalMoves.add(new MajorMove(board , candidateDestinationCoordinate , this)) ;
            }
            else if(currentCandidate == 16 && this.isFirstMove()
                    && (BoardUtils.SECOND_ROW[this.getPiecePosition()] && this.getAlliance().isBlack())
                    || (BoardUtils.SEVENTH_ROW[this.getPiecePosition()] && this.getAlliance().isWhite()))
            {
                //Making sure that the tile number 8 is not occupied !
                int behindCandidateDestination = this.getPiecePosition() + (this.getAlliance().getDirection() * 8) ;
                if(!board.getTile(behindCandidateDestination).isTileOccupied() &&
                   !board.getTile(candidateDestinationCoordinate).isTileOccupied())
                    //The move should be updated .
                    legalMoves.add(new MajorMove(board , candidateDestinationCoordinate , this)) ;
            }
            else if( currentCandidate == 7
                    && !((this.getAlliance().isWhite() && BoardUtils.EIGHTH_COLUMN[this.getPiecePosition()])
                    || (this.getAlliance().isBlack() && BoardUtils.FIRST_COLUMN[this.getPiecePosition()])))
            {
                if(board.getTile(candidateDestinationCoordinate).isTileOccupied()
                   && this.getAlliance() != board.getTile(candidateDestinationCoordinate).getPiece().getAlliance())
                    //TODO more work
                    //The move should be updated .
                    legalMoves.add(new MajorMove(board , candidateDestinationCoordinate , this)) ;
            }
            else if(currentCandidate == 9
                    && !((this.getAlliance().isWhite() && BoardUtils.FIRST_COLUMN[this.getPiecePosition()])
                    || (this.getAlliance().isBlack() && BoardUtils.EIGHTH_COLUMN[this.getPiecePosition()])))
            {
                if(board.getTile(candidateDestinationCoordinate).isTileOccupied()
                        && this.getAlliance() != board.getTile(candidateDestinationCoordinate).getPiece().getAlliance())
                    //The move should be updated .
                    legalMoves.add(new MajorMove(board , candidateDestinationCoordinate , this)) ;
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
