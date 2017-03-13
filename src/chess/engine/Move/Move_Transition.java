package chess.engine.Move;


import chess.engine.Board.Board;

public class Move_Transition {
    private final Board transitionBoard ;
    private final Move move ;
    private final Move_Status moveStatus ;

    /**
     *
     * @param transitionBoard       The board after a move is made
     * @param move                  The move that will cause the transition
     * @param moveStatus            The status of the move wether its inCheck or any other move .
     */
    public Move_Transition(final Board transitionBoard , final Move move , final Move_Status moveStatus)
    {
        this.transitionBoard = transitionBoard ;
        this.move = move ;
        this.moveStatus = moveStatus ;
    }

    public Move_Status getMoveStatus(){return this.moveStatus ; }
    public Board getTransitionBoard(){return this.transitionBoard ; }

}
