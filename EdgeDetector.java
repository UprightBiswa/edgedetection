import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.lang.Math;


public class EdgeDetector {
    public static void writeImage(int[][] GImg, int width, int height, String fileName) {
        BufferedImage image = new BufferedImage(GImg.length, GImg[0].length, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB( x, y, GImg[x][y]);
            }
        }

        File ImageFile = new File("C:\\Users\\biswa\\OneDrive\\Desktop\\red_edge\\"+fileName+".png");
        try {
            ImageIO.write(image, "png", ImageFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        BufferedImage img = null;
        try {
//read image
            img = ImageIO.read(new File("C:\\Users\\biswa\\OneDrive\\Desktop\\red_edge\\eye_test2.png"));
            System.out.println("height: " + img.getHeight() + " width: " + img.getWidth());

            int width = img.getWidth();
            int height = img.getHeight();


            int[][] PImg = new int[width][height];

            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                        PImg[i][j] = img.getRGB(i,j);
                }
            }


            int[][] GImg = new int[width][height];

            for (int i = 0; i < width -1 ; i++) {
                for (int j = 0; j < height-1 ; j++) {

                    int p1=PImg[i][j];
                    int p2 = PImg[i][j + 1];
                    int p3 = PImg[i+1][j];
                    int p4 = PImg[i+1][j+1];

                    int gradient_cross = (p1-p4) + (p2-p3);
                    int gradient_plus = (p1-p2) + (p1-p3);


                    if(gradient_cross!=0 || gradient_plus!=0)
                        GImg[i][j]=16711680;
                    else
                        GImg[i][j]=PImg[i][j];

                }
            }

            writeImage(GImg, width, height, "output_test2");

        }
        catch (IOException ignored){

        }

    }
}