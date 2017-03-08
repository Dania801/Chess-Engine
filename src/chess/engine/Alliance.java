package chess.engine;

import chess.engine.Board.BoardUtils;
import chess.engine.Player.Player;

public enum Alliance {
    /**
     * Two main types with several properties for each one :
     * WHITE    the white alliance in the game .
     * BLACK    the black alliance in the game .
     *
     * The properties of each alliance are :
     * The direction of the alliance .
     * Whether it's black or white alliance .
     */
    WHITE {
        public int getDirection() {
            return -1;
        }

        public int getOppositeDirection()  { return 1; }

        public boolean isBlack() {
            return false;
        }

        public boolean isWhite() {
            return true;
        }

        public Player choosePlayer(Player whitePlayer, Player blackPlayer) {
            return whitePlayer;
        }

        public boolean isPromotionSquare(int coordinate) {
            return BoardUtils.EIGHTH_ROW[coordinate] ;
        }
    },
    BLACK {
        public int getDirection() {
            return 1;
        }

        public int getOppositeDirection() {return -1; }

        public boolean isBlack() {
            return true;
        }

        public boolean isWhite() {
            return false;
        }

        public Player choosePlayer(Player whitePlayer, Player blackPlayer) {
            return blackPlayer;
        }

        public boolean isPromotionSquare(int coordinate) {
            return BoardUtils.FIRST_ROW[coordinate] ;
        }
    } ;

    /**
     * @return      1 for BLACK alliance , and -1 for WHITE alliance .
     */
    public abstract int getDirection() ;

    public abstract int getOppositeDirection() ;

    /**
     * @return      true for BLACK alliance , and false for WHITE alliance .
     */
    public abstract boolean isBlack() ;

    /**
     * @return      true for WHITE alliance , and false for BLACK alliance .
     */
    public abstract boolean isWhite() ;

    public abstract Player choosePlayer(final Player whitePlayer , final Player blackPlayer) ;

    public abstract boolean isPromotionSquare(int coordinate) ;
}
