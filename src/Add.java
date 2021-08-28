import javax.imageio.ImageIO;
import java.io.*;
import java.awt.image.BufferedImage;

public class Add {
    public static void main(String args[]) throws IOException {
        BufferedImage image = Util.loadImageFromFile("src/input/img3.png");
        BufferedImage image2 = Util.loadImageFromFile("src/input/img2.png");

        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int p1 = image.getRGB(x, y);
                int p2 = image2.getRGB(x, y);

                // separação e soma dos canais de cores e do alpha do pixel
                int alpha = Util.getAlpha(p1) + Util.getAlpha(p2);
                int red = Util.getRed(p1) + Util.getRed(p2);
                int green = Util.getGreen(p1) + Util.getGreen(p2);
                int blue = Util.getBlue(p1) + Util.getBlue(p2);

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
        Util.writeImageOnFile(newImage, "src/output/add.png");
    }
}