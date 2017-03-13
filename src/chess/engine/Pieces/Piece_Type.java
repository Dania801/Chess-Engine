package chess.engine.Pieces;

public enum Piece_Type {
    PAWN("P" , 100) {
        public boolean isKing() {
            return false;
        }

        public boolean isRook() {
            return false;
        }
    },
    KNIGHT("N" , 300) {
        public boolean isKing() {
            return false;
        }

        public boolean isRook() {
            return false;
        }
    },
    BISHOP("B" , 300) {
        public boolean isKing() {
            return false;
        }

        public boolean isRook() {
            return false;
        }
    },
    ROOK("R" , 500) {
        public boolean isKing() {
            return false;
        }

        public boolean isRook() {
            return true;
        }
    },
    QUEEN("Q" , 900) {
        public boolean isKing() {
            return false;
        }

        public boolean isRook() {
            return false;
        }
    },
    KING("K" , 1000) {
        //Recheck the value of king
        public boolean isKing() {
            return true;
        }

        public boolean isRook() {
            return false;
        }
    };

    private final String pieceName;
    private final int pieceValue;

    Piece_Type(final String pieceName , final int pieceValue) {
        this.pieceName = pieceName;
        this.pieceValue = pieceValue ; }

    public abstract boolean isKing() ;
    public abstract boolean isRook() ;

    @Override
    public String toString() {
        return this.pieceName;
    }

    public int getPieceValue()
    {
        return this.pieceValue ;
    }

}