import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
public class RandomImageGenerator
{

    BufferedImage img = null;
    public RandomImageGenerator () throws IOException
    {
        // Image file dimensions 
        int width = 640, height = 320;

        // Create buffered image object 

        this.img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);



        // create random values pixel by pixel 
        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            {
                int a = (int)(Math.random()*256); //generating 
                int r = (int)(Math.random()*256); //values 
                int g = (int)(Math.random()*256); //less than 
                int b = (int)(Math.random()*256); //256 

                int p = (a<<24) | (r<<16) | (g<<8) | b; //pixel 

                img.setRGB(x, y, p);
            }
        }



    }

    public BufferedImage getImg(){


        return this.img;
    }
} 