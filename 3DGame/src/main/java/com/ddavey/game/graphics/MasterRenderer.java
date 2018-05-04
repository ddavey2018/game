package com.ddavey.game.graphics;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;

import com.ddavey.game.graphics.model.Model;
import com.ddavey.game.graphics.model.Vertex;


public class MasterRenderer {
	
	private Model model;
	
	public MasterRenderer() {
		init();
		
		model = new Model();
		
		Vertex[] vertices = {
				new Vertex(-1,-1,0),
				new Vertex(1,-1,0),
				new Vertex(0,1,0)
		};
		
		model.bufferVertices(vertices);
		
	}
	
	public void render() {
		prepare();
		
		glBindBuffer(GL_ARRAY_BUFFER,model.getVbo());
		glEnableVertexAttribArray(0);
		glVertexAttribPointer(0, 3, GL_FLOAT, false, Vertex.SIZE * 4, 0);
		glDrawArrays(GL_TRIANGLES, 0, model.getSize());
		glDisableVertexAttribArray(0);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		
	}
	
	public void prepare() {
	//	glClearColor(1f,0f,0f,1f);
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	}
	
	public void init() {
		glEnable(GL_DEPTH_TEST);
		
	}
	
	public void cleanUp() {
		
	}
}
