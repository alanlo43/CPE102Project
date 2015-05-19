import java.util.List;

import processing.core.PImage;

public class save_load {
   public int PROPERTY_KEY = 0;   
   public String BGND_KEY = 'background';
   public int BGND_NUM_PROPERTIES = 4;
   public int BGND_NAME = 1;
   public int BGND_COL = 2;
   public int BGND_ROW = 3;
   public String MINER_KEY = 'miner';
   public int MINER_NUM_PROPERTIES = 7;
   public int MINER_NAME = 1;
   public int MINER_LIMIT = 4;
   public int MINER_COL = 2;
   public int MINER_ROW = 3;
   public int MINER_RATE = 5;
   public int MINER_ANIMATION_RATE = 6;
   public String OBSTACLE_KEY = 'obstacle';
   public int OBSTACLE_NUM_PROPERTIES = 4;
   public int OBSTACLE_NAME = 1;
   public int OBSTACLE_COL = 2;
   public int OBSTACLE_ROW = 3;
   public String ORE_KEY = 'ore';
   public int ORE_NUM_PROPERTIES = 5;
   public int ORE_NAME = 1;
   public int ORE_COL = 2;
   public int ORE_ROW = 3;
   public int ORE_RATE = 4;
   public String SMITH_KEY = 'blacksmith';
   public int SMITH_NUM_PROPERTIES = 7;
   public int SMITH_NAME = 1;
   public int SMITH_COL = 2;
   public int SMITH_ROW = 3;
   public int SMITH_LIMIT = 4;
   public int SMITH_RATE = 5;
   public int SMITH_REACH = 6;
   public String VEIN_KEY = 'vein';
   public int VEIN_NUM_PROPERTIES = 6;
   public int VEIN_NAME = 1;
   public int VEIN_RATE = 4;
   public int VEIN_COL = 2;
   public int VEIN_ROW = 3;
   public int VEIN_REACH = 5;

   public void load_world(WorldModel world, List<PImage> images, File file)
   {
      for (int x = 0; x < file.length; x++)
      {
         int[] line = file[x];
         if (line != null)
         {
            if (line[PROPERTY_KEY] == BGND_KEY)
            {
               add_background(world, line, images);               
            }
            else
            {
               add_entity(world, line, images, run)
            }
         }
      }
   }
   
   public void add_background(WorldModel world, String[] properties, int i_store)
   {
      if (properties.length >= BGND_NUM_PROPERTIES)
      {
         Point pt = new Point(int(properties[BGND_COL]), int(properties[BGND_ROW]));
         String name = properties[BGND_NAME];
         world.set_background(pt, entities.Background(name, image_store.get_images(i_store, name)));
      }
   }
   
   public void add_entity(WorldModel world, String[] properties,int i_store,boolean run)
   {
      new_entity = create_from_properties(properties, i_store);
      if (new_entity != null)
      {
         world.add_entity(new_entity);
         if (run)
         {
            world.schedule_entity(new_entity, i_store);
         }
         
      }     
   }
   
   public Object create_from_properties(String[] properties, int i_store)
   {
      int key = int(properties[PROPERTY_KEY]);
      if (properties != null)
      {
         if (key == MINER_KEY)
         {
            return create_miner(properties, i_store);
         }
         if (key == VEIN_KEY)
         {
            return create_vein(properties, i_store);            
         }
         if (key == ORE_KEY)
         {
            return create_ore(properties, i_store);            
         }
         if (key == SMITH_KEY)
         {
            return create_blacksmith(properties, i_store);
         }
         if (key == OBSTACLE_KEY)
         {
            return create_obstacle(properties, i_store);
         }         
      }
      return null;
   }
   
   public Miner create_miner(String[] properties, int i_store)
   {
      if (properties.length == MINER_NUM_PROPERTIES)
      {   
         Miner miner = new MinerNotFUll(properties[MINER_NAME], int(properties[MINER_LIMIT]), new Point(int(properties[MINER_COL]), int(properties[MINER_ROW])), int(properties[MINER_RATE]), image_store.get_images(i_store, properties[PROPERTY_KEY]),int(properties[MINER_ANIMATION_RATE]));
         return miner;
      }
      else
      {
         return null;
      }         
   }
   
   public Vein create_vein(String[] properties, int i_store)
   {
      if (properties.length == VEIN_NUM_PROPERTIES)
      {
         Vein vein = new Vein( properties[VEIN_NAME], int(properties[VEIN_RATE]), new Point(int(properties[VEIN_COL]), int(properties[VEIN_ROW])), image_store.get_images(i_store, properties[PROPERTY_KEY]), int(properties[VEIN_REACH]));
         return vein;
      }
      else
      {
         return null;
      }
   }
   
   public Ore create_ore(String[] properties, int i_store)
   {
      if (properties.length == ORE_NUM_PROPERTIES)
      {
         Ore ore = new ore(properties[ORE_NAME], new Point(int(properties[ORE_COL]), int(properties[ORE_ROW])), image_store.get_images(i_store, properties[PROPERTY_KEY]), int(properties[ORE_RATE]));
         return ore;
      }
      else
      {
         return null;
      }
   }
   
   public Blacksmith create_blacksmith(String[] properties, int i_store)
   {
      if (properties.length == SMITH_NUM_PROPERTIES)
      {
         Blacksmith smith = new Blacksmith(properties[SMITH_NAME], new Point(int(properties[SMITH_COL]), int(properties[SMITH_ROW])), image_store.get_images(i_store, properties[PROPERTY_KEY]),int(properties[SMITH_LIMIT]), int(properties[SMITH_RATE]), int(properties[SMITH_REACH]));
         return smith;
      }
      else
      {
         return null;
      }
   }
   
   public Obstacle create_obstacle(String[] properties, int i_store)
   {
      if (properties.length == OBSTACLE_NUM_PROPERTIES)
      {
         Obstacle obs = new Obstacle( properties[OBSTACLE_NAME], new Point(int(properties[OBSTACLE_COL]), int(properties[OBSTACLE_ROW])),image_store.get_images(i_store, properties[PROPERTY_KEY]));
      }
      else
      {
         return null;
      }
   }
}
