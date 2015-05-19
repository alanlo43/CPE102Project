public class Point {
   private int x;
   private int y;

   public Point(int x, int y) 
   {
       this.x = x;
       this.y = y;
   }


   public int getX() 
   {
       return x;
   }
   
   public void setX(int input)
   {
      this.x = input;
   }

   public void setY(int input)
   {
      this.y = input;
   }
   
   public int getY() 
   {
       return y;
   }

   public Point viewport_to_world(Object viewport)
   {
      return Point(this.x + viewport.left, this.y + viewport.top);      
   }
   
   public Point world_to_viewport(Object viewport)
   {
      return Point(this.x - viewport.left, this.y - viewport.top);
   }
   
   public int distance_sq(Point p2)
   {
      return (this.x - p2.x) * (this.x - p2.x) + (this.y - p2.y) * (this.y - p2.y);
   }
}
