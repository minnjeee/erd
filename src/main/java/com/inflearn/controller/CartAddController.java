package com.inflearn.controller;

import com.inflearn.entity.CartEntity;
import com.inflearn.entity.OrderEntity;
import com.inflearn.respository.ShoppingDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet({"/cartAdd"})
public class CartAddController extends HttpServlet {
    private static Logger logger = LogManager.getLogger("CartAddController.class");

    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String customer_id = req.getParameter("customer_id");
        int product_id = Integer.parseInt(req.getParameter("product_id"));

        OrderEntity order = new OrderEntity();
        order.setCustomer_id(customer_id);
        order.setProduct_id(product_id);
        logger.debug(order);

        ShoppingDAO shopDAO = new ShoppingDAO();
        int result = shopDAO.cartAdd(order);
        PrintWriter out = resp.getWriter();
        out.println(result);
    }
}