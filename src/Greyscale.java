import java.io.*;
import java.awt.image.BufferedImage;

public class Greyscale {
	public static void main(String args[]) throws IOException {
		BufferedImage image = Util.loadImageFromFile("src/img1.png");

		int width = image.getWidth();
		int height = image.getHeight();
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				int p = image.getRGB(x, y);
				int a = (p >> 24) & 0xff;
				int r = (p >> 16) & 0xff;
				int g = (p >> 8) & 0xff;
				int b = p & 0xff;
				int avg = (r + g + b) / 3;
				p = (a << 24) | (avg << 16) | (avg << 8) | avg;
				image.setRGB(x, y, p);
			}
		}
		Util.writeImageOnFile(image, "src/greyscale.png");
	}
}