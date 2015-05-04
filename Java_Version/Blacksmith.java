public class Blacksmith extends People{
   private String name;
   private Point position;
   private Object[] imgs;
   private int current_img;
   private int resource_limit;
   private int resource_count;
   private int rate;
   private int resource_distance;
   private Object[] pending_actions;

   public Blacksmith(String name, Point position, Object[] imgs, int resource_limit, int rate)
   {
      this.name = name;
      this.position = position;
      this.imgs = imgs;
      this.current_img = 0;
      this.resource_limit = resource_limit;
      this.resource_count = 0;
      this.rate = rate;            
      this.resource_distance = 1;
   }
   public int get_resource_distance()
   {
      return this.resource_distance;
   }
}
