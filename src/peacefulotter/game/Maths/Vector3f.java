package peacefulotter.game.Maths;

public class Vector3f
{
    private float x, y, z;

    public Vector3f( float x, float y, float z )
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public float getX() { return x; }
    public void setX( float x ) { this.x = x; }

    public float getY() { return y; }
    public void setY( float y ) { this.y = y; }

    public float getZ() { return z; }
    public void setZ( float z ) { this.z = z; }

    public float length()
    {
        return (float) Math.sqrt( BMaths.square( x ) + BMaths.square( y ) + BMaths.square( z ) );
    }

    public float dot( Vector3f other )
    {
        return x * other.getX() + y * other.getY() + z * other.getZ();
    }

    public Vector3f cross( Vector3f other )
    {
        float x_ = y * other.getZ() - z * other.getY();
        float y_ = z * other.getX() - x * other.getZ();
        float z_ = x * other.getY() - y * other.getX();

        return new Vector3f( x_, y_, z_ );
    }

    public Vector3f normalize()
    {
        float length = length();
        return new Vector3f( x / length, y / length, z / length );
    }

    public Vector3f rotate( float angleDeg )
    {
        return null;
    }

    public Vector3f add( Vector3f other )
    {
        return new Vector3f( x + other.getX(), y + other.getY(), z + other.getZ() );
    }

    public Vector3f add( float r )
    {
        return new Vector3f( x + r, y + r, z + r );
    }

    public Vector3f sub( Vector3f other )
    {
        return new Vector3f( x - other.getX(), y - other.getY(), z - other.getZ() );
    }

    public Vector3f sub( float r )
    {
        return new Vector3f( x - r, y - r, z - r );
    }

    public Vector3f mul( Vector3f other )
    {
        return new Vector3f( x * other.getX(), y * other.getY(), z * other.getZ() );
    }

    public Vector3f mul( float r )
    {
        return new Vector3f( x * r, y * r, z * r );
    }

    public Vector3f div( Vector3f other )
    {
        return new Vector3f( x / other.getX(), y / other.getY(), z / other.getZ() );
    }

    public Vector3f div( float r )
    {
        return new Vector3f( x / r, y / r, z / r );
    }
    @Override
    public String toString()
    {
        return "(" + x + ":" + y + ":" + z + ")";
    }
}