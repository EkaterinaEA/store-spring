package store.system.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import store.system.model.Cart;
import store.system.service.CartService;

import java.util.List;

public class CartDAO {

    private static final SessionFactory sessionFactory = HibernateFactory.getSessionFactory();

    public static void saveOrUpdate(Cart cart){
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.saveOrUpdate(cart);
        session.getTransaction().commit();
        session.close();
    }

    public static Cart create(Cart cart){
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        Integer id = (Integer) session.save(cart);
        cart.setId(id);
        session.getTransaction().commit();
        session.close();
        return cart;
    }

    public static boolean findClosedFalse(Integer userId){
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        Query q = session.createNativeQuery("SELECT * FROM carts WHERE user_id=?", Cart.class);
        q.setParameter(1, userId);
        boolean closed = true;
        List<Cart> carts = q.getResultList();
        for (int i=0; i<carts.size(); i++){
            if (carts.get(i).getClosed() == false){
                closed = false;
            };
        }
        return closed;
    }

    public static Cart findById(Integer id){
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        Cart cartFromDB = session.find(Cart.class, id);
        session.getTransaction().commit();
        session.close();
        return cartFromDB;
    }

    public static Cart getCartByUserId(Integer userId){
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        Query q = session.createNativeQuery("SELECT * FROM carts WHERE user_id=?", Cart.class);
        q.setParameter(1, userId);
        List<Cart> cartFromDB = q.getResultList();
        session.close();
        if (cartFromDB.size() == 0){
            return null;
        } else {
            return cartFromDB.get(0);
        }
    }

    public static List<Cart> findAll(){
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        String sql = "SELECT * FROM carts";
        List<Cart> result = session.createNativeQuery(sql, Cart.class).getResultList();
        return result;
    }

    public static void delete(Cart cart){
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.delete(cart);
        session.getTransaction().commit();
        session.close();
    }
}