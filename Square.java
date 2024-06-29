package Bishops.Model;

/**
 * Types of squares possible in this project.
 */
public enum Square {
    /**
     * A white piece
     */
    white,
    /**
     * A black piece
     */
    black,
    /**
     * A square not attacked by anyone.
     */
    free,
    /**
     * A square blocked by at least 1 white piece.
     */
    w_blocked,
    /**
     * A square blocked by at least 1 black piece.
     */
    b_blocked,
    /**
     * A square blocked by at least one of each colored piece.
     */
    both
}
