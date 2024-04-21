package com.inflearn.controller;

import com.inflearn.entity.CustomerEntity;
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

@WebServlet({"/cartEmpty"})
public class CartEmptyController extends HttpServlet {
    private static Logger logger = LogManager.getLogger("CartEmptyController.class");


    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String customer_id = req.getParameter("customer_id");
        int total = Integer.parseInt(req.getParameter("total"));
        int point = (int)((double)total * 0.1);

        ShoppingDAO shopDAO = new ShoppingDAO();

        // 장바구니 비우기
        int result = shopDAO.cartEmpty(customer_id);

        // 적립금 업데이트
        CustomerEntity customer = new CustomerEntity();
        customer.setCustomer_id(customer_id);
        customer.setCustomer_reserves(point);
        shopDAO.pointSave(customer);
        logger.debug("*************" + customer);
        int updatedPoint = customer.getCustomer_reserves();
        logger.debug("*************" + String.valueOf(updatedPoint));

        // 헤더에도 반영하기 위해 session의 custoemr 객체 반영하기
        HttpSession session = req.getSession();
        CustomerEntity cus = (CustomerEntity)session.getAttribute("customer");
        cus.setCustomer_reserves(updatedPoint);
        session.setAttribute("customer", cus);

        PrintWriter out = resp.getWriter();
        out.println(result);
    }
}
