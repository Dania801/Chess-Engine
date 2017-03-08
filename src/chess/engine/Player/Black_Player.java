package chess.engine.Player;

import chess.engine.Alliance;
import chess.engine.Board.Board;
import chess.engine.Move.Move;
import chess.engine.Pieces.Piece;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Black_Player extends Player {

    public Black_Player(final Board board, final Collection<Move> blackLegalMoves, final Collection<Move> whiteLegalMoves) {
        super(board, blackLegalMoves, whiteLegalMoves);
    }

    public Collection<Piece> getActivePieces() {
        return this.board.getBlackPieces();
    }

    public Alliance getAlliance() {
        return Alliance.BLACK;
    }

    public Player getOpponent() {
        return board.getWhitePlayer();
    }

   public Collection<Move> calculateKingCastling(Collection<Move> playerLegalMoves, Collection<Move> opponentLegalMoves) {
         List<Move> kingCastlingMoves = new ArrayList<Move>();
/*
        if (this.king.isFirstMove() &&
                !this.isInCheck()) {
            //Black KingSide castling
            if (!this.board.getTile(5).isTileOccupied() &&
                    !this.board.getTile(6).isTileOccupied()) {

                Tile rookTile = this.board.getTile(7);
                if (rookTile.isTileOccupied() &&
                        rookTile.getPiece().isFirstMove() &&
                        rookTile.getPiece().getPieceType().isRook())

                    if (this.calculateAttacksOnTile(5, opponentLegalMoves).isEmpty() &&
                            this.calculateAttacksOnTile(6, opponentLegalMoves).isEmpty())

                        //TODO add the proper Move .
                        kingCastlingMoves.add(new KingCastle_Move(this.board,
                                                                        6,
                                                                        this.king,
                                                                        (Rook) rookTile.getPiece(),
                                                                        rookTile.getTileCoordinate(),
                                                                        5));
            }
            //Black QueenSide castling
            if (!this.board.getTile(1).isTileOccupied() &&
                    !this.board.getTile(2).isTileOccupied() &&
                    !this.board.getTile(3).isTileOccupied()) {
                Tile rookTile2 = this.board.getTile(0);
                if (rookTile2.isTileOccupied() &&
                        rookTile2.getPiece().isFirstMove() &&
                        rookTile2.getPiece().getPieceType().isRook())

                    if (this.calculateAttacksOnTile(1, opponentLegalMoves).isEmpty() &&
                            this.calculateAttacksOnTile(2, opponentLegalMoves).isEmpty() &&
                            this.calculateAttacksOnTile(3, opponentLegalMoves).isEmpty())

                        //TODO add the proper Move .
                        kingCastlingMoves.add(new QueenCastle_Move(board,
                                                                        2,
                                                                        this.king,
                                                                        (Rook) rookTile2.getPiece(),
                                                                        rookTile2.getTileCoordinate(),
                                                                        3));
            }
        }
*/
            return ImmutableList.copyOf(kingCastlingMoves) ;
    }
}
