package SR.venueSR;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class goButton extends Component {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	BufferedImage bfrImg;
	
	public void paint(Graphics g) {
		g.drawImage(bfrImg, 0, 0, 193, 116, null);
	}
	
	public BufferedImage getBfrImg() {
		return bfrImg;
	}

	public void setBfrImg(BufferedImage bfrImg) {
		this.bfrImg = bfrImg;
	}

	public goButton() {
		try {
			bfrImg = ImageIO.read(new File(".\\imgs\\goButton.png"));
		} catch (IOException e) {
		//	e.printStackTrace();
		}
	}

	
	public goButton(BufferedImage bfrImg) {
		super();
		this.bfrImg = bfrImg;
		try {
			bfrImg = ImageIO.read(new File(".\\imgs\\goButton.png"));
		} catch (IOException e) {
		//	e.printStackTrace();
		}
	}

	public Dimension buttonSize() {
		if ( bfrImg != null ) {
			return new Dimension(193,116);
		}else{
			return new Dimension(bfrImg.getHeight(),bfrImg.getWidth());
		}
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
