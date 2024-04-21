package com.inflearn.respository;

import com.inflearn.entity.CartEntity;
import com.inflearn.entity.CustomerEntity;
import com.inflearn.entity.OrderEntity;
import com.inflearn.entity.ProductEntity;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

public class ShoppingDAO {
    private static SqlSessionFactory sqlSessionFactory;
    static {
        try {
            String resource = "mybatis-config/config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = (new SqlSessionFactoryBuilder()).build(inputStream);
        } catch (Exception var2) {
            Exception e = var2;
            e.printStackTrace();
        }

    }
    public ShoppingDAO() {
    }

    public List<ProductEntity> productList() {
        SqlSession session = sqlSessionFactory.openSession();
        List<ProductEntity> list = session.selectList("productList");
        session.close();
        return list;
    }

    public CustomerEntity customerLogin(CustomerEntity customer) {
        SqlSession session = sqlSessionFactory.openSession();
        CustomerEntity cus = (CustomerEntity)session.selectOne("customerLogin", customer);
        session.close();
        return cus;
    }

    public int cartAdd(OrderEntity order) {
        SqlSession session = sqlSessionFactory.openSession();
        OrderEntity chk = (OrderEntity)session.selectOne("checkAdd", order);
        int result;
        if (chk != null) {
            result = session.update("cartUpdate", order);
        } else {
            result = session.insert("cartAdd", order);
        }

        session.commit();
        session.close();
        return result;
    }

    public List<CartEntity> cartList(String customerId) {
        SqlSession session = sqlSessionFactory.openSession();
        List<CartEntity> list = session.selectList("cartList", customerId);
        session.close();
        return list;
    }

    public int cartCancel(int orderId) {
        SqlSession session = sqlSessionFactory.openSession();
        int result = session.delete("cartCancel", orderId);
        session.commit();
        session.close();
        return result;
    }

    public int cartEmpty(String customerId) {
        SqlSession session = sqlSessionFactory.openSession();
        int result = session.delete("cartEmpty", customerId);
        session.commit();
        session.close();
        return result;
    }

    public int pointSave(CustomerEntity customer) {
        SqlSession session = sqlSessionFactory.openSession();
        int result = session.update("pointSave", customer);
        session.commit();
        session.close();
        return result;
    }

    public int cartQty(OrderEntity order) {
        SqlSession session = sqlSessionFactory.openSession();
        int result = session.update("qtyUpdate", order);
        session.commit();
        session.close();
        return result;
    }
}