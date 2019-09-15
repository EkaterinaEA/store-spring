package store.system.controller;

import store.system.model.Cart;
import store.system.model.Item;
import store.system.model.Order;
import store.system.service.CartService;
import store.system.service.ItemService;
import store.system.service.OrderService;
import store.system.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@WebServlet(name="CartServlet", urlPatterns="/cart")
public class CartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        final int userid = Integer.parseInt(req.getParameter("userId"));
        final int productIdDelete = Integer.parseInt(req.getParameter("productIdDelete"));
        final int cartId = Integer.parseInt(req.getParameter("cartId"));

        OrderService.delete(OrderService.findFirstByCartIdAndProductId(cartId, productIdDelete));

        Cart cart = CartService.getCartByUserId(userid);

        if (OrderService.findAllOrdersByCartId(cart.getId()).size() == 0) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/cart.jsp");
            req.setAttribute("cartempty", 1);
            dispatcher.forward(req, resp);
        } else {

            List<Order> orderlist = OrderService.findAllOrdersByCartId(cart.getId());
            List<Item> itemsfromcart = new ArrayList<>();

            for (int i = 0; i < orderlist.size(); i++) {
                itemsfromcart.add(ItemService.findById(orderlist.get(i).getItem().getId()));
            }
            RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/cart.jsp");
            req.setAttribute("itemsfromcart", itemsfromcart);
            req.setAttribute("itemsfromcartsize", itemsfromcart.size());
            req.setAttribute("orderlist", orderlist);
            req.setAttribute("userId", userid);
            req.setAttribute("cartId", cart.getId());
            req.setAttribute("totalPrice", ItemService.totalPrice(itemsfromcart));
            dispatcher.forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        final int userid = Integer.parseInt(req.getParameter("userId"));

        if (req.getParameter("productId") == null && (CartService.getCartByUserId(userid) == null
                || CartService.findClosedFalse(userid))) {

            Cart cart = new Cart();
            cart.setUser(UserService.findById(userid));
            cart.setClosed(false);
            cart.setCreationTime(new Date().getTime());
            Cart createdCart = CartService.create(cart);

            RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/cart.jsp");
            req.setAttribute("cartempty", 1);
            dispatcher.forward(req, resp);

        } else if (req.getParameter("productId") == null && (CartService.getCartByUserId(userid) != null
                && !CartService.findClosedFalse(userid))) {

            Cart cart = CartService.getCartByUserId(userid);

            if (OrderService.findAllOrdersByCartId(cart.getId()) == null) {
                RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/cart.jsp");
                req.setAttribute("cartempty", 1);
                dispatcher.forward(req, resp);
            } else {

                List<Order> orderlist = OrderService.findAllOrdersByCartId(cart.getId());
                List<Item> itemsfromcart = new ArrayList<>();

                for (int i = 0; i < orderlist.size(); i++) {
                    itemsfromcart.add(ItemService.findById(orderlist.get(i).getItem().getId()));
                }
                RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/cart.jsp");
                req.setAttribute("itemsfromcart", itemsfromcart);
                req.setAttribute("itemsfromcartsize", itemsfromcart.size());
                req.setAttribute("totalPrice", ItemService.totalPrice(itemsfromcart));
                req.setAttribute("orderlist", orderlist);
                req.setAttribute("userId", userid);
                req.setAttribute("cartId", cart.getId());
                dispatcher.forward(req, resp);
            }
        } else if ((req.getParameter("productId") != null)) {

            final int productid = Integer.parseInt(req.getParameter("productId"));

            final Item item = ItemService.findById(productid);

            if (CartService.getCartByUserId(userid) == null || CartService.findClosedFalse(userid)) {
                Cart cart = new Cart();
                cart.setUser(UserService.findById(userid));
                cart.setClosed(false);
                cart.setCreationTime(new Date().getTime());
                Cart createdcart = CartService.create(cart);

                Order order = new Order();
                order.setCart(CartService.findById(createdcart.getId()));
                order.setItem(ItemService.findById(productid));
                Order createdorder = OrderService.create(order);

                List<Order> orderlist = OrderService.findAllOrdersByCartId(createdcart.getId());
                List<Item> itemsfromcart = new ArrayList<>();

                for (int i = 0; i < orderlist.size(); i++) {
                    itemsfromcart.add(ItemService.findById(orderlist.get(i).getItem().getId()));
                }

                RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/cart.jsp");
                req.setAttribute("orderlist", orderlist);
                req.setAttribute("userId", userid);
                req.setAttribute("itemsfromcart", itemsfromcart);
                req.setAttribute("itemsfromcartsize", itemsfromcart.size());
                req.setAttribute("totalPrice", ItemService.totalPrice(itemsfromcart));
                req.setAttribute("cartId", createdcart.getId());
                dispatcher.forward(req, resp);

            } else if (CartService.getCartByUserId(userid) != null && !CartService.findClosedFalse(userid)) {

                final Cart cart = CartService.getCartByUserId(userid);

                Order order = new Order();
                order.setCart(CartService.findById(cart.getId()));
                order.setItem(ItemService.findById(productid));
                Order createdorder = OrderService.create(order);

                List<Order> orderlist = OrderService.findAllOrdersByCartId(cart.getId());

                List<Item> itemsfromcart = new ArrayList<>();

                for (int i = 0; i < orderlist.size(); i++) {
                    itemsfromcart.add(ItemService.findById(orderlist.get(i).getItem().getId()));
                }

                RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/cart.jsp");
                req.setAttribute("orderlist", orderlist);
                req.setAttribute("userId", userid);
                req.setAttribute("itemsfromcart", itemsfromcart);
                req.setAttribute("itemsfromcartsize", itemsfromcart.size());
                req.setAttribute("totalPrice", ItemService.totalPrice(itemsfromcart));
                req.setAttribute("cartId", cart.getId());
                dispatcher.forward(req, resp);

            }
        }
    }
}
