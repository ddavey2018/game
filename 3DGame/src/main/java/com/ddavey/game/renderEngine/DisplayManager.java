package com.ddavey.game.renderEngine;

import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;

import java.nio.*;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

public class DisplayManager {
	
	// The window handle
		private static long window;
	
	public static void createDisplay() {
		// Setup an error callback. The default implementation
				// will print the error message in System.err.

				// Initialize GLFW. Most GLFW functions will not work before doing this.
				if ( !glfwInit() )
					throw new IllegalStateException("Unable to initialize GLFW");


				// Create the window
				window = glfwCreateWindow(1280, 720, "First Display", NULL, NULL);
				if ( window == NULL )
					throw new RuntimeException("Failed to create the GLFW window");

				// Setup a key callback. It will be called every time a key is pressed, repeated or released.
				glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
					if ( key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE )
						glfwSetWindowShouldClose(window, true); // We will detect this in the rendering loop
				});
				

				// Make the OpenGL context current
				glfwMakeContextCurrent(window);
				// Enable v-sync
				glfwSwapInterval(1);

				// Make the window visible
				glfwShowWindow(window);
				
				// This line is critical for LWJGL's interoperation with GLFW's
				// OpenGL context, or any context that is managed externally.
				// LWJGL detects the context that is current in the current thread,
				// creates the GLCapabilities instance and makes the OpenGL
				// bindings available for use.
				GL.createCapabilities();

				// Set the clear color
				//glClearColor(1.0f, 0.0f, 0.0f, 0.0f);
				GL11.glViewport(0, 0, 1280, 720);
				
	}
	
	public static void updateDisplay() {}
	
	public static void closeDisplay() {
		// Free the window callbacks and destroy the window
				glfwFreeCallbacks(window);
				glfwDestroyWindow(window);

				// Terminate GLFW and free the error callback
				glfwTerminate();
		//		glfwSetErrorCallback(null).free();
	}
	
	public static long getWindow() {
		return window;
	}
}
