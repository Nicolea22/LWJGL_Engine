package com.engine.Tester;

import com.engine.render.DisplayManager;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class MainGameLoop {


    public static void main(String[] args){

        DisplayManager.createDisplay();
        while(!Display.isCloseRequested()) {
            Display.update();
        }

        Display.destroy();

    }
}
