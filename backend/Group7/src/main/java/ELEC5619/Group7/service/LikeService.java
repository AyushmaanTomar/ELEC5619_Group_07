package ELEC5619.Group7.service;

import ELEC5619.Group7.entity.Item;
import ELEC5619.Group7.entity.LikeTo;
import ELEC5619.Group7.entity.User;
import ELEC5619.Group7.repository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LikeService {

    @Autowired
    private LikeRepository likeRepository;

    public List<Integer> getAllUserIDWithItemID(Item item) {
        return likeRepository.getUserIDWithItem(item.getId());
    }

    public List<Integer> getAllItemIDWithUserID(User user) {
        return likeRepository.getItemIDWithUser(user.getId());
    }

    public void likeItemByUser(Item item, User user) {
        LikeTo likeTo = new LikeTo();
        likeTo.setItem(item);
        likeTo.setUser(user);
        likeTo.setLike(likeRepository.findMaxId() + 1); // Setting the next available ID
        likeRepository.save(likeTo);
    }

    public void unlikeItemByUser(Item item, User user) {
        Optional<LikeTo> optionalLikeTo = likeRepository.findById(item.getId());
        optionalLikeTo.ifPresent(likeRepository::delete);
    }

    public boolean hasUserLikedItem(User user, Item item) {
        List<Integer> likedItems = likeRepository.getItemIDWithUser(user.getId());
        return likedItems.contains(item.getId());
    }

    public int getTotalLikesForItem(Item item) {
        return likeRepository.getUserIDWithItem(item.getId()).size();
    }

    public int getTotalItemsLikedByUser(User user) {
        return likeRepository.getItemIDWithUser(user.getId()).size();
    }
}