package processamento.de.imagens;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Dilation {
    public static void main(String args[]) throws IOException {
        BufferedImage imagem = Util.loadImageFromFile("src/blackAndWhite.png");
        BufferedImage imagemnova = imagem;
        
        int largura = imagem.getWidth();
        int altura = imagem.getHeight();
        int [][] kernel = Util.loadMatrix3x3(3);
        
        //validação se no kernell ele passou por um ponto branco, que será o alvo da dilatação
        boolean passouBranco;
        int branco = new Color(255, 255, 255).getRGB(); //cor branca
        int preto = new Color(0, 0, 0).getRGB(); // cor preta

        for (int i = 0; i < largura; i++) {
            for (int j = 0; j < altura; j++) {
                
                //define como falso a validação, a cada laço ele é redefinido como falso
                passouBranco = false;

                for (int xi = 0; xi < 3; xi++) {
                    for (int xj = 0; xj < 3; xj++) {
                        
                        if (kernel[xi][xj] == 1) {
                            
                            //essas são as posições ao redor do pixel hotspot (o do meio)
                            int posX = (i -1 + xi);
                            int posY = (j -1 + xj);
                            
                            //validação para que o kernell não calcule fora da borda da imagem
                            if (posX >= 0 && posX < largura && posY >= 0 && posY < altura) {
                                
                                //se o pixel na posição ao redor do hotspot for branco.
                                if (imagem.getRGB(posX, posY) == branco) {
                                    //a validação é dada como verdadeiro, quebrando o laço para o kernel, já que foi provado para a dilatação.
                                    passouBranco = true;
                                    break;
                                }
                            }
                        }
                    }
                    //quebrando o laço de repetição do kernell, pois já foi realizada a validação
                    if (passouBranco == true) {
                        break;
                    }
                }
                if (passouBranco == true) {
                    //colocar o pixel nessa posição hotspot como branco
                    imagemnova.setRGB(i, j, branco);
                }
            }
        }

        Util.showBufferedImages(imagem, imagemnova);
    }
}
