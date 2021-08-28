import java.awt.image.BufferedImage;
import java.io.IOException;

public class GrayscaleWeighted {
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
				int weight = (int) (r * 0.299 + g * 0.587 + b * 0.114);
				p = (a << 24) | (weight << 16) | (weight << 8) | weight;
				greyscale.setRGB(x, y, p);
			}
		}
		Util.showBufferedImages(image, greyscale);
		Util.writeImageOnFile(greyscale, "src/output/greyscaleWeighted.png");
	}
}
