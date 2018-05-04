package com.ddavey.game.graphics.model;

import static org.lwjgl.opengl.GL15.*;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;

public class Model {
	
	private int vbo;
	private int size = 0;
	
	public Model() {
		vbo = glGenBuffers();
	}
	
	public void bufferVertices(Vertex[] vertices) {
		FloatBuffer buffer = BufferUtils.createFloatBuffer(vertices.length * Vertex.SIZE);
		for(Vertex vertex : vertices) {
			buffer.put(vertex.getX());
			buffer.put(vertex.getY());
			buffer.put(vertex.getZ());
		}
		
		buffer.flip();
		glBindBuffer(GL_ARRAY_BUFFER, vbo);
		glBufferData(GL_ARRAY_BUFFER,buffer,GL_STATIC_DRAW);
		glBindBuffer(GL_ARRAY_BUFFER, 0);//unbind buffer
		
		size = vertices.length;
	}

	public int getVbo() {
		return vbo;
	}

	public int getSize() {
		return size;
	}
	
	
}
