package peacefulotter.game.Utils;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL30.*;

public class RenderUtil
{
    public static void clearScreen()
    {
        glClear( GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT );
    }

    public static void initGraphics()
    {
        glClearColor( 0, 0, 0, 0 );
        glFrontFace( GL_CW );
        glCullFace( GL_BACK );
        glEnable( GL_CULL_FACE );
        glEnable( GL_DEPTH_TEST );

        glEnable( GL_FRAMEBUFFER_SRGB );
    }
}
