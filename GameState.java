package Bishops.Model;

import puzzle.State;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents the current state and applicable functions to it.
 */
public class GameState implements puzzle.State<Move> {

    /**
     * The current gamestate.
     */
    private Table table;

    /**
     * Creates the current gamestate.
     * @param other The current gamestate.
     */
    public GameState(Table other) {
        this.table = new Table(other);
    }

    /**
     * Check if there are 4 white pieces on the top and 4 black on the bottom, 8 correct positions altogether.
     * @return whether the current state is a goal state.
     */
    @Override
    public boolean isSolved() {
        int count = 0;
        for (int j = 0; j < 4; j++) {
            if (table.getBoard()[0][j] == Square.white) {
                count++;
            }
            if (table.getBoard()[4][j] == Square.black) {
                count++;
            }
        }
        return count >= 8;
    }

    /**
     *
     * @param m move
     * @return True or False based on whether that move can be made in the current state.
     */
    @Override
    public boolean isLegalMove(Move m) {

        if (table.player == m.player && m.player == Player.white) {
            if (table.getBoard()[m.rowF][m.colF] == Square.white) {
                if (table.getBoard()[m.rowT][m.colT] == Square.w_blocked) {
                    // positiv / negatice slope
                    return m.colT - m.colF == m.rowT - m.rowF || m.colT - m.colF == m.rowF - m.rowT;
                }
            }
        } else if (table.getBoard()[m.rowF][m.colF] == Square.black) {
            if (table.getBoard()[m.rowT][m.colT] == Square.b_blocked) {
                // positiv / negatice slope
                return m.colT - m.colF == m.rowT - m.rowF || m.colT - m.colF == m.rowF - m.rowT;
            }
        }
        return false;
    }

    /**
     *
     * Applies the specified move to the current state.
     * @param m The move we want to make.
     */
    @Override
    public void makeMove(Move m) {
        this.table.setBoard(m.rowT, m.colT, this.table.getBoard()[m.rowF][m.colF]);
        this.table.setBoard(m.rowF, m.colF, Square.free);
        this.table.calculateTable();
        this.table.player = this.table.player.toggle();
    }

    /**
     *
     * @return All the possible moves from the current state.
     */
    @Override
    public Set<Move> getLegalMoves() {
        Set<Move> legalMoves = new HashSet<>();
        Player player = this.table.player;
        int[][] directions = { { 1, 1 }, { 1, -1 }, { -1, 1 }, { -1, -1 } };
        for (int i = 0; i < this.table.rows; i++) {
            for (int j = 0; j < this.table.columns; j++) {
                if (this.table.getBoard()[i][j] == Square.white && Player.white == player) {
                    for (int[] direction : directions) {
                        int x = i + direction[0];
                        int y = j + direction[1];
                        while (x >= 0 && x < 5 && y >= 0 && y < 4) {
                            if (isLegalMove(new Move(i, j, x, y, player))) {
                                legalMoves.add(new Move(i, j, x, y, player));
                            }
                            x += direction[0];
                            y += direction[1];
                        }
                    }
                }
                if (this.table.getBoard()[i][j] == Square.black && Player.black == player) {
                    for (int[] direction : directions) {
                        int x = i + direction[0];
                        int y = j + direction[1];
                        while (x >= 0 && x < 5 && y >= 0 && y < 4) {
                            if (isLegalMove(new Move(i, j, x, y, player))) {
                                legalMoves.add(new Move(i, j, x, y, player));
                            }
                            x += direction[0];
                            y += direction[1];
                        }
                    }
                }
            }
        }
        return legalMoves;
    }

    @Override
    public State<Move> clone() {
        GameState copy = null;
        try {
            copy = (GameState) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
        copy = new GameState(this.table);

        return copy;
    }

    @Override
    public String toString() {
          return "\nGameState{" +
          table +
          "}\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        GameState gameState = (GameState) o;
        return gameState.table.equals(this.table);
    }

    @Override
    public int hashCode() {
        return table.hashCode();
    }
}
