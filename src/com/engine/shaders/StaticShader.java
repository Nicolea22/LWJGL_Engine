package com.engine.shaders;

import com.engine.entities.Camera;
import com.engine.toolbox.EngineMath;
import org.lwjgl.util.vector.Matrix4f;

public class StaticShader extends ShaderProgram {

    private static final String VERTEX_FILE =   "src/shaders_files/vertexShader";
    private static final String FRAGMENT_FILE = "src/shaders_files/fragmentShader";

    private int location_transformationMatrix;
    private int location_projectionMatrix;
    private int location_viewMatrix;


    public StaticShader(){
        super(VERTEX_FILE, FRAGMENT_FILE );
    }

    @Override
    protected void getAllUniformLocations() {
        location_transformationMatrix = super.getUniformLocation("transformationMatrix");
        location_projectionMatrix = super.getUniformLocation("projectionMatrix");
        location_viewMatrix = super.getUniformLocation("viewMatrix");
    }

    public void loadTransformationMatrix(Matrix4f matrix){
        super.loadMatrix(location_transformationMatrix, matrix);
    }

    public void loadProjectionMatrix(Matrix4f matrix){
        super.loadMatrix(location_projectionMatrix, matrix);
    }


    public void loadViewMatrix(Camera camera){
        Matrix4f viewMatrix = EngineMath.createViewMatrix(camera);
        super.loadMatrix(location_viewMatrix,viewMatrix);
    }


    @Override
    protected void bindAttributes() {
        super.bindAttribute(0, "position");
        super.bindAttribute(1, "textureCoord");
    }
}
