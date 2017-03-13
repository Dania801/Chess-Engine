package chess.AI;

import chess.engine.Board.Board;
import chess.engine.Pieces.Piece;
import chess.engine.Player.Player;

/**
 * Created by dania on 3/12/17.
 */
public class StandardBoardEvaluator implements Board_Evaluator {

    public final static int CHECK_BONUS = 50 ;
    public final static int CHECK_MATE_BONUS = 1000 ;
    public final static int DEPTH_BONUS = 100 ;
    public final static int CASTLE_BONUS = 100 ;


    /**
     * This method evaluates the current board depending on the score of each player .
     * @param board     The current board .
     * @param depth     The specified depth .
     * @return          The evaluation result
     */
    @Override
    public int evaluate(Board board, int depth) {
        return playerScore(board.getWhitePlayer() , depth) - playerScore(board.getBlackPlayer() , depth) ;
    }

    /**
     * This method adds the evaluation factors together .
     * @param player     The player to be evaluated .
     * @param depth      The specified depth .
     * @return           The sum of evaluation factors .
     */
    public int playerScore(Player player , int depth)
    {
        return activePiecesScore(player) +
               legalMovesScore(player) +
               inCheckScore(player) +
               inCheckMateScore(player , depth) +
               castlingScore(player) ;
    }

    /**
     * @param player    The player to be evaluated
     * @return          The sum of active pieces values .
     */
    public int activePiecesScore(Player player)
    {
        int pieceScore = 0 ;
        for (Piece piece : player.getActivePieces())
            pieceScore += piece.getPieceType().getPieceValue() ;

        return pieceScore ;
    }

    /**
     * @param player    The player to be evaluated .
     * @return          The number of legal moves .
     */
    public int legalMovesScore(Player player)
    {
        return  player.getLegalMoves().size() ;
    }

    /**
     * @param player    The player to be evaluated .
     * @return          Check bonus if the player is inCheck
     */
    public int inCheckScore(Player player)
    {
        return player.isInCheck() ? CHECK_BONUS : 0 ;
    }

    /**
     * @param player    The player to be evaluated .
     * @param depth     The specified depth .
     * @return          Check Mate bonus if the player is inCheckMate (depending on the depth) .
     */
    public int inCheckMateScore(Player player , int depth)
    {
        return player.isInCheckMate() ? CHECK_MATE_BONUS * depthBonus(depth): 0 ;
    }

    /**
     * @param player    The player to be evaluated .
     * @return          Castling bonus if the player is castling .
     */
    public int castlingScore(Player player)
    {
        return player.isCastled() ? CASTLE_BONUS : 0 ;
    }

    /**
     * @param depth     The specified depth .
     * @return          depth bonus .
     */
    public int depthBonus(int depth)
    {
        return depth == 0 ? 1 : depth * DEPTH_BONUS ;
    }


}
