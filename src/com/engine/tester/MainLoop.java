package com.engine.tester;

import com.engine.models.RawModel;
import com.engine.models.TexturedModel;
import com.engine.render.DisplayManager;
import com.engine.render.Loader;
import com.engine.render.Renderer;
import com.engine.shaders.StaticShader;
import com.engine.textures.ModelTexture;
import com.engine.toolbox.EngineMath;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

public class MainLoop {


    public static void main(String[] args){

        DisplayManager.createDisplay();

        Loader loader = new Loader();
        Renderer renderer = new Renderer();
        StaticShader shader = new StaticShader();

        Vector3f vector = new Vector3f(0f, 0f, 0);
        Matrix4f matrix = new Matrix4f(EngineMath.createTransformationMatrix(vector, (float) ((90/180)*Math.PI), 0, 0, 1));

        shader.loadTransformationMatrix(matrix);

        float[] vertices = {
                -0.5f, 0.5f, 0f,
                -0.5f,-0.5f, 0f,
                0.5f,-0.5f, 0f,
                0.5f, 0.5f, 0f,
        };

        int[] indices = {
                0,1,3,
                3,1,2
        };

        float[] textureCoord ={
                0,0,
                0,1,
                1,1,
                1,0
        };

        RawModel model = loader.loadToVAO(vertices, textureCoord, indices);
        ModelTexture texture = new ModelTexture(loader.loadTexture("texturePrueba"));

        TexturedModel texturedModel = new TexturedModel(model, texture);

        while(!Display.isCloseRequested()) {
            renderer.prepare();
            shader.start();
            renderer.render(texturedModel);
            shader.stop();
            Display.update();
        }

        shader.cleanUp();
        loader.cleanUp();
        Display.destroy();
    }

}
