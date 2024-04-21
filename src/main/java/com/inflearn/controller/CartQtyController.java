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

@WebServlet({"/cartQty"})
public class CartQtyController extends HttpServlet {
    private static Logger logger = LogManager.getLogger("CartQtyController.class");

    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("/cartQty 실행");
        int order_id = Integer.parseInt(req.getParameter("order_id"));
        int order_qty = Integer.parseInt(req.getParameter("order_qty"));

        OrderEntity order = new OrderEntity();
        order.setOrder_id(order_id);
        order.setOrder_qty(order_qty);

        ShoppingDAO shopDAO = new ShoppingDAO();
        int result = shopDAO.cartQty(order);

        PrintWriter out = resp.getWriter();
        out.println(result);
    }
}
