package com.engine.entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;

public class Camera {

    private Vector3f position;
    private float pitch;
    private float yaw;
    private float roll;

    public Camera(){
        position = new Vector3f(0,5,0);
    }

    public void move(){
        if(Keyboard.isKeyDown(Keyboard.KEY_W)){
            position.y -= 0.08f;
        }

        if(Keyboard.isKeyDown(Keyboard.KEY_D)){
            position.x -= 0.02f;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_A)){
            position.x += 0.02f;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_S)){
            position.y += 0.08f;
        }
    }

    public Vector3f getPosition() {
        return position;
    }

    public float getPitch() {
        return pitch;
    }

    public float getYaw() {
        return yaw;
    }

    public float getRoll() {
        return roll;
    }




}
