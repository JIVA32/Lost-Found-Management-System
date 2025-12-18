package Com.AUCA.LostFound.Service;

import Com.AUCA.LostFound.Model.Item;
import Com.AUCA.LostFound.Repository.ItemRepository;
import java.util.List;

/**
 * Service class for handling item-related business logic.
 */
public class ItemService {
    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public void addItem(Item item) {
        itemRepository.save(item);
    }

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public Item getItemById(String id) {
        return itemRepository.findById(id);
    }

    public void deleteItemById(String id) {
        itemRepository.deleteById(id);
    }
}