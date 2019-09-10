package store.system.service;

import store.system.dao.ItemDAO;
import store.system.model.Item;

import java.util.List;

public class ItemService {

    public ItemService() {
    }

    public static void saveOrUpdate(Item item){
        ItemDAO.saveOrUpdate(item);
    }

    public static Item findById(Integer id){
        return new ItemDAO().findById(id);
    }

    public static List<Item> findAll(){
        return ItemDAO.findAll();
    }

    public static void delete(Item item){
        ItemDAO.delete(item);
    }

    public static Integer totalPrice(List<Item> items){
        Integer totalPrice = 0;
        for (int i = 0; i<items.size(); i++){
            totalPrice += items.get(i).getPrice();
        }
        return totalPrice;
    }
}

