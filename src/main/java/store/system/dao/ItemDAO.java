package store.system.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
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

    public static Item create(Item item){
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        Integer id = (Integer) session.save(item);
        item.setId(id);
        session.getTransaction().commit();
        session.close();
        return item;
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
        Query q = session.createNativeQuery("SELECT * FROM products", Item.class);
        List<Item> result = q.getResultList();
        session.close();
        if (result.size() == 0){
            return null;
        } else {
            return result;
        }
    }

    public static void delete(Item item){
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.delete(item);
        session.getTransaction().commit();
        session.close();
    }
}
