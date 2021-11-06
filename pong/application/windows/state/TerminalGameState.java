package pong.application.windows.state;

import java.awt.Graphics2D;

import pong.Drawable;
import pong.application.ResourceProvider;
import pong.application.windows.CenteredStringDrawingContext;

/**
 * Represents the terminal game state. This class implements the State Design
 * Pattern as defined by the {@link GameContext} and {@link Drawable}
 * interfaces.
 *
 * @author Ishan Pranav
 */
public class TerminalGameState implements Drawable
{
    private final GameContext _context;
    private final boolean _leftVictory;

    /**
     * Initializes a new instance of the {@link TerminalGameState} class.
     *
     * @param context     The game context.
     * @param leftVictory {@code true} if the Left player won the game; otherwise,
     *                    {@code false}.
     */
    public TerminalGameState(GameContext context, boolean leftVictory)
    {
        this._context = context;
        this._leftVictory = leftVictory;
    }

    /** {@inheritDoc} */
    @Override
    public final void draw(Graphics2D graphics2D)
    {
        final ResourceProvider resources = this._context.getResources();

        final CenteredStringDrawingContext stringDrawingContext = new CenteredStringDrawingContext(graphics2D,
                this._context.getWidth(), 75);

        if (this._leftVictory)
        {
            stringDrawingContext.draw(resources.getLeftVictoryText());
        }
        else
        {
            stringDrawingContext.draw(resources.getRightVictoryText());
        }

        stringDrawingContext.draw(resources.getExitText());
    }

    /** {@inheritDoc} */
    @Override
    public final void update()
    {
    }
}
