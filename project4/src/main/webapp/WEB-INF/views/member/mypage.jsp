<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.101.0">
    <title>Pricing example · Bootstrap v4.6</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/4.6/examples/pricing/">

    
<%@include file="/WEB-INF/views/include/common.jsp" %>
    



    <!-- Favicons 
<link rel="apple-touch-icon" href="/docs/4.6/assets/img/favicons/apple-touch-icon.png" sizes="180x180">
<link rel="icon" href="/docs/4.6/assets/img/favicons/favicon-32x32.png" sizes="32x32" type="image/png">
<link rel="icon" href="/docs/4.6/assets/img/favicons/favicon-16x16.png" sizes="16x16" type="image/png">
<link rel="manifest" href="/docs/4.6/assets/img/favicons/manifest.json">
<link rel="mask-icon" href="/docs/4.6/assets/img/favicons/safari-pinned-tab.svg" color="#563d7c">
<link rel="icon" href="/docs/4.6/assets/img/favicons/favicon.ico">
<meta name="msapplication-config" content="/docs/4.6/assets/img/favicons/browserconfig.xml">
-->
<meta name="theme-color" content="#563d7c">


    <style>
      .bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
        -webkit-user-select: none;
        -moz-user-select: none;
        -ms-user-select: none;
        user-select: none;
      }

      @media (min-width: 768px) {
        .bd-placeholder-img-lg {
          font-size: 3.5rem;
        }
      }
    </style>

    <script>
      let msg = '${msg}';
      if(msg != '') {
        alert(msg);
      }

    </script>
    
    
  </head>
  <body>
    
<%@include file="/WEB-INF/views/include/header.jsp" %>



<div class="container">
  <h3>마이페이지</h3>
  <div class="mb-3 text-center">
		  <div class="row">
		    <label for="mem_id" class="col-sm-2 col-form-label">회원 가입일</label>
		    <div class="col-sm-5">
		      <span><fmt:formatDate value="${sessionScope.loginStatus.mem_date_sub }" pattern="yyyy-MM-dd hh:mm" />
		     </span>
		    </div>
		  </div>
		  <div class="row">
		    <label for="mem_pw" class="col-sm-2 col-form-label">최근 로그인</label>
		    <div class="col-sm-5">
		      <span><fmt:formatDate value="${sessionScope.loginStatus.mem_date_last }" pattern="yyyy-MM-dd hh:mm" /></span>
		    </div>
		  </div>
		  <div class="row">
		    <label for="mem_pw" class="col-sm-2 col-form-label">진행중 주문</label>
		    <div class="col-sm-5">
		      <span>${orderProcessCount} 개</span>
		    </div>
		  </div>
		  <div class="row">
		    <label for="mem_pw" class="col-sm-2 col-form-label">장바구니</label>
		    <div class="col-sm-5">
		      <span>${cartProductCount} 개</span>
		    </div>
		  </div>
		  <div class="row">
		    <label for="mem_pw" class="col-sm-2 col-form-label">총 주문금액</label>
		    <div class="col-sm-5">
		      <span>${odr_totalPrice }원 (배송완료 기준)</span>
		    </div>
		  </div>
		  <div class="row">
		    <label for="mem_pw" class="col-sm-2 col-form-label">사용가능 포인트</label>
		    <div class="col-sm-5">
		      <span>${sessionScope.loginStatus.mem_point }원</span>
		    </div>
		  </div>
  </div>


  <!--  footer.jsp -->
  <%@include file="/WEB-INF/views/include/footer.jsp" %>
</div>

  <script>

    $(document).ready(function() {
      $("#btnSearchIDPW").on("click", function() {
        location.href = "/member/lostpass";
      });
    });

  </script>
  </body>
</html>
