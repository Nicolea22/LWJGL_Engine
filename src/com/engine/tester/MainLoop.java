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

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainLoop {

    public static void main(String[] args) {

        DisplayManager.createDisplay();

        Loader loader = new Loader();

        Light light = new Light(new Vector3f(0, 20, -10), new Vector3f(1, 1, 1));
        Terrain terrain = new Terrain(0, 0, loader, new ModelTexture(loader.loadTexture("grass")));
        Terrain terrain2 = new Terrain(1, 0, loader, new ModelTexture(loader.loadTexture("grass")));
        Camera camera = new Camera();

        RawModel model = OBJLoader.loadObjModel("lowPolyTree", loader);
        TexturedModel staticModel = new TexturedModel(model, new ModelTexture(loader.loadTexture("lowPolyTree")));
        ModelTexture texture = staticModel.getTexture();
        texture.setShineDamper(30);
        texture.setReflectivity(0.1f);

        TexturedModel fern = new TexturedModel(OBJLoader.loadObjModel("fern",loader),
                             new ModelTexture(loader.loadTexture("fern")));

        List<Entity> entities = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < 500; i++){
            entities.add(new Entity(fern, new Vector3f(random.nextFloat() * 800 - 400,0,random.nextFloat() * 800 - 600), 0,0,0,1));
            entities.add(new Entity(staticModel, new Vector3f(random.nextFloat() * 800 - 400,0,random.nextFloat() * 800 - 600), 0,0,0,1));
        }
        MasterRenderer renderer = new MasterRenderer();

        while(!Display.isCloseRequested()) {
            renderer.processTerrain(terrain);
            renderer.processTerrain(terrain2);
            for(Entity entity:entities){
                renderer.processEntity(entity);
            }
            camera.move();
            renderer.render(light, camera);
            DisplayManager.updatedisplay();
        }
        renderer.cleanUp();
        Display.destroy();
    }
}