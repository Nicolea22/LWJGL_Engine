package com.engine.tester;

import com.engine.entities.Camera;
import com.engine.entities.Entity;
import com.engine.entities.Light;
import com.engine.models.RawModel;
import com.engine.models.TexturedModel;
import com.engine.render.*;
import com.engine.shaders.StaticShader;
import com.engine.textures.ModelTexture;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

public class MainLoop {


    public static void main(String[] args) {

        DisplayManager.createDisplay();

        Loader loader = new Loader();

        RawModel model = OBJLoader.loadObjModel("stall", loader);

        TexturedModel staticModel = new TexturedModel(model, new ModelTexture(loader.loadTexture("stallTexture")));

        ModelTexture texture = staticModel.getTexture();
        texture.setShineDamper(10);
        texture.setReflectivity(0.3f);


        Entity entity = new Entity(staticModel, new Vector3f(0, -3.5f, -15f), 0, 0.1f, 0, 1);

        Light light = new Light(new Vector3f(0, 20, -10), new Vector3f(1, 1, 1));
        Camera camera = new Camera();

        MasterRender renderer = new MasterRender();

        while(!Display.isCloseRequested()) {
            entity.increasePosition(0, 0, 0);
            entity.increaseRotation(0, 0.4f, 0);

            renderer.processEntity(entity);

            camera.move();
            renderer.render(light, camera);
            DisplayManager.updatedisplay();
        }

        renderer.cleanUp();
        Display.destroy();
    }


}
