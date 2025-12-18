package Com.AUCA.LostFound.Repository;

import Com.AUCA.LostFound.Model.Item;
import java.util.List;

public interface ItemRepository {
    void save(Item item);
    List<Item> findAll();
    Item findById(String id);
    void deleteById(String id);
}
