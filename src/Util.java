import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Util {
    /*
     * Carrega uma matriz de inteiros do arquivo "matrix$.txt" onde $ e o
     * identificador numerico passado por argumento. Cada linha do arquivo
     * representa um inteiro. Um arquivo no formato a seguir representa uma matriz
     * 3x3 respectivamente:
     * Arquivo:
     * 1
     * 2
     * 3
     * 4
     * 5
     * 6
     * 7
     * 8
     * 9
     * Matriz:
     * [1, 2, 3]
     * [4, 5, 6]
     * [7, 8, 9]
     */
    public static int[][] loadMatrix3x3(int arg) {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File("src/matrixes/matrix" + arg + ".txt"));
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo nao encontrado");
            e.printStackTrace();
        }
        int[][] matrix3x3 = new int[3][3];
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                matrix3x3[x][y] = scanner.nextInt();
            }
        }
        return matrix3x3;
    }

    // Mostra as imagens carregadas ou criadas em uma janela.
    public static void showBufferedImages(BufferedImage... imgs) {
        JFrame frame = new JFrame();
        frame.getContentPane().setLayout(new FlowLayout());
        for (BufferedImage img : imgs)
            frame.getContentPane().add(new JLabel(new ImageIcon(img)));
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // Carrega uma imagem de um arquivo com caminho passado por argumento.
    public static BufferedImage loadImageFromFile(String path) {
        BufferedImage image = null;
        File file = null;
        try {
            file = new File(path);

            image = ImageIO.read(file);
        } catch (IOException e) {
            System.out.println(e);
        }
        return image;
    }

    // Escreve uma imagem em um arquivo com caminho e conteudo passado por
    // argumento.
    public static void writeImageOnFile(BufferedImage img, String path) {
        File file = null;
        try {
            file = new File(path);
            ImageIO.write(img, "png", file);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static int getAlpha(int p) {
        return (p >> 24) & 0xff;
    }

    public static int getRed(int p) {
        return (p >> 16) & 0xff;
    }

    public static int getGreen(int p) {
        return (p >> 8) & 0xff;
    }

    public static int getBlue(int p) {
        return p & 0xff;
    }
}
