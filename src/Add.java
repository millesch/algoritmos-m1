import javax.imageio.ImageIO;
import java.io.*;
import java.awt.image.BufferedImage;

public class Add {
    public static void main(String args[]) throws IOException {
        BufferedImage image = Util.loadImageFromFile("src/img3.png");
        BufferedImage image2 = Util.loadImageFromFile("src/img2.png");

        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int iOne = image.getRGB(x, y);
                int iTwo = image2.getRGB(x, y);

                // separação e soma dos canais de cores e do alpha do pixel
                int alpha = Util.getAlpha(iOne) + Util.getAlpha(iTwo);
                int red = Util.getRed(iOne) + Util.getRed(iTwo);
                int green = Util.getGreen(iOne) + Util.getGreen(iTwo);
                int blue = Util.getBlue(iOne) + Util.getBlue(iTwo);

                // função Math.min compara a soma ao número 255 e retorna o menor deles
                // para que o resultado não seja maior do que 255
                int pAlpha = Math.min(alpha, 255);
                int pRed = Math.min(red, 255);
                int pGreen = Math.min(green, 255);
                int pBlue = Math.min(blue, 255);

                int p = pAlpha | (pRed << 16) | (pGreen << 8) | pBlue;

                newImage.setRGB(x, y, p);
            }
        }

        Util.showBufferedImages(image, image2, newImage);
    }
}