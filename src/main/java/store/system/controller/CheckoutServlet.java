package store.system.controller;

import store.system.model.Cart;
import store.system.service.CartService;
import store.system.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name="CheckoutServlet", urlPatterns="/checkout")
public class CheckoutServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        final int cartid = Integer.parseInt(req.getParameter("cartId"));

        Cart cart = CartService.findById(cartid);
        cart.setClosed(true);
        CartService.saveOrUpdate(cart);

        final int userid = Integer.parseInt(req.getParameter("userId"));
        final int totalprice = Integer.parseInt(req.getParameter("totalPrice"));

        RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/checkout.jsp");
        req.setAttribute("userName", UserService.findById(Integer.parseInt(req.getParameter("userId"))).getName());
        req.setAttribute("totalPrice", totalprice);
        req.setAttribute("cartId", cartid);
        dispatcher.forward(req, resp);
    }
}