package store.system.controller;

import store.system.model.Item;
import store.system.model.User;
import store.system.service.ItemService;
import store.system.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


@WebServlet(name="AuthServlet", urlPatterns="/auth")
public class AuthServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.setCharacterEncoding("UTF-8");

        String login = req.getParameter("login");
        String password = req.getParameter("password");
        if (login == null || password == null)
        {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/login.jsp");
            dispatcher.forward(req, resp);
        }
        else{

            User user = UserService.findByNameAndPassword(login, password);

            if (user != null && user.getPassword().equals(password)){
                RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/catalog.jsp");
                HttpSession session = req.getSession();

                List<Item> items = ItemService.findAll();

                session.setAttribute("userName", String.format(user.getName()));
                session.setAttribute("items", items);
                session.setAttribute("userId", user.getId());
                dispatcher.forward(req, resp);
            }
            else{
                RequestDispatcher dispatcher = req.getRequestDispatcher("/login.jsp");
                dispatcher.forward(req, resp);
            }
        }
    }
}