import java.lang.Math;

public class WorldModel {
   int BLOB_RATE_SCALE = 4;
   int BLOB_ANIMATION_RATE_SCALE = 50;
   int BLOB_ANIMATION_MIN = 1;
   int BLOB_ANIMATION_MAX = 3;
   int ORE_CORRUPT_MIN = 20000;
   int ORE_CORRUPT_MAX = 30000;
   int QUAKE_STEPS = 10;
   int QUAKE_DURATION = 1100;
   int QUAKE_ANIMATION_RATE = 100;
   int VEIN_SPAWN_DELAY = 500;
   int VEIN_RATE_MIN = 8000;
   int VEIN_RATE_MAX = 17000;
   
   public Object background;
   public int num_rows;
   public int num_cols;
   public Object occupancy;
   public Entity[] entities;
   public Object action_queue;
   
   public WorldModel(int num_rows, int num_cols, Object background)
   {
      this.background = background;
      this.num_rows = num_rows;
      this.num_cols = num_cols;
      this.occupancy = background;                 
   }
   
   public boolean within_bounds(Point point)
   {
      return (point.getX() >= 0 && point.getX() < this.num_cols && point.getY() >= 0 && point.getY() < this.num_rows);
   }
 
   public boolean is_occupied(Point point)
   {
      return (this.within_bounds(point) and this.occupancy.get_cell(point) != None);
   }
   
   public Entity find_nearest(Point point, String type)
   {
      int[] oftype;
      int i = 0;
      for(i; i< this.entities.length; i++ )
      {
         if (isInstance(e, type))
         {
            oftype[(e, pt.distance_sq(e.get_position()))];
         }
      }
      return nearest_entity(oftype);
   }
   
   public add_entity(Entity entity)
   {
      Point pt = entity.get_position();
      if (this.within_bounds(pt))
      {
         Entity[] old_entity = this.occupancy.get_cell(pt);
         if (old_entity.length != 0)
         {
            old_entity.clear_pending_actions();
         }
         this.occupancy.set_cell(pt, entity);
         this.entities.append(entity);         
      }   
   }
   
   public Point[] move_entity(Entity entity, Point point)
   {
      Point[] tiles;
      if (this.within_bounds(point))
      {
         Point old_pt = entity.get_position();
         this.occupancy.set_cell(old_pt, null);
         tiles.append(old_pt);
         this.occupancy.set_cell(point, entity);
         tiles.append(point);
         entity.set_position(point);         
      }
      return tiles;
   }
   
   public void remove_entity_at(Point pt)
   {
      if (this.within_bounds(point) && this.occupancy.get_cell(pt) != null)
      {
         Entity entity = this.occupancy.get_cell(pt);
         Point point = Point(-1,-1);
         entity.set_position(point);
         this.entities.remove(entity);
         this.occupancy.set_cell(point, null);         
      }
      
   }
   
   public Object get_background_image(Point point)
   {
      if (this.within_bounds(point))
      {
         return this.background.get_cell(point).get_image();
      }
   }
   
   public Background get_background(Point point)
   {
      if (this.within_bounds(point))
      {
         return this.background.get_cell(point);
      }
   }
   
   public void set_background(Point point, Background bgnd)
   {
      if (this.within_bounds(point))
      {
         this.background.set_cell(point, bgnd);
      }
   }
   
   public Background get_tile_occupant(Point point)
   {
      if (this.within_bounds(point))
      {
         return this.occupancy.get_cell(point);
      }
   }
   
   public Entity[] get_entities()
   {
      return this.entities;
   }
   
   public void save_world(Object file)
   {
      this.save_entities(file);
      this.save_background(file);
   }
   
   public void save_entities(Object file)
   {
      int i = 0;
      Entity[] entitylist = this.get_entities();
      for(i; i< this.entitylist.length; i++)
      {
         file.write(entitylist[i] + '\n' );
      }      
   }
   public void save_background(Object file)
   {
      int col = 0;
      int row = 0;
      for (row; row<this.num_rows; row++)
      {
         for (col; col<this.num_cols; col++)
         {
            file.write('background ' + entities.get_name(this.get_background(point.Point(col, row))) + ' ' + str(col) + ' ' + str(row) + '\n');
         }
      }
   }
   
   public Point next_position(Point entity_pt, Point dest_pt)
   {
      float horiz = Math.signum(dest_pt.getX() - entity_pt.getX());
      Point new_pt = new Point((int) (entity_pt.getX() + horiz), entity_pt.getY());
      
      if (horiz == 0 || this.is_occupied(new_pt))
      {
         float vert = Math.signum(dest_pt.getY() - entity_pt.getY());
         Point new_pt = new Point(entity_pt.getX(), (int) (entity_pt.getY() + vert));
         if (vert == 0 || this.is_occupied(new_pt))
         {
            Point new_pt = new Point(entity_pt.getX(), entity_pt.getY());
         }
      }
      return new_pt;      
   }
   
   public Point blob_next_position(Point entity_pt, Point dest_pt)
   {
      float horiz = Math.signum(dest_pt.getX() - entity_pt.getX());
      Point new_pt = new Point((int) (entity_pt.getX() + horiz), entity_pt.getY());
      
      if (horiz == 0 || this.is_occupied(new_pt) || !(Ore.isinstance(this.get_tile_occupant(new_pt))))
      {
         int vert = Math.signum(dest_pt.getY() - entity_pt.getX());
         Point new_pt = new Point(entity_pt.getX(), (int) (entity_pt.getY() + vert));
         
         if (vert == 0 || this.is_occupied(new_pt) || !(Ore.isinstance(this.get_tile_occupant(new_pt))))
         {
            Point new_pt = new Point(entity_pt.getX(), entity_pt.getY());
         }
      }
      return new_pt;
   }
   public boolean miner_to_ore(Entity entity, boolean ore)
   {
      Point entity_pt = entity.get_position();
      if (!(ore))
      {
         return false;         
      }
      if (adjacent(entity_pt, ore_pt))
      {
         entity.set_resource_count(1 + entity.get_resource_count());
         this.remove_entity(ore);
         return true;
      }
      else
      {
         new_pt = this.next_position(entity_pt, ore_pt);
         return false;
      }
   }
   
   public Object create_ore_transform_action(Entity entity, String i_store)
   {
      public Point action(int current_ticks)
      {
         entity.remove_pending_action(action);
         OreBlob blob = this.create_blob(entity.getname() + " --blob", entity.get_position(), Math.floor(entity.get_rate() / BLOB_RATE_SCALE),current_ticks, i_store);
         this.remove_entity(entity);
         this.add_entity(blob);
         return [blob.get_position()];
      }
      return action
   }
   
   public Object create_entity_death_action(Entity entity)
   {
      public Point action(int current_ticks)
      {
         entity.remove_pending_action(action);
         Point pt = new entity.get_position();
         this.remove_entity(entity)
         return [pt];         
      }
      return action
   }
   
   public void clear_pending_actions(Entity entity)
   {
      Object[] thisarray = new entity.get_pending_actions();
      int i = 0;
      for (i; i < thisarray.lengthG i++)
      {
         this.unschedule_action_world(thisarray[i]);
      }
   }

   public Miner try_transform_miner(Entity entity)
   {
      Miner new_entity = this.transform(entity);
      if (entity != new_entity)
      {
         this.clear_pending_actions(entity);
         this.remove_entity_at(entity.get_position());
         this.add_entity(new_entity);
         this.schedule_animation(new_entity);
      }
      return new_entity;
   }
   public boolean miner_to_smith(Entity entity, boolean smith)
   {
      Point entity_pt = new entity.get_position();
      if (!(smith))
      {
         return false;
      }
      Point smith_pt = new smith.get_position();
      if adjacent(entity_pt, smith_pt)
      {
         smith.set_resource_count(smith.get_resource_count() + entity.get_resource_count());
         entity.set_resource_count(0);
         return true;
      }
   }
   
   public Object create_miner_full_action(Entity entity, String i_store)
   {
      public Point action(int current_ticks)
      {
         Point entity_pt = entity.get_position();
         Entity smith = this.find_nearest(entity_pt, entities.Blacksmith);
         (tiles, found) = this.miner_to_smith(entity, smith);
         Entity new_entity = entity;
         if (found)
         {
            new_entity = this.try_transform_miner(entity, try_transform_miner_full);
         }
         this.schedule_action(new_entity, this.create_miner_action(new_entity, i_store), current_ticks + new_entity.get_rate());
         return tiles
      }
      return action;
   }
   
   public Object create_miner_not_full_action(Entity entity, String i_store)
   {
      public Point action(int current_ticks)
      {
         Point entity_pt = entity.get_position();
         Entity ore = this.find_nearest(entity_pt, entities.Ore);
         (tiles, found) = this.miner_to_ore(entity, ore);
         Entity new_entity = entity;
         if (found)
         {
            new_entity = this.try_transform_miner(entity, try_transform_miner_not_full);
         }
         this.schedule_action(new_entity, this.create_miner_action(new_entity, i_store), current_ticks + new_entity.get_rate());
         return tiles
      }
      return action;
   }
   
   public Entity create_miner_action(Entity entity, Object image_store)
   {
      if (Entity.isinstance(entities.MinerNotFull))
      {
         return this.create_miner_not_full_action(entity, image_store);
      }
      else
      {
         return this.create_miner_full_action(entity,image_store);
      }
   }
   
   public boolean blob_to_vein(Entity entity, boolean vein)
   {
      Point entity_pt = entity.get_position();
      if (!(vein))
      {
         return false;
      }
      Point vein_pt = vein.get_position();
      if (adjecent(entity_pt, vein_pt))
      {
         this.remove_entity(vein);
         return true;
      }
      else
      {
         Point new_pt = this.blob_next_position(entity_pt, vein_pt);
         Entity old_entity = this.get_tile_occupant(new_pt);
         if (old_entity.isinstance(entities.Ore))
         {
            this.remove_entity(old_entity);
         }
         return false;
      }
   }
   public Object create_ore_blob_action(Entity entity, String i_store)
   {
      public Point action(int current_ticks)
      {
         entity.remove_pending_action(action);
         Point entity_pt = entity.get_position();
         Entity vein = this.find_nearest(entity_pt, entities.Vein);
         (tiles, found) = this.blob_to_vein(entity, vein);
         int next_time = current_ticks + entity.get_rate();         
         if (found)
         {
            new_entity = this.try_create_quake(tiles[0], current_ticks,i_store);
            this.add_entity(quake);
            next_time = current_ticks + entity.get_rate() * 2;            
         }
         this.schedule_action(entity,this.create_ore_blob_action(entity,i_store),next_time);
         return tiles;
      }
      return action;      
   }
   public Point find_open_around(Point pt, int distance)
   {
      int dy = -distance;
      int dx = -distance;
      for (dy; dy < distance + 1; dy++)
      {
         for (dx; dx < distance + 1; dx++)
         {
            Point new_pt = new Point(pt.getX() + dx , pt.getY() + dy);
            if (this.within_bounds(new_pt) && (!(this.is_occupied(new_pt))))
            {
               return new_pt;
            }
         }
      }
      return null;
   }
   
   public action create_vein_action(Entity entity, String i_store)
   {
      public Point action(int current_ticks)
      {
         entity.remove_pending_action(action);
         boolean open_pt = this.find_open_around(entity.get_position(),entity.get_resource_distance());         
         if (open_pt)
         {
            Entity ore = this.create_ore("ore - " + entity.get_name() + "-" + Integer.toString(current_ticks), open_pt, current_ticks, i_store);
            this.add_entity(ore);
            Point[] tiles = [open_pt];
         }
         else:
         {
            Point[] tiles = [];
         }
         this.schedule_action(entity,this.create_vein_action(entity, i_store), current_ticks + entity.get_rate());
         return tiles;
      }
      return action;      
   }
   public MinerNotFull try_transform_miner_full(Entity entity)
   {
      MinerNotFull new_entity = new MinerNotFull(entity.get_name(),entity.get_resource_limit(), entity.get_position(), entity.get_rate(), entity.get_images, entity.get_animation_rate());
      return new_entity;
   }
   
   public MinerFull try_transform_miner_not_full(Entity entity)
   {
      if (entity.resource_count < entity.resource_limit)
      {
         return entity;
      }
      else
      {
         MinerFull new_entity = new MinerFull(entity.get_name(),entity.get_resource_limit(), entity.get_position(), entity.get_rate(), entity.get_images, entity.get_animation_rate());
         return new_entity;
      }
      
   }
   
   public Object create_animation_action(entity, repeat_count)
   {
      public Point action(int current_ticks)
      {
         entity.remove_pending_action(action);
         entity.next_image();
         if (repeat_count != 1)
         {
            this.schedule_action(entity,this.create_animation_action(entity, max(repeat_count -1 , 0)), current_ticks + entity.get_animation_rate());            
         }
         return [entity.get_position()];
      }
      return action;
   }
   
   public OreBlob create_blob(String name, Point pt, int rate, int ticks, String i_store)
   {
      Oreblob blob = new Oreblob(name, pt, rate, image_store.get_images(i_store, 'blob'), random.randint(BLOB_ANIMATION_MIN, BLOB_ANIMATION_MAX) * BLOB_ANIMATION_RATE_SCALE);
      this.schedule_blob(blob, ticks, i_store);
      return blob;
   }
      
   public Ore create_ore(String name, Point pt, int ticks, String i_store)
   {
      Ore ore = new Ore(name, pt, image_store.get_images(i_store, 'ore'), random.randint(ORE_CORRUPT_MIN, ORE_CORRUPT_MAX));
      this.schedule_ore(ore, ticks, i_store);
      return ore;
   }
   
   public Quake create_quake(Point pt, int ticks, String i_store)
   {
      Quake quake = new quake("quake",pt, image_store.get_images(i_store, 'quake'), QUAKE_ANIMATION_RATE);
      this.schedule_quake(quake,ticks);
      return quake;
   }
    
}

