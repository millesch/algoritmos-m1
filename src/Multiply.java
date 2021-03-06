import java.awt.image.BufferedImage;
import java.io.IOException;

public class Multiply {
    public static void main(String args[]) throws IOException {
        BufferedImage image = Util.loadImageFromFile("src/img2.png");
        BufferedImage image2 = Util.loadImageFromFile("src/img3.png");

        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int iOne = image.getRGB(x, y);
                int iTwo = image2.getRGB(x, y);

                // separação e multiplicação dos canais de cores e do alpha do pixel
                int red = Util.getRed(iOne) * Util.getRed(iTwo);
                int green = Util.getGreen(iOne) * Util.getGreen(iTwo);
                int blue = Util.getBlue(iOne) * Util.getBlue(iTwo);

                // função Math.min compara a multiplicação ao número 255 e retorna o menor deles
                // para que o resultado não seja maior do que 255
                int pRed = Math.min(red, 255);
                int pGreen = Math.min(green, 255);
                int pBlue = Math.min(blue, 255);

                int p = 255 | (pRed << 16) | (pGreen << 8) | pBlue;

                newImage.setRGB(x, y, p);
            }
        }

        Util.showBufferedImages(image, image2, newImage);
    }
}