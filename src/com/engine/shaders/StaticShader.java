package com.engine.shaders;

public class StaticShader extends ShaderProgram {

    private static final String VERTEX_FILE =   "src/shaders_files/vertexShader";
    private static final String FRAGMENT_FILE = "src/shaders_files/fragmentShader";

    public StaticShader(){
        super(VERTEX_FILE, FRAGMENT_FILE );
    }

    @Override
    protected void bindAttributes() {
        super.bindAttribute(0, "position");
        super.bindAttribute(1, "textureCoord");
    }
}
