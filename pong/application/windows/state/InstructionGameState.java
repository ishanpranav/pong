package pong.application.windows.state;

import java.awt.Graphics2D;

import pong.Drawable;
import pong.application.ResourceProvider;
import pong.application.windows.CenteredStringDrawingContext;

/**
 * Represents the Instruction game state. This class implements the State Design
 * Pattern as defined by the {@link GameContext} and {@link Drawable}
 * interfaces.
 *
 * @author Ishan Pranav
 */
public class InstructionGameState implements Drawable
{
    private final GameContext _context;
    private final int _maxScore;

    /**
     * Initializes a new instance of the {@link InstructionGameState} class.
     *
     * @param context  The game context.
     * @param maxScore The maximum score.
     */
    public InstructionGameState(GameContext context, int maxScore)
    {
        this._context = context;
        this._maxScore = maxScore;
    }

    /** {@inheritDoc} */
    @Override
    public final void draw(Graphics2D graphics2D)
    {
        final ResourceProvider resources = this._context.getResources();
        final CenteredStringDrawingContext stringDrawingContext = new CenteredStringDrawingContext(graphics2D,
                this._context.getWidth(), 75);

        stringDrawingContext.setSize(100);
        stringDrawingContext.setMargin(1d / 3);
        stringDrawingContext.setSize(20);

        stringDrawingContext.draw(resources.getInstructions(this._maxScore));
    }

    /** {@inheritDoc} */
    @Override
    public final void update()
    {
    }
}
