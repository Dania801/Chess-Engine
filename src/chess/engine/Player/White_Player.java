package chess.engine.Player;

import chess.engine.Alliance;
import chess.engine.Board.Board;
import chess.engine.Move.Move;
import chess.engine.Pieces.Piece;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class White_Player extends Player {

    public White_Player(final Board board ,final Collection<Move> whiteLegalMoves ,final Collection<Move> blackLegalMoves) {
        super(board , whiteLegalMoves , blackLegalMoves);
    }

    public Collection<Piece> getActivePieces() {
        return this.board.getWhitePieces();
    }

    public Alliance getAlliance() {
        return Alliance.WHITE;
    }

    public Player getOpponent() {
        return board.getBlackPlayer();
    }

    @Override
    public Collection<Move> calculateKingCastling(Collection<Move> playerLegalMoves , Collection<Move> opponentLegalMoves) {
        List<Move> kingCastlingMoves = new ArrayList<Move>() ;
        /*
        if(this.king.isFirstMove()&&
           !this.isInCheck()) {
            //White KingSide castling
            if (!this.board.getTile(61).isTileOccupied() &&
                !this.board.getTile(62).isTileOccupied()) {

                Tile rookTile = this.board.getTile(63);
                if (rookTile.isTileOccupied() &&
                    rookTile.getPiece().isFirstMove() &&
                    rookTile.getPiece().getPieceType().isRook())

                    if(this.calculateAttacksOnTile(61 , opponentLegalMoves).isEmpty() &&
                       this.calculateAttacksOnTile(62 , opponentLegalMoves).isEmpty())

                        //Recheck the coordinates
                        kingCastlingMoves.add(new KingCastle_Move(this.board ,
                                                                     62 ,
                                                                     this.king ,
                                                                     (Rook) rookTile.getPiece() ,
                                                                      rookTile.getTileCoordinate() ,
                                                                      61) );
            }

            //White QueenSide castling
            if(!this.board.getTile(59).isTileOccupied() &&
               !this.board.getTile(58).isTileOccupied() &&
               !this.board.getTile(57).isTileOccupied())
            {
                Tile rookTile = this.board.getTile(56);
                if (rookTile.isTileOccupied() &&
                    rookTile.getPiece().isFirstMove() &&
                    rookTile.getPiece().getPieceType().isRook())

                    if (this.calculateAttacksOnTile(59 , opponentLegalMoves).isEmpty() &&
                        this.calculateAttacksOnTile(58 , opponentLegalMoves).isEmpty() &&
                        this.calculateAttacksOnTile(57 , opponentLegalMoves).isEmpty())

                        //TODO add the proper Move .
                        kingCastlingMoves.add(new QueenCastle_Move(board ,
                                                                      58 ,
                                                                      this.king ,
                                                                      (Rook) rookTile.getPiece() ,
                                                                       rookTile.getTileCoordinate() ,
                                                                       59));
            }
        }
        */
        return ImmutableList.copyOf(kingCastlingMoves) ;
    }
}
