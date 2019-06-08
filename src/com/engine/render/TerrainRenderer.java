package com.engine.render;

import com.engine.entities.Entity;
import com.engine.models.TexturedModel;
import com.engine.shaders.TerrainShader;
import com.engine.terrains.Terrain;
import com.engine.textures.ModelTexture;
import com.engine.toolbox.EngineMath;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import java.util.List;

public class TerrainRenderer {

    private TerrainShader shader;

    public TerrainRenderer(TerrainShader shader, Matrix4f projectionMatrix){
        this.shader = shader;
        shader.start();
        shader.loadProjectionMatrix(projectionMatrix);
        shader.stop();
    }

    public void render(List<Terrain> terrains){
        for(Terrain terrain:terrains){
            prepareTerrain(terrain);
            loadModelMatrix(terrain);
            GL11.glDrawElements(GL11.GL_TRIANGLES, terrain.getModel().getRawModel().getVertexCount(),
                    GL11.GL_UNSIGNED_INT, 0);
            unbindTextureModel();
        }
    }

    private void loadModelMatrix(Terrain terrain) {
        Matrix4f transformationMatrix = EngineMath.createTransformationMatrix(new Vector3f(terrain.getPosition().x, 0, terrain.getPosition().z),
                0, -180, 0, 1);
        shader.loadTransformationMatrix(transformationMatrix);
    }


    private void drawElements(Terrain terrain) {

    }

    private void unbindTextureModel() {
        GL20.glDisableVertexAttribArray(0);
        GL20.glDisableVertexAttribArray(1);
        GL20.glDisableVertexAttribArray(2);
        GL30.glBindVertexArray(0);
    }

    private void prepareTerrain(Terrain terrain) {
        GL30.glBindVertexArray(terrain.getModel().getRawModel().getVaoID());
        GL20.glEnableVertexAttribArray(0);
        GL20.glEnableVertexAttribArray(1);
        GL20.glEnableVertexAttribArray(2);
        ModelTexture texture = terrain.getModel().getTexture();
        shader.loadShineVariables(texture.getShineDamper(), texture.getReflectivity());
        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture.getTextureID());
    }

}
