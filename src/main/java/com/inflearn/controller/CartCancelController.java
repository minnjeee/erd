package com.inflearn.controller;

import com.inflearn.entity.OrderEntity;
import com.inflearn.respository.ShoppingDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet({"/cartCancel"})
public class CartCancelController extends HttpServlet {
    private static Logger logger = LogManager.getLogger("CartCancelController.class");


    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int order_id = Integer.parseInt(req.getParameter("order_id"));
        String customer_id = req.getParameter("customer_id");
        ShoppingDAO shopDAO = new ShoppingDAO();
        shopDAO.cartCancel(order_id);
        resp.sendRedirect("/shopping/cartList?customer_id=" + customer_id);
    }
}
