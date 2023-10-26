package ELEC5619.Group7.controller;

import ELEC5619.Group7.entity.Item;
import ELEC5619.Group7.entity.ProductCategory;
import ELEC5619.Group7.entity.User;
import ELEC5619.Group7.service.ItemService;
import ELEC5619.Group7.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<String> createItem(@RequestBody Item item) {
        String result = itemService.createItem(item);
        if("success".equals(result)) {
            return new ResponseEntity<>("Item created successfully", HttpStatus.CREATED); // HTTP 201: CREATED
        }
        return new ResponseEntity<>("Failed to create item", HttpStatus.BAD_REQUEST); // HTTP 400: BAD REQUEST
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> getItemByID(@PathVariable Integer id) {
        Item item = itemService.getItemByID(id);
        if(item == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // HTTP 404: NOT FOUND
        }
        return new ResponseEntity<>(item, HttpStatus.OK); // HTTP 200: OK
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Item>> getAllItemByUser(@PathVariable Integer userId) {
        User user = userService.getUserById(userId);
        if(user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // HTTP 404: NOT FOUND
        }
        List<Item> items = itemService.getAllItemByUser(user);
        return new ResponseEntity<>(items, HttpStatus.OK); // HTTP 200: OK
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Item>> getItemWithSameCategory(@PathVariable Integer categoryId) {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setId(categoryId);
        List<Item> items = itemService.getItemWithSameCategoryWithCategory(productCategory);
        if(items == null || items.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // HTTP 404: NOT FOUND
        }
        return new ResponseEntity<>(items, HttpStatus.OK); // HTTP 200: OK
    }
}
