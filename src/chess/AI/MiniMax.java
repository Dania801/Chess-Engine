package chess.AI;

import chess.engine.Board.Board;
import chess.engine.Move.Move;
import chess.engine.Move.Move_Transition;

/**
 * Created by dania on 3/10/17.
 */
public class MiniMax implements Move_Strategy {
    public Board_Evaluator boardEvaluator;
    private int depth ;


    /**
     * MiniMax constructor
     * @param depth     Defines the depth in which the traverse tree will go .
     */
    public MiniMax(int depth)
    {
        boardEvaluator = new StandardBoardEvaluator() ;
        this.depth = depth ;
    }

    /**
     * For every legal move for the current player , the method execute that move virtually .
     * if the move isDone , then the method find the min value for the white alliance or max value for the black alliance
     * then the method checks is the current player is white
     *  and the current value is greater or equal to -Infinite then the best move is the current move and the highest value is the current value .
     * and the opposite situation to the black .
     * finally the method returns the best move .
     * @param board     The current board
     * @return          The best move (depending on current player alliance)
     */
    @Override
    public Move execute(Board board) {

        int highestValue = Integer.MIN_VALUE ;
        int lowestValue = Integer.MAX_VALUE ;
        Move bestMove = null ;
        int legalMoveNum = board.getCurrentPlayer().getLegalMoves().size() ;
        int currentValue ;

        for(Move move : board.getCurrentPlayer().getLegalMoves())
        {
            Move_Transition moveTransition = board.getCurrentPlayer().makeMove(move) ;
            if(moveTransition.getMoveStatus().isDone())
            {
                if(board.getCurrentPlayer().getAlliance().isWhite())
                    currentValue = Min(board, this.depth) ;
                else
                    currentValue = Max(board, this.depth) ;

                if(board.getCurrentPlayer().getAlliance().isWhite() && currentValue >= highestValue)
                {
                    bestMove = move ;
                    highestValue = currentValue ;
                }
                else if (board.getCurrentPlayer().getAlliance().isBlack() && currentValue <= lowestValue)
                {
                    bestMove = move ;
                    lowestValue = currentValue ;
                }
            }
        }

        return bestMove;
    }


    /**
     * Firstly the method checks if the depth = 0 (meaning that the traversal is over) or the game is over , then it redirects the process to evaluation
     * Otherwise , for every legal move for the current player , it performs the move virtually , and checks if its done .
     * If yes , it assigns the current value to the max value of the transition board , but with depth -1
     * now if the current value is less than or equal to the lowest value (+Infinite by default)
     * and it returns the lowest value .
     * @param board     The current board
     * @param depth     The specified depth
     * @return          The min value
     */
    public int Min(Board board , int depth)
    {
        if (depth == 0 || gameEnded(board))
            return this.boardEvaluator.evaluate(board, depth) ;

        int lowestValue = Integer.MAX_VALUE ;
        for(Move move : board.getCurrentPlayer().getLegalMoves())
        {
            Move_Transition moveTransition = board.getCurrentPlayer().makeMove(move) ;
            if(moveTransition.getMoveStatus().isDone())
            {
                int currentValue = Max(moveTransition.getTransitionBoard(),depth-1) ;
                if(currentValue <= lowestValue)
                    lowestValue = currentValue ;
            }
        }

        return lowestValue ;
    }

    /**
     * Firstly the method checks if the depth = 0 (meaning that the traversal is over) or the game is over , then it redirects the process to evaluation
     * Otherwise , for every legal move for the current player , it performs the move virtually , and checks if its done .
     * If yes , it assigns the current value to the min value of the transition board , but with depth -1
     * now if the current value is greater than or equal to the highest value (-Infinite by default)
     * and it returns the highest value .
     * @param board     The current board
     * @param depth     The specified depth
     * @return          The max value .
     */
    public int Max(Board board , int depth)
    {
        if (depth == 0 || gameEnded(board))
            return this.boardEvaluator.evaluate(board, depth) ;

        int highestValue = Integer.MIN_VALUE ;
        for(Move move : board.getCurrentPlayer().getLegalMoves())
        {
            Move_Transition moveTransition = board.getCurrentPlayer().makeMove(move) ;
            if(moveTransition.getMoveStatus().isDone())
            {
                int currentValue = Min(moveTransition.getTransitionBoard(),depth-1) ;
                if(currentValue >= highestValue)
                    highestValue = currentValue ;
            }
        }
        return highestValue ;
    }

    /**
     * This method checks whether the current player is inCheckMate or inStaleMate
     * @param board     The currentBoard
     * @return          True if the next move will end the game , and False otherwise .
     */
    private boolean gameEnded(Board board) {
        return board.getCurrentPlayer().isInCheckMate() || board.getCurrentPlayer().isInStaleMate() ;
    }
}
