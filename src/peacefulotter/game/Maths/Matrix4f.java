package peacefulotter.game.Maths;

public class Matrix4f
{
    private static final int MATRIX_SIZE = 4;
    private float m[][];

    public Matrix4f()
    {
        this.m = new float[ MATRIX_SIZE ][ MATRIX_SIZE ];
    }

    public Matrix4f initIdentity()
    {
        for( int i = 0; i < MATRIX_SIZE; i++ )
            m[ i ][ i ] = 1;
        return this;
    }

    public Matrix4f initTranslation( float x, float y, float z )
    {
        initIdentity();
        m[ 0 ][ 3 ] = x;
        m[ 1 ][ 3 ] = y;
        m[ 2 ][ 3 ] = z;
        return this;
    }

    public Matrix4f initRotation( float x, float y, float z )
    {
        Matrix4f rotationX = new Matrix4f().initIdentity();
        Matrix4f rotationY = new Matrix4f().initIdentity();
        Matrix4f rotationZ = new Matrix4f().initIdentity();

        x = (float) Math.toRadians( x );
        y = (float) Math.toRadians( y );
        z = (float) Math.toRadians( z );

        float cosX = (float) Math.cos( x ); float sinX = (float) Math.sin( x );
        rotationX.m[ 1 ][ 1 ] =  cosX;
        rotationX.m[ 1 ][ 2 ] = -sinX;
        rotationX.m[ 2 ][ 1 ] =  sinX;
        rotationX.m[ 2 ][ 2 ] =  cosX;

        float cosY = (float) Math.cos( y ); float sinY = (float) Math.sin( y );
        rotationY.m[ 0 ][ 0 ] =  cosY;
        rotationY.m[ 0 ][ 2 ] = -sinY;
        rotationY.m[ 2 ][ 0 ] =  sinY;
        rotationY.m[ 2 ][ 2 ] =  cosY;

        float cosZ = (float) Math.cos( z ); float sinZ = (float) Math.sin( z );
        rotationZ.m[ 0 ][ 0 ] =  cosZ;
        rotationZ.m[ 0 ][ 1 ] = -sinZ;
        rotationZ.m[ 1 ][ 0 ] =  sinZ;
        rotationZ.m[ 1 ][ 1 ] =  cosZ;

        m = rotationZ.mul( rotationY.mul( rotationX ) ).getM();

        return this;
    }

    public float[][] getM() { return m; }
    public void setM( float[][] other ) { m = other; }

    public float getAt( int x, int y )
    {
        return m[ x ][ y ];
    }

    public void setAt( int x, int y, float value )
    {
        m[ x ][ y ] = value;
    }

    public Matrix4f mul( Matrix4f other )
    {
        Matrix4f res = new Matrix4f();
        for ( int i = 0; i < MATRIX_SIZE; i++ )
        {
            for ( int j = 0; j < MATRIX_SIZE; j++ )
            {
                float value = 0;
                for ( int k = 0; k < MATRIX_SIZE; k++ )
                {
                    value += getAt( i, k ) * other.getAt( k, j );
                }
                res.setAt( i, j, value );
            }
        }
        return res;
    }
}