package pong.application.windows.state;

import pong.Drawable;

/**
 * Represents the Multiplayer game state. This class implements the State Design
 * Pattern as defined by the {@link GameContext} and {@link Drawable}
 * interfaces.
 *
 * @author Ishan Pranav
 */
public class MultiplayerGameState extends GameState
{
    /**
     * Initializes a new instance of the {@link MultiplayerGameState} class.
     *
     * @param context The game context.
     */
    public MultiplayerGameState(GameContext context)
    {
        super(context);
    }

    /** {@inheritDoc} */
    @Override
    protected void moveLeftDown()
    {
        this.getTable().getLeft().moveDown();
    }

    /** {@inheritDoc} */
    @Override
    protected void moveLeftUp()
    {
        this.getTable().getLeft().moveUp();
    }

    /** {@inheritDoc} */
    @Override
    protected void moveRightDown()
    {
        this.getTable().getRight().moveDown();
    }

    /** {@inheritDoc} */
    @Override
    protected void moveRightUp()
    {
        this.getTable().getRight().moveUp();
    }
}
