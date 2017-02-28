package chess.engine.Board;
import chess.engine.Pieces.Piece;

public class Occupied_Tile extends Tile{
    private Piece PieceOnTile ;

    public Occupied_Tile(final int TileCoordinate ,final Piece PieceOnTile) {
        super(TileCoordinate);
        this.PieceOnTile = PieceOnTile ;
    }

    @Override
    public String toString() {
        return this.PieceOnTile.getAlliance().isWhite() ?
                this.PieceOnTile.toString() :
                this.PieceOnTile.toString().toLowerCase();
    }

    @Override
    public boolean isTileOccupied() {
        return true;
    }

    @Override
    public Piece getPiece() {
        return PieceOnTile ;
    }
}
