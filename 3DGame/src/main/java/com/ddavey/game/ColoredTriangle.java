package com.ddavey.game;

import static org.lwjgl.glfw.GLFW.GLFW_DECORATED;
import static org.lwjgl.glfw.GLFW.GLFW_FOCUSED;
import static org.lwjgl.glfw.GLFW.GLFW_RESIZABLE;
import static org.lwjgl.glfw.GLFW.GLFW_VISIBLE;
import static org.lwjgl.glfw.GLFW.glfwCreateWindow;
import static org.lwjgl.glfw.GLFW.glfwDefaultWindowHints;
import static org.lwjgl.glfw.GLFW.glfwGetPrimaryMonitor;
import static org.lwjgl.glfw.GLFW.glfwGetVideoMode;
import static org.lwjgl.glfw.GLFW.glfwInit;
import static org.lwjgl.glfw.GLFW.glfwMakeContextCurrent;
import static org.lwjgl.glfw.GLFW.glfwSetWindowPos;
import static org.lwjgl.glfw.GLFW.glfwWindowHint;
import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL11.GL_TRUE;
import static org.lwjgl.system.MemoryUtil.NULL;

import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL11;

public class ColoredTriangle {
	long window;
    public void start() {
    	glfwInit();
    	glfwDefaultWindowHints();
		glfwWindowHint(GLFW_RESIZABLE, GL_FALSE);
		glfwWindowHint(GLFW_VISIBLE, GL_TRUE);
		glfwWindowHint(GLFW_DECORATED, GL_TRUE);
		glfwWindowHint(GLFW_FOCUSED, GL_TRUE);
		
		window = glfwCreateWindow(700, 600, "show me a triangle!", NULL, NULL);
		GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
		glfwSetWindowPos(window, 10,10);
		
		glfwMakeContextCurrent(window);

        // Init OpenGL
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glOrtho(0, 640, 480, 0, 1, -1);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);

        boolean quit = false;

        while (!quit) {         
            // Clear the screen.
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

            // Begin drawing
            GL11.glBegin(GL11.GL_TRIANGLES);
                // Top & Red
                GL11.glColor3f(1.0f, 0.0f, 0.0f);
                GL11.glVertex2f(0.0f, 1.0f);

                // Right & Green
                GL11.glColor3f(0.0f, 1.0f, 0.0f);
                GL11.glVertex2f(1.0f, 1.0f);

                // Left & Blue
                GL11.glColor3f(0.0f, 0.0f, 1.0f);
                GL11.glVertex2f(1.0f, -1.0f);
            GL11.glEnd();

        }

    }

    public static void main(String args[]) {
        ColoredTriangle ct = new ColoredTriangle();
        ct.start();
    }

}