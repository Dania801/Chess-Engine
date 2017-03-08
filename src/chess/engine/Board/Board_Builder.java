package chess.engine.Board;

import chess.engine.Alliance;
import chess.engine.Pieces.Pawn;
import chess.engine.Pieces.Piece;

import java.util.HashMap;
import java.util.Map;

public class Board_Builder {
    Map<Integer , Piece> boardConfig ;
    //moveMaker is not initialized .
    private Alliance moveMaker ;
    protected Pawn enPassantPawn ;

    /**
     * This method sets a piece on the board .
     * Remark : We can add the coordinate to the set of parameters , but instead we got use of getPiecePosition method .
     *
     * @param piece     a piece to be added to the map of boardConfig
     * @return          the builder after adding piece
     */
    public Board_Builder setPiece(final Piece piece)
    {
        this.boardConfig.put(piece.getPiecePosition() ,piece) ;
        return this ;
    }

    /**
     * This method specified what alliance we're dealing with , WHITE or BLACK
     *
     * @param moveMaker     WHITE or BLACK
     * @return              the builder after specifying the alliance of current pieces and positions .
     */
    public Board_Builder setMoveMaker(final Alliance moveMaker)
    {
        this.moveMaker = moveMaker ;
        return this ;
    }

    /**
     * This method returns a board with the current builder that map a coordinate to its piece and specifies the alliance .
     * @return      a created board .
     */
    public Board build() { return new Board(this) ;}

    /**
     * Initializing the boardConfig map !
     */
    public Board_Builder() {
        this.boardConfig = new HashMap<Integer,Piece>();
    }

    public Alliance getMoveMaker()
    {
        return this.moveMaker ;
    }

    public void setEnPassantPawn(Pawn enPassantPawn) {
        this.enPassantPawn = enPassantPawn;
    }
}
