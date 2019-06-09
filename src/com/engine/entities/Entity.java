package com.engine.entities;

import com.engine.models.TexturedModel;
import org.lwjgl.util.vector.Vector3f;

public class Entity {

    protected Vector3f position;
    protected Vector3f rotation;
    protected float scale;

    public Entity(Vector3f position, Vector3f rotation, float scale) {
        this.position = position;
        this.rotation = rotation;
        this.scale = scale;
    }

    public void increasePosition(float dx, float dy, float dz){
        this.position.x += dx;
        this.position.y += dy;
        this.position.z += dz;
    }

    public void increaseRotation(float dx, float dy, float dz){
        this.rotation.x += dx;
        this.rotation.y += dy;
        this.rotation.z += dz;
    }



    public Vector3f getPosition() {
        return position;
    }
    public void setPosition(Vector3f position) {
        this.position = position;
    }

    public Vector3f getRotation(){ return rotation;}
    public void setRotation(Vector3f rotation) {
        this.rotation = rotation;
    }

    public float getScale() {
        return scale;
    }
    public void setScale(float scale) {
        this.scale = scale;
    }

    public float getX(){ return position.getX(); }
    public void setX(float x){ this.position.x = x;}

    public float getY(){ return position.getY(); }
    public void setY(float y){ this.position.y = y;}

    public float getZ(){ return position.getZ(); }
    public void setZ(float z){ this.position.z = z;}
}
