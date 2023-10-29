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

}
