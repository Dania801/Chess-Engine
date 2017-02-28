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

public class Bishop extends Piece{

    private final int[] candidateCoordinate = {-9, -7, 7, 9} ;

    public Bishop(int piecePosition, Alliance pieceAlliance) {
        super(piecePosition, pieceAlliance , Piece_Type.BISHOP );
    }

    // The moves have to be rechecked !
    public List<Move> calculateLegalMoves(Board board) {

        List<Move> legalMove = new ArrayList<Move>() ;

        for (int candidateDestination : candidateCoordinate) {
            int candidateDestinationCoordinate = this.getPiecePosition();
            while (BoardUtils.isValidCoordinate(candidateDestinationCoordinate)) {
                if (firstColumnExclusion(candidateDestinationCoordinate, candidateDestination)
                        || eighthColumnExclusion(candidateDestinationCoordinate, candidateDestination))
                    break;

                candidateDestinationCoordinate += candidateDestination;
                if (BoardUtils.isValidCoordinate(candidateDestinationCoordinate)) {
                    Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinate);
                    if (!candidateDestinationTile.isTileOccupied())
                        legalMove.add(new MajorMove(board , candidateDestinationCoordinate , this));
                    else {
                        Piece candidateDestinationPiece = candidateDestinationTile.getPiece();
                        Alliance candidateDestinationAlliance = candidateDestinationPiece.getAlliance();
                        if (this.getAlliance() != candidateDestinationAlliance)
                            legalMove.add(new AttackMove(board , candidateDestinationCoordinate , this , candidateDestinationPiece));
                    }
                    break;
                }
            }

        }

        if(legalMove != null)
            return ImmutableList.copyOf(legalMove);
        throw new NullPointerException("Error in Bishop's legal moves") ;

    }

    public Bishop movePiece(Move move) {
        return new Bishop(move.getDestinationCoordinate() , move.getMovedPiece().getAlliance());
    }


    private static boolean firstColumnExclusion(int currentCoordinate , int candidateCoordinate)
    {
        return BoardUtils.FIRST_COLUMN[currentCoordinate] &&
                (candidateCoordinate == -9
                || candidateCoordinate == 7) ;
    }
    private  static boolean eighthColumnExclusion(int currentCoordinate , int candidateCoordinate)
    {
        return BoardUtils.EIGHTH_COLUMN[currentCoordinate] &&
                (candidateCoordinate == 9
                 || candidateCoordinate == -7) ;
    }

    @Override
    public String toString() {
        return Piece_Type.BISHOP.toString();
    }

}
