package com.ddavey.game;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import com.ddavey.game.graphics.MasterRenderer;
import com.ddavey.game.graphics.Window;


import static org.lwjgl.glfw.Callbacks.*;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public class MainLoop {
	
	private GLFWErrorCallback errorCallback;
	private Window window;
	private boolean running = false;
	private MasterRenderer renderer;
	
	public MainLoop() {
		init();
		renderer = new MasterRenderer();
	}
	
	private void input() {
		glfwPollEvents();
		if(window.shouldClose()) stop();
	}
	
	private void update() {}
	
	private void render() {
		renderer.render();
		window.render();
	}
	
	private void run() {
		
		long lastTime = System.nanoTime();
		long curTime = lastTime;
		
		long timer = System.currentTimeMillis();
		
		double ns = 1000000000 / 60.0;
		double delta = 0.0;
		
		int fps = 0;
		int ups = 0;
		
		
		while(running) {
			curTime=System.nanoTime();
			delta+= (curTime - lastTime) / ns;
			lastTime = curTime;
			while(delta >= 1.0) {
				 input();
				 update();
				 ups++;
				 delta--;
			}
			render();
			
			fps++;
			
			if(System.currentTimeMillis() > timer + 1000) {
				window.setTitle("Title - ups: "+ups + " | fps: "+fps);
				ups=0;
				fps=0;
				timer += 1000;
			}
		}
		cleanUp();
	}
	
	public void start() {
		if(running) return;
		running = true;
		run();
	}
	
	public void stop() {
		if(!running) return;
		running = false;
	}
	
	private void init() {
		GLFWErrorCallback.createPrint(System.err).set();
		glfwInit();
		
		window = new Window(1080,720,"Title - ");
		GL.createCapabilities();
	}
	
	private void cleanUp() {
		window.hide();
		window.dispose();
		renderer.cleanUp();
	}

	public static void main(String[] args) {
		MainLoop main = new MainLoop();
		main.start();
	}

}