<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom shadow-sm">
  <h5 class="my-0 mr-md-auto font-weight-normal" >Mall : ${sessionScope.loginStatus.mem_nick }</h5>
  <nav class="my-2 my-md-0 mr-md-3">
    
    <!-- 로그인 이전 상태표시 -->
    <c:if test="${sessionScope.loginStatus == null }">
    <a class="p-2 text-dark" href="/member/login">LOGIN</a>
    <a class="p-2 text-dark" href="/member/join">JOIN</a>
    </c:if>
    
    <!-- 로그인 이후 상태표시 -->
    <c:if test="${sessionScope.loginStatus != null }">
    <a class="p-2 text-dark" href="/member/logout">LOGOUT</a>
    <a class="p-2 text-dark" href="/member/confirmPW">MODIFY</a>
    </c:if>
    <a class="p-2 text-dark" href="/">MAIN</a>
    <a class="p-2 text-dark" href="/order/orderList">ORDER</a>
    <a class="p-2 text-dark" href="/member/mypage">MYPAGE</a>
    <a class="p-2 text-dark" href="/cart/cart_list">CART</a>
  </nav>
</div>