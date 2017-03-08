package chess.engine.Move;

import chess.engine.Board.Board;
import chess.engine.Pieces.Piece;

/**
 * Created by dania on 2/18/17.
 */
public class Null_Move extends Move {

    public Null_Move() {
        super(null, -1, null);
    }

    @Override
    public Board executeMove()
    {
        throw new RuntimeException("Can't excute a Null move!");
    }
}
