public class Ore extends Minerals{
   private String name;
   private Point position;
   private Object[] imgs;
   private int current_img;
   private int rate;
   private Object[] pending_actions;

   public Ore(String name, Point position, Object[] imgs, int rate)
   {
      this.name = name;
      this.position = position;
      this.imgs = imgs;
      this.rate = 5000;
      this.current_img = 0;
   }
}
