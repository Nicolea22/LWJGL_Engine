package com.engine.shader;

public class StaticShader extends ShaderProgram {

    private static final String VERTEX_FILE = "src/resources/vertexShader";
    private static final String FRAGMENT_FILE = "src/resources/fragmentShader";

    public StaticShader(){
        super(VERTEX_FILE, FRAGMENT_FILE );
    }

    @Override
    protected void bindAttributes() {
        super.bindAttribute(0, "position");
    }
}
