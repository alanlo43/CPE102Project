public class MinerNotFull extends Miner{
   private String name;
   private Point position;
   private int rate;
   private Object[] imgs;
   private int current_img;
   private int resource_limit;
   private int resource_count;
   private int animation_rate;
   private Object[] pending_actions;
   
   public MinerNotFull(String name, int resource_limit, Point position, int rate, Object[] imgs, int animation_rate)
   {
      this.name = name;
      this.position = position;
      this.rate = rate;
      this.imgs = imgs;
      this.current_img = 0;
      this.resource_limit = resource_limit;
      this.resource_count = 0;
      this.animation_rate = animation_rate;
          
   }
}
