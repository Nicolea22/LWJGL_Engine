package com.engine.render;

import com.engine.entities.Entity;
import com.engine.models.TexturedModel;
import com.engine.shaders.StaticShader;
import com.engine.textures.ModelTexture;
import com.engine.toolbox.EngineMath;
import org.lwjgl.opengl.*;
import org.lwjgl.util.vector.Matrix4f;

import java.util.List;
import java.util.Map;

public class EntityRenderer {

    private StaticShader shader;

    public EntityRenderer(StaticShader shader, Matrix4f projectionMatrix) {
        this.shader = shader;
        shader.start();
        shader.loadProjectionMatrix(projectionMatrix);
        shader.stop();
    }


    public void render(Map<TexturedModel, List<Entity>> entities) {
        for (TexturedModel model : entities.keySet()) {
            prepareTexturedModel(model);
            List<Entity> batch = entities.get(model);
            for (Entity entity : batch) {
                prepareInstance(entity);
                drawElements(model);
            }
            unbindTextureModel();
        }
    }

    private void prepareInstance(Entity entity) {
        Matrix4f transformationMatrix = EngineMath.createTransformationMatrix(entity.getPosition(),
                entity.getRotX(), entity.getRotY(), entity.getRotZ(), entity.getScale());
        shader.loadTransformationMatrix(transformationMatrix);
    }


    private void drawElements(TexturedModel model) {
        GL11.glDrawElements(GL11.GL_TRIANGLES, model.getRawModel().getVertexCount(),
                GL11.GL_UNSIGNED_INT, 0);
    }

    private void unbindTextureModel() {
        MasterRenderer.enableCulling();
        GL20.glDisableVertexAttribArray(0);
        GL20.glDisableVertexAttribArray(1);
        GL20.glDisableVertexAttribArray(2);
        GL30.glBindVertexArray(0);
    }

    private void prepareTexturedModel(TexturedModel rawModel) {
        GL30.glBindVertexArray(rawModel.getRawModel().getVaoID());
        GL20.glEnableVertexAttribArray(0);
        GL20.glEnableVertexAttribArray(1);
        GL20.glEnableVertexAttribArray(2);
        ModelTexture texture = rawModel.getTexture();

        if(texture.isHasTransparency()){
            MasterRenderer.disableCulling();
        }

        shader.loadShineVariables(texture.getShineDamper(), texture.getReflectivity());
        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, rawModel.getTexture().getTextureID());
    }



}
