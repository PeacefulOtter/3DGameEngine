package peacefulotter.game.Maths;

public class Vector2f
{
    private float x, y;

    public Vector2f( float x, float y )
    {
        this.x = x;
        this.y = y;
    }

    public float getX() { return x; }
    public float getY() { return y; }

    public void setX( float newX ) { x = newX; }
    public void setY( float newY ) { y = newY; }

    public float length()
    {
        return (float) Math.sqrt( BMaths.square( x ) + BMaths.square( y ) );
    }

    public float dot( Vector2f other )
    {
        return x * other.getX() + y * other.getY();
    }

    public Vector2f normalize()
    {
        float length = length();
        return new Vector2f( x / length, y / length );
    }

    public Vector2f rotate( float angleDeg )
    {
        double rad = Math.toRadians( angleDeg );
        double cos = Math.cos( rad );
        double sin = Math.sin( rad );
        return new Vector2f( (float) (x * cos - y * sin), (float) (x * sin + y * cos) );
    }

    public Vector2f add( Vector2f other )
    {
        return new Vector2f( x + other.getX(), y + other.getY() );
    }

    public Vector2f add( float r )
    {
        return new Vector2f( x + r, y + r );
    }

    public Vector2f sub( Vector2f other )
    {
        return new Vector2f( x - other.getX(), y - other.getY() );
    }

    public Vector2f sub( float r )
    {
        return new Vector2f( x - r, y - r );
    }

    public Vector2f mul( Vector2f other )
    {
        return new Vector2f( x * other.getX(), y * other.getY() );
    }

    public Vector2f mul( float r )
    {
        return new Vector2f( x * r, y * r );
    }

    public Vector2f div( Vector2f other )
    {
        return new Vector2f( x / other.getX(), y / other.getY() );
    }

    public Vector2f div( float r )
    {
        return new Vector2f( x / r, y / r );
    }

    public Vector2f abs() { return new Vector2f( Math.abs( x ), Math.abs( y ) ); }

    @Override
    public String toString()
    {
        return "(" + x + ":" + y + ")";
    }
}
