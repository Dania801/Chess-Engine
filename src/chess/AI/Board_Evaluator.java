package chess.AI;

import chess.engine.Board.Board;

/**
 * Created by dania on 3/10/17.
 */
public interface Board_Evaluator {
    int evaluate(Board board , int depth) ;
}
