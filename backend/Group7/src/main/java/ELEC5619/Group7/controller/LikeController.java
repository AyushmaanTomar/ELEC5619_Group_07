package ELEC5619.Group7.controller;


import ELEC5619.Group7.entity.Item;

import ELEC5619.Group7.repository.ItemRepository;
import ELEC5619.Group7.repository.UserRepository;
import ELEC5619.Group7.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/likes")
@CrossOrigin(origins = "http://localhost:3000")
public class LikeController {

    @Autowired
    private LikeService likeService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ItemRepository itemRepository;

    /**
     * All users_id with the item_id associated
     **/
    @GetMapping("/users/byItem/{itemId}")
    public ResponseEntity<Object> getAllUserIDWithItemID(@PathVariable Integer itemId) {
        Optional<Item> optionalItem = itemRepository.findById(itemId);
        if (!optionalItem.isPresent()) {
            return new ResponseEntity<>("No item found for the given item ID.", HttpStatus.NOT_FOUND);
        }
        List<Integer> userIds = likeService.getAllUserIDWithItemID(itemRepository.getById(itemId));

        if (userIds.isEmpty()) {
            return new ResponseEntity<>("No users found for the given item ID.", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userIds, HttpStatus.OK);
    }

    /**
     * All item_ids with the user_id associated
     **/
    @GetMapping("/items/byUser/{userId}")
    public ResponseEntity<Object> getAllItemIDWithUserID(@PathVariable Integer userId) {
        List<Integer> itemIds = likeService.getAllItemIDWithUserID(userRepository.getById(userId));
        if (itemIds.isEmpty()) {
            return new ResponseEntity<>("No items found for the given user ID.", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(itemIds, HttpStatus.OK);
    }

    /**
     * User likes an item
     **/
    @PostMapping("/like")
    public ResponseEntity<String> likeItemByUser(@RequestParam Integer itemId, @RequestParam Integer userId) {
        try {
            likeService.likeItemByUser(itemRepository.getById(itemId), userRepository.getById(userId));
            return new ResponseEntity<>("Item liked successfully.", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error liking the item.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * User unlikes an item
     **/
    @DeleteMapping("/unlike")
    public ResponseEntity<String> unlikeItemByUser(@RequestParam Integer itemId, @RequestParam Integer userId) {
        try {
            likeService.unlikeItemByUser(itemId, userId);
            return new ResponseEntity<>("Item unliked successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error unliking the item.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Check if user liked a specific item
     **/
    @GetMapping("/hasLiked")
    public ResponseEntity<String> hasUserLikedItem(@RequestParam Integer itemId, @RequestParam Integer userId) {
        boolean hasLiked = likeService.hasUserLikedItem(userRepository.getById(userId), itemRepository.getById(itemId));
        if (hasLiked) {
            return new ResponseEntity<>("User has liked the item.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User has not liked the item.", HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Total of users liking item
     **/
    @GetMapping("/count/byItem/{itemId}")
    public ResponseEntity<Object> getTotalLikesForItem(@PathVariable Integer itemId) {
        int totalLikes = likeService.getTotalLikesForItem(itemRepository.getById(itemId));
        if (totalLikes == 0) {
            return new ResponseEntity<>("No likes found for the given item ID.", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(totalLikes, HttpStatus.OK);
    }

    /**
     * Total of items likes by user
     **/
    @GetMapping("/count/byUser/{userId}")
    public ResponseEntity<Object> getTotalItemsLikedByUser(@PathVariable Integer userId) {
        int totalItemsLiked = likeService.getTotalItemsLikedByUser(userRepository.getById(userId));
        if (totalItemsLiked == 0) {
            return new ResponseEntity<>("The user has not liked any items.", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(totalItemsLiked, HttpStatus.OK);
    }
}
