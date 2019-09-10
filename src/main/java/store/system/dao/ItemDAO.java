package store.system.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import store.system.model.Item;

import java.util.List;

public class ItemDAO {

    private static final SessionFactory sessionFactory = HibernateFactory.getSessionFactory();

    public static void saveOrUpdate(Item item){
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.saveOrUpdate(item);
        session.getTransaction().commit();
        session.close();
    }

    public static Item findById(Integer id){
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        Item itemFromDB = (Item) session.find(Item.class, id);
        session.getTransaction().commit();
        session.close();
        return itemFromDB;
    }

    public static List<Item> findAll(){
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        String sql = "SELECT * FROM products";
        List<Item> result = session.createNativeQuery(sql).getResultList();
        return result;
    }

    public static void delete(Item item){
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.delete(item);
        session.getTransaction().commit();
        session.close();
    }
}
