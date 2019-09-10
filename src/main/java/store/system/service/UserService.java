package store.system.service;

import store.system.dao.UserDAO;
import store.system.model.User;

import java.util.List;

public class UserService {

    public UserService() {
    }

    public static User save(User user){
        return UserDAO.save(user);
    }

    public static User update(User user){
        return UserDAO.update(user);
    }

    public static User findById(Integer id){
        return new UserDAO().findById(id);
    }

    public static List<User> findAll(){
        return UserDAO.findAll();
    }

    public static void delete(User user){
        UserDAO.delete(user);
    }

    public static User findByNameAndPassword(String name, String password){
        return UserDAO.findByNameAndPassword(name, password);
    }

}
