package ELEC5619.Group7.service;


import ELEC5619.Group7.entity.Item;
import ELEC5619.Group7.entity.User;
import ELEC5619.Group7.repository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}

