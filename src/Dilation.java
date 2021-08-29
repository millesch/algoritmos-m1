import java.awt.image.BufferedImage;
import java.io.IOException;

public class Dilation {
	public static void main(String args[]) throws IOException {
		BufferedImage image = null;

		image = Util.loadImageFromFile("src/input/blackAndWhite.png");

		BufferedImage imageDilation = dilation(image);

		Util.showBufferedImages(image, imageDilation);
		Util.writeImageOnFile(imageDilation, "src/output/dilation.png");
	}

	public static BufferedImage dilation(BufferedImage image) {
		int[][] mask = Util.loadMatrix3x3(3);

		int width = image.getWidth();
		int height = image.getHeight();

		BufferedImage imageDilation = new BufferedImage(width, height, image.getType());
		//fors aninhados para pecorrer cada pixel da imagem excluindo os pixeis mais as bordas.
		for (int y = 1; y < height - 1; y++) {
			for (int x = 1; x < width - 1; x++) {

				// flag para o controle se o pixel deve ser dilatado ou não
				boolean dilate = false;

				int k = -1; //auxiliares para interar sobre os pixeis adjacentes ao pivo.
				int z = -1;

				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {
						//separa�ao dos canais de cores e alpha do pixel corrente.
						int p = image.getRGB(x + k, y + z++);
						int b = p & 0xff;

						// se a máscara tiver o bit 1 e for diferente de preto (0), o pixel deve ser dilatado
						if (mask[i][j] == 1)
							if (b != 0)
								dilate =  true;
					}
					z = -1;
					k++;
				}

				int p;

				// se a flag dilate for true, o pixel deve ser branco (255), se não, deve ser preto (0)
				if (dilate)
					p = (0 << 24) | (255 << 16) | (255 << 8) | 255;
				else
					p = (255 << 24) | (0 << 16) | (0 << 8) | 0;

				//setando o pixel gerado na nova imagem
				imageDilation.setRGB(x, y, p);
			}
		}
		return imageDilation;
	}
}
