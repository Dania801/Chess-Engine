package chess.AI;

import chess.engine.Board.Board;
import chess.engine.Move.Move;

/**
 * Created by dania on 3/10/17.
 */
public interface Move_Strategy {
    Move execute(Board board) ;
}
