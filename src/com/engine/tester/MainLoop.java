package com.engine.tester;

import com.engine.entities.Camera;
import com.engine.entities.Entity;
import com.engine.entities.Light;
import com.engine.entities.SolidEntity;
import com.engine.render.DisplayManager;
import com.engine.render.MasterRenderer;
import com.engine.terrains.Terrain;
import com.engine.textures.ModelTexture;
import org.lwjgl.opengl.Display;

import static com.engine.constants.Constants.Entities;
import static com.engine.entities.EntitiesFactory.getEntity;

public class MainLoop {


    public static void main(String[] args) {
        DisplayManager.createDisplay();

        SolidEntity entity = (SolidEntity)getEntity(Entities.SOLID);
        Light light = (Light)getEntity(Entities.LIGHT);
        Terrain terrain = (Terrain)getEntity(Entities.TERRAIN);
        Camera camera = (Camera)getEntity(Entities.CAMERA);

        MasterRenderer renderer = new MasterRenderer();

        while(!Display.isCloseRequested()) {
            renderer.processEntity(entity);
            entity.increaseRotation(0, 0.3f, 0);
            renderer.processTerrain(terrain);
            camera.move();
            renderer.render(light, camera);
            DisplayManager.updatedisplay();
        }
        renderer.cleanUp();
        Display.destroy();
    }


}
