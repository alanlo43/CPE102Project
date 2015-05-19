public class Grid {
   private int width;
   private int height;
   private int[][] cells;
   public final int EMPTY = 0;
   public final int GATHERER = 1;
   public final int GENERATOR = 2;
   public final int RESOURCE = 3;
   
   public Grid(int width, int height, int occupancy_value)
   {
      this.width = width;
      this.height = height;
      this.cells = new int[height][width];
      for (int i = 0; i < cells.length; i++)
      {
         for (int x = 0; x < cells[i].length; x++)
         {
            this.cells[i][x] = occupancy_value;
         }
      }
   }
   
   public void set_cell(Point point, int value)
   {
      this.cells[point.getY()][point.getX()] = value;
   }
   
   public int get_width()
   {
      return this.width;
   }
   
   public int get_height()
   {
      return this.height;
   }
   
   public int get_cell(Point point)
   {
      return this.cells[point.getY()][point.getX()];
   }
   
}
