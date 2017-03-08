package chess.engine.Board;


public class BoardUtils {

    public static boolean[] FIRST_COLUMN = initiateColumn(0) ;
    public static boolean[] SECOND_COLUMN = initiateColumn(1) ;
    public static boolean[] SEVENTH_COLUMN= initiateColumn(6) ;
    public static boolean[] EIGHTH_COLUMN = initiateColumn(7) ;

    public static boolean[] FIRST_ROW = initiateRow(0) ;
    public static boolean[] SECOND_ROW = initiateRow(8) ;
    public static boolean[] SEVENTH_ROW = initiateRow(48) ;
    public static boolean[] EIGHTH_ROW = initiateRow(56) ;

    public final static int TILES_NUM = 64 ;
    public final static int TILES_NUM_PER_ROW = 8 ;

    /**
     * The function creates a board of boolean values equal to zero by default
     * except the range from [row,row+8] will be equal to true.
     *
     * @param row   takes the number of tile
     * @return      a board of boolean values
     */
    private static boolean[] initiateRow(int row) {
        boolean[] board = new boolean[TILES_NUM] ;
        for(int i = row ; i < row+8 ; i++)
            board[i] = true ;

        return board ;
    }

    /**
     * The function takes the tile of the beginning of the column
     * which is needed to make the value of it equal to true . and the whole board equal false.
     *
     * @param column    takes the number of tile
     * @return          a board of boolean values
     */
    private static boolean[] initiateColumn(int column)
    {
        boolean[] board = new boolean[TILES_NUM] ;
        for(int i = column ; i < TILES_NUM ; i+=TILES_NUM_PER_ROW )
            board[i] = true ;

        return board ;
    }

    /**
     * This function checks whether coordinate is in a valid position.
     * considering that the valid position must be in the range [0-63]
     *
     * @param coordinate    an integer that represents the number of tile.
     * @return              true if the coordinate is in the right position , and false otherwise
     */
    public static boolean isValidCoordinate(int coordinate)
    {
        return coordinate > 0 && coordinate < 64 ? true : false;
    }
}
