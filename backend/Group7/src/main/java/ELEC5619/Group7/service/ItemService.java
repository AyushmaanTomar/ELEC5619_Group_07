package ELEC5619.Group7.service;

import ELEC5619.Group7.entity.Item;
import ELEC5619.Group7.entity.ProductCategory;
import ELEC5619.Group7.entity.User;
import ELEC5619.Group7.repository.ItemRepository;
import ELEC5619.Group7.repository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private LikeRepository likeRepository;

    /*
     * Add Item
     */
    @Transactional
    public String createItem(Item item) {
        try {
            if (!itemRepository.existsById(item.getId())) {
                item.setId(null == itemRepository.findMaxId() ? 0 : itemRepository.findMaxId() + 1);
                itemRepository.save(item);
                return "Item record created successfully.";
            } else {
                return "Item already exists in the database.";
            }
        } catch (Exception e) {
            throw e;
        }
    }

    /*
     * For page Specific Item
     */
    @Transactional
    public Item getItemByID(Integer id) {
        try {
            if (itemRepository.existsById(id)) {
                Item item = itemRepository.findById(id).get();
                return item;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw e;
        }
    }

    /*
     * For page profile page
     */
    public List<Item> getAllItemByUser(User user) {
        return itemRepository.findAllItemByUser(user.getId());
    }

    /*
     * List all item with same Category
     */
    public List<Item> getItemWithSameCategoryWithCategory(ProductCategory productCategory) {
        return itemRepository.findAllItemByCategory(productCategory.getId());
    }

}
