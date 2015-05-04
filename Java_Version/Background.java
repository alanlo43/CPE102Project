public class Background extends Entity
{
   private String name;
   private Object[] imgs;
   private int current_img = 0;
   
   public Background(String name, Object[] imgs)
   {
      this.name = name;
      this.imgs = imgs;
   }
}
