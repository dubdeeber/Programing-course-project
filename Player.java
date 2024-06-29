package Bishops.Model;

/**
 * The possible players of this project.
 */
public enum Player {
    /**
     * The white player
     */
    white,
    /**
     * The black player
     */
    black;

    /**
     *
     * @return The other player.
     */
    Player toggle() {
        if (this == white) { // switching players
            return black;
        } else {
            return white;
        }
    }
}
