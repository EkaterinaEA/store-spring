package store.system.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import store.system.model.Cart;
import store.system.model.User;
import store.system.service.CartService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CartDAOTest {

    private static final SessionFactory sessionFactory = HibernateFactory.getSessionFactory();
    private static User user = new User("test", "test@gmail.com", "test");
    private static Cart cart = null;
    private static List<Cart> dataFromDB = CartService.findAll();

    @BeforeAll
    static void setUpBeforeClass(){

        for (int i=0; i<dataFromDB.size(); i++) {
            CartService.delete(dataFromDB.get(i));
        }
    }

    @Test
    void testCreateAndDelete(){
        Session session = sessionFactory.openSession();
        Cart cart = new Cart();
        List<Cart> testData = new ArrayList<>();
        cart.setUser(user);
        cart.setClosed(false);
        cart.setCreationTime(System.currentTimeMillis());

        CartDAO.create(cart);

        session.getTransaction().begin();
        testData = session.createNativeQuery("SELECT * FROM carts", Cart.class).getResultList();

        assertEquals(1, testData.size());
        assertEquals(cart.getUser(), testData.get(0).getUser());
        assertEquals(cart.getClosed(), testData.get(0).getClosed());
        assertEquals(cart.getCreationTime(), testData.get(0).getCreationTime());

        CartDAO.delete(cart);

        testData = session.createNativeQuery("SELECT * FROM carts", Cart.class).getResultList();
        session.close();

        assertEquals(0, testData.size());

    }

    @AfterAll
    static void tearDownAfterClass(){

        for (int i=0; i<dataFromDB.size(); i++) {
            CartDAO.create(dataFromDB.get(i));
        }
    }

}
