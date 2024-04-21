package com.inflearn.controller;

import com.inflearn.entity.CartEntity;
import com.inflearn.entity.ProductEntity;
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
import java.util.Iterator;
import java.util.List;

@WebServlet({"/cartList"})
public class CartListController extends HttpServlet {
    private static Logger logger = LogManager.getLogger("CartListController.class");

    public CartListController() {
    }

    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String customer_id = req.getParameter("customer_id");
        ShoppingDAO shopDAO = new ShoppingDAO();
        List<CartEntity> list = shopDAO.cartList(customer_id);
        req.setAttribute("list", list);
        int totalAmount = 0;

        for(CartEntity cart : list ) {
            totalAmount += cart.getAmount();
        }

        req.setAttribute("total", totalAmount);
        RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/cartList.jsp");
        rd.forward(req, resp);
    }
}
