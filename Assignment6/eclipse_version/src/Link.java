import processing.core.PImage;
import java.util.List;

public class Link
   extends Miner
{
   public Link(String name, Point position, int rate,
      int animation_rate, int resource_limit, List<PImage> imgs)
   {
      super(name, position, rate, animation_rate, resource_limit,
         resource_limit, Obstacle.class, imgs);
   }

   protected Miner transform(WorldModel world)
   {
      return new LinkInvert(getName(), getPosition(), getRate(),
         getAnimationRate(), getResourceLimit(), getImages());
   }

   protected boolean move(WorldModel world, WorldEntity smith)
   {
      if (smith == null)
      {
         return false;
      }

      if (adjacent(getPosition(), smith.getPosition()))
      {
         setResourceCount(0);
         return true;
      }
      else
      {
         world.moveEntity(this, nextPosition(world, smith.getPosition()));
         return false;
      }
   }
}
