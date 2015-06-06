import processing.core.*;

import java.lang.Math;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JTextArea;

public class Main extends PApplet
{
   private static final int WORLD_WIDTH_SCALE = 2;
   private static final int WORLD_HEIGHT_SCALE = 2;
   
   private static final int SCREEN_WIDTH = 640;
   private static final int SCREEN_HEIGHT = 480;
   private static final int TILE_WIDTH = 32;
   private static final int TILE_HEIGHT = 32;

   private static final int TIMER_ACTION_DELAY = 100;

   private static final String IMAGE_LIST_FILE_NAME = "imagelist";
   private static final String DEFAULT_IMAGE_NAME = "background_default";
   private static final int DEFAULT_IMAGE_COLOR = 0x808080;

   private static final String SAVE_FILE_NAME = "gaia.sav";

   private ImageStore imageStore;
   private long next_time;
   private WorldModel world;
   private WorldView view;
   private PImage moon;
   private static List<PImage> link_img;
   private MousePoint moon_tip;
   private Point moon_tip_int;
   private int view_x;
   private int view_y;
   private Moon moon_1;

   public void setup()
   {
      size(SCREEN_WIDTH, SCREEN_HEIGHT);
      imageStore = new ImageStore(
         createImageColored(TILE_WIDTH, TILE_HEIGHT, DEFAULT_IMAGE_COLOR));
      loadImages(IMAGE_LIST_FILE_NAME, imageStore, this);

      int num_cols = SCREEN_WIDTH / TILE_WIDTH * WORLD_WIDTH_SCALE;
      int num_rows = SCREEN_HEIGHT / TILE_HEIGHT * WORLD_HEIGHT_SCALE;

      Background background = createDefaultBackground(imageStore);

      world = new WorldModel(num_rows, num_cols, background);

      Map<String, PropertyParser> parsers = buildPropertyParsers(world,
         imageStore, System.currentTimeMillis());
      loadWorld(world, SAVE_FILE_NAME, imageStore, parsers);

      view = new WorldView(SCREEN_WIDTH / TILE_WIDTH,
         SCREEN_HEIGHT / TILE_HEIGHT, this, world, TILE_WIDTH, TILE_HEIGHT);

      next_time = System.currentTimeMillis() + TIMER_ACTION_DELAY;
      moon = loadImage("moon.png");
      view_x = 0;
      view_y = 0;
      link_img = new ArrayList<PImage>();
      link_img.add(loadImage("link.gif")); 
   }

   public void mouseClicked(MouseEvent evt) {

      int clicked_x = evt.getX();
      int clicked_y = evt.getY();
      clicked_x = (Math.floorDiv(clicked_x, 32) + view_x );
      clicked_y = (Math.floorDiv(clicked_y, 32) + view_y );
      moon_tip = new MousePoint(clicked_x * 32,clicked_y * 32);
      moon_tip_int = new Point(clicked_x, clicked_y); 
      WorldEntity moon_1 = new Moon("moon1", moon_tip_int , imageStore.get(MOON_KEY));
      world.addEntity(moon_1);
      
      if (!(world.isOccupied(new Point(clicked_x - 1 , clicked_y - 1))))
      {
         WorldEntity link_entity_1 = new Link("link1", new Point(clicked_x - 1 , clicked_y - 1), 800, 100, 2, imageStore.get(LINK_KEY));
         world.addEntity(link_entity_1);         
      }
      if (!(world.isOccupied(new Point(clicked_x + 4 , clicked_y - 1))))
      {
         WorldEntity link_entity_2 = new Link("link2", new Point(clicked_x + 4 , clicked_y - 1), 800, 100, 2, imageStore.get(LINK_KEY));
         world.addEntity(link_entity_2);
      }
      if (!(world.isOccupied(new Point(clicked_x + 4 , clicked_y + 4))))
      {
         WorldEntity link_entity_3 = new Link("link3", new Point(clicked_x + 4 , clicked_y + 4), 800, 100, 2, imageStore.get(LINK_KEY));
         world.addEntity(link_entity_3);
      }
      if (!(world.isOccupied(new Point(clicked_x - 1 , clicked_y + 4))))
      {
         WorldEntity link_entity_4 = new Link("link4", new Point(clicked_x - 1 , clicked_y + 4), 800, 100, 2, imageStore.get(LINK_KEY));
         world.addEntity(link_entity_4);
      }
   }
   
   public void draw()
   {
      long time = System.currentTimeMillis();
      if (time >= next_time)
      {
         world.updateOnTime(time);
         next_time = time + TIMER_ACTION_DELAY;
      }

      view.drawViewport();
      if (moon_tip != null)
      {
         image(moon , moon_tip.x - (view_x*32) , moon_tip.y - (view_y*32));
      }
   }

   public void keyPressed()
   {
      if (key == CODED)
      {
         int dx = 0;
         int dy = 0;
         switch (keyCode)
         {
            case UP:
               dy = -1;
               if(view_y > 0)
               {
                  view_y -= 1;
               }
               break;
            case DOWN:
               dy = 1;
               if (view_y < 15)
               {
                  view_y += 1;
               }
               break;
            case LEFT:
               dx = -1;
               if (view_x > 0)
               {
                  view_x -= 1;
               }
               break;
            case RIGHT:
               dx = 1;
               if (view_x < 20)
               {
                  view_x += 1;
               }
               break;
         }
         view.updateView(dx, dy);
      }
   }

   private static Background createDefaultBackground(ImageStore imageStore)
   {
      List<PImage> bgndImgs = imageStore.get(DEFAULT_IMAGE_NAME);
      return new Background(DEFAULT_IMAGE_NAME, bgndImgs);
   }

   private static PImage createImageColored(int width, int height, int color)
   {
      PImage img = new PImage(TILE_WIDTH, TILE_HEIGHT, RGB);
      img.loadPixels();
      for (int i = 0; i < img.pixels.length; i++)
      {
         img.pixels[i] = color;
      }
      img.updatePixels();
      return img;
   }


   private static void loadImages(String filename, ImageStore imageStore,
      PApplet screen)
   {
      try
      {
         Scanner in = new Scanner(new File(filename));
         ImageStore.loadImages(in, imageStore, TILE_WIDTH,
               TILE_HEIGHT, screen);
      }
      catch (FileNotFoundException e)
      {
         System.err.println(e.getMessage());
      }
   }

   private static void loadWorld(WorldModel world, String filename,
      ImageStore imageStore, Map<String, PropertyParser> parsers)
   {
      try
      {
         Scanner in = new Scanner(new File(filename));
         WorldLoad.load(in, world, imageStore, parsers);
      }
      catch (FileNotFoundException e)
      {
         System.err.println(e.getMessage());
      }
   }

   private static final String BGND_KEY = "background";
   private static final int BGND_NUM_PROPERTIES = 4;
   private static final int BGND_NAME = 1;
   private static final int BGND_COL = 2;
   private static final int BGND_ROW = 3;

   private static final String MINER_KEY = "miner";
   private static final int MINER_NUM_PROPERTIES = 7;
   private static final int MINER_NAME = 1;
   private static final int MINER_LIMIT = 4;
   private static final int MINER_COL = 2;
   private static final int MINER_ROW = 3;
   private static final int MINER_RATE = 5;
   private static final int MINER_ANIMATION_RATE = 6;

   private static final String OBSTACLE_KEY = "obstacle";
   private static final int OBSTACLE_NUM_PROPERTIES = 4;
   private static final int OBSTACLE_NAME = 1;
   private static final int OBSTACLE_COL = 2;
   private static final int OBSTACLE_ROW = 3;

   private static final String ORE_KEY = "ore";
   private static final int ORE_NUM_PROPERTIES = 5;
   private static final int ORE_NAME = 1;
   private static final int ORE_COL = 2;
   private static final int ORE_ROW = 3;
   private static final int ORE_RATE = 4;

   private static final String SMITH_KEY = "blacksmith";
   private static final int SMITH_NUM_PROPERTIES = 4;
   private static final int SMITH_NAME = 1;
   private static final int SMITH_COL = 2;
   private static final int SMITH_ROW = 3;
   
   private static final String LINK_KEY = "link";
   private static final String LINK_SWORD_KEY = "linkinvert";
   
   private static final String VEIN_KEY = "vein";
   private static final int VEIN_NUM_PROPERTIES = 6;
   private static final int VEIN_NAME = 1;
   private static final int VEIN_RATE = 4;
   private static final int VEIN_COL = 2;
   private static final int VEIN_ROW = 3;
   private static final int VEIN_REACH = 5;
   
   private static final String MOON_KEY = "moon";

   private static Map<String, PropertyParser> buildPropertyParsers(
      WorldModel world, ImageStore imageStore, long time)
   {
      Map<String, PropertyParser> parsers = new HashMap<>();

      parsers.put(BGND_KEY, properties -> {
            if (properties.length >= BGND_NUM_PROPERTIES)
            {
               Point pt = new Point(Integer.parseInt(properties[BGND_COL]),
                  Integer.parseInt(properties[BGND_ROW]));
               String name = properties[BGND_NAME];
               world.setBackground(pt, new Background(name,
                  imageStore.get(name)));
            }
         });

      parsers.put(MINER_KEY, properties -> {
            if (properties.length == MINER_NUM_PROPERTIES)
            {
               Point pt = new Point(Integer.parseInt(properties[MINER_COL]),
                  Integer.parseInt(properties[MINER_ROW]));
               Actor entity = new MinerNotFull(properties[MINER_NAME],
                  pt,
                  Integer.parseInt(properties[MINER_RATE]),
                  Integer.parseInt(properties[MINER_ANIMATION_RATE]),
                  Integer.parseInt(properties[MINER_LIMIT]),
                  imageStore.get(MINER_KEY));
               world.addEntity(entity);
               entity.schedule(world, time + entity.getRate(), imageStore);
            }
         });

      parsers.put(OBSTACLE_KEY, properties -> {
            if (properties.length == OBSTACLE_NUM_PROPERTIES)
            {
               Point pt = new Point(
                  Integer.parseInt(properties[OBSTACLE_COL]),
                  Integer.parseInt(properties[OBSTACLE_ROW]));
               WorldEntity entity = new Obstacle(properties[OBSTACLE_NAME],
                  pt,
                  imageStore.get(OBSTACLE_KEY));
               world.addEntity(entity);
            }
         });

      parsers.put(ORE_KEY, properties -> {
            if (properties.length == ORE_NUM_PROPERTIES)
            {
               Point pt = new Point(Integer.parseInt(properties[ORE_COL]),
                  Integer.parseInt(properties[ORE_ROW]));
               Actor entity = new Ore(properties[ORE_NAME],
                  pt,
                  Integer.parseInt(properties[ORE_RATE]),
                  imageStore.get(ORE_KEY));
               world.addEntity(entity);
               entity.schedule(world, time + entity.getRate(), imageStore);
            }
         });

      parsers.put(SMITH_KEY, properties -> {
            if (properties.length >= SMITH_NUM_PROPERTIES)
            {
               Point pt = new Point(Integer.parseInt(properties[SMITH_COL]),
                  Integer.parseInt(properties[SMITH_ROW]));
               WorldEntity entity = new Blacksmith(properties[SMITH_NAME],
                  pt,
                  imageStore.get(SMITH_KEY));
               world.addEntity(entity);
            }
         });
      
      
      parsers.put(VEIN_KEY, properties -> {
            if (properties.length == VEIN_NUM_PROPERTIES)
            {
               Point pt = new Point(Integer.parseInt(properties[VEIN_COL]),
                  Integer.parseInt(properties[VEIN_ROW]));
               Actor entity = new Vein(properties[VEIN_NAME],
                  pt,
                  Integer.parseInt(properties[VEIN_RATE]),
                  Integer.parseInt(properties[VEIN_REACH]),
                  imageStore.get(VEIN_KEY));
               world.addEntity(entity);
               entity.schedule(world, time + entity.getRate(), imageStore);
            }
         });

      return parsers;
   }

   public static void main(String[] args)
   {
      PApplet.main("Main");
   }
}
