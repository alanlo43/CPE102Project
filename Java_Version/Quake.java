public class Quake extends AnimatedObject{
   private String name;
   private Point position;
   private Object[] imgs;
   private int current_img = 0;
   private int animation_rate;
   private Object[] pending_actions;

   public Quake(String name, Point position, Object[] imgs, int animation_rate)
   {
      this.name = name;
      this.position = position;
      this.imgs = imgs;
      this.animation_rate = animation_rate;
            
   }
}
