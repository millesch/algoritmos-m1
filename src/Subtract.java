import java.awt.image.BufferedImage;
import java.io.IOException;

public class Subtract {
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

                int alpha = Util.getAlpha(iOne) - Util.getAlpha(iTwo);
                int red = Util.getRed(iOne) - Util.getRed(iTwo);
                int green = Util.getGreen(iOne) - Util.getGreen(iTwo);
                int blue = Util.getBlue(iOne) - Util.getBlue(iTwo);

                int pAlpha = Math.max(alpha, 0);
                int pRed = Math.max(red, 0);
                int pGreen = Math.max(green, 0);
                int pBlue = Math.max(blue, 0);

                int p = pAlpha | (pRed << 16) | (pGreen << 8) | pBlue;

                newImage.setRGB(x, y, p);
            }
        }

        Util.showBufferedImages(image, image2, newImage);
    }
}