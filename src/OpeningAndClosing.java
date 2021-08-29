import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class OpeningAndClosing {

    public static void main(String[] args) throws IOException {
        BufferedImage image = null;

        image = Util.loadImageFromFile("src/input/blackAndWhite.png");
        
        //Erosao da imagem e matriz, e a dilatacao do resultado com a matriz
        BufferedImage imageOpening = Dilation.dilation(Erosion.erosion(image));

        Util.showBufferedImages(image, imageOpening);
        Util.writeImageOnFile(imageOpening, "src/output/opening.png");
        
        //Dilatacao da imagem e matriz, e a erosao do resultado com a matriz
        BufferedImage imageClosing = Erosion.erosion(Dilation.dilation(image));

        Util.showBufferedImages(image, imageClosing);
        Util.writeImageOnFile(imageClosing, "src/output/closing.png");
    }
}
