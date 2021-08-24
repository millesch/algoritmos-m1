import java.awt.image.BufferedImage;
import java.io.IOException;

public class Divide {
    public static void main(String args[]) throws IOException {
        BufferedImage image = Util.loadImageFromFile("src/img2.png");
        BufferedImage image2 = Util.loadImageFromFile("src/img3.png");

        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int iOne = image.getRGB(x, y);
                int iTwo = image2.getRGB(x, y);

                // separação e divisão dos canais de cores e do alpha do pixel
                // caso o alpha ou canal da segunda imagem for menor que 0, é retornado 0
                // senão, é realizada a divisão
                int alpha = Util.getAlpha(iTwo) > 0 ? Util.getAlpha(iOne) / Util.getAlpha(iTwo) : 0;
                int red = Util.getRed(iTwo) > 0 ? Util.getRed(iOne) / Util.getRed(iTwo) : 0;
                int green = Util.getGreen(iTwo) > 0 ? Util.getGreen(iOne) / Util.getGreen(iTwo) : 0;
                int blue = Util.getBlue(iTwo) > 0 ? Util.getBlue(iOne) / Util.getBlue(iTwo) : 0;

                int p = alpha | (red << 16) | (green << 8) | blue;

                newImage.setRGB(x, y, p);
            }
        }
        Util.showBufferedImages(image, image2, newImage);
    }
}