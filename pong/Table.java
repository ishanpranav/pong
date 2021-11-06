package pong;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import pong.application.windows.CenteredStringDrawingContext;

/**
 * Represents a table used for table tennis.
 *
 * @author Ishan Pranav
 */
public class Table implements Entity
{
    private final Ball _ball;
    private final int _height;
    private final Paddle _left;
    private final Rectangle _rectangle;
    private final Paddle _right;
    private final Score _score;
    private final int _width;
    private final Entity[] _entities;

    /**
     * Initializes a new instance of the {@link Table} class.
     *
     * @param score  The score.
     * @param ball   The ball.
     * @param left   The left paddle.
     * @param right  The right paddle.
     * @param width  The width.
     * @param height The height.
     * @param margin The margin.
     */
    public Table(Score score, Ball ball, Paddle left, Paddle right, int width, int height, int margin)
    {
        this._entities = new Entity[]
        {
            ball, left, right
        };

        this._score = score;

        ball.setTable(this);
        ball.serveLeft();

        this._ball = ball;

        left.setTable(this);

        this._left = left;

        right.setTable(this);

        this._right = right;

        this._width = width;
        this._height = height;
        this._rectangle = new Rectangle(width / 2, margin, 1, height - margin * 2);
    }

    /** {@inheritDoc} */
    @Override
    public final void draw(Graphics2D graphics2D)
    {
        graphics2D.setColor(Color.WHITE);

        final int halfWidth = (int)(this.getWidth() / 2);

        final CenteredStringDrawingContext leftStringDrawingContext = new CenteredStringDrawingContext(graphics2D,
                halfWidth, 0, 50);
        final CenteredStringDrawingContext rightStringDrawingContext = new CenteredStringDrawingContext(graphics2D,
                halfWidth, halfWidth, 50);

        leftStringDrawingContext.setSize(50);
        rightStringDrawingContext.setSize(50);

        leftStringDrawingContext.draw(this.getScore().getLeft());
        rightStringDrawingContext.draw(this.getScore().getRight());

        graphics2D.fill(this._rectangle);

        for (final Entity entity : this._entities)
        {
            entity.draw(graphics2D);
        }
    }

    /**
     * Gets the ball.
     *
     * @return The ball.
     */
    public final Ball getBall()
    {
        return this._ball;
    }

    final double getHeight()
    {
        return this._height;
    }

    /**
     * Gets the left paddle.
     *
     * @return The left paddle.
     */
    public final Paddle getLeft()
    {
        return this._left;
    }

    /**
     * Gets the right paddle.
     *
     * @return The right paddle.
     */
    public final Paddle getRight()
    {
        return this._right;
    }

    /**
     * Gets the score.
     *
     * @return The score.
     */
    public final Score getScore()
    {
        return this._score;
    }

    final double getWidth()
    {
        return this._width;
    }

    /** {@inheritDoc} */
    @Override
    public final void reset()
    {
        for (final Entity entity : this._entities)
        {
            entity.reset();
        }
    }

    /** {@inheritDoc} */
    @Override
    public final void update()
    {
        for (final Entity entity : this._entities)
        {
            entity.update();
        }
    }
}
