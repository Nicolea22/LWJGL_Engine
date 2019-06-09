package com.engine.entities;

import com.engine.models.TexturedModel;
import org.lwjgl.util.vector.Vector3f;

public class SolidEntity extends Entity{

    private TexturedModel model;

    public SolidEntity(TexturedModel model, Vector3f position, Vector3f rotation, float scale){
        super(position, rotation, 1);
        this.model = model;
    }

    public TexturedModel getModel() {
        return model;
    }
    public void setModel(TexturedModel model) {
        this.model = model;
    }
}
