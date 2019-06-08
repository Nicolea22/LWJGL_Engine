package com.engine.entities;

import com.engine.models.TexturedModel;
import com.engine.render.Loader;
import com.engine.terrains.Terrain;
import com.engine.textures.ModelTexture;
import org.lwjgl.util.vector.Vector3f;

public class EntitiesFactory {

    public static Entity getEntity(Integer entityType){

        if (entityType == null){
            return null;
        }

        switch(entityType.intValue()){
            case 0:
                return new Terrain(0.5f, 0, new TexturedModel(null, new ModelTexture(Loader.getLoader().loadTexture("grass"))));
            case 1:
                return new Light(new Vector3f(0, 20, -10), new Vector3f(1, 1, 1));
            case 2:
                return new Camera();
            case 3:
            default:
                break;
        }
        return null;
    }

}
