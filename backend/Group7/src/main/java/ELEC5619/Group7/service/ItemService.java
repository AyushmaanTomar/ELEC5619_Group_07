package ELEC5619.Group7.service;

import ELEC5619.Group7.entity.Item;
import ELEC5619.Group7.entity.User;
import ELEC5619.Group7.repository.ItemRepository;
import ELEC5619.Group7.repository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private LikeRepository likeRepository;
    @Transactional
    public String createItem(Item item) {
        try {
            List<Item> items = itemRepository.findAll();
            if (!items.contains(item)) {
                item.setId(null == itemRepository.findMaxId() ? 0 : itemRepository.findMaxId() + 1);
                itemRepository.save(item);
                return "success";
            } else {
                itemRepository.save(item);
                return "update";
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
     * List all item
     */
    public List<Item> readItems() {
        return itemRepository.findAll();
    }

    @Transactional
    public String updateItem(Item item, String itemDescription,
                             String productName, Double price,
                             Boolean active) {
        if (itemRepository.existsById(item.getId())) {
            try {
                item.setDescription(itemDescription);
                item.setPrice(price);
                item.setSold(active);
                item.setName(productName);
                itemRepository.save(item);
                return "Update";
            } catch (Exception e) {
                throw e;
            }
        } else {
            return "item_not_found";
        }
    }

    @Transactional
    public boolean deleteItemByID(Integer id) {
        itemRepository.deleteItemByID(id);

        if (itemRepository.existsById(id)) {
            return false;
        }
        return true;
    }

}
