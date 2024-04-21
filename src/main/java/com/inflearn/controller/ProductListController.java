package com.inflearn.controller;

import com.inflearn.entity.ProductEntity;
import com.inflearn.respository.ShoppingDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet({"/product/list"})
public class ProductListController extends HttpServlet {
    public ProductListController() {
    }

    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ShoppingDAO shopDAO = new ShoppingDAO();
        List<ProductEntity> list = shopDAO.productList();
        req.setAttribute("list", list);
        RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/productList.jsp");
        rd.forward(req, resp);
    }
}