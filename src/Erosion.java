import java.awt.image.BufferedImage;
import java.io.IOException;

public class Erosion {
	public static void main(String args[]) throws IOException {
		BufferedImage image = null;
		BufferedImage imageEros = null;

		image = Util.loadImageFromFile("src/input/blackAndWhite.png");

		int[][] mask = Util.loadMatrix3x3(3);

		int width = image.getWidth();
		int height = image.getHeight();

		imageEros = new BufferedImage(width, height, image.getType());
		//fors aninhados para pecorrer cada pixel da imagem excluindo os pixeis mais as bordas.
		for (int y = 1; y < height - 1; y++) {
			for (int x = 1; x < width - 1; x++) {
				
				boolean fit = true;

				int k = -1; //auxiliares para interar sobre os pixeis adjacentes ao pivo.
				int z = -1;

				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {
						//separa�ao dos canais de cores e alpha do pixel corrente.
						int p = image.getRGB(x + k, y + z++);
						int b = p & 0xff;
						
						if(mask[i][j] == 1)
							if(b != 255)
								fit = false;
					}
					z = -1;
					k++;
				}

				int p;
				
				if(fit) 
					p = (0 << 24) | (255 << 16) | (255 << 8) | 255;
				else 
					p = (255 << 24) | (0 << 16) | (0 << 8) | 0;

				//setando o pixel gerado na nova imagem
				imageEros.setRGB(x, y, p);
			}
		}
		Util.showBufferedImages(image, imageEros);
		Util.writeImageOnFile(imageEros, "src/output/erosion.png");
	}
}
