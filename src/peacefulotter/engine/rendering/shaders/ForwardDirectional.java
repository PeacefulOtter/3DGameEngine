package peacefulotter.engine.rendering.shaders;

import peacefulotter.engine.components.lights.DirectionalLight;
import peacefulotter.engine.core.maths.Matrix4f;
import peacefulotter.engine.components.Camera;
import peacefulotter.engine.rendering.RenderingEngine;
import peacefulotter.engine.rendering.graphics.Material;
import peacefulotter.engine.rendering.shaders.transfomations.STransform;
import peacefulotter.engine.utils.ResourceLoader;

public class ForwardDirectional extends Shader
{
    private static ForwardDirectional instance = new ForwardDirectional();

    private ForwardDirectional()
    {
        super( "forward-directional" );
    }

    public void updateUniforms( STransform transform, Material material, RenderingEngine renderingEngine )
    {
        material.getTexture( "diffuse" ).bind();

        Camera camera = renderingEngine.getCamera();
        Matrix4f worldMatrix = transform.getTransformationMatrix();
        Matrix4f projectedMatrix = camera.getViewProjection().mul( worldMatrix );

        setUniformMatrix( "model", worldMatrix );
        setUniformMatrix( "MVP", projectedMatrix );
        setUniformF( "specularIntensity", material.getFloat( "specularIntensity" ) );
        setUniformF( "specularExponent", material.getFloat( "specularExponent" ) );
        setUniformVector( "eyePos", camera.getTransform().getTransformedTranslation() );
        setUniformDirLight( "dirLight", (DirectionalLight) renderingEngine.getActiveLight());
    }

    public static Shader getInstance() { return instance; }
}
