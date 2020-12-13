import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageUtils {

    public static BufferedImage loadImage(String filename) {
        BufferedImage img = null;

        try {
            img = ImageIO.read(new File(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }

    public static void saveImage(BufferedImage img, String format, String filename) {

        try {
            ImageIO.write(img, format, new File(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void displayImage(BufferedImage img, String description) {
        if (img == null)
            return;

        JFrame frame = new JFrame(description);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        ImagePanel imagePanel = new ImagePanel();
        imagePanel.setImage(img);

        frame.setContentPane(imagePanel);
        frame.pack();
        frame.setVisible(true);
    }


    public static BufferedImage extractBand(BufferedImage inImg, int band) {
        BufferedImage outImg = new BufferedImage(inImg.getWidth(), inImg.getHeight(), BufferedImage.TYPE_BYTE_GRAY);

        for (int y = 0; y < inImg.getHeight(); y++)
            for (int x = 0; x < inImg.getWidth(); x++) {
                int pixel = inImg.getRaster().getSample(x, y, band);
                outImg.getRaster().setSample(x, y, 0, pixel);
            }
        return outImg;
    }


    public static void displayImage(BufferedImage img){
        displayImage(img,null);
    }
    public static BufferedImage composeImage(BufferedImage R, BufferedImage G, BufferedImage B) {
        BufferedImage outImg = new BufferedImage(R.getWidth(), R.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
        if (R.getType() != BufferedImage.TYPE_BYTE_GRAY || G.getType() != BufferedImage.TYPE_BYTE_GRAY || B.getType() != BufferedImage.TYPE_BYTE_GRAY) {
            System.out.println("Invalid input images");
        }
        else{

            for (int y = 0; y < R.getHeight(); y++)
                for (int x = 0; x < R.getWidth(); x++) {
                    int pixelR = R.getRaster().getSample(x, y, 0);
                    int pixelG = G.getRaster().getSample(x, y, 0);
                    int pixelB = B.getRaster().getSample(x, y, 0);
                    outImg.getRaster().setSample(x, y, 1, pixelR);
                  /*  outImg.getRaster().setSample(x, y, 1, pixelG);
                    outImg.getRaster().setSample(x, y, 3, pixelB);*/
                }

        }

        return outImg;
    }


}
