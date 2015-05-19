public class ListItem {
   private int item;
   private int ord;
   
   public ListItem(int item, int ord)
   {
      this.item = item;
      this.ord = ord;            
   }
   public int getItem()
   {
      return this.item;
   }
   
   public int getOrd()
   {
      return this.ord;
   }   
   
   public boolean equals(ListItem a, ListItem b)
   {
      return a.getItem() == b.getItem() && a.getOrd() == b.getOrd();
   }
}
