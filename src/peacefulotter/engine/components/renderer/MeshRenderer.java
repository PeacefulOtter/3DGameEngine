package peacefulotter.engine.components.renderer;

import peacefulotter.engine.components.GameComponent;
import peacefulotter.engine.rendering.RenderingEngine;
import peacefulotter.engine.rendering.graphics.Material;
import peacefulotter.engine.rendering.graphics.Mesh;
import peacefulotter.engine.rendering.shaders.Shader;

public class MeshRenderer extends GameComponent
{
    private final Mesh mesh;
    private final Material material;

    public MeshRenderer( Mesh mesh, Material material )
    {
        this.mesh = mesh;
        this.material = material;
    }

    @Override
    public void render( Shader shader, RenderingEngine renderingEngine )
    {
        if ( material.hasTransparency() )
            RenderingEngine.disableCulling();
        shader.bind();
        shader.updateUniforms( getTransform(), material, renderingEngine );
        mesh.draw();
        if ( material.hasTransparency() )
            RenderingEngine.enableCulling();
    }
}
