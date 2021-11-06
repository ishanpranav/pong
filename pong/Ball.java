package pong;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 * Represents a table tennis ball.
 *
 * @author Ishan Pranav
 */
public class Ball implements Entity
{
    private static final double SMASH_FORCE_FACTOR = 1.2;

    private final int _initialHorizontalPosition;
    private final int _initialVerticalPosition;
    private final Rectangle _rectangle;
    private final int _speed;
    private final int _maxSpeed;

    private Table _table;
    private int _horizontalVelocity;
    private int _verticalVelocity;
    private boolean _confused;

    /**
     * Initializes a new instance of the {@link Ball} class.
     *
     * @param horizontalPosition The starting horizontal position of the ball.
     * @param verticalPosition   The starting vertical position of the ball.
     * @param size               The size of the ball.
     * @param speed              The speed of the ball.
     */
    public Ball(int horizontalPosition, int verticalPosition, int size, int speed)
    {
        this._rectangle = new Rectangle(horizontalPosition, verticalPosition, size, size);
        this._speed = speed;
        this._maxSpeed = speed * 2;
        this._initialHorizontalPosition = horizontalPosition;
        this._initialVerticalPosition = verticalPosition;
    }

    /**
     * Reverses the direction of the ball.
     */
    public void confuse()
    {
        if (!this._confused)
        {
            this._verticalVelocity *= -1;
            this._horizontalVelocity *= -1;
            this._confused = true;
        }
    }

    /** {@inheritDoc} */
    @Override
    public final void draw(Graphics2D graphics2D)
    {
        final int intensity = Math.max(55, Math.min(255, 255 * Math.abs(this._horizontalVelocity) / this._maxSpeed));

        graphics2D.setColor(new Color(intensity, intensity / 2, 0));
        graphics2D.fill(this._rectangle);
    }

    final double getVerticalPosition()
    {
        return this._rectangle.getCenterY();
    }

    final void hit(Rectangle rectangle, Direction verticalDirection)
    {
        final Rectangle intersection = this._rectangle.intersection(rectangle);

        if (!intersection.isEmpty())
        {
            if (intersection.getHeight() > 0)
            {
                this._horizontalVelocity *= -1;
            }
            
            if (intersection.getWidth() > (this._rectangle.getWidth() / 5))
            {
                this._verticalVelocity *= -1;
            }

            System.out.println(intersection.getWidth() + " x " + intersection.getHeight());
            
            if (Direction.fromVector(this._verticalVelocity) == verticalDirection)
            {
                this._horizontalVelocity *= SMASH_FORCE_FACTOR;
                this._verticalVelocity *= SMASH_FORCE_FACTOR;
            }
        }
    }

    /** {@inheritDoc} */
    @Override
    public final void reset()
    {
        this._confused = false;

        this._rectangle.setLocation(this._initialHorizontalPosition, this._initialVerticalPosition);

        this._horizontalVelocity = 0;
        this._verticalVelocity = 0;
    }

    private void serve()
    {
        this._verticalVelocity = this._speed;
    }

    /**
     * Serves the ball to the left.
     */
    public final void serveLeft()
    {
        this.serve();

        this._horizontalVelocity = -this._speed;
    }

    private void serveRight()
    {
        this.serve();

        this._horizontalVelocity = this._speed;
    }

    final void setTable(Table value)
    {
        this._table = value;
    }

    /** {@inheritDoc} */
    @Override
    public final void update()
    {
        if (Math.abs(this._horizontalVelocity) > this._maxSpeed)
        {
            this._horizontalVelocity = this._maxSpeed * (int)Math.signum(this._horizontalVelocity);
        }

        if (Math.abs(this._verticalVelocity) > this._maxSpeed)
        {
            this._horizontalVelocity = this._maxSpeed * (int)Math.signum(this._verticalVelocity);
        }

        this._rectangle.translate(this._horizontalVelocity, this._verticalVelocity);

        if (this._rectangle.getMaxX() > this._table.getWidth())
        {
            this._table.getScore().scoreLeft();
            this._table.reset();

            this.serveLeft();
        }
        else if (this._rectangle.getX() < 0)
        {
            this._table.getScore().scoreRight();
            this._table.reset();

            this.serveRight();
        }

        if (this._rectangle.getMinY() <= 0 || this._rectangle.getMaxY() >= this._table.getHeight())
        {
            this._verticalVelocity *= -1;
        }
    }
}