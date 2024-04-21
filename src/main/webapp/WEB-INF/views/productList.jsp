<%@ page contentType="text/html;charset=UTF-8" language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="contextPath" value="${pageContext.request.contextPath }" />

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Shopping Mall</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        function goCartList(){
            if(false){
                alert('로그인을 하세요');
                return false;
            } else {
                location.href='${contextPath}/cartList?customer_id=${customer.customer_id}';
            }
        }

        function goAddCart(product_id){
            if(false){
                alert('로그인을 하세요');
                return false;
            } else {
                (jQuery).ajax({
                    url : '${contextPath}/cartAdd',
                    type: 'post',
                    data: {'customer_id': '${customer.customer_id}', 'product_id': product_id},
                    success: cartAdd,
                    error: function(){alert('저장에 실패했습니다.');}
                })
            }
        }

        function cartAdd(result){
            console.log(result);
            alert('Added to Cart!');
        }
    </script>

</head>
<body>
    <div class="container pt-5">
        <h2>MVC 기반 ShoppingMall</h2>
        <div class="card">
            <div class="card-header">
                <div class="container text-center">
                    <div class="row">
                        <c:if test="${!empty customer}">
                            <div class="col-8">Welcome, <b>${customer.customer_id}</b> Rewards : <b>${customer.customer_reserves}</b></div>
                            <div class="col">
                                <form class="form-inline" method="get" action="/shopping/customer/logout">
                                    <button class="btn btn-primary">logout</button>
                                </form>
                            </div>
                        </c:if>
                        <c:if test="${empty customer}">
                            <div class="col">Welcome, <b>Guests</b> Rewards : <b>0</b></div>
                            <div class="col-8">
                                <form class="form-inline" method="get" action="${contextPath}/customer/login">
                                    <label for="customer_id">id : </label>
                                    <input type="text" name="customer_id" id="customer_id" />
                                    <label for="customer_pwd">password : </label>
                                    <input type="password" name="customer_pwd" id="customer_pwd" />
                                    <button class="btn btn-primary">login</button>
                                </form>
                            </div>
                        </c:if>
                    </div>
                </div>
            </div>
            <div class="card-body">
                <div class="row">
                    <div class="col text-right">
                        <button type="button" onclick="goCartList()" class="btn btn-sm btn-danger">My Cart List </button>
                    </div>
                </div>
                <h2>Product List</h2>
                <table class="table table-bordered table-hover">
                    <thead>
                        <tr>
                            <th scope="col">제품 번호</th>
                            <th scope="col">제품명</th>
                            <th scope="col">재고량</th>
                            <th scope="col">단가</th>
                            <th scope="col">제조업체</th>
                            <th scope="col">주문</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="product" items="${list}">
                            <tr>
                                <td>${product.product_id}</td>
                                <td>${product.product_name}</td>
                                <td>${product.product_price}</td>
                                <td><fmt:formatNumber value="${product.product_id}" />원</td>
                                <td>${product.product_manufacturer}</td>
                                <td class="text-center">
                                    <button type="button" onclick="goAddCart(${product.product_id})" class="btn btn-sm btn-primary">add to cart</button>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="card-footer">[7일 완성]생각하는 데이터베이스 모델링_박매일</div>
        </div>
    </div>



</body>
</html>

