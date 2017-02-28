package chess.engine.Move;

/**
 * Created by dania on 12/25/16.
 */
public enum Move_Status {
    DONE {
        public boolean isDone() {
            return true;
        }
    },
    ILLEGAL_MOVE {
        public boolean isDone() {
            return false;
        }
    },
    LEAVES_PLAYER_IN_CHECK {
        public boolean isDone() {
            return false;
        }
    };
    public abstract boolean isDone() ;
}
