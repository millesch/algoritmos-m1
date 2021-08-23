import java.awt.image.BufferedImage;
import java.io.IOException;

public class SplitChannels {
	public static void main(String args[]) throws IOException {
		BufferedImage image = Util.loadImageFromFile("src/img1.png");
		
		int width = image.getWidth();
		int height = image.getHeight();
		
		
		BufferedImage imagemR = new BufferedImage(width, height, image.getType());
		BufferedImage imagemG = new BufferedImage(width, height, image.getType());
		BufferedImage imagemB = new BufferedImage(width, height, image.getType());
		
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				int p = image.getRGB(x, y);
				int a = (p >> 24) & 0xff;
				int r = (p >> 16) & 0xff;
				int g = (p >> 8) & 0xff;
				int b = p & 0xff;
			
				int pr = (a << 24) | (r << 16) | (0 << 8) | 0;
				int pg = (a << 24) | (0 << 16) | (g << 8) | 0;
				int pb = (a << 24) | (0 << 16) | (0 << 8) | b;
							
				imagemR.setRGB(x, y, pr);
				imagemG.setRGB(x, y, pg);
				imagemB.setRGB(x, y, pb);
			}
		}
		Util.showBufferedImages(image, imagemR, imagemG, imagemB);
	}
}
