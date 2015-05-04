public class OreBlob extends AnimatedObject{
   private String name;
   private Point position;
   private int rate;
   private Object[] imgs;
   private int current_img = 0;
   private int animation_rate;
   private Object[] pending_actions;

   public OreBlob(String name, Point position, int rate, Object[] imgs, int animation_rate)
   {
      this.name = name;
      this.position = position;
      this.rate = rate;
      this.imgs = imgs;
      this.animation_rate = animation_rate;
            
   }
   public int get_rate()
   {
      return this.rate;
   }
   
}
