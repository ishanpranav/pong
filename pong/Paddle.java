package pong;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 * Represents a table tennis paddle.
 *
 * @author Ishan Pranav
 */
public class Paddle implements Entity
{
    private final Rectangle _rectangle;
    private final int _speed;
    private final int _initialHorizontalPosition;
    private final int _initialVerticalPosition;

    private Table _table;
    private boolean _follow;
    private Direction _direction;

    /**
     * Initializes a new instance of the {@link Paddle} class.
     *
     * @param horizontalPosition The starting horizontal position of the paddle.
     * @param verticalPosition   The starting vertical position of the paddle.
     * @param width              The width of the paddle.
     * @param height             The height of the paddle.
     * @param speed              The speed of the paddle.
     */
    public Paddle(int horizontalPosition, int verticalPosition, int width, int height, int speed)
    {
        this._rectangle = new Rectangle(horizontalPosition, verticalPosition, width, height);
        this._speed = speed;
        this._initialHorizontalPosition = horizontalPosition;
        this._initialVerticalPosition = verticalPosition;
    }

    /** {@inheritDoc} */
    @Override
    public final void draw(Graphics2D graphics2D)
    {
        if (this._follow)
        {
            graphics2D.setColor(Color.LIGHT_GRAY);
        }
        else
        {
            graphics2D.setColor(Color.WHITE);
        }

        graphics2D.fill(this._rectangle);
    }

    /**
     * Tracks the position of a ball and follows it.
     *
     * @param ball The ball.
     */
    public final void follow(Ball ball)
    {
        this._follow = true;

        final int y = (int)(ball.getVerticalPosition() - this._rectangle.getHeight() / 2);

        this._rectangle.setLocation((int)this._rectangle.getX(), y);

        this._direction = Direction.fromDistance(this._rectangle.getY(), y);
    }

    /**
     * Moves the paddle down.
     */
    public final void moveDown()
    {
        if (this._rectangle.getY() <= this._table.getHeight() - this._rectangle.getHeight())
        {
            this._rectangle.translate(0, this._speed);
        }

        this._direction = Direction.POSITIVE;
    }

    /**
     * Moves the paddle up.
     */
    public final void moveUp()
    {
        if (this._rectangle.getY() >= 0)
        {
            this._rectangle.translate(0, -this._speed);
        }

        this._direction = Direction.NEGATIVE;
    }

    /** {@inheritDoc} */
    @Override
    public final void reset()
    {
        this._rectangle.setLocation(this._initialHorizontalPosition, this._initialVerticalPosition);
    }

    final void setTable(Table value)
    {
        this._table = value;
    }

    /** {@inheritDoc} */
    @Override
    public final void update()
    {
        if (this._rectangle.getMinY() < 0)
        {
            this._rectangle.setLocation((int)this._rectangle.getX(), 0);
        }
        else if (this._rectangle.getMaxY() > this._table.getHeight())
        {
            this._rectangle.setLocation((int)this._rectangle.getX(),
                    (int)(this._table.getHeight() - this._rectangle.getHeight()));
        }

        this._table.getBall().hit(this._rectangle, this._direction);
    }
}