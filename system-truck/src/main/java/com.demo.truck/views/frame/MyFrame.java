
package com.demo.truck.views.frame;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class MyFrame extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8004897016172454414L;

	private int height;
	
	private int width;
	
	public MyFrame(int width,int height) {
		this.height = height;
		this.width = width;
		setLocation();
	}
	private void setLocation(){
		Dimension demesion = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds((demesion.width-width)/2,(demesion.height-height)/2,width, height);
	}

}
