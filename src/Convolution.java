import java.io.*;
import java.awt.image.BufferedImage;

public class Convolution {
	public static void main(String args[]) throws IOException {
		BufferedImage image = null;
		BufferedImage imageConv = null;

		image = Util.loadImageFromFile("src/input/greyscale.png");

		int[][] mask = Util.loadMatrix3x3(1);

		int width = image.getWidth();
		int height = image.getHeight();

		imageConv = new BufferedImage(width, height, image.getType());
		//fors aninhados para pecorrer cada pixel da imagem excluindo os pixeis mais as bordas.
		for (int y = 1; y < height - 1; y++) {
			for (int x = 1; x < width - 1; x++) {

				int ac = 0; //acumulador para a soma de todas as mult da mascara pelos pixeis central e adjacentes.

				int k = -1; //auxiliares para interar sobre os pixeis adjacentes ao pivo.
				int z = -1;

				//fors aninhados para percorre cada elemento da matriz mascara e multiplicar pelo pixel correpondente da imagem.
				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {
						//separa�ao dos canais de cores e alpha do pixel correntemente iterando.
						int p = image.getRGB(x + k, y + z++);
						int b = p & 0xff;

						ac += mask[i][j] * b; //acumulado todas as multiplicacoes de mascara e pixel
					}
					z = -1;
					k++;
				}

				//garantia que os valores gerados estao dentro do limite de 8 bits.
				if (ac > 255)
					ac = 255;
				if (ac < 0)
					ac = 0;

				//resgate do alpha da imagem original
				int originalAlpha = (image.getRGB(x, y) >> 24) & 0xff;
				//criacao de um pixel de 32 bits com os valores acumulados e alpha original
				int p = (originalAlpha << 24) | (ac << 16) | (ac << 8) | ac;

				//setando o pixel gerado na nova imagem.
				imageConv.setRGB(x, y, p);
			}
		}
		Util.showBufferedImages(image, imageConv);
		Util.writeImageOnFile(imageConv, "src/output/convolution.png");
	}
}
