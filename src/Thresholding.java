import java.awt.image.BufferedImage;
import java.io.IOException;

public class Thresholding {
	public static void main(String args[]) throws IOException {
		BufferedImage image = Util.loadImageFromFile("src/greyscale.png");

		int width = image.getWidth();
		int height = image.getHeight();

		BufferedImage imagemThres = new BufferedImage(width, height, image.getType());

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				int p = image.getRGB(x, y);
				int a = (p >> 24) & 0xff;
				int r = (p >> 16) & 0xff;
				int g = (p >> 8) & 0xff;
				int b = p & 0xff;

				int pT;

				if (b > 127)
					pT = (0 << 24) | (255 << 16) | (255 << 8) | 255;
				else
					pT = (255 << 24) | (0 << 16) | (0 << 8) | 0;

				System.out.println("" + pT);
				imagemThres.setRGB(x, y, pT);
			}
		}
		Util.writeImageOnFile(imagemThres, "src/blackAndWhite.png");
		Util.showBufferedImages(image, imagemThres);
	}
}
