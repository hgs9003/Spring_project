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
				<h3 class="box-title">장바구니 목록</h3>
			</div>

			<div class="box-body no-padding">
				<table class="table table-striped" id="cartlist">
				<tbody><tr>
					<th style="width: 2%"><input type="checkbox" id="checkAll"></th>
					<th style="width: 12%">이미지</th>
					<th style="width: 13%">상품정보</th>
					<th style="width: 13%">판매가</th>
					<th style="width: 10%">수량</th>
					<th style="width: 10%">적립금</th>
					<th style="width: 10%">배송구분</th>
					<th style="width: 10%">배송비</th>
					<th style="width: 10%">합계</th>
					<th style="width: 10%">선택</th>
					</tr>
				<c:forEach items="${cartList }" var="cartListVO">
				<c:set var="unit_price" value="${cartListVO.sales_price }" />
				<tr>
					<td><input type="checkbox" class="check" value="${cartListVO.cart_code }"></td>
					<td><img src="/cart/displayFile?folderName=${cartListVO.pdt_img_folder }&fileName=s_${cartListVO.pdt_img}"></td>
					<td>${cartListVO.pdt_name }</td>
					<td>￦<span class="pdt_price"><fmt:formatNumber type="currency" pattern="#,###" value="${cartListVO.pdt_price }" /></span></td>
					<td>
						<input type="number" name="cart_amount" value="${cartListVO.cart_amount }" style="width:50px;"><br>
						<button type="button" name="btnQtyChange" class="btn btn-link">변경</button>
					</td>
					<td>포인트</td>
					<td>기본배송</td>
					<td>무료</td>
					<td>￦<span class="sales_price"><fmt:formatNumber type="currency" pattern="#,###" value="${cartListVO.sales_price }" />
					</span></td>
					<td>
						<input type="hidden" name="cart_code" value="${cartListVO.cart_code }">
						
						<button type="button" name="btnCartDel" class="btn btn-link">Delete</button>
					</td>
				</tr>
				<c:set var="sum_price" value="${sum_price +  unit_price}"></c:set>
				</c:forEach>
				</tbody>
				<tfoot>
					<tr>
					<c:if test="${empty  cartList}">
						<td colspan="10" style="text-align: center">장바구니가 비워져 있습니다.</td>
					</c:if>
					<c:if test="${!empty  cartList}">
						<td colspan="10" style="text-align: right">총금액: ￦<span id="sum_price"><fmt:formatNumber type="currency" pattern="#,###" value="${sum_price }" /></span></td>
					</c:if>
					</tr>
				</tfoot>
				</table>
			</div>
			<c:if test="${!empty  cartList}">
			<div class="box-footer">
				<div class="row">
					<div class="col-md-12">
						<button type="button" name="btnSelectDel" class="btn btn-link">선택삭제</button>
						<button type="button" name="btnCartEmpty" class="btn btn-link">장바구니비우기</button>
						<button type="button" name="btnOrder" class="btn btn-link">주문하기</button><br>
					</div>
				</div>
			</div>
			</c:if>

		</div>
  	
  	</div>
  </div>

  <%@include file="/WEB-INF/views/include/footer.jsp" %>
</div>


  <script>
  
  
    $(document).ready(function(){

      //장바구니 클릭
      $("button[name='btnCart']").on("click", function(){
        
        $.ajax({
          url: '/cart/cart_add',
          data: {pdt_num : $(this).parent().find("input[name='pdt_num']").val(), cart_amount : 1},  // javascript object구문
          success : function(result) {
            if(result == "success") {
              alert("장바구니에 추가됨.");
              if(confirm("장바구니로 이동하시겠습니까?")) {
                location.href = "장바구니목록주소";
              }
            }
          }
        });
      });

      //수량변경 클릭
      
      $("button[name='btnQtyChange']").on("click", function(){
        
        let btnQtyChange = $(this); // 변경버튼이 클릭시 상대적인 위치를 참조.

        $.ajax({
          url: '/cart/cart_qty_change',
          data: {cart_code : $(this).parent().parent().find("input[name='cart_code']").val(), cart_amount : $(this).siblings("input[name='cart_amount']").val()},  // javascript object구문
          beforeSend : function(xhr) {
            xhr.setRequestHeader("AJAX", "true");
          },
          success : function(result) {
            if(result == "success") {
              alert("장바구니 수량이 변경됨.");
              //sales_price가격 변경
              //연산시 콤마제거
              let pdt_price = parseInt($.withoutCommas(btnQtyChange.parent().parent().find("span.pdt_price").text()));
              let cart_amount = parseInt(btnQtyChange.siblings("input[name='cart_amount']").val());
              btnQtyChange.parent().parent().find("span.sales_price").text($.numberWithCommas(pdt_price*cart_amount)); // 표시는 콤마를 찍기
              
              //sum_price가격 변경
              fn_sum_price();
              
          }
        },
        error : function(xhr, status, error) {
          alert(status);
          alert("로그인 페이지로 이동합니다.");
          location.href = "/member/login";
        }
      });
    });

      //장바구니 삭제
      $("button[name='btnCartDel']").on("click", function(){
        
        if(!confirm("항목을 삭제하겠습니까?")) return;

        let current_tr = $(this).parent().parent(); // ajax구문이 실행되기전 삭제행을 참조해야 한다.(중요)
        
        $.ajax({
          url: '/cart/cart_delete',
          data: {cart_code : $(this).parent().find("input[name='cart_code']").val()},  // javascript object구문
          success : function(result) {
            if(result == "success") {
              alert("장바구니 항목이 삭제됨.");
              current_tr.remove();

              //sum_price가격 변경
              fn_sum_price();
 
            }
          }
        });
      });

      //사용자정의함수
      // 콤마제거하기. 연산할 때 사용
      $.withoutCommas = function(x) {

        return x.toString().replace(",", "");
      }

      //3자리마다 콤마찍기. 표시할 때 사용.
      $.numberWithCommas = function(x) {
        return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
      }
      
      /*
      $.fn_sum_price = function() {
          let sum_price = 0;
          $("table#cartlist span.sales_price").each(function(index, item){
            sum_price += parseInt($.withoutCommas($(item).text()));  
          });
        
          $("span#sum_price").text($.numberWithCommas(sum_price)); 
        }
      
      */


      //장바구니 비우기 클릭
      $("button[name='btnCartEmpty']").on("click", function(){

        if(!confirm("장바구니를 비우겠습니까?")) return;
        location.href = "/cart/cart_empty";
      });

      //제목행 체크박스 선택
      let isCheck = true;
      $("#checkAll").on("click", function() {
        //데이터 행 checkbox
        $(".check").prop("checked", this.checked);

        isCheck = this.checked;
      });

      //데이터행 체크박스 선택
      $(".check").on("click", function(){
        //제목행 체크박스
        $("#checkAll").prop("checked", this.checked);

        //데이터행의 체크박스 상태를 확인.
        $(".check").each(function() {
          if(!$(this).is(":checked")) {
            $("#checkAll").prop("checked", false);
          }
        });
      });

      //선택삭제
      $("button[name='btnSelectDel']").on("click", function() {
        if($(".check:checked").length == 0) {
          alert("삭제할 상품을 체크하세요.");
          return;
        }

        if(!confirm("선택 상품을 삭제하시겠습니까?")) return;

        //삭제 할 장바구니코드.  자바스크립트 배열 : https://www.w3schools.com/js/js_arrays.asp
        let cart_code_arr = [];

        //선택한 체크박스 . 
        $(".check:checked").each(function() {
          cart_code_arr.push($(this).val());
        });

        console.log("삭제할 장바구니코드: " + cart_code_arr);


        $.ajax({
          url: '/cart/cart_sel_delete',
          type: 'post',
          dataType: 'text',
          data: { cart_code_arr: cart_code_arr },
          success: function(result) {
            if(result == "success") {
              alert("선택한 상품이 삭제되었습니다.");
              location.href = "/cart/cart_list";
            }
          }
        });

      });

      //주문하기
      $("button[name='btnOrder']").on("click", function(){

        location.href = "/order/orderListInfo?type=cart";
      });


    }); // ready() 끝

    //sum_price가격 변경
    
    function fn_sum_price() {
        
      let sum_price = 0;
      $("table#cartlist span.sales_price").each(function(index, item){
        sum_price += parseInt($.withoutCommas($(item).text()));  
      });
      
      $("span#sum_price").text($.numberWithCommas(sum_price)); 
    }
    
    
    
    

  </script>  
  </body>
</html>
