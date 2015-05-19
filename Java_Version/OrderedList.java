import java.util.ArrayList;
import java.util.List;

public class OrderedList {
   private List<ListItem> list;  
   public OrderedList()
   {
      list = new ArrayList<ListItem>();      
   }
   
   public void insert(int item, int ord)
   {
      int list_size;
      list_size = this.list.size();
      int idx;
      int total_idx = 0;
      for (idx = 0; idx < list_size; idx++)
      {
         total_idx += 1;
      }
      list.set(total_idx, (new ListItem(item,ord)));
   }
   public void remove(int item)
   {
      int list_size;
      list_size = this.list.size();
      int idx;
      int total_idx = 0;
      for (idx = 0; idx < list_size; idx++)
      {
         total_idx += 1;
      }
      if (total_idx < list_size)
      {
         list.set(total_idx, null);         
      }      
   }
   
   public ListItem head()
   {
      return list.get(0);
   }
   
   public void pop()
   {
      if (this.list != null)
      {
         list.remove(list.size() - 1);
      }
   }
        
}
