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

public class Rook extends Piece{

    private final int[] candidateCoordinate = { -8, -1, 1, 8 };

    public Rook(int piecePosition, Alliance pieceAlliance) {
        super(piecePosition, pieceAlliance , Piece_Type.ROOK);
    }

    public List<Move> calculateLegalMoves(Board board) {
        List<Move> legalMove = new ArrayList<Move>() ;

        for(int candidateDestination : candidateCoordinate)
        {
            int candidateDestinationCoordinate = this.getPiecePosition() ;
            while(BoardUtils.isValidCoordinate(candidateDestinationCoordinate))
            {
                if(firstColumnExclusion(candidateDestinationCoordinate,candidateDestination)
                        || eighthColumnExclusion(candidateDestinationCoordinate,candidateDestination))
                    break ;

                candidateDestinationCoordinate += candidateDestination ;
                if(BoardUtils.isValidCoordinate(candidateDestinationCoordinate))
                {
                    Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinate) ;
                    if(!candidateDestinationTile.isTileOccupied())
                        legalMove.add(new Major_Move(board , candidateDestinationCoordinate , this));
                    else
                    {
                        Piece candidateDestinationPiece = candidateDestinationTile.getPiece() ;
                        Alliance candidateDestinationAlliance = candidateDestinationPiece.getAlliance() ;
                        if(this.getAlliance() != candidateDestinationAlliance)
                            legalMove.add(new Attack_Move(board , candidateDestinationCoordinate , this , candidateDestinationPiece));
                    }
                    break ;
                }
            }

        }

        if(legalMove != null)
            return ImmutableList.copyOf(legalMove);
        throw new NullPointerException("Error in Rook's legal moves") ;
    }

    public Rook movePiece(Move move) {
        return new Rook(move.getDestinationCoordinate() , move.getMovedPiece().getAlliance());
    }

    private static boolean firstColumnExclusion(int currentCoordinate , int candidateCoordinate)
    {
        return BoardUtils.FIRST_COLUMN[currentCoordinate] && candidateCoordinate == -1 ;
    }
    private  static boolean eighthColumnExclusion(int currentCoordinate , int candidateCoordinate)
    {
        return BoardUtils.EIGHTH_COLUMN[currentCoordinate] && candidateCoordinate == 1 ;
    }

    @Override
    public String toString() {
        return Piece_Type.ROOK.toString();
    }
}
