public class Vein extends Minerals{
   private String name;
   private Point position;
   private int rate;
   private Object[] imgs;
   private int current_img;
   private int resource_distance;
   private Object[] pending_actions;

   public Vein(String name, int rate, Point position, Object[] imgs)
   {
      this.name = name;
      this.rate = rate;
      this.position = position;
      this.imgs = imgs;
      this.resource_distance = 1;
      this.current_img = 0;
   }
   public int get_resource_distance()
   {
      return this.resource_distance;
   }
}
