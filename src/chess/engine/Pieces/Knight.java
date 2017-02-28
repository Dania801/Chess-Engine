package chess.engine.Pieces;
import chess.engine.Alliance;
import chess.engine.Board.Board;
import chess.engine.Board.BoardUtils;
import chess.engine.Move.AttackMove;
import chess.engine.Move.MajorMove;
import chess.engine.Move.Move;
import chess.engine.Board.Tile;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {

    int[] candidateDestination = {-6,-10,-15,-17,17,15,10,6};

    public Knight(int piecePosition, Alliance pieceAlliance) {
        super(piecePosition, pieceAlliance , Piece_Type.KNIGHT);
    }

    @Override
    public List<Move> calculateLegalMoves(Board board) {
        List<Move> legalMoves = new ArrayList<Move>() ;

        for(int currentCandidate: candidateDestination)
        {
            if(isFirstColumnExclusion(this.getPiecePosition() , currentCandidate)
                    || isSeventhColumnExclusion(this.getPiecePosition() , currentCandidate)
                    || isSecondColumnExclusion(this.getPiecePosition() , currentCandidate)
                    || isEighthColumnExclusion(this.getPiecePosition() , currentCandidate))
                continue ;

            int candidateDestination = currentCandidate + this.getPiecePosition() ;
            if(BoardUtils.isValidCoordinate(candidateDestination))
            {
                Tile candidateTile = board.getTile(candidateDestination) ;

                if(! candidateTile.isTileOccupied())
                    legalMoves.add(new MajorMove(board , candidateDestination , this)) ;
                else
                {
                    Piece candidatePiece = candidateTile.getPiece() ;
                    Alliance candidateAlliance = candidatePiece.getAlliance() ;
                    if(this.getAlliance() != candidateAlliance)
                        legalMoves.add(new AttackMove(board , candidateDestination , this , candidatePiece)) ;
                }
            }
        }
        if(legalMoves != null)
            return ImmutableList.copyOf(legalMoves);
        throw new NullPointerException("Error in Knight's legal moves") ;
    }

    public Knight movePiece(Move move) {
        return new Knight(move.getDestinationCoordinate() , move.getMovedPiece().getAlliance());
    }

    public static boolean isFirstColumnExclusion(final int currentCoordinate ,final int candidateCoordinate)
    {
        return BoardUtils.FIRST_COLUMN[currentCoordinate] &&
                (candidateCoordinate == -10
                || candidateCoordinate == -17
                || candidateCoordinate == 6
                || candidateCoordinate ==15) ;
    }

    public static boolean isEighthColumnExclusion(final int currentCoordinate ,final int candidateCoordinate)
    {
        return BoardUtils.EIGHTH_COLUMN[currentCoordinate] &&
                (candidateCoordinate == -6
                || candidateCoordinate == -15
                || candidateCoordinate == 10
                || candidateCoordinate == 17);
    }

    public static boolean isSecondColumnExclusion(final int currentCoordinate ,final int candidateCoordinate)
    {
        return BoardUtils.SECOND_COLUMN[currentCoordinate] &&
                (candidateCoordinate == -10
                || candidateCoordinate == 6);
    }

    public static boolean isSeventhColumnExclusion(final int currentCoordinate ,final int candidateCoordinate)
    {
        return BoardUtils.SEVENTH_COLUMN[currentCoordinate] &&
                (candidateCoordinate == -6
                || candidateCoordinate == 10);
    }

    @Override
    public String toString() {
        return Piece_Type.KNIGHT.toString();
    }
}
