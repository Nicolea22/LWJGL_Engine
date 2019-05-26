package com.engine.tester;

import com.engine.render.DisplayManager;
import com.engine.render.Loader;
import com.engine.render.RawModel;
import com.engine.render.Renderer;
import org.lwjgl.opengl.Display;

public class MainGameLoop {


    public static void main(String[] args){

        DisplayManager.createDisplay();

        Loader loader = new Loader();
        Renderer renderer = new Renderer();

        float[] vertices = {
                -0.5f, 0.5f, 0f,
                -0.5f,-0.5f, 0f,
                 0.5f,-0.5f, 0f,

                 0.5f,-0.5f, 0f,
                 0.5f, 0.5f, 0f,
                -0.5f, 0.5f, 0f,
        };

        RawModel model = loader.loadToVAO(vertices);

        while(!Display.isCloseRequested()) {
            renderer.prepare();
            renderer.render(model);

            Display.update();
        }

        Display.destroy();

    }
}
