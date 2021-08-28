import java.io.*;
import java.awt.image.BufferedImage;

public class Greyscale {
	public static void main(String args[]) throws IOException {
		BufferedImage image = Util.loadImageFromFile("src/input/img1.png");

		int width = image.getWidth();
		int height = image.getHeight();

		BufferedImage greyscale = new BufferedImage(width, height, image.getType());
		
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				int p = image.getRGB(x, y);
				int a = (p >> 24) & 0xff;
				int r = (p >> 16) & 0xff;
				int g = (p >> 8) & 0xff;
				int b = p & 0xff;
				int avg = (r + g + b) / 3;
				p = (a << 24) | (avg << 16) | (avg << 8) | avg;
				greyscale.setRGB(x, y, p);
			}
		}

		Util.writeImageOnFile(greyscale, "src/output/greyscale.png");
		Util.showBufferedImages(image, greyscale);
	}
}