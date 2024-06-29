package Bishops.Model;

import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;

/**
 * Represents the current state of the game. how the board looks like
 * and who is the next player to move.
 */
public class Table {
    /**
     * The number of rows in the table.
     */
    public final int rows = 5;
    /**
     * The number of columns in the table
     */
    public final int columns = 4;
    /**
     * Setting the default player to White.
     */
    Player player = Player.white;
    @Getter
    private Square[][] board = new Square[rows][columns];

    /**
     * This constructor makes a value copy instead of a reference copy.
     * @param other A {@code table}, which we want to copy.
     */
    public Table(Table other) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                board[i][j] = other.board[i][j];
            }
        }
        this.player = other.player;
        calculateTable();
    }

    /**
     * Generates the base problem.
     */
    public Table() {
        for (int i = 0; i < columns; i++) {
            board[0][i] = Square.black;
            board[4][i] = Square.white;
        }
        calculateTable();
    }

    /**
     * Generates a problem based on the {@code board} provided
     * @param board the game board.
     */
    public Table(Square[][] board) {
        this.board = board;
        calculateTable();
    }

    /**
     * Returns the current table as a Table.
     * @return The current {@code Board}, and the {@code Player} to move.
     */
    public Table getTable() {
        return this;
    }

    /**
     * This method calculates which squares are candidates for moves, and fills the {@code board} accordingly.
     */
    public void calculateTable() { // generating fields, so we know which squares are viable for moves
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (board[i][j] != Square.black && board[i][j] != Square.white) {
                    board[i][j] = Square.free;
                }
            }
        }
        // we freed every square that Does NOT have a piece on it, because they might
        int[][] directions = { { 1, 1 }, { 1, -1 }, { -1, 1 }, { -1, -1 } };
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (board[i][j] == Square.white) { // if the square is White, we explore where it could go, and fill the table accordingly
                    for (int[] direction : directions) {
                        int x = i + direction[0];
                        int y = j + direction[1];
                        while (x >= 0 && x < 5 && y >= 0 && y < 4) {
                            if (board[x][y] == Square.free) {
                                board[x][y] = Square.w_blocked;
                            }
                            if (board[x][y] == Square.b_blocked) {
                                board[x][y] = Square.both;
                            }
                            x += direction[0];
                            y += direction[1];
                        }
                    }
                }
                if (board[i][j] == Square.black) {// same as White
                    for (int[] direction : directions) {
                        int x = i + direction[0];
                        int y = j + direction[1];
                        while (x >= 0 && x < 5 && y >= 0 && y < 4) {
                            if (board[x][y] == Square.free) {
                                board[x][y] = Square.b_blocked;
                            }
                            if (board[x][y] == Square.w_blocked) {
                                board[x][y] = Square.both;
                            }
                            x += direction[0];
                            y += direction[1];
                        }
                    }
                }
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Table table = (Table) o;
        return Objects.deepEquals(getBoard(), table.getBoard()) && this.player.equals(((Table) o).player);
    }

    @Override
    public int hashCode() {
        return player.hashCode() + Arrays.deepHashCode(getBoard());
    }

    /**
     * Sets a specific {@code Square} in the {@code Board} to a type of {@code Square}, For example, {@code free}
     * @param a Row of the square we want to change.
     * @param b Column of the square we want to change.
     * @param c Square type object, we want our specific location(square) to be.
     */
    public void setBoard(int a, int b, Square c) {
        this.board[a][b] = c;
    }

    @Override
    public String toString() {
        String s = "\n";
        for (int i = 0; i < rows; i++) {
            s += Arrays.deepToString(board[i]);
            s += "\n";

        }
        s += player.toString();
        s += "\n";
        return s;
    }
}
