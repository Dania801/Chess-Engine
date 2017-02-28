package chess.engine.Pieces;

public enum Piece_Type {
    PAWN("P") {
        public boolean isKing() {
            return false;
        }

        public boolean isRook() {
            return false;
        }
    },
    KNIGHT("N") {
        public boolean isKing() {
            return false;
        }

        public boolean isRook() {
            return false;
        }
    },
    BISHOP("B") {
        public boolean isKing() {
            return false;
        }

        public boolean isRook() {
            return false;
        }
    },
    ROOK("R") {
        public boolean isKing() {
            return false;
        }

        public boolean isRook() {
            return true;
        }
    },
    QUEEN("Q") {
        public boolean isKing() {
            return false;
        }

        public boolean isRook() {
            return false;
        }
    },
    KING("K") {
        public boolean isKing() {
            return true;
        }

        public boolean isRook() {
            return false;
        }
    };

    private final String pieceName;

    Piece_Type(final String pieceName) { this.pieceName = pieceName; }

    public abstract boolean isKing() ;
    public abstract boolean isRook() ;

    @Override
    public String toString() {
        return this.pieceName;
    }

}