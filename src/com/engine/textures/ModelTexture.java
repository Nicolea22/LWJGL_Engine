package com.engine.textures;

public class ModelTexture {

    private int textureID;

    private float shineDamper = 1;
    private float reflectivity = 0;
    private boolean hasTransparency = false;
    private boolean fakeLight = false;

    public int getTextureID(){
        return this.textureID;
    }
    public void setTextureID(int textureID) { this.textureID = textureID; }

    public float getShineDamper() { return shineDamper; }
    public void setShineDamper(float shineDamper) { this.shineDamper = shineDamper; }

    public float getReflectivity() { return reflectivity; }
    public void setReflectivity(float reflectivity) { this.reflectivity = reflectivity; }

    public ModelTexture(int id){ this.textureID = id; }

    public boolean isHasTransparency() { return hasTransparency; }
    public void setHasTransparency(boolean hasTransparency) { this.hasTransparency = hasTransparency; }

    public boolean isFakeLight() { return fakeLight; }
    public void setFakeLight(boolean fakeLight) { this.fakeLight = fakeLight; }
}
