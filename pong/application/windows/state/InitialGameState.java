package pong.application.windows.state;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import pong.Drawable;
import pong.application.ResourceProvider;
import pong.application.windows.CenteredStringDrawingContext;

/**
 * Represents the initial menu game state. This class implements the State
 * Design Pattern as defined by the {@link GameContext} and {@link Drawable}
 * interfaces.
 *
 * @author Ishan Pranav
 */
public class InitialGameState implements Drawable
{
    private final GameContext _context;
    private final int _maxScore;

    /**
     * Initializes a new instance of the {@link InitialGameState} class.
     *
     * @param context  The game context.
     * @param maxScore The maximum score.
     */
    public InitialGameState(GameContext context, int maxScore)
    {
        this._context = context;
        this._maxScore = maxScore;
    }

    /** {@inheritDoc} */
    @Override
    public final void draw(Graphics2D graphics2D)
    {
        final ResourceProvider resources = this._context.getResources();
        final String[] titleSegments = resources.getTitleSegments();
        final CenteredStringDrawingContext stringDrawingContext = new CenteredStringDrawingContext(graphics2D,
                this._context.getWidth(), 75);

        stringDrawingContext.setSize(25);
        stringDrawingContext.setMargin(-1d / 3);

        stringDrawingContext.draw(titleSegments[0]);

        stringDrawingContext.setSize(100);
        stringDrawingContext.setMargin(1d / 3);

        stringDrawingContext.draw(titleSegments[1]);

        stringDrawingContext.setSize(20);

        stringDrawingContext.draw(resources.getObserverText());
        stringDrawingContext.draw(resources.getSingleplayerText());
        stringDrawingContext.draw(resources.getMultiplayerText());
        stringDrawingContext.draw(resources.getInstructionText());
    }

    /** {@inheritDoc} */
    @Override
    public final void update()
    {
        if (this._context.isPressed(KeyEvent.VK_0))
        {
            this._context.setState(new ObserverGameState(this._context));
        }
        else if (this._context.isPressed(KeyEvent.VK_1))
        {
            this._context.setState(new SingleplayerGameState(this._context));
        }
        else if (this._context.isPressed(KeyEvent.VK_2))
        {
            this._context.setState(new MultiplayerGameState(this._context));
        }
        else if (this._context.isPressed(KeyEvent.VK_Z))
        {
            this._context.setState(new InstructionGameState(this._context, this._maxScore));
        }
    }
}
