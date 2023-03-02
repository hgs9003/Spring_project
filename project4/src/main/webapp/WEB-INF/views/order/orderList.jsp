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
  



    <!-- Favicons -->
<link rel="apple-touch-icon" href="/docs/4.6/assets/img/favicons/apple-touch-icon.png" sizes="180x180">
<link rel="icon" href="/docs/4.6/assets/img/favicons/favicon-32x32.png" sizes="32x32" type="image/png">
<link rel="icon" href="/docs/4.6/assets/img/favicons/favicon-16x16.png" sizes="16x16" type="image/png">
<link rel="manifest" href="/docs/4.6/assets/img/favicons/manifest.json">
<link rel="mask-icon" href="/docs/4.6/assets/img/favicons/safari-pinned-tab.svg" color="#563d7c">
<link rel="icon" href="/docs/4.6/assets/img/favicons/favicon.ico">
<meta name="msapplication-config" content="/docs/4.6/assets/img/favicons/browserconfig.xml">
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
<%@include file="/WEB-INF/views/include/categoryMenu.jsp" %>

<div class="pricing-header px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">
  <h1 class="display-4">${cate_name }</h1>
</div>

<div class="container">
  
  <div class="row">
  	<div class="col-md-12">
  		<div class="box">
			<div class="box-header">
				<h3 class="box-title">주문내역</h3>
			</div>

			<div class="box-body no-padding">
				<table class="table table-hover">
	  <thead>
	    <tr>
          <th scope="col">주문번호</th>
	      <th scope="col">주문일시</th>
	      <th scope="col">주문자/수령인</th>
	      <th scope="col">주문금액/배송비</th>
	      <th scope="col">결제상태</th>
	      <th scope="col">주문상태</th>
	      <th scope="col">상세보기</th>
	    </tr>
	  </thead>
	  <tbody>
	    <c:forEach items="${orderList }" var="orderVO">
	    <!-- BoardVO클래스의 필드명으로 코딩했지만, 호출은 getter메서드가 사용됨. -->
	    <tr>
       		<td><span class="odr_code">${orderVO.odr_code}</span></td>
	      <td><fmt:formatDate value="${orderVO.odr_date}" pattern="yyyy-MM-dd hh:mm"/></td>
	      <td>${orderVO.mem_id} / ${orderVO.odr_name}</td>
	      <td>${orderVO.odr_total_price}</td>
	      <td>결제상태</td>
	      <td> ${orderVO.odr_status}</td>
	      <td><button type="button" name="btnOrderDetail" class="btn btn-link">보기</button></td>
	    </tr>
	    </c:forEach>
	    
	  </tbody>
	</table>
			</div>
			
			<div class="box-footer">
				<div class="row">
					<div class="col-md-12">
						<nav aria-label="...">
	  <ul class="pagination">
	    <!-- 이전표시 -->
	    <c:if test="${pageMaker.prev }">
		    <li class="page-item">
		      <a class="page-link" href="${pageMaker.startPage - 1 }">Previous</a>
		    </li>
	    </c:if>
	    
	    <!-- 페이지번호 표시.  1  2  3  4  5 -->
	    
	    <c:forEach begin="${pageMaker.startPage }" end="${pageMaker.endPage }" var="num" >
	    	<li class='page-item ${pageMaker.cri.pageNum == num ? "active": "" }'><a class="page-link" href="${num}">${num}</a></li>
	    </c:forEach>
	    <!-- 
	    <li class="page-item active" aria-current="page">
	      <span class="page-link">2</span>
	    </li>
	    <li class="page-item"><a class="page-link" href="#">3</a></li>
	     -->
	    <!-- 다음표시 -->
	    <c:if test="${pageMaker.next }">
		    <li class="page-item">
		      <a class="page-link" href="${pageMaker.endPage + 1 }">Next</a>
		    </li>
	    </c:if>
		
	  </ul>
	  <!--1)페이지 번호 클릭시-->
		<form id="actionForm" action="" method="get">
			<input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum}">
			<input type="hidden" name="amount" value="${pageMaker.cri.amount}">
			<input type="hidden" name="type" value="${pageMaker.cri.type}">
			<input type="hidden" name="keyword" value="${pageMaker.cri.keyword}">
		</form>
	</nav>
					</div>
				</div>
			</div>


		</div>
  	
  	</div>
  </div>

  <%@include file="/WEB-INF/views/include/footer.jsp" %>
</div>


  <script>
  
  
    $(document).ready(function(){

    	let actionForm = $("#actionForm");
    	// 이전, 페이지번호, 다음 클릭시
        $("ul.pagination li a.page-link").on("click", function(e) {
          e.preventDefault();

          let pageNum = $(this).attr("href");

          actionForm.find("input[name='pageNum']").val(pageNum);
          actionForm.attr("method", "get");
          actionForm.attr("action", "/order/orderList");
          actionForm.submit();
        });
    	
     // 상세보기  Modal
        $("button[name='btnOrderDetail']").on("click", function() {
          console.log("주문상세내역");

          let odr_code = $(this).parent().parent().find("span.odr_code").text();
          // orderDetail.jsp실행결과를 불러온다.
          $("#orderDetailModal").load('/order/orderDetail?odr_code=' + odr_code);
          $("#orderDetailModal").modal('show');
        });

    }); // ready() 끝

  </script> 
  
  <!-- 주문상세내역 Modal : orderDetail.jsp 실행 결과 -->
<!-- Modal -->
<div class="modal" id="orderDetailModal" tabindex="-1"></div>
  
  </body>
</html>
