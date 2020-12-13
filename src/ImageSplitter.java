import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class ImageSplitter extends JFrame{
    public ImageSplitter() {
        getContentPane().setLayout(null);
        setBounds(0, 0, 1500, 900);

        setVisible(true);

        //Original image panel

        ImagePanel OriginalImg = new ImagePanel();
        OriginalImg.setBounds(22, 11, 500, 400);
        getContentPane().add(OriginalImg);


        // Red image panel
        ImagePanel RImg = new ImagePanel();

        RImg.setBounds(514, 10, 500, 400);
        getContentPane().add(RImg);


        // Green Panel
        ImagePanel GImg = new ImagePanel();
        GImg.setBounds(22, 379, 500, 400);
        getContentPane().add(GImg);

        // Blue image panel


        ImagePanel BImg = new ImagePanel();
        BImg.setBounds(514, 379, 500, 400);
        getContentPane().add(BImg);


        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        setVisible(true);

        JMenu mnNewMenu = new JMenu("File");
        menuBar.add(mnNewMenu);

        JMenuItem mntmNewMenuItem_5 = new JMenuItem("Upload original");
        mnNewMenu.add(mntmNewMenuItem_5);

        mntmNewMenuItem_5.addActionListener(new ActionListener() {
            BufferedImage bi = null;

            public void actionPerformed(ActionEvent ev) {
                try {
                    bi = getFileChooser();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                OriginalImg.setImage(bi);
               /* BufferedImage ImgR = ImageUtils.extractBand(bi, 0);
                RImg.setImage(ImgR);
                BufferedImage ImgG = ImageUtils.extractBand(bi, 1);
                GImg.setImage(ImgG);
                BufferedImage ImgB = ImageUtils.extractBand(bi, 2);
                BImg.setImage(ImgB);*/

            }
        });

        JMenuItem UploadR = new JMenuItem("Upload R");
        mnNewMenu.add(UploadR);
        UploadR.addActionListener(new ActionListener() {
            BufferedImage bi = null;

            public void actionPerformed(ActionEvent ev) {
                try {
                    bi = getFileChooser();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                BufferedImage ImgR = ImageUtils.extractBand(bi, 0);
                RImg.setImage(ImgR);
                BufferedImage ImgG = ImageUtils.extractBand(bi, 1);
                GImg.setImage(ImgG);
                BufferedImage ImgB = ImageUtils.extractBand(bi, 2);
                BImg.setImage(ImgB);
            }
        });

        JMenuItem UploadG = new JMenuItem("Upload G");
        mnNewMenu.add(UploadG);
        UploadG.addActionListener(new ActionListener() {
            BufferedImage bi = null;

            public void actionPerformed(ActionEvent ev) {
                try {
                    bi = getFileChooser();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ImageUtils.extractBand(bi, 1);
                GImg.setImage(bi);

            }
        });

        JMenuItem UploadB = new JMenuItem("Upload B");
        mnNewMenu.add(UploadB);
        UploadB.addActionListener(new ActionListener() {
            BufferedImage bi = null;

            public void actionPerformed(ActionEvent ev) {
                try {
                    bi = getFileChooser();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ImageUtils.extractBand(bi, 2);
                BImg.setImage(bi);

            }
        });

        JMenuItem GenerateRandom = new JMenuItem("Generate random image");
        mnNewMenu.add(GenerateRandom);
        GenerateRandom.addActionListener(new ActionListener() {
            BufferedImage img = null;

            public void actionPerformed(ActionEvent ev) {
                try {
                    img = new RandomImageGenerator().getImg();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                ImageUtils.displayImage(img);

            }
        });

        JMenuItem Convert = new JMenuItem("Convert to negative");
        mnNewMenu.add(Convert);
        Convert.addActionListener(new ActionListener() {


            public void actionPerformed(ActionEvent ev) {
                BufferedImage img = OriginalImg.getImage();
                img= ConvertToNegative.Process(img);
                ImageUtils.displayImage(img);

            }
        });




        JMenu Processing = new JMenu("Processing");
        menuBar.add(Processing);

        JMenuItem Compose = new JMenuItem("Compose");
        Processing.add(Compose);
        Compose.addActionListener(new ActionListener() {


            public void actionPerformed(ActionEvent ev) {

                BufferedImage ImgR = RImg.getImage();
                BufferedImage ImgG = GImg.getImage();
                BufferedImage ImgB = BImg.getImage();
                BufferedImage ComposedOriginal = ImageUtils.composeImage(ImgR, ImgG, ImgB);
                OriginalImg.setImage(ComposedOriginal);

            }
        });






        //Decompose image



        JMenuItem decompose = new JMenuItem("Decompose");
        Processing.add(decompose);

        decompose.addActionListener(new ActionListener() {


            public void actionPerformed(ActionEvent ev) {
                BufferedImage bi = OriginalImg.getImage();
                BufferedImage ImgR = ImageUtils.extractBand(bi, 0);
                ImageUtils.saveImage(ImgR, ".bmp", OriginalImg.getImageName()+"Red");
                RImg.setImage(ImgR);
                BufferedImage ImgG = ImageUtils.extractBand(bi, 1);
                ImageUtils.saveImage(ImgG, ".bmp", OriginalImg.getImageName()+"Green");
                GImg.setImage(ImgG);
                BufferedImage ImgB = ImageUtils.extractBand(bi, 2);
                ImageUtils.saveImage(ImgB, ".bmp", OriginalImg.getImageName()+"Blue");
                BImg.setImage(ImgB);
            }
        });

    }

    private BufferedImage getFileChooser() throws IOException {
        File file = null;
        BufferedImage image = null;
        Scanner fileIn;
        int response;
        JFileChooser chooser = new JFileChooser(".");
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        response = chooser.showOpenDialog(null);
        if (response == JFileChooser.APPROVE_OPTION){
            file=chooser.getSelectedFile();
            image = ImageIO.read(file);
        }
        return image;
    }
}