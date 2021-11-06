package pong.application.windows;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import pong.Ball;
import pong.Drawable;
import pong.Entity;
import pong.Paddle;
import pong.Score;
import pong.Table;
import pong.application.ResourceProvider;
import pong.application.windows.state.GameContext;
import pong.application.windows.state.InitialGameState;

/**
 * Represents an application used to play table tennis.
 *
 * @author Ishan Pranav
 */
public class Game extends GameDriverSlim implements GameContext, Entity
{
    private static final long serialVersionUID = -1614426423878335492L;

    private static final int HEIGHT = 600;
    private static final int HALF_HEIGHT = HEIGHT / 2;
    private static final int MAX_SCORE = 10;
    private static final int OBJECT_SIZE = 20;
    private static final int MARGIN = OBJECT_SIZE / 2;
    private static final int WIDTH = 800;

    /**
     * Specifies the resource provider. This field is serialized.
     */
    private final ResourceProvider _resources;

    /**
     * Specifies the game state. This field is serialized.
     */
    private Drawable _state;

    /**
     * Initializes a new instance of the {@link Game} class.
     *
     * @param resources The resource provider.
     */
    public Game(ResourceProvider resources)
    {
        this._resources = resources;
        this.setTitle(this._resources.getTitle());
        this.setSize(WIDTH, HEIGHT);
        this.reset();
    }

    /** {@inheritDoc} */
    @Override
    public final Table createTable()
    {
        return new Table(new Score(MAX_SCORE), new Ball((WIDTH - OBJECT_SIZE) / 2, HALF_HEIGHT, OBJECT_SIZE, 4),
                new Paddle(MARGIN, HALF_HEIGHT - OBJECT_SIZE - MARGIN, OBJECT_SIZE, OBJECT_SIZE * 4, 10),
                new Paddle(WIDTH - OBJECT_SIZE - MARGIN, HALF_HEIGHT - OBJECT_SIZE - MARGIN, OBJECT_SIZE,
                        OBJECT_SIZE * 4, 10),
                WIDTH, HEIGHT, MARGIN);
    }

    /** {@inheritDoc} */
    @Override
    public void draw(Graphics2D graphics2D)
    {
        graphics2D.clearRect(0, 0, this.getWidth(), this.getHeight());
        graphics2D.setColor(Color.white);

        this._state.draw(graphics2D);
    }

    /** {@inheritDoc} */
    @Override
    public final ResourceProvider getResources()
    {
        return this._resources;
    }

    /** {@inheritDoc} */
    @Override
    public final void reset()
    {
        this.setState(new InitialGameState(this, MAX_SCORE));
    }

    /** {@inheritDoc} */
    @Override
    public final void setState(Drawable value)
    {
        this._state = value;
    }

    /** {@inheritDoc} */
    @Override
    public void update()
    {
        if (this.isPressed(KeyEvent.VK_ESCAPE))
        {
            this.reset();
        }

        this._state.update();
    }
}