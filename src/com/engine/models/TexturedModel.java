package com.engine.models;

import com.engine.textures.ModelTexture;

public class TexturedModel {

    private RawModel rawModel;
    private ModelTexture texture;
    private boolean hasTransparency = false;

    public TexturedModel(RawModel model, ModelTexture texture){
        this.rawModel = model;
        this.texture = texture;
    }

    public RawModel getRawModel() {
        return rawModel;
    }
    public void setRawModel(RawModel rawModel) {
        this.rawModel = rawModel;
    }

    public ModelTexture getTexture() { return texture; }
    public void setTexture(ModelTexture texture) {
        this.texture = texture;
    }

    public boolean isHasTransparency() { return hasTransparency; }
    public void setHasTransparency(boolean hasTransparency) { this.hasTransparency = hasTransparency; }
}
