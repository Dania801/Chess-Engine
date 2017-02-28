package chess.engine.Board;
import chess.engine.Pieces.Piece ;
import com.google.common.collect.ImmutableMap;
import java.util.HashMap;
import java.util.Map;

 //TODO eliminate the redundant empty tiles created in the map .

public abstract class Tile{

    protected final int TileCoordinate ;
    static Map<Integer , Empty_Tile> emptyTiles = createAllPossibleEmptyTiles() ;


    public int getTileCoordinate()
    {
        return TileCoordinate ;
    }
    /**
     *Constructor for a tile.
     * @param TileCoordinate    The coordinate of the tile.
     */
    public Tile(final int TileCoordinate)
    {
        this.TileCoordinate = TileCoordinate ;
    }

    /**
     * Creates a map that matches each created Empty_Tile with numbers from 0 to 63 .
     * Basically this method is created to benefit the creation of a board .
     * because we're going to return one of those created empty tiles if no piece is associated with the coordinate .
     * But there's another way to benefit the creation of standard board
     * which is creating an empty tile each time no piece is associated with the coordinate .
     * and the result will be an immutable list of Occupied or Empty tiles that created when needed .
     * @return         a map of all possible empty tiles.
     */
    private static Map<Integer , Empty_Tile> createAllPossibleEmptyTiles()
    {
        Map<Integer , Empty_Tile> EmptyTiles = new HashMap<Integer, Empty_Tile>() ;
        for(int i = 0 ; i < BoardUtils.TILES_NUM ; i++)
            EmptyTiles.put(i, new Empty_Tile(i)) ;
        return ImmutableMap.copyOf(EmptyTiles);
    }

    public static Tile createTile(int coordinate, Piece piece)
    {
        return piece != null ? new Occupied_Tile(coordinate , piece) : emptyTiles.get(coordinate) ;
    }

    /**
     * @return         true if the tile is occupied by a piece , and false otherwise .
     */
    public abstract boolean isTileOccupied() ;

    /**
     * @return         the piece if the tile is occupied , and null otherwise .
     */
    public abstract Piece getPiece() ;
}