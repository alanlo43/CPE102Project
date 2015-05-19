import java.lang.Math;

public class Actions {

   public int sign(int x)
   {
      if (x < 0)
      {
         return -1;        
      }
      if (x > 0)
      {
         return 1;         
      }
      else
      {
         return 0;
      }
   }
   
   public boolean adjacent(Point pt1, Point pt2)
   {
      return (pt1.getX() == pt2.getX() && Math.abs(pt1.getY() - pt2.getY()) == 1) || (pt1.getY() == pt2.getY() && Math.abs(pt1.getX() - pt2.getX()) == 1 );
   }
}
