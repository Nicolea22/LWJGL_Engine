package com.engine.tester;

import com.engine.entities.Camera;
import com.engine.entities.Entity;
import com.engine.entities.Light;
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


        RawModel model = OBJLoader.loadObjModel("dragon", loader);

        TexturedModel staticModel = new TexturedModel(model, new ModelTexture(loader.loadTexture("texturaPlana")));

        ModelTexture texture = staticModel.getTexture();
        texture.setShineDamper(10);
        texture.setReflectivity(1);


        Entity entity = new Entity(staticModel, new Vector3f(0, -3.5f, -15f), 0, 0.1f, 0, 1);

        Light light = new Light(new Vector3f(0, 20, -10), new Vector3f(1, 0, 1));

        Camera camera = new Camera();

        while(!Display.isCloseRequested()) {
            entity.increasePosition(0, 0, 0);
            entity.increaseRotation(0, 0.4f, 0);
            renderer.prepare();
            shader.start();
            shader.loadLight(light);
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
