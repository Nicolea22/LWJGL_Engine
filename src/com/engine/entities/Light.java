package com.engine.entities;

import org.lwjgl.util.vector.Vector3f;

public class Light extends Entity{

    private Vector3f position;
    private Vector3f colour;

    public Light(Vector3f position, Vector3f colour) {
        super(null, position, null, 1);
        this.position = position;
        this.colour = colour;
    }

    public Vector3f getPosition() {
        return position;
    }

    public void setPosition(Vector3f position) {
        this.position = position;
    }

    public Vector3f getColour() {
        return colour;
    }

    public void setColour(Vector3f colour) {
        this.colour = colour;
    }

}
