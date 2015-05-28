import processing.core.PImage;

import java.util.ArrayList;
import java.util.List;

//import OrderedList.ListItem;
import static java.lang.Math.abs;

public abstract class MobileAnimatedActor
   extends AnimatedActor
{
   private final static int OBSTACLE = 1;
   private final static int VISITED = 2;
   private final static int GOAL = 3;
   private static int[][] obstacle_grid;
   private List<Point> path;
   private Point next_pt;
   public MobileAnimatedActor(String name, Point position, int rate,
      int animation_rate, List<PImage> imgs)
   {
      super(name, position, rate, animation_rate, imgs);
   }

   public static void find_entity(WorldModel world, int[][] obstacle_grid)
   {
      for (WorldEntity entity : world.getEntities())
      {
         Point entity_point = entity.getPosition();
         obstacle_grid[entity_point.x][entity_point.y] = 1;
      }
   }
   
   public static boolean search(Point pt, List<Point> path, Point dest_pt)
   {
      if (!(pt.y >= 0 && pt.y < 15 && pt.x >= 0 && pt.x < 20))
      {
         return false;
      }
      if (obstacle_grid[pt.x][pt.y] == OBSTACLE)
      {
         return false;
      }
      if (obstacle_grid[pt.x][pt.y] == VISITED)
      {
         return false;
      }
      if (pt.x == dest_pt.x && pt.y == dest_pt.y)
      {        
         path.add(0, pt);
         return true;
      }
      obstacle_grid[pt.x][pt.y] = VISITED;
      boolean found = search(new Point(pt.x + 1, pt.y), path, dest_pt) ||                      
                      search(new Point(pt.x, pt.y + 1), path, dest_pt) ||
                      search(new Point(pt.x - 1, pt.y), path, dest_pt) ||
                      search(new Point(pt.x, pt.y - 1), path, dest_pt);      
      if (found)
      {
         path.add(0, pt);
      }
      return found;
   }
   
   protected Point nextPosition(WorldModel world, Point dest_pt)
   {
      obstacle_grid = new int[40][30];
      find_entity(world, obstacle_grid);
      List<Point> path = new ArrayList<Point>();
      search(this.getPosition(), path, dest_pt);
      int truth = 0;
      for (Point p : path) 
      {
         if (this.getPosition() == p)
         {   
            truth = 1;
         }
         if (truth == 1)
         {
            Point next_pt = p;
         }
      }
      return next_pt;      
   }
   
   protected static boolean adjacent(Point p1, Point p2)
   {
      return (p1.x == p2.x && abs(p1.y - p2.y) == 1) ||
         (p1.y == p2.y && abs(p1.x - p2.x) == 1);
   }

   protected abstract boolean canPassThrough(WorldModel world, Point new_pt);
}
