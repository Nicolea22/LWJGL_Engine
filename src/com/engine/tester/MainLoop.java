package com.engine.tester;

import com.engine.constants.Constants;
import com.engine.entities.Camera;
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
import static com.engine.entities.EntitiesFactory.getEntity;
import static com.engine.constants.Constants.Entities;

public class MainLoop {


    public static void main(String[] args) {

        DisplayManager.createDisplay();

        RawModel model = OBJLoader.loadObjModel("stall", Loader.getLoader());
        TexturedModel staticModel = new TexturedModel(model, new ModelTexture(Loader.getLoader().loadTexture("stallTexture")));

        ModelTexture texture = staticModel.getTexture();
        texture.setShineDamper(10);
        texture.setReflectivity(0.3f);
        Light light = (Light)getEntity(Entities.LIGHT);
        Terrain terrain = (Terrain)getEntity(Entities.TERRAIN);
        Terrain terrain2 = (Terrain)getEntity(Entities.TERRAIN);
        Camera camera = (Camera)getEntity(Entities.CAMERA);

        MasterRenderer renderer = new MasterRenderer();

        while(!Display.isCloseRequested()) {
            renderer.processTerrain(terrain);
            renderer.processTerrain(terrain2);
            camera.move();
            renderer.render(light, camera);
            DisplayManager.updatedisplay();
        }
        renderer.cleanUp();
        Display.destroy();
    }


}
