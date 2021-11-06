package pong.application;

/**
 * Defines the core behavior of resource providers and provides a base for
 * derived classes.
 *
 * @author Ishan Pranav
 */
public abstract class ResourceProvider
{
    /**
     * Called from constructors in derived classes to initialize the
     * {@link ResourceProvider} class.
     */
    protected ResourceProvider()
    {
    }

    /**
     * Gets the Exit caption.
     *
     * @return A string containing the caption for the Exit game option.
     */
    public String getExitText()
    {
        return this.getString("0");
    }

    /**
     * Gets a multiline string that contains the game instructions.
     *
     * @param maxScore The maximum score.
     * @return The game instructions.
     */
    public String getInstructions(int maxScore)
    {
        return this.getString("1") + maxScore + this.getString("2") + this.getExitText();
    }

    /**
     * Gets a string containing the caption for the Instructions game option.
     *
     * @return The Instructions game option caption.
     */
    public String getInstructionText()
    {
        return this.getString("3");
    }

    /**
     * Gets a string containing the caption presented when the player on the left
     * side wins.
     *
     * @return The left victory text.
     */
    public String getLeftVictoryText()
    {
        return this.getString("4");
    }

    /**
     * Gets a string containing the caption for the Multiplayer game option.
     *
     * @return The Multiplayer game option caption.
     */
    public String getMultiplayerText()
    {
        return this.getString("5");
    }

    /**
     * Gets a string containing the caption for the Observer game option.
     *
     * @return The Observer game option caption.
     */
    public String getObserverText()
    {
        return this.getString("6");
    }

    /**
     * Gets a string containing the caption presented when the player on the right
     * side wins.
     *
     * @return The Right victory text.
     */
    public String getRightVictoryText()
    {
        return this.getString("7");
    }

    /**
     * Gets a string containing the caption for the Singleplayer game option.
     *
     * @return The Singleplayer game option caption.
     */
    public String getSingleplayerText()
    {
        return this.getString("8");
    }

    /**
     * Gets the localized string value associated with the given key.
     *
     * @param key The key.
     * @return The string value.
     */
    protected abstract String getString(String key);

    /**
     * Gets the application title.
     *
     * @return The application title.
     */
    public final String getTitle()
    {
        return String.join(" ", this.getTitleSegments());
    }

    /**
     * Gets the segments of the application title string.
     *
     * @return An array that contains the segments of the application title string.
     */
    public String[] getTitleSegments()
    {
        return new String[]
        {
            this.getString("9"), this.getString("10")
        };
    }
}
