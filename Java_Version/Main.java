import java.util.List;
import java.util.ArrayList;
import processing.core.*;

public class Main extends PApplet
{
   private List<PImage> miner;
   private int current_image;
   private int current_image_win;
   private long next_time;
   private Point eyeLocation;
   private int[][] world;  
   private List<PImage> blob;
   private PImage bgnd;
   private PImage vein;
   private PImage rock;
   private PImage ore;
   private PImage obstacle;
   private PImage blacksmith;
   private final int row = 15;
   private final int total_row = 30;
   private final int col = 20;
   private final int total_col = 40;
   private final int BGND_COLOR = color(220,230,245);
   private static final int ANIMATION_TIME = 100;

   public void setup()
   {
      size(640,480);
      background(BGND_COLOR);
      miner = new ArrayList<PImage>();
      miner.add(loadImage("miner1.bmp"));
      miner.add(loadImage("miner2.bmp"));
      miner.add(loadImage("miner3.bmp"));
      miner.add(loadImage("miner4.bmp"));
      miner.add(loadImage("miner5.bmp"));
      bgnd = loadImage("grass.bmp");
      vein = loadImage("vein.bmp");
      rock = loadImage("rock.bmp");
      ore = loadImage("ore.bmp");
      obstacle = loadImage("obstacle.bmp");
      blacksmith = loadImage("blacksmith.bmp");
      blob = new ArrayList<PImage>();
      blob.add(loadImage("blob1.bmp"));
      blob.add(loadImage("blob2.bmp"));
      blob.add(loadImage("blob3.bmp"));
      blob.add(loadImage("blob4.bmp"));
      blob.add(loadImage("blob5.bmp"));
      blob.add(loadImage("blob6.bmp"));
      blob.add(loadImage("blob7.bmp"));
      blob.add(loadImage("blob8.bmp"));
      blob.add(loadImage("blob9.bmp"));
      blob.add(loadImage("blob10.bmp"));
      blob.add(loadImage("blob11.bmp"));
      blob.add(loadImage("blob12.bmp"));                  
      this.eyeLocation = new Point(0,0);      
      world= new int[total_col][total_row];
      world[2][3] = 2;
      world[3][2] = 2;
      world[4][1] = 2;
      world[4][2] = 2;
      world[4][3] = 2;
      world[4][4] = 2;
      world[4][5] = 2;
      world[2][6] = 2;
      world[3][6] = 2;
      world[4][6] = 2;
      world[5][6] = 2;
      world[6][6] = 2;
      world[9][1] = 2;
      world[10][1] = 2;
      world[8][2] = 2;      
      world[8][3] = 2;
      world[8][4] = 2;
      world[8][5] = 2;
      world[9][6] = 2;
      world[10][6] = 2;
      world[11][2] = 2;      
      world[11][3] = 2;
      world[11][4] = 2;
      world[11][5] = 2;
      world[13][2] = 2;
      world[14][1] = 2;
      world[15][1] = 2;
      world[16][2] = 2;
      world[16][3] = 2;
      world[15][4] = 2;
      world[14][5] = 2;
      world[13][6] = 2;
      world[14][6] = 2;
      world[15][6] = 2;
      world[16][6] = 2;
      world[4][5] = 2;
      world[4][13] = 1;
      world[5][13] = 2;
      world[6][13] = 3;
      world[10][21] = 6;
      world[29][13] = 6;
      world[7][13] = 4;
      world[8][13] = 5;
      world[35][28] = 4;
      world[30][24] = 5;
      world[19][14] = 5;
      world[39][14] = 5;
      world[19][29] = 5;
      world[39][29] = 5;
      world[9][13] = 6;
      world[19][21] = 7;
      world[10][16] = 7;
      current_image = 0;
      next_time = System.currentTimeMillis() + ANIMATION_TIME;
  }

   private void next_image()
   {
      current_image = (current_image + 1) % miner.size();
   }

   public void draw()
   {
      // A simplified action scheduling handler
      long time = System.currentTimeMillis();
      if (time >= next_time)
      {
         next_image();
         next_time = time + ANIMATION_TIME;
      }

      background(BGND_COLOR);
      int i,j;      
      
      for (i= eyeLocation.getX(); i< eyeLocation.getX() + col ; i++)
      {
         for (j= eyeLocation.getY(); j< eyeLocation.getY() + row; j++)
         {
            if (world[i][j] == 0)
            {
               image(bgnd ,(i - eyeLocation.getX())* 32, (j-eyeLocation.getY())  *32);
            }
            if (world[i][j] == 1) 
            {
               image(vein, (i - eyeLocation.getX()) * 32, (j-eyeLocation.getY()) * 32);
            }
            if (world[i][j] == 2) 
            {
               image(rock, (i - eyeLocation.getX()) * 32, (j-eyeLocation.getY()) * 32);
            }
            if (world[i][j] == 3) 
            {
               image(ore, (i - eyeLocation.getX()) * 32, (j-eyeLocation.getY()) * 32);
            }
            if (world[i][j] == 4) 
            {
               image(obstacle, (i - eyeLocation.getX()) * 32, (j-eyeLocation.getY()) * 32);
            }
            if (world[i][j] == 5) 
            {
               image(blacksmith, (i - eyeLocation.getX()) * 32, (j-eyeLocation.getY()) * 32);
            }
            if (world[i][j] == 6) 
            {
               image(miner.get(current_image), (i - eyeLocation.getX()) * 32, (j-eyeLocation.getY()) * 32);
            }
            if (world[i][j] == 7) 
            {
               image(blob.get(current_image), (i - eyeLocation.getX()) * 32, (j-eyeLocation.getY()) * 32);
            }
         }
      }
      
   }

   public void keyPressed()
   {
      switch (key)
      {
         case 'a':
            if (eyeLocation.getX() > 0)
            {
               eyeLocation.setX(eyeLocation.getX() - 1);
            }
            break;

         case 'd':
            if (eyeLocation.getX() < 20)
            {              
               eyeLocation.setX(eyeLocation.getX() + 1);
            }
            break;
                        
         case 'w':
            if (eyeLocation.getY() > 0)
            {
               eyeLocation.setY(eyeLocation.getY() - 1);
            }
            break;
         case 's':
            if (eyeLocation.getY() < 15)
            {
               eyeLocation.setY(eyeLocation.getY() + 1);
            }
            break;
         case 'q':
            eyeLocation.setY(0);
            eyeLocation.setX(0);            
      }
   }

   public static void main(String[] args)
   {
      PApplet.main("Main");
   }
}
