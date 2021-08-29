import java.awt.image.BufferedImage;
import java.io.IOException;

public class InternalLimit {
	public static void main(String args[]) throws IOException {
		BufferedImage image = null;
		BufferedImage imageErosion = null;
		BufferedImage imageLimit = null;

		image = Util.loadImageFromFile("src/input/blackAndWhite.png");

		// erosão da imagem
		imageErosion = Erosion.erosion(image);

		// subtração da imagem original pela imagem erodida para encontrar os limites internos
		imageLimit = Subtract.sub(image, imageErosion);

		Util.showBufferedImages(image, imageLimit);
		Util.writeImageOnFile(imageLimit, "src/output/internalLimit.png");
	}
}
