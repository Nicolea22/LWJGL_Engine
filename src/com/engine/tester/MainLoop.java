package com.engine.tester;

import com.engine.entities.Camera;
import com.engine.entities.Entity;
import com.engine.models.RawModel;
import com.engine.models.TexturedModel;
import com.engine.render.DisplayManager;
import com.engine.render.Loader;
import com.engine.render.OBJLoader;
import com.engine.render.Renderer;
import com.engine.shaders.StaticShader;
import com.engine.textures.ModelTexture;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

public class MainLoop {


    public static void main(String[] args) {

        DisplayManager.createDisplay();

        Loader loader = new Loader();
        StaticShader shader = new StaticShader();
        Renderer renderer = new Renderer(shader);


        RawModel model = OBJLoader.loadObjModel("stall", loader);

        TexturedModel staticModel = new TexturedModel(model, new ModelTexture(loader.loadTexture("stallTexture")));
        Entity entity = new Entity(staticModel, new Vector3f(0, 0, -1.5f), 0, 0.1f, 0, 1);

        Camera camera = new Camera();

        while(!Display.isCloseRequested()) {
            entity.increasePosition(0, 0, 0);
            entity.increaseRotation(0, 0.4f, 0);
            renderer.prepare();
            shader.start();
            shader.loadViewMatrix(camera);
            camera.move();
            renderer.render(entity, shader);
            shader.stop();
            DisplayManager.updatedisplay();
        }

        shader.cleanUp();
        loader.cleanUp();
        Display.destroy();
    }


}
