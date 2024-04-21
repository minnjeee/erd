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
        function goCancel(order_id){
            location.href='${contextPath}/cartCancel?order_id='+order_id+'&customer_id=${customer.customer_id}';
        }

        function goOrder(){
            if(false){
                alert('로그인을 하세요.');
            } else{
                alert('주문이 완료되었습니다.');
                (jQuery).ajax({
                    url: '${contextPath}/cartEmpty',
                    type: 'get',
                    data: {'customer_id': '${customer.customer_id}', 'total': ${total}},
                    success: function(data){
                        alert('장바구니를 비웠습니다.');
                        location.href='${contextPath}/cartList?customer_id=${customer.customer_id}';
                    },
                    error: function(){ alert('에러 발생'); }
                });
            }
        }

        function goQty(order_id){
            //alert($('#order_qty'+order_id).val());
            var qty = $('#order_qty'+order_id).val();
            $.ajax({
                url: '${contextPath}/cartQty',
                type: 'post',
                data: {'order_id': order_id, 'order_qty': qty},
                success: function(data){
                    location.href='${contextPath}/cartList?customer_id=${customer.customer_id}';
                },
                error: function(){ alert('에러 발생'); }
            })
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
                        <button type="button" onclick="goOrder()" class="btn btn-sm btn-danger">주문하기</button>
                    </div>
                </div>
                <h2>Cart List</h2>
                <table class="table table-bordered table-hover">
                    <thead>
                        <tr>
                            <th scope="col">제품 번호</th>
                            <th scope="col">제품명</th>
                            <th scope="col">수량</th>
                            <th scope="col">가격</th>
                            <th scope="col">금액</th>
                            <th scope="col">취소</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:if test="${fn:length(list)==0}">
                            <tr><td colspan="6" class="text-center">장바구니가 비었습니다.</td></tr>
                        </c:if>
                        <c:forEach var="cart" items="${list}">
                            <tr>
                                <td>${cart.product_id}</td>
                                <td>${cart.product_name}</td>
                                <td>${cart.order_qty}</td>
                                <td><fmt:formatNumber value="${cart.product_price}" />원</td>
                                <td>${cart.amount}</td>
                                <td class="text-center">
                                    <button type="button" onclick="goCancel(${cart.order_id})" class="btn btn-sm btn-primary">Cancel</button>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <div class="row">
                    <div class="col text-right">
                        Total Amount : <fmt:formatNumber value="${total}" />원
                    </div>
                </div>
                <div class="row">
                    <div class="col text-right">
                        <button type="button" onclick="location.href='${contextPath}/product/list'" class="btn btn-sm btn-primary">Continous Shopping</button>
                    </div>
                </div>
            </div>
            <div class="card-footer">[7일 완성]생각하는 데이터베이스 모델링_박매일</div>
        </div>
    </div>

</body>
</html>


