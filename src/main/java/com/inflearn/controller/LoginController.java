package com.inflearn.controller;

import com.inflearn.entity.CustomerEntity;
import com.inflearn.entity.OrderEntity;
import com.inflearn.respository.ShoppingDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet({"/customer/login"})
public class LoginController extends HttpServlet {
    private static Logger logger = LogManager.getLogger("LoginController.class");

    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String customer_id = req.getParameter("customer_id");
        String customer_pwd = req.getParameter("customer_pwd");
        logger.debug(customer_id);

        CustomerEntity customer = new CustomerEntity();
        customer.setCustomer_id(customer_id);
        customer.setCustomer_pwd(customer_pwd);
        logger.debug(customer);

        ShoppingDAO shopDAO = new ShoppingDAO();
        CustomerEntity cus = shopDAO.customerLogin(customer);
        if (cus != null) {
            logger.debug("login 성공");
            HttpSession httpSession = req.getSession();
            httpSession.setAttribute("customer", cus);
        }

        resp.sendRedirect("/shopping/product/list");
    }
}
