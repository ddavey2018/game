package com.ddavey.game.graphics;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;

import java.nio.ByteBuffer;

import org.lwjgl.glfw.GLFWVidMode;
public class Window {
	private long window;

	public Window(int width,int height,String title) {
		
		
		glfwDefaultWindowHints();
		glfwWindowHint(GLFW_RESIZABLE, GL_FALSE);
		glfwWindowHint(GLFW_VISIBLE, GL_TRUE);
		glfwWindowHint(GLFW_DECORATED, GL_TRUE);
		glfwWindowHint(GLFW_FOCUSED, GL_TRUE);
		
		window = glfwCreateWindow(width, height, title, NULL, NULL);
		GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
		int x = (vidmode.width() - width);
		int y = (vidmode.height() - height);
		glfwSetWindowPos(window, 10,10);
		glfwMakeContextCurrent(window);
	}
	
	public void dispose() {
		glfwDestroyWindow(window);
	}
	
	public void hide() {
		glfwHideWindow(window);
	}
	
	public void render() {
		glfwSwapBuffers(window);
	}
	
	public void show() {
		glfwShowWindow(window);
	}
	
	public void setTitle(String title) {
		glfwSetWindowTitle(window, title);
	}
	
	public boolean shouldClose() {
		return glfwWindowShouldClose(window);
	}
}
