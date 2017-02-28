package chess.engine.Board;
import chess.engine.Pieces.Piece;

public class Empty_Tile extends Tile{

    public Empty_Tile(final int TileCoordinate)
    {
        super(TileCoordinate);
    }

    @Override
    public boolean isTileOccupied() {
        return false;
    }

    @Override
    public Piece getPiece() {
        return null;
    }

    @Override
    public String toString() {
        return "-";
    }

}
