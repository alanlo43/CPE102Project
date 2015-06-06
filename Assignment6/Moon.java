import processing.core.PImage;
import java.util.List;

public class Moon
   extends WorldEntity
{
   public Moon(String name, Point position, List<PImage> imgs)
   {
      super(name, position, imgs);
   }

   public String toString()
   {
      return String.format("moon %s %d %d", this.getName(),
         this.getPosition().x, this.getPosition().y);
   }
}
