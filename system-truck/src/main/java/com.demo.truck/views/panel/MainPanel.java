
package com.demo.truck.views.panel;

import java.awt.Graphics;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import org.springframework.core.io.ClassPathResource;

public class MainPanel extends JPanel {


	public MainPanel() {
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		ClassPathResource res = new ClassPathResource("image.jpg");
		URL url;
		try {
			url = res.getURL();
			ImageIcon  image = new ImageIcon(url);
			g.drawImage(image.getImage(), 40, 40, getSize().width-80,
				     getSize().height-80, this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
