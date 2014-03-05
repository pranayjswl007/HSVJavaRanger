/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hsvranger;

/**
 *
 * @author Pranay
 */
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;

import org.opencv.core.Size;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;

public class Imshow {

	public JFrame Window;
	private ImageIcon image;
	private JLabel label;
     MatOfByte matOfByte;
	private Boolean SizeCustom;
	private int Height, Width;

	public Imshow(String title) {
		Window = new JFrame();
                Window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		image = new ImageIcon();
		label = new JLabel();
                matOfByte = new MatOfByte();
		label.setIcon(image);
		Window.getContentPane().add(label);
		Window.setResizable(false);
		Window.setTitle(title);
		SizeCustom = false;
	}

	public Imshow(String title, int height, int width) {
		SizeCustom = true;
		Height = height;
		Width = width;

		Window = new JFrame();
		image = new ImageIcon();
		label = new JLabel();
		matOfByte = new MatOfByte();
		label.setIcon(image);
		Window.getContentPane().add(label);
		Window.setResizable(false);
		Window.setTitle(title);

	}

	public void showImage(Mat img) {
		if (SizeCustom) {
			Imgproc.resize(img, img, new Size(Height, Width));
		}
		Highgui.imencode(".jpg", img, matOfByte);
		byte[] byteArray = matOfByte.toArray();
		BufferedImage bufImage = null;
		try {
			InputStream in = new ByteArrayInputStream(byteArray);
			bufImage = ImageIO.read(in);
			image.setImage(bufImage);
			Window.pack();
			label.updateUI();
			Window.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}