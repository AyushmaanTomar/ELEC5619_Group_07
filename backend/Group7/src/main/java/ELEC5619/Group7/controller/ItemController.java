package ELEC5619.Group7.controller;

import ELEC5619.Group7.entity.Item;
import ELEC5619.Group7.entity.User;
import ELEC5619.Group7.service.ItemService;
import ELEC5619.Group7.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/items")
public class ItemController {


//    Test
//    different user and ite,
//    Search
//    getAllItemByUser
//    getItemByID
    @Autowired
    private ItemService itemService;

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<String> createItem(@RequestBody Item item) {
        System.out.println(item.getUser().getEmail());
        System.out.println(userService.getUserByEmail(item.getUser().getEmail()));
        if (userService.getUserByEmail(item.getUser().getEmail()) == null) {
            return new ResponseEntity<>("No such User", HttpStatus.BAD_REQUEST); // HTTP 400: BAD REQUEST
        }
        if (item.getName() == null || item.getName().length() == 0) return new ResponseEntity<>("Input Item name is not correct", HttpStatus.BAD_REQUEST);

        String result = itemService.createItem(item);

        if("success".equals(result)) {
            return new ResponseEntity<>("Item created successfully", HttpStatus.CREATED); // HTTP 201: CREATED
        } else if ("update".equals(result)) {
            return new ResponseEntity<>("Item updated successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Failed to create item", HttpStatus.BAD_REQUEST); // HTTP 400: BAD REQUEST
    }

    @PostMapping("/addItem")
    public ResponseEntity<String> addItem(@RequestParam String name,
                                          @RequestParam String description,
                                          @RequestParam String price,
                                          @RequestParam String listingDate,
                                          @RequestParam String imagePath,
                                          @RequestParam String userName) {

        if (name == null || description == null || price == null || listingDate == null || imagePath == null || userName == null) {
            return new ResponseEntity<>("Required fields not set", HttpStatus.BAD_REQUEST);
        }

        User user = userService.getUserByUsername(userName);
        if (user == null) {
            return new ResponseEntity<>("No such User", HttpStatus.BAD_REQUEST);
        }

        Double priceNum;
        try {
            priceNum = Double.parseDouble(price);
        } catch(Exception e) {
            return new ResponseEntity<>("Price was not a double", HttpStatus.BAD_REQUEST);
        }

        Item item = new Item();
        item.setUser(user);
        item.setName(name);
        item.setDescription(description);
        item.setPrice(priceNum);
        item.setImagePath(imagePath);
        item.setQty(1);
        item.setLikeAmount(1);
        item.setSold(false);
        item.setListingDate(listingDate);

        String result = itemService.createItem(item);

        if("success".equals(result)) {
            return new ResponseEntity<>("Item created successfully", HttpStatus.CREATED); // HTTP 201: CREATED
        }

        return new ResponseEntity<>("Failed to add item", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> getItemByID(@PathVariable Integer id) {
        Item item = itemService.getItemByID(id);
        if(item == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // HTTP 404: NOT FOUND
        }
        return new ResponseEntity<>(item, HttpStatus.OK); // HTTP 200: OK
    }
    @GetMapping("/{id}/user")
    public ResponseEntity<User> getUserByItem(@PathVariable Integer id,
                                              @PathVariable Integer userID) {
        Item item = itemService.getItemByID(id);
        if(item == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // HTTP 404: NOT FOUND
        }
        return new ResponseEntity<>(item.getUser(), HttpStatus.OK); // HTTP 200: OK
    }

    @PostMapping("/user")
    public ResponseEntity<List> getItemByUser(@RequestParam String username) {
        List<Item> items = itemService.getAllItemByUser(userService.getUserByUsername(username));

        if(items == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // HTTP 404: NOT FOUND
        }
        return new ResponseEntity<>(items, HttpStatus.OK); // HTTP 200: OK
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

    @PostMapping("/search")
    public ResponseEntity<List<Item>> getItemByKeyName(@RequestParam String key) {
        List<Item> items = itemService.readItems();
        List<Item> keyNameItem = new ArrayList<Item>();

        for (Item i: items) {
            if (i.getName().toLowerCase().contains(key.toLowerCase())) {
                keyNameItem.add(i);
            }
        }
        if (keyNameItem.size() == 0) return new ResponseEntity<>(keyNameItem, HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(keyNameItem, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteItem(@RequestParam Integer itemId) {

        if (itemService.getItemByID(itemId) == null) {
            return new ResponseEntity<>("No Such Item to be Deleted", HttpStatus.NO_CONTENT);
        }
        if (itemService.deleteItemByID(itemId)) {
            return new ResponseEntity<>("Delete Successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Delete Fail", HttpStatus.BAD_REQUEST);
    }

}
