import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.Buffer;

public class Subtract {
    public static void main(String args[]) throws IOException {
        BufferedImage image = Util.loadImageFromFile("src/input/img2.png");
        BufferedImage image2 = Util.loadImageFromFile("src/input/img3.png");

        BufferedImage newImage = sub(image, image2);

        Util.showBufferedImages(image, image2, newImage);
        Util.writeImageOnFile(newImage, "src/output/subtract.png");
    }

    public static BufferedImage sub(BufferedImage image, BufferedImage image2) {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int p1 = image.getRGB(x, y);
                int p2 = image2.getRGB(x, y);

                // separação e subtração dos canais de cores e do alpha do pixel
                int alpha = Util.getAlpha(p1) - Util.getAlpha(p2);
                int red = Util.getRed(p1) - Util.getRed(p2);
                int green = Util.getGreen(p1) - Util.getGreen(p2);
                int blue = Util.getBlue(p1) - Util.getBlue(p2);

                // função Math.max compara a subtração ao número 0 e retorna o maior deles
                // para que o resultado não seja menor do que 0
                int pAlpha = Math.max(alpha, 0);
                int pRed = Math.max(red, 0);
                int pGreen = Math.max(green, 0);
                int pBlue = Math.max(blue, 0);

                int p = pAlpha | (pRed << 16) | (pGreen << 8) | pBlue;

                newImage.setRGB(x, y, p);
            }
        }

        return newImage;
    }
}