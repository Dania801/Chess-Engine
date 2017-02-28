package chess.engine.Player;

import chess.engine.Alliance;
import chess.engine.Board.Board;
import chess.engine.Move.Move;
import chess.engine.Move.Move_Status;
import chess.engine.Move.Move_Transition;
import chess.engine.Pieces.Piece;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class Player {
    protected Board board ;
    protected Collection<Move> legalMoves ;
    protected Collection<Move> opponentMoves ;
    protected Piece king ;
    private boolean inCheck ;

    /**
     *
     * @param board             The playing board
     * @param legalMoves        The set of legal moves for the current player
     * @param opponentMoves     The set of legal moves for the opponent player
     */
    public Player(final Board board ,final Collection<Move> legalMoves ,final Collection<Move> opponentMoves)
    {
        this.board = board ;
        this.legalMoves = ImmutableList.copyOf(Iterables.concat(legalMoves , calculateKingCastling(legalMoves , opponentMoves))) ;
        this.opponentMoves = opponentMoves ;
        this.king = establishKing() ;
        this.inCheck = !this.calculateAttacksOnTile(this.king.getPiecePosition() , opponentMoves).isEmpty() ;
    }

    /**
     * This method calculates possible attacks on a given piece .
     * @param piecePosition            The piece to check possible attacks on it
     * @param opponentMoves     Collection of all attacks that may be done by the opponent
     * @return                  list of moves that will end the Piece .
     */
    public Collection<Move> calculateAttacksOnTile(int piecePosition, Collection<Move> opponentMoves) {
        List<Move> moves = new ArrayList<Move>();
        for(Move move : opponentMoves)
            if(piecePosition == move.getDestinationCoordinate())
                moves.add(move) ;
        return ImmutableList.copyOf(moves) ;
    }

    /**
     * Makes sure that the king is established if not then the board is invalid
     * @return      The king piece if the king if found in the active pieces .
     */
    protected Piece establishKing() {
        for(Piece piece : getActivePieces())
            if (piece.getPieceType().isKing())
                return piece;

        throw new RuntimeException("The board is not valid !") ;
    }

    /**
     * @param move      Checks if the move is legal
     * @return          True if the move is contained in the legalMoves set of the current set of legal moves .
     */
    public boolean isMoveLegal(final Move move)
    {
        return this.legalMoves.contains(move) ;
    }

    /**
     * This method checks if the current player is inCheck
     * @return      true if the player is inCheck and false otherwise .
     */
    //TODO what the heck ?!
    public boolean isInCheck() { return this.inCheck ; }

    /**
     * This method checks if the player is inCheckMate . which means the player is inCheck and has no escape plans .
     * @return      true if the player is inCheckMate and false otherwise .
     */
    public boolean isInCheckMate() { return isInCheck() && !hasEscapeMove() ;}

    /**
     * This method checks if the player is inStaleMate . which  means the player isn't inCheck has no escape planes too .
     * @return      true if the player is inStaleMate and false otherwise .
     */
    public boolean isInStaleMate() { return !isInCheck() && !hasEscapeMove() ;}

    /**
     * This method returns checks if there's any possible move for any of the legal moves .
     * @return      true if there's any possible move to perform and false otherwise .
     */
    private boolean hasEscapeMove() {
        for(Move move : legalMoves) {
            Move_Transition transition = makeMove(move);
            if(transition.getMoveStatus().isDone())
                return true ;
        }
        return false ;
    }

    //TODO more implementation
    public boolean isCastled()
    {
        return false;
    }

    /**
     * This method perform a move by returning a transition board with three possible cases :
     * The first scenario the move is not one of the legal moves for the current player .
     * The second scenario the move will leaves the player inCheck , the thing that will prevent the transition to a new board .
     * The third scenario is the happy scenario .
     * @param move      The move that should transit the board into another new board after performing the move (if possible)
     * @return          a new transition board .
     */
    public Move_Transition makeMove(final Move move)
    {
        if(!this.isMoveLegal(move))
            return new Move_Transition(this.board , move , Move_Status.ILLEGAL_MOVE) ;

        Board transitionBoard = move.executeMove() ;

        if(!calculateAttacksOnTile(transitionBoard.getCurrentPlayer().getOpponent().getKing().getPiecePosition()
                , transitionBoard.getCurrentPlayer().getLegalMoves()).isEmpty())
            return new Move_Transition(this.board , move , Move_Status.LEAVES_PLAYER_IN_CHECK) ;

        return new Move_Transition(transitionBoard , move , Move_Status.DONE) ;
    }

    /**
     * @return      The king of the current player .
     */
    public Piece getKing() { return this.king ;}

    /**
     * @return      The set of legal moves for the current player .
     */
    public Collection<Move> getLegalMoves() { return this.legalMoves ;}

    /**
     * @return      The set of opponent moves .
     */
    public Collection<Move> getOpponentMoves() { return this.opponentMoves ;}

    /**
     * Gets the active pieces in the board .
     * @return      A collection of active pieces .
     */
    public abstract Collection<Piece> getActivePieces() ;

    /**
     * Returns the alliance of the current player .
     * @return      The alliance .
     */
    public abstract Alliance getAlliance() ;

    /**
     * Returns the opponent player
     * @return      The opponent player .
     */
    public abstract Player getOpponent() ;
    public abstract Collection<Move> calculateKingCastling(Collection<Move> playerLegalMoves, Collection<Move> opponentLegalMoves) ;


}
