package peacefulotter.engine.rendering;

import org.lwjgl.opengl.GL;
import peacefulotter.engine.components.*;
import peacefulotter.engine.components.lights.BaseLight;
import peacefulotter.engine.components.renderer.TerrainRenderer;
import peacefulotter.engine.core.maths.Vector3f;
import peacefulotter.engine.rendering.shaders.Shader;
import peacefulotter.engine.rendering.shaders.ShaderTypes;
import peacefulotter.engine.utils.MappedValues;
import peacefulotter.engine.utils.ProfileTimer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL32.GL_DEPTH_CLAMP;

public class RenderingEngine extends MappedValues
{
    private static final Vector3f skyColor = new Vector3f( 0.6f, 0.6f, 1f );

    private final ProfileTimer profiler;
    private final Window window;
    private final List<BaseLight> lights;
    private final Shader ambient;

    private final Map<String, Integer>  samplerMap;

    private TerrainRenderer terrainRenderer;
    private BaseLight activeLight;
    private Camera camera;


    public RenderingEngine()
    {
        this.profiler = new ProfileTimer();
        this.lights = new ArrayList<>();
        this.window = new Window();
        this.samplerMap = new HashMap<>();

        samplerMap.put( "diffuse", 0 );
        samplerMap.put( "normalMap", 1 );
        samplerMap.put( "dispMap", 2 );
        samplerMap.put( "aTexture", 3 );
        samplerMap.put( "rTexture", 4 );
        samplerMap.put( "gTexture", 5 );
        samplerMap.put( "bTexture", 6 );
        samplerMap.put( "blendMap", 7 );

        GL.createCapabilities();
        glClearColor( skyColor.getX(), skyColor.getY(), skyColor.getZ(), 0.8f );
        glFrontFace( GL_CW );
        glCullFace( GL_BACK );
        glEnable( GL_CULL_FACE );
        glEnable( GL_DEPTH_TEST );
        //glEnable( GL_DEPTH_CLAMP );
        glEnable( GL_TEXTURE_2D );

        addVector3f( "ambient", new Vector3f( 0.4f, 0.4f, 0.4f ) );
        addVector3f( "skyColor", new Vector3f( 0.2f, 0.2f, 0.34f ) );
        ambient = ShaderTypes.AMBIENT.getShader();

        // Window.bindAsRenderTarget(); Use for Render to Texture (not finished)
    }


    public void render( GameObject object )
    {
        if ( camera == null )
            throw new NullPointerException( "Main camera not found." );

        profiler.startInvocation();

        glClear( GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT );

        object.renderAll( ambient, this );

        glEnable( GL_BLEND );
        glBlendFunc( GL_ONE, GL_ONE );
        glDepthMask( false );
        glDepthFunc( GL_EQUAL );

        for ( BaseLight light : lights )
        {
            activeLight = light;
            object.renderAll( light.getShader(), this );
        }

        terrainRenderer.renderTerrain( this );

        glDepthFunc( GL_LESS );
        glDepthMask( true );
        glDisable( GL_BLEND );

        profiler.stopInvocation();
    }

    public void addLight( BaseLight light ) { lights.add( light ); }
    public void addTerrainRenderer( TerrainRenderer tr ) { terrainRenderer = tr; }

    public BaseLight getActiveLight() { return activeLight; }

    public long getCurrentWindow() { return window.getWindow(); }

    public Camera getCamera() { return camera; }
    public void setCamera( Camera camera ) { this.camera = camera; }

    public int getSamplerSlot( String samplerName )
    {
        return samplerMap.get( samplerName );
    }

    public double displayRenderTime( double dividend ) { return profiler.displayAndReset( "Render time", dividend ); }
}
