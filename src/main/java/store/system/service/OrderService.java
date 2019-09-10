package store.system.service;

import store.system.dao.OrderDAO;
import store.system.model.Order;

import java.util.List;

public class OrderService {

    public OrderService() {
    }

    public static void saveOrUpdate(Order order){
        OrderDAO.saveOrUpdate(order);
    }

    public static Order findById(Integer id){
        return new OrderDAO().findOne(id);
    }

    public static List<Order> findAll(){
        return OrderDAO.findAll();
    }

    public static void delete(Order order){
        OrderDAO.delete(order);
    }

    public static Order findFirstByCartIdAndProductId(Integer cartId, Integer productId){
        return OrderDAO.findFirstByCartIdAndProductId(cartId, productId);
    }

    public static Order create(Order order){
        return OrderDAO.create(order);
    }

    public static List<Order> findAllOrdersByCartId(Integer cartId){
        return OrderDAO.findAllOrdersByCartId(cartId);
    }
}
