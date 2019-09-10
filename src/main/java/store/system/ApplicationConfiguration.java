package store.system;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import store.system.dao.*;
import store.system.service.CartService;
import store.system.service.ItemService;
import store.system.service.OrderService;
import store.system.service.UserService;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public HibernateFactory hibernateFactory(){
        return new HibernateFactory();
    }

    @Bean
    public CartDAO cartDAO(){
        return new CartDAO();
    }

    @Bean
    public ItemDAO itemDAO(){
        return new ItemDAO();
    }

    @Bean
    public OrderDAO orderDAO(){
        return new OrderDAO();
    }

    @Bean
    public UserDAO userDAO(){
        return new UserDAO();
    }

    @Bean
    public CartService cartService(){
        return new CartService();
    }

    @Bean
    public ItemService itemService(){
        return new ItemService();
    }

    @Bean
    public OrderService orderService(){
        return new OrderService();
    }

    @Bean
    public UserService userService(){
        return new UserService();
    }

}
