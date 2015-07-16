package vistas;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;

public class ImagenUsuarioIcon extends ImageIcon {

	private static final long serialVersionUID = -8438135542757051309L;
	  
	 	public ImagenUsuarioIcon(String filename) { 
	 	    super(filename); 
	 	}; 

	    public ImagenUsuarioIcon(Image image) {
	        super(image);
	    }

	    public ImagenUsuarioIcon(URL location) {
	        super(location);
	    }	    
	        
	    @Override
	 	public synchronized void paintIcon(Component c, Graphics g, int x, int y) { 
	 	    g.setColor(Color.white); 
	 	    g.fillRect(0,0, c.getWidth(), c.getHeight()); 
	 	    if(getImageObserver() == null) {                   
	 		g.drawImage( 
	 		    getImage(), 
	 		    0, 
	 		    0, 
	                    c.getWidth(),
	                    c.getHeight(),
	 		    c 
	 		); 
	 	    } else { 
	 		g.drawImage( 
	 		    getImage(), 
	 		    0, 
	 		    0, 
	                    c.getWidth(),
	                    c.getHeight(),
	 		    getImageObserver() 
	 		); 
	 	    } 
	 	} 
}
