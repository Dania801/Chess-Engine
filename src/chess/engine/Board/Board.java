package chess.engine.Board;


import chess.engine.Alliance;
import chess.engine.Move.Move;
import chess.engine.Pieces.*;
import chess.engine.Player.Black_Player;
import chess.engine.Player.Player;
import chess.engine.Player.White_Player;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Board {

    private final List<Tile> gameBoard ;
    private final Collection<Piece> whitePieces ;
    private final Collection<Piece> blackPieces ;
    private final Collection<Move> whiteLegalMoves ;
    private final Collection<Move> blackLegalMoves ;
    private final White_Player whitePlayer ;
    private final Black_Player blackPlayer ;
    private Player currentPlayer ;

    /**
     * A constructor that build a board from board_builder that makes everything ready for the board .
     * @param builder       sets pieces on tiles and specify the mocve maker .
     */
    public Board(Board_Builder builder) {
        this.gameBoard = createGameBoard(builder) ;
        this.whitePieces = calculateActivePieces(builder , Alliance.WHITE) ;
        this.blackPieces = calculateActivePieces(builder , Alliance.BLACK) ;
        this.whiteLegalMoves = calculateLegalMoves(this.whitePieces) ;
        this.blackLegalMoves = calculateLegalMoves(this.blackPieces) ;
        this.whitePlayer = new White_Player(this, this.whiteLegalMoves , this.blackLegalMoves) ;
        this.blackPlayer = new Black_Player(this, this.blackLegalMoves , this.whiteLegalMoves) ;
        this.currentPlayer = builder.getMoveMaker().choosePlayer(this.whitePlayer , this.blackPlayer) ;
    }

    private Collection<Move> calculateLegalMoves(Collection<Piece> activePieces)
    {
        List<Move> legalMoves = new ArrayList<Move>() ;
        for(Piece piece : activePieces)
            legalMoves.addAll(piece.calculateLegalMoves(this)) ;

        if(legalMoves != null)
            return ImmutableList.copyOf(legalMoves) ;
        throw new NullPointerException("Error in calculating legal moves for active pieces !") ;

    }

    /**
     * A method to calculate active pieces on board for each alliance .
     * @param builder        The board with current position of pieces
     * @param alliance       WHITE or BLACK
     * @return              A collection of active piece for the alliance .
     */
    public static Collection<Piece> calculateActivePieces(final Board_Builder builder , final Alliance alliance)
    {
        List<Piece> activePieces = new ArrayList<Piece>() ;
        for (final Piece piece : builder.boardConfig.values()) {
            if (piece.getAlliance() == alliance) {
                activePieces.add(piece);
            }
        }
        if(activePieces != null)
             return ImmutableList.copyOf(activePieces) ;
        throw new NullPointerException("Error in calculating Active pieces !") ;
    }

    /**
     * A method for creating game board that use what StandardBoard created already .
     * @param builder       to get pieces to add it to the tile .
     * @return              list of occupied/empty tiles .
     */
    private List<Tile> createGameBoard(final Board_Builder builder)
    {
        Tile[] boardTile = new Tile[BoardUtils.TILES_NUM] ;
        for(int i = 0 ; i < BoardUtils.TILES_NUM ; i++)
            boardTile[i] = Tile.createTile(i,builder.boardConfig.get(i)) ;

        if(boardTile != null)
            return ImmutableList.copyOf(boardTile) ;
        throw new NullPointerException("Error in creating game board ! ") ;
    }

    /**
     * A method for creating the standard board with the standard piece positions and standard move maker .
     * @return              the standard board
     */
    public static Board createStandardBoard()
    {
        Board_Builder standardBoardBuilder = new Board_Builder() ;
        standardBoardBuilder.setPiece(new Rook(0,Alliance.BLACK)) ;
        standardBoardBuilder.setPiece(new Knight(1,Alliance.BLACK)) ;
        standardBoardBuilder.setPiece(new Bishop(2,Alliance.BLACK)) ;
        standardBoardBuilder.setPiece(new Queen(3,Alliance.BLACK)) ;
        standardBoardBuilder.setPiece(new King(4,Alliance.BLACK)) ;
        standardBoardBuilder.setPiece(new Bishop(5,Alliance.BLACK)) ;
        standardBoardBuilder.setPiece(new Knight(6,Alliance.BLACK)) ;
        standardBoardBuilder.setPiece(new Rook(7,Alliance.BLACK)) ;
        standardBoardBuilder.setPiece(new Pawn(8,Alliance.BLACK)) ;
        standardBoardBuilder.setPiece(new Pawn(9,Alliance.BLACK)) ;
        standardBoardBuilder.setPiece(new Pawn(10,Alliance.BLACK)) ;
        standardBoardBuilder.setPiece(new Pawn(11,Alliance.BLACK)) ;
        standardBoardBuilder.setPiece(new Pawn(12,Alliance.BLACK)) ;
        standardBoardBuilder.setPiece(new Pawn(13,Alliance.BLACK)) ;
        standardBoardBuilder.setPiece(new Pawn(14,Alliance.BLACK)) ;
        standardBoardBuilder.setPiece(new Pawn(15,Alliance.BLACK)) ;

        standardBoardBuilder.setPiece(new Pawn(48,Alliance.WHITE)) ;
        standardBoardBuilder.setPiece(new Pawn(49,Alliance.WHITE)) ;
        standardBoardBuilder.setPiece(new Pawn(50,Alliance.WHITE)) ;
        standardBoardBuilder.setPiece(new Pawn(51,Alliance.WHITE)) ;
        standardBoardBuilder.setPiece(new Pawn(52,Alliance.WHITE)) ;
        standardBoardBuilder.setPiece(new Pawn(53,Alliance.WHITE)) ;
        standardBoardBuilder.setPiece(new Pawn(54,Alliance.WHITE)) ;
        standardBoardBuilder.setPiece(new Pawn(55,Alliance.WHITE)) ;
        standardBoardBuilder.setPiece(new Rook(56,Alliance.WHITE)) ;
        standardBoardBuilder.setPiece(new Knight(57,Alliance.WHITE)) ;
        standardBoardBuilder.setPiece(new Bishop(58,Alliance.WHITE)) ;
        standardBoardBuilder.setPiece(new Queen(59,Alliance.WHITE)) ;
        standardBoardBuilder.setPiece(new King(60,Alliance.WHITE)) ;
        standardBoardBuilder.setPiece(new Bishop(61,Alliance.WHITE)) ;
        standardBoardBuilder.setPiece(new Knight(62,Alliance.WHITE)) ;
        standardBoardBuilder.setPiece(new Rook(63,Alliance.WHITE)) ;

        standardBoardBuilder.setMoveMaker(Alliance.WHITE) ;
        if (standardBoardBuilder != null) {
            return standardBoardBuilder.build();
        }
        throw new NullPointerException("Couldn't build a standard board ! some null values occurs ") ;
    }

    /**
     * A method that returns the tile on the specified coordinate .
     * @param coordinate        the coordinate of the tile
     * @return                  the tile .
     */
    public Tile getTile(final int coordinate)
    {
        return gameBoard.get(coordinate) ;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        for (int i = 0; i < BoardUtils.TILES_NUM; i++) {
            final String tileText = prettyPrint(this.gameBoard.get(i));
            builder.append(String.format("%3s", tileText));
            if ((i + 1) % 8 == 0) {
                builder.append("\n");
            }
        }
        return builder.toString();
    }
    private static String prettyPrint(final Tile tile) {
        if(tile.isTileOccupied()) {
            return tile.getPiece().getAlliance().isBlack() ?
                    tile.toString().toLowerCase() : tile.toString();
        }
        return tile.toString();
    }

    public Collection<Piece> getWhitePieces() {
        return this.whitePieces ;
    }

    public Collection<Piece> getBlackPieces() {
        return this.blackPieces ;
    }

    public Player getWhitePlayer() {
        return this.whitePlayer ;
    }

    public Player getBlackPlayer() {
        return this.blackPlayer ;
    }

    public Player getCurrentPlayer() { return this.currentPlayer ; }
}
