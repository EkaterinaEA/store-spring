package store.system.dao;


import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import store.system.model.Cart;
import store.system.model.Item;
import store.system.model.Order;
import store.system.model.User;


public class HibernateFactory {

    private static SessionFactory sessionFactory;

    private HibernateFactory() {
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Class.forName("org.postgresql.Driver");
                Configuration configuration = new Configuration();
                Properties properties = new Properties();

                properties.setProperty("hibernate.connection.driverClassName", "org.postgresql.Driver");
                properties.setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5432/store");
                properties.setProperty("hibernate.connection.username", "postgres");
                properties.setProperty("hibernate.connection.password", "89520290819");
                properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
                properties.setProperty("hibernate.show_sql", "true");
                properties.setProperty("hibernate.format_sql", "true");
                properties.setProperty("hibernate.hbm2ddl.auto", "update");
                configuration.addProperties(properties);
                configuration.addAnnotatedClass(User.class);
                configuration.addAnnotatedClass(Item.class);
                configuration.addAnnotatedClass(Cart.class);
                configuration.addAnnotatedClass(Order.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}
