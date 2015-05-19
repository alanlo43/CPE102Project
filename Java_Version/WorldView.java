import java.util.List;
import java.lang.Math;
import processing.core.*;

public class WorldView extends PApplet{
   public int MOUSE_HOVER_ALPHA = 120;
   private Object viewport;
   private PImage screen;
   private Point mouse_pt;
   private WorldModel world;
   private int tile_width;
   private int tile_height;
   private int num_rows;
   private int num_cols;
   private PImage mouse_img;
   
   public WorldView(int view_cols,int view_rows, PImage screen, WorldModel world, int tile_width, int tile_height, mouse_img = null)
   {
      this.viewport = 1;
      this.screen = screen;
      this.mouse_pt = new Point(0,0);
      this.world = world;
      this.tile_width = tile_width;
      this.tile_height = tile_height;
      this.num_rows = world.num_rows;
      this.num_cols = world.num_cols;
      this.mouse_img = mouse_img;      
   }
   
   public void draw_background()
   {
      for (int y = 0; y < this.viewport.height; y++)
      {
         for (int x = 0; x< this.viewport.width; x++)
         {
            Point w_pt = Point(x,y).viewport_to_world(this.viewport);
            PImage img = this.world.get_background_image(w_pt);
            image(img, x* this.tile_width, y*this.tile_height);            
         }
      }
   }
   
   public void draw_entities()
   {
      for (int i = 0; i < this.world.entities.length; i++)
      {
         Entity entity = world.entities[i];
         if this.viewport.collidepoint(entity.position.x, entity.position.y)
         {
            Point v_pt = entity.position.world_to_viewport(this.viewport);
            image(entity.get_image(), v_pt.getX() * this.tile_width, v_pt.getY() * this.tile_height);            
         }
      }
   }
   
   public void draw_viewport()
   {
      this.draw_background();
      this.draw_entities();
   }
   
   public void update_tiles(Point[] tiles)
   {
      List<Point> rects;
      for (int i = 0; i < tiles.length; i++)
      {
         Point tile = tiles[i];
         if (this.viewport.collidepoint(tile.getX(), tile.getY()))
         {
            Point v_pt = tile.world_to_viewport(this.viewport);
            img = this.get_tile_image(v_pt);
            rects.add(this.update_tiles(v_pt, img));            
         }         
      }      
   }
   
   public Rect update_tile(Point view_tile_pt, PImage surface)
   {
      int abs_x = view_tile_pt.getX() * this.tile_width;
      int abs_y = view_tile_pt.getY() * this.tile_height;
      image(surface, abs_x, abs_y);            
   }
   
   public PImage get_tile_image(view_tile_pt)
   {
      Point pt = new view_tile_pt.viewpoint_to_world(this.viewport);
      PImage bgnd = this.world.get_background_image(pt);
      boolean occupant = this.world.get_tile_occupant(pt);
      if (occupant)
      {
         PImage img = Surface(this.tile_width, this.tile_height);
         image(bgnd,0,0);
         image(occupant.get_image(),0,0)
         return img;                 
      }
      else
      {
         return bgnd;
      }      
   }
   
   public void update_view(PImage mouse_img)
   {
      Point delta = new Point(0,0); 
      this.viewport = create_shifted_viewport(this.viewport, delta, this.num_rows, this.num_cols);
      this.mouse_img = mouse_img;
      this.draw_viewport();
      
   }
   public int clamp(int v, int low, int high)
   {
      return Math.min(high, Math.max(v, low));      
   }
   
   public Rect create_shifted_viewport(Object viewport, int[] delta, int num_rows, int num_cols)
   {
      int new_x = clamp(viewport.left + delta[0], 0, num_cols - viewport.width);
      int new_y = clamp(viewport.top + delta[1], 0, num_rows - viewport.height);
      return Rect(new_x, new_y, viewport.width, viewport.height);
   }
   
}
