package Bishops.Model;

/**
 * Representation of a move.
 */
public class Move {
    int rowF;
    int colF;
    int rowT;
    int colT;
    Player player;

    /**
     * Defines a move, which we want to take.
     * @param rowF Original row of moved piece.
     * @param colF Original column of moved piece.
     * @param rowT Desired row of piece.
     * @param colT Desired column of piece.
     * @param p The current player.
     */
    public Move(int rowF, int colF, int rowT, int colT, Player p) {
        this.rowF = rowF;
        this.colF = colF;
        this.rowT = rowT;
        this.colT = colT;
        this.player = p;

    }

    @Override
    public String toString() {
        return "Move{" +
                rowF +
                ":" + colF +
                " --->>> " + rowT +
                ":" + colT +
                ",  player=" + player +
                '}';
    }
}
