package store.system.service;


import store.system.dao.CartDAO;
import store.system.model.Cart;

import java.util.List;

public class CartService {

    public CartService() {
    }

    public static void saveOrUpdate(Cart cart){
        CartDAO.saveOrUpdate(cart);
    }

    public static Cart findById(Integer id){
        return new CartDAO().findById(id);
    }

    public static List<Cart> findAll(){
        return CartDAO.findAll();
    }

    public static void delete(Cart cart){
        CartDAO.delete(cart);
    }

    public static Cart getCartByUserId(Integer userId){
        return CartDAO.getCartByUserId(userId);
    }

    //  public static Cart create(Cart cart){

    public static boolean findClosedFalse(Integer userId){
        return CartDAO.findClosedFalse(userId);
    }

    public static Cart create(Cart cart){
        return CartDAO.create(cart);
    }
}
