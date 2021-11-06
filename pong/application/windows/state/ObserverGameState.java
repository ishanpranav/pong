package pong.application.windows.state;

import pong.Ball;
import pong.Drawable;
import pong.Table;

/**
 * Represents the Observer game state. This class implements the State Design
 * Pattern as defined by the {@link GameContext} and {@link Drawable}
 * interfaces.
 *
 * @author Ishan Pranav
 */
public class ObserverGameState extends GameState
{
    /**
     * Initializes a new instance of the {@link ObserverGameState} class.
     *
     * @param context The game context.
     */
    public ObserverGameState(GameContext context)
    {
        super(context);
    }

    /** {@inheritDoc} */
    @Override
    public void update()
    {
        final Table table = this.getTable();
        final Ball ball = table.getBall();

        table.getLeft().follow(ball);
        table.getRight().follow(ball);

        super.update();
    }
}
