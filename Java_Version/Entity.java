public class Entity {

   public Object[] get_images()
   {
      return this.imgs;
   }
   
   public Object get_image()
   {
      return this.imgs[this.current_img];
   }
   
   public String get_name()
   {
      return this.name;     
   }
}
