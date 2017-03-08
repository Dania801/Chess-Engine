package chess.engine.Pieces;

import chess.engine.Alliance;
import chess.engine.Board.Board;
import chess.engine.Board.BoardUtils;
import chess.engine.Move.Attack_Move;
import chess.engine.Move.Major_Move;
import chess.engine.Move.Move;
import chess.engine.Board.Tile;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece{

    private final static int[] candidateDestination = { -9, -8, -7, -1, 1, 7, 8, 9 };

    public King(int piecePosition, Alliance pieceAlliance) {
        super(piecePosition, pieceAlliance , Piece_Type.KING);
    }

    public List<Move> calculateLegalMoves(Board board) {
        List<Move> legalMoves = new ArrayList<Move>() ;

        for(int currentCandidate: candidateDestination)
        {
            if(firstColumnExclusion(this.getPiecePosition() , currentCandidate)
                    || eighthColumnExclusion(this.getPiecePosition() , currentCandidate))
                continue ;

            int candidateDestination = currentCandidate + this.getPiecePosition() ;
            if(BoardUtils.isValidCoordinate(candidateDestination))
            {
                Tile candidateTile = board.getTile(candidateDestination) ;
                if(! candidateTile.isTileOccupied())
                    legalMoves.add(new Major_Move(board , candidateDestination , this)) ;
                else
                {
                    Piece candidatePiece = candidateTile.getPiece() ;
                    Alliance candidateAlliance = candidatePiece.getAlliance() ;

                    if(this.getAlliance() != candidateAlliance)
                        legalMoves.add(new Attack_Move(board , candidateDestination , this , candidatePiece)) ;
                }
            }
        }
        if(legalMoves != null)
            return ImmutableList.copyOf(legalMoves);
        throw new NullPointerException("Error in King's legal moves") ;
    }

    public King movePiece(Move move) {
        return new King(move.getDestinationCoordinate() , move.getMovedPiece().getAlliance());
    }

    private static boolean firstColumnExclusion(int currentCoordinate , int candidateCoordinate)
    {
        return BoardUtils.FIRST_COLUMN[currentCoordinate] &&
                (candidateCoordinate == -1
                 || candidateCoordinate == -9
                 || candidateCoordinate == 7) ;

    }
    private  static boolean eighthColumnExclusion(int currentCoordinate , int candidateCoordinate)
    {
        return BoardUtils.EIGHTH_COLUMN[currentCoordinate] &&
                (candidateCoordinate == 1
                 || candidateCoordinate == 9
                 || candidateCoordinate == -7) ;
    }

    @Override
    public String toString() {
        return Piece_Type.KING.toString();
    }
}
