package pong.application.windows.state;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import pong.Drawable;
import pong.Score;
import pong.Table;

/**
 * Defines the core behavior of a game state and provides a base for derived
 * classes.
 *
 * @author Ishan Pranav
 */
public abstract class GameState implements Drawable
{
    private final GameContext _context;
    private final Table _table;

    /**
     * Called from constructors in derived classes to initialize the
     * {@link GameState} class.
     *
     * @param context The game context.
     */
    protected GameState(GameContext context)
    {
        this._table = context.createTable();
        this._context = context;
    }

    /** {@inheritDoc} */
    @Override
    public final void draw(Graphics2D graphics2D)
    {
        this._table.draw(graphics2D);
    }

    /**
     * Gets the table.
     *
     * @return The table.
     */
    protected final Table getTable()
    {
        return this._table;
    }

    /** {@inheritDoc} */
    protected void moveLeftDown()
    {
    }

    /** {@inheritDoc} */
    protected void moveLeftUp()
    {
    }

    /** {@inheritDoc} */
    protected void moveRightDown()
    {
    }

    /** {@inheritDoc} */
    protected void moveRightUp()
    {
    }

    /** {@inheritDoc} */
    @Override
    public void update()
    {
        this._table.update();

        final Score score = this._table.getScore();

        if (score.isTerminated())
        {
            this._table.reset();
            this._context.setState(new TerminalGameState(this._context, score.isLeftVictor()));
        }

        if (this._context.isPressed(KeyEvent.VK_W))
        {
            this.moveLeftUp();
        }

        if (this._context.isPressed(KeyEvent.VK_UP))
        {
            this.moveRightUp();
        }

        if (this._context.isPressed(KeyEvent.VK_S))
        {
            this.moveLeftDown();
        }

        if (this._context.isPressed(KeyEvent.VK_DOWN))
        {
            this.moveRightDown();
        }

        if (this._context.isPressed(KeyEvent.VK_SPACE))
        {
            this._table.getBall().confuse();
        }
    }
}
