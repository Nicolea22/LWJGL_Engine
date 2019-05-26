package com.engine.tester;

import com.engine.render.DisplayManager;
import com.engine.render.Loader;
import com.engine.models.RawModel;
import com.engine.render.Renderer;
import org.lwjgl.opengl.Display;
import com.engine.shaders.StaticShader;

public class MainLoop {


    public static void main(String[] args){

        DisplayManager.createDisplay();

        Loader loader = new Loader();
        Renderer renderer = new Renderer();
        StaticShader shader = new StaticShader();

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

        RawModel model = loader.loadToVAO(vertices,indices);

        while(!Display.isCloseRequested()) {
            renderer.prepare();
            shader.start();
            renderer.render(model);
            shader.stop();
            Display.update();
        }

        shader.cleanUp();
        loader.cleanUp();
        Display.destroy();

    }
}
