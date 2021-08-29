import java.awt.image.BufferedImage;
import java.io.IOException;

public class ExternalLimit {
	public static void main(String args[]) throws IOException {
		BufferedImage image = null;
		BufferedImage imageLimit = null;
		BufferedImage imageDilation = null;

		image = Util.loadImageFromFile("src/input/blackAndWhite.png");

		// dilatação da imagem
		imageDilation = Dilation.dilation(image);

		// subtração da imagem dilatada pela imagem original para encontrar os limites internos
		imageLimit = Subtract.sub(imageDilation, image);

		Util.showBufferedImages(image, imageLimit);
		Util.writeImageOnFile(imageLimit, "src/output/externalLimit.png");
	}
}
