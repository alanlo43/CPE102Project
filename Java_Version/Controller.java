import java.lang.Math;

import processing.core.PImage;

public class Controller {

   public Point on_keydown(Action event)
   {
      int x_delta = 0;
      int y_delta = 0;
      if (event.key == K_UP)
      {
         y_delta -= 1;
      }
      if (event.key == K_DOWN)
      {
         y_delta += 1;         
      }
      if (event.key == K_LEFT)
      {
         x_delta -= 1;
      }
      if (event.key == K_RIGHT)
      {
         x_delta += 1;
      }
      
      return new Point(x_delta, y_delta);
   }
   
   public Point mouse_to_tile(int[] pos, int tile_width, int tile_height)
   {
      return new Point(Math.floorDiv(pos[0], tile_width), Math.floorDiv(pos[0], tile_height));      
   }
   
   public void handle_timer_event(WorldModel world, WorldView view)
   {
      int rects = world.update_on_time(time.getTicks());
      view.update_view_tiles(rects);      
   }
   
   public handle_keydown(WorldView view, Action event)
   {
      PImage view_delta = on_keydown(event);
      view.update_view(view_delta);     
   }   
   
}
