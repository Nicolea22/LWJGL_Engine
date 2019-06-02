package com.engine.tester;

import com.engine.entities.Camera;
import com.engine.entities.Entity;
import com.engine.entities.Light;
import com.engine.models.RawModel;
import com.engine.models.TexturedModel;
import com.engine.render.DisplayManager;
import com.engine.render.Loader;
import com.engine.render.MasterRenderer;
import com.engine.render.OBJLoader;
import com.engine.terrains.Terrain;
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

        Light light = new Light(new Vector3f(0, 20, -10), new Vector3f(1, 1, 1));
        Terrain terrain = new Terrain(0, 0, loader, new ModelTexture(loader.loadTexture("grass")));
        Terrain terrain2 = new Terrain(1, 0, loader, new ModelTexture(loader.loadTexture("grass")));
        Camera camera = new Camera();

        MasterRenderer renderer = new MasterRenderer();

        while(!Display.isCloseRequested()) {
            renderer.render(light, camera);

            renderer.processTerrain(terrain);
            renderer.processTerrain(terrain2);

            camera.move();
            DisplayManager.updatedisplay();
        }
        renderer.cleanUp();
        Display.destroy();
    }


}
