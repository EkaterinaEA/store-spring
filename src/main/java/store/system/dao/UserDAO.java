package store.system.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import store.system.model.User;

import java.util.List;

public class UserDAO {

    private static final SessionFactory sessionFactory = HibernateFactory.getSessionFactory();

    public static User save(User user){
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        Integer id = (Integer) session.save(user);
        user.setId(id);
        session.getTransaction().commit();
        session.close();
        return user;
    }

    public static User update(User user){
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.getTransaction().commit();
        session.close();
        user.setId(findByNameAndPassword(user.getName(), user.getPassword()).getId());
        return user;
    }

    public static User findById(Integer id){
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        User userFromDB = session.find(User.class, id);
        session.getTransaction().commit();
        session.close();
        return userFromDB;
    }

    public static User findByNameAndPassword(String name, String password){
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        Query q = session.createNativeQuery("SELECT * FROM users WHERE name=? AND password=?", User.class);
        q.setParameter(1, name);
        q.setParameter(2, password);
        List<User> usersFromDB = q.getResultList();
        session.getTransaction().commit();
        session.close();
        return usersFromDB.get(0);
    }

    public static List<User> findAll(){
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        String sql = "SELECT * FROM users";
        List<User> result = session.createNativeQuery(sql).getResultList();
        return result;
    }

    public static void delete(User user){
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.delete(user);
        session.getTransaction().commit();
        session.close();
    }

}
