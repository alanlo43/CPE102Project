public class Object extends Entity{
  
   public void set_position(Point point)
   {
      this.position = point;
   }
   public Point get_position()
   {
      return this.position;
   }
   public int next_image()
   {
      this.current_img = (this.current_img + 1) % len(this.imgs);
   }
}
