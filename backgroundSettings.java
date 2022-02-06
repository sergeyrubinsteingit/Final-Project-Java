package SR.venueSR;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class backgroundSettings extends Component {
          
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
//	private static final long serialVersionUID = 1L;
	
	BufferedImage buffrImg;

    public void paint(Graphics g) {
      //  g.drawImage(buffrImg, 0, 0, 1100, 700, null);
    	drawImg(g);
    }
    
    public void drawImg (Graphics grf) {
		grf.drawImage(buffrImg, 0, 0, 1100, 700, null);
	}
    
    public backgroundSettings() {
       try {
           buffrImg = ImageIO.read(new File(".\\imgs\\backgroundImg.jpg"));
       } catch (IOException e) {
       }

    }

    public Dimension getPreferredSize() {
        if (buffrImg != null) {
             return new Dimension(1100,700);
        } else {
           return new Dimension(buffrImg.getWidth(), buffrImg.getHeight());
       }
    }

    public static void main(String[] args) {
/*
        JFrame f = new JFrame("Load Image Sample");
            
        f.addWindowListener(new WindowAdapter(){
                public void windowClosing(WindowEvent e) {
                    System.exit(0);
                }
            });

        f.add(new changeImgDimensions());
        f.pack();
        f.setVisible(true);
     */   
    }
}
