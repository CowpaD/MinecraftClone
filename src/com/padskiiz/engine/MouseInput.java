package com.padskiiz.engine;

import org.joml.Vector2d;
import org.joml.Vector2f;

import static org.lwjgl.glfw.GLFW.*;
import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWCursorEnterCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallback;

public class MouseInput {
	private final Vector2d previousPos;
	private final Vector2d currentPos;
	private final Vector2f displVec;
	
	private boolean inWindow = false;
	private boolean leftButtonPressed = false;
	private boolean rightButtonPressed = false;
	
	@SuppressWarnings("unused")
	private GLFWCursorPosCallback cursorPosCallback;
	@SuppressWarnings("unused")
	private GLFWCursorEnterCallback cursorEnterCallback;
	@SuppressWarnings("unused")
	private GLFWMouseButtonCallback mouseButtonCallback;
	
	public MouseInput() {
		previousPos = new Vector2d(-1, -1);
		currentPos = new Vector2d(0, 0);
		displVec = new Vector2f();
	}
	
	public void init(Window window) {
        glfwSetCursorPosCallback(window.getWindowHandle(), 
        		cursorPosCallback = new GLFWCursorPosCallback() {
		            public void invoke(long window, double xpos, double ypos) {
		                currentPos.x = xpos;
		                currentPos.y = ypos;
		            }
		        });
        glfwSetCursorEnterCallback(window.getWindowHandle(), 
        		cursorEnterCallback = new GLFWCursorEnterCallback() {
		            public void invoke(long window, boolean entered) {
		                inWindow = entered;
		            }
		        });
        glfwSetMouseButtonCallback(window.getWindowHandle(), 
        		mouseButtonCallback = new GLFWMouseButtonCallback() {
		            public void invoke(long window, int button, int action, int mods) {
		                leftButtonPressed = button == GLFW_MOUSE_BUTTON_1 && action == GLFW_PRESS;
		                rightButtonPressed = button == GLFW_MOUSE_BUTTON_2 && action == GLFW_PRESS;
		            }
		        });
    }
	
	public Vector2f getDisplVec() {
		return displVec;
	}
	
	public void input(Window window) {
		displVec.x = 0;
		displVec.y = 0;
		
		if (previousPos.x > 0 && previousPos.y > 0 && inWindow) {
			double dX = currentPos.x - previousPos.x;
			double dY = currentPos.y - previousPos.y;
			
			boolean rotateX = dX != 0;
			boolean rotateY = dY != 0;
			
			if (rotateX) {
				displVec.y = (float) dX;
			}
			if (rotateY) {
				displVec.x = (float) dY;
			}
		}
		previousPos.x = currentPos.x;
		previousPos.y = currentPos.y;
	}
	
	public boolean isLeftButtonPressed() {
		return leftButtonPressed;
	}
	
	public boolean isRightButtonPressed() {
		return rightButtonPressed;
	}
}