package pong;

/**
 * Represents a game score.
 *
 * @author Ishan Pranav
 */
public class Score
{
    private int _left;

    private final int _max;

    private int _right;

    /**
     * Initializes a new instance of the {@link Score} class.
     *
     * @param max The maximum score.
     */
    public Score(int max)
    {
        this._max = max;
    }

    /**
     * Gets the Left player's score.
     *
     * @return The Left score.
     */
    public final int getLeft()
    {
        return this._left;
    }

    /**
     * Gets the Right player's score.
     *
     * @return The Right score.
     */
    public final int getRight()
    {
        return this._right;
    }

    /**
     * Gets a value indicating whether the Left player won.
     *
     * @return {@code true} if the Left player won; otherwise {@code false}.
     */
    public final boolean isLeftVictor()
    {
        return this._left >= this._max;
    }

    /**
     * Gets a value indicating whether the Right player won.
     *
     * @return {@code true} if the Right player won; otherwise {@code false}.
     */
    public final boolean isRightVictor()
    {
        return this._right >= this._max;
    }

    /**
     * Gets a value indicating whether the game ended.
     *
     * @return {@code true} if the game ended; otherwise {@code false}.
     */
    public final boolean isTerminated()
    {
        return this.isLeftVictor() || this.isRightVictor();
    }

    /**
     * Scores a point for the Left player.
     */
    public final void scoreLeft()
    {
        this._left++;
    }

    /**
     * Scores a point for the Right player.
     */
    public final void scoreRight()
    {
        this._right++;
    }
}
