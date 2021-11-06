package pong.application.windows.state;

import pong.Drawable;
import pong.Table;

/**
 * Represents the Singleplayer game state. This class implements the State
 * Design Pattern as defined by the {@link GameContext} and {@link Drawable}
 * interfaces.
 *
 * @author Ishan Pranav
 */
public class SingleplayerGameState extends GameState
{
    /**
     * Initializes a new instance of the {@link SingleplayerGameState} class.
     *
     * @param context The game context.
     */
    public SingleplayerGameState(GameContext context)
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
        this.moveLeftDown();
    }

    /** {@inheritDoc} */
    @Override
    protected void moveRightUp()
    {
        this.moveLeftUp();
    }

    /** {@inheritDoc} */
    @Override
    public void update()
    {
        final Table table = this.getTable();

        table.getRight().follow(table.getBall());

        super.update();
    }
}
