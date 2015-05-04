public class Obstacle extends Object
{
   private String name;
   private Point position;
   private Object[] imgs;
   private int current_imgs = 0;
   
   public Obstacle(String name, Point position, Object[] imgs)
   {
      this.name = name;
      this.position = position;
      this.imgs = imgs;      
   }
}
