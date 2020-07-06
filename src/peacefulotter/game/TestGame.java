package peacefulotter.game;

import peacefulotter.engine.components.*;
import peacefulotter.engine.components.lights.DirectionalLight;
import peacefulotter.engine.components.lights.PointLight;
import peacefulotter.engine.components.lights.SpotLight;
import peacefulotter.engine.core.Game;
import peacefulotter.engine.core.maths.Quaternion;
import peacefulotter.engine.core.maths.Vector2f;
import peacefulotter.engine.core.maths.Vector3f;
import peacefulotter.engine.rendering.Window;
import peacefulotter.engine.rendering.graphics.*;
import peacefulotter.engine.rendering.shaders.*;

import static peacefulotter.engine.utils.IO.Input.MOUSE_PRIMARY;
import static peacefulotter.engine.utils.IO.Input.MOUSE_SECONDARY;

public class TestGame extends Game
{
    public TestGame( String winName, int winWidth, int winHeight )
    {
        super( winName, winWidth, winHeight );
    }

    GameObject plane2;

    public void init()
    {
        Material material = new Material(
                new Texture( "bricks.jpg" ),
                new Texture( "bricks_normal.jpg" ),
                new Texture( "bricks_height.jpg" ),
                1f, 2, 0.03f, -0.5f );


        Mesh mesh = new Mesh( "plane3.obj" );
        GameObject plane1 = new GameObject().addComponent( new MeshRenderer( mesh, material ) );
        plane1.getTransform().scale( 3 );
        plane2 = new GameObject().addComponent( new MeshRenderer( mesh, material ) );
        GameObject plane3 = new GameObject().addComponent( new MeshRenderer( mesh, material ) );
        GameObject plane4 = new GameObject().addComponent( new MeshRenderer( mesh, material ) );
        plane2.getTransform().translate( new Vector3f( 8, 4, 8 ) ).scale( 0.5f );
        plane3.getTransform().translate( new Vector3f( 0, 3, 0 ) ).scale( 0.5f );
        plane4.getTransform().translate( new Vector3f( 0, 6, 0 ) ).scale( 0.5f );
        plane3.addChild( plane4 );
        plane2.addChild( plane3 );

        Material alienMaterial = new Material(
                new Texture( "metal.jpg" ),
                new Texture( "metal_normal.jpg" ),
                new Texture( "metal_height.png" ),
                1, 2, 0.04f, -0.5f );
        GameObject alienObject = new GameObject().addComponent(
                new MeshRenderer(  new Mesh( "alien.obj" ), alienMaterial ) );
        addObject( alienObject );
        alienObject.getTransform().translate( new Vector3f( 3, 0, 3 ) ).scale( 0.5f );


        /*Material treesMaterial = new Material();
        treesMaterial.addTexture( "diffuse", new Texture( "grass.jpg" ) );
        treesMaterial.addFloat( "specularIntensity", 1 );
        treesMaterial.addFloat( "specularExponent", 2 );
        GameObject treesObject = new GameObject().addComponent(
                new MeshRenderer(  new Mesh( "trees9.obj" ), treesMaterial ) );
        addObject( treesObject );
        treesObject.getTransform().translate( new Vector3f( -30, 0, -30 ) );*/


        /*Material m4a4Material = new Material();
        m4a4Material.addTexture( "diffuse", new Texture( "test.png" ) );
        m4a4Material.addFloat( "specularIntensity", 1 );
        m4a4Material.addFloat( "specularExponent", 2 );*/
        GameObject m4a4Object = new GameObject().addComponent(
                new MeshRenderer(  new Mesh( "m4a1.obj" ), alienMaterial ) );
        addObject( m4a4Object );
        m4a4Object.getTransform().translate( new Vector3f( 30, 0, 30 ) );

        GameObject dirLightObject = new GameObject();
        DirectionalLight dirLight = new DirectionalLight(
                new Vector3f( 0.5f, 0.5f,0.5f ),
                0.2f );
        dirLightObject.addComponent( dirLight );
        dirLightObject.getTransform().setRotation( new Quaternion( new Vector3f( 1, -1, 0 ), -45 ) );


        GameObject pointLightObject = new GameObject();
        PointLight pointLight = new PointLight(
                new Vector3f( 0f, 1f,  1f ),
                0.4f,
                new Attenuation( 0.5f, 0, 0.01f ) );
        pointLightObject.addComponent( pointLight );
        pointLightObject.getTransform().translate( new Vector3f( 4, 0.5f, 4 ) );

        GameObject spotLightObject = new GameObject();
        SpotLight spotLight = new SpotLight(
                new Vector3f( 0f, 1f,  1f ),
                1f,
                new Attenuation( 0, 0.1f, 0.001f ),
                0.7f
        );
        spotLightObject.getTransform()
                .rotate( new Vector3f( 0, 1, 0 ), -90 )
                .rotate( new Vector3f( 1, 0, 0 ), 40 )
                .translate( new Vector3f( 55, 10f, 8 ) );
        spotLightObject.addComponent( spotLight );

        GameObject cameraObject = new GameObject();
        Camera camera = new Camera(
                70f,
                (float) Window.getWidth() / (float) Window.getHeight(),
                0.01f, 1000f );
        camera.addMouseCallback( MOUSE_PRIMARY, ( deltaTime ) -> {
            System.out.println("shoottingg");
        } );
        camera.addMouseCallback( MOUSE_SECONDARY, ( deltaTime ) -> {
            System.out.println("aiminggg");
        } );
        cameraObject.getTransform().translate( new Vector3f( 0, 1, 0 ) );
        cameraObject.addComponent( camera );
        // Hide Mouse
        // int hideMouse = action == 1 ? GLFW_CURSOR_DISABLED : GLFW_CURSOR_NORMAL;
        // glfwSetInputMode( window, GLFW_CURSOR, hideMouse );
        cameraObject.getTransform().translate( new Vector3f(0, 2, 0) );


        addObjects( plane1, plane2, dirLightObject, pointLightObject, spotLightObject, cameraObject );

        super.init();
    }

    float t = 0;

    public void update( float deltaTime )
    {
        t += deltaTime * 5;
        plane2.getTransform()
                .setTranslation( new Vector3f( (float)Math.cos(t)*2 - 30, (float)Math.sin(t)*2 + 4, (float)Math.sin(t)*2 + 8 ) );

        super.update( deltaTime );
    }
}
