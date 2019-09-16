package store.system.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import store.system.model.Order;
import store.system.service.OrderService;

import java.util.List;

public class OrderDAO {

    private static final SessionFactory sessionFactory = HibernateFactory.getSessionFactory();

    public static void saveOrUpdate(Order order){
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.saveOrUpdate(order);
        session.getTransaction().commit();
        session.close();
    }

    public static Order create(Order order){
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        Integer id = (Integer) session.save(order);
        session.getTransaction().commit();
        session.close();
        return OrderService.findById(id);
    }

    public static Order findOne(Integer id){
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        Order ordersFromDB = session.find(Order.class, id);
        session.getTransaction().commit();
        session.close();
        return ordersFromDB;
    }

    public static Order findFirstByCartIdAndProductId(Integer cartId, Integer productId){
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        Query q = session.createNativeQuery("SELECT * FROM orders WHERE cart_cart_id=? AND item_id=?", Order.class);
        q.setParameter(1, cartId);
        q.setParameter(2, productId);
        List<Order> orderFromDB = q.getResultList();
        session.getTransaction().commit();
        session.close();
        return orderFromDB.get(0);
    }

    public static List<Order> findAll(){
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        String sql = "SELECT * FROM orders";
        List<Order> result = session.createNativeQuery(sql, Order.class).getResultList();
        return result;
    }

    public static List<Order> findAllOrdersByCartId(Integer cartId){
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        Query q = session.createNativeQuery("SELECT * FROM orders WHERE cart_cart_id=?", Order.class);
        q.setParameter(1, cartId);
        List<Order> orderFromDB = q.getResultList();
        session.close();
        if (orderFromDB.size() == 0){
            return null;
        } else {
            return orderFromDB;
        }
    }

    public static void delete(Order order){
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.delete(order);
        session.getTransaction().commit();
        session.close();
    }
}
