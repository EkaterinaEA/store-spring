package store.system;

import store.system.dao.CartDAO;
import store.system.model.Cart;
import store.system.model.Item;
import store.system.model.User;
import store.system.service.CartService;
import store.system.service.ItemService;
import store.system.service.UserService;

import java.util.List;

public class DBdata {

    public static void main(String[] args) {

        List<Cart> dataFromDB = CartService.findAll();

        for (int i=0; i< dataFromDB.size(); i++) {
            System.out.println(dataFromDB.get(i).getId());
            System.out.println(dataFromDB.get(i).getClosed());
            System.out.println(dataFromDB.get(i).getCreationTime());
            System.out.println(dataFromDB.get(i).getUser());
            System.out.println(dataFromDB.get(i).getUser().getName());
        }

        for (int i=0; i<dataFromDB.size(); i++) {
            CartService.delete(dataFromDB.get(i));
        }

        for (int i=0; i<dataFromDB.size(); i++) {
            CartDAO.create(dataFromDB.get(i));
        }

    }

}
