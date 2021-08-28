import java.awt.image.BufferedImage;
import java.io.IOException;

public class Divide {
    public static void main(String args[]) throws IOException {
        BufferedImage image = Util.loadImageFromFile("src/input/img2.png");
        BufferedImage image2 = Util.loadImageFromFile("src/input/img3.png");

        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int p1 = image.getRGB(x, y);
                int p2 = image2.getRGB(x, y);

                // separação e divisão dos canais de cores e do alpha do pixel
                // caso o alpha ou canal da segunda imagem for menor que 0, é retornado 0
                // senão, é realizada a divisão
                int alpha = Util.getAlpha(p2) > 0 ? Util.getAlpha(p1) / Util.getAlpha(p2) : 0;
                int red = Util.getRed(p2) > 0 ? Util.getRed(p1) / Util.getRed(p2) : 0;
                int green = Util.getGreen(p2) > 0 ? Util.getGreen(p1) / Util.getGreen(p2) : 0;
                int blue = Util.getBlue(p2) > 0 ? Util.getBlue(p1) / Util.getBlue(p2) : 0;

                int p = alpha | (red << 16) | (green << 8) | blue;

                newImage.setRGB(x, y, p);
            }
        }
        Util.showBufferedImages(image, image2, newImage);
        Util.writeImageOnFile(newImage, "src/output/divide.png");
    }
}