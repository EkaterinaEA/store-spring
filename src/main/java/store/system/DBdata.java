package store.system;

import store.system.model.Item;
import store.system.model.User;
import store.system.service.ItemService;
import store.system.service.UserService;

import java.util.List;

public class DBdata {

    public static void main(String[] args) {

        List<Item> items = ItemService.findAll();
        for (int i=0; i< items.size(); i++) {
            System.out.println(items.get(i).getName());
        }
    }

}
