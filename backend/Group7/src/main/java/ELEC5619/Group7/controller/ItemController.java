package ELEC5619.Group7.controller;

import ELEC5619.Group7.service.ItemService;
import ELEC5619.Group7.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;


public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private UserService userService;

}
