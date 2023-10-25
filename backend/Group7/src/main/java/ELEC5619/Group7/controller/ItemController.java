package ELEC5619.Group7.controller;

import ELEC5619.Group7.entity.Item;
import ELEC5619.Group7.entity.ProductCategory;
import ELEC5619.Group7.entity.User;
import ELEC5619.Group7.service.ItemService;
import ELEC5619.Group7.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private UserService userService;

    // Create an Item
    @PostMapping("/create")
    public String createItem(@RequestBody Item item) {
        return itemService.createItem(item);
    }

    // Get an Item by ID
    @GetMapping("/{id}")
    public Item getItemByID(@PathVariable Integer id) {
        return itemService.getItemByID(id);
    }

//    // Get all Items by a User
//    @GetMapping("/user/{userId}")
//    public List<Item> getAllItemByUser(@PathVariable Integer userId) {
//        User user = userService.getUserById(userId);
//        return itemService.getAllItemByUser(user);
//    }

    // Get all Items with the same Category
    @GetMapping("/category/{categoryId}")
    public List<Item> getItemWithSameCategory(@PathVariable Integer categoryId) {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setId(categoryId);
        return itemService.getItemWithSameCategoryWithCategory(productCategory);
    }
}
