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
				<table class="table table-striped" id="cartlist">
				<tbody><tr>
					<th style="width: 20%">이미지</th>
					<th style="width: 15%">상품정보</th>
					<th style="width: 15%">판매가</th>
					<th style="width: 10%">수량</th>
					<th style="width: 10%">적립금</th>
					<th style="width: 10%">배송구분</th>
					<th style="width: 10%">배송비</th>
					<th style="width: 10%">합계</th>
					</tr>
				<c:forEach items="${orderList }" var="orderListVO">
				<c:set var="unit_price" value="${orderListVO.sales_price }" />
				<tr>
					<td><img src="/order/displayFile?folderName=${orderListVO.pdt_img_folder }&fileName=s_${orderListVO.pdt_img}"></td>
					<td>${orderListVO.pdt_name }</td>
					<td>￦<span class="pdt_price"><fmt:formatNumber type="currency" pattern="#,###" value="${orderListVO.pdt_price }" /></span></td>
					<td>
						<input type="text" name="cart_amount" id="d_odr_amount" value="${orderListVO.cart_amount }" style="width:50px;"><br>
						<input type="hidden" name="pdt_num" id="d_pdt_num" value="${orderListVO.pdt_num }">
					</td>
					<td>포인트</td>
					<td>기본배송</td>
					<td>무료</td>
					<td>￦<span class="sales_price"><fmt:formatNumber type="currency" pattern="#,###" value="${orderListVO.sales_price }" />
					</span>
					<input type="hidden" name="sales_price" id="d_odr_price" value="${orderListVO.sales_price }">
					</td>
					
				</tr>
				<c:set var="sum_price" value="${sum_price +  unit_price}"></c:set>
				</c:forEach>
				</tbody>
				<tfoot>
					<tr>
					<c:if test="${empty  orderList}">
						<td colspan="10" style="text-align: center">주문내역이 없습니다.</td>
					</c:if>
					<c:if test="${!empty  orderList}">
						<td colspan="10" style="text-align: right">총금액: ￦<span id="sum_price"><fmt:formatNumber type="currency" pattern="#,###" value="${sum_price }" /></span></td>
					</c:if>
					</tr>
				</tfoot>
				</table>
				
				<div>
					<form id="orderForm" action="" method="post">
						  <h5>주문자정보</h5>
						  <hr>
						  <div class="form-group row">
						    <label for="mem_name" class="col-sm-2 col-form-label">이름</label>
						    <div class="col-sm-10">
								<input type="hidden" name="type" value="${type}">
								<!-- 주문상세정보 -->
								<input type="hidden" name="pdt_num" id="od_pdt_num">
								<input type="hidden" name="odr_amount" id="od_odr_amount">
								<input type="hidden" name="odr_price" id="od_odr_price">

								<input type="text" class="form-control" id="s_mem_name" value="${sessionScope.loginStatus.mem_name }"  readonly>
						    </div>
						  </div>
						  <div class="form-group row">
						    <label for="mem_email" class="col-sm-2 col-form-label">전자우편</label>
						    <div class="col-sm-10">
						      <input type="text" class="form-control" id="s_mem_email" value="${sessionScope.loginStatus.mem_email }" readonly>
						    </div>
						  </div>
						  <div class="form-group row">
						    <label for="mem_phone" class="col-sm-2 col-form-label">휴대폰 번호</label>
						    <div class="col-sm-10">
						      <input type="text" class="form-control" id="s_mem_phone" value="${sessionScope.loginStatus.mem_phone }" readonly>
						      <input type="hidden" class="form-control" id="s_mem_zipcode" value="${sessionScope.loginStatus.mem_zipcode }">
						      <input type="hidden" class="form-control" id="s_mem_addr" value="${sessionScope.loginStatus.mem_addr }">
						      <input type="hidden" class="form-control" id="s_mem_addr_d" value="${sessionScope.loginStatus.mem_addr_d }">
						    </div>
						  </div>
						  <input type="checkbox" id="same"><label for="same">위 정보와 같음</label>
						  <h5>배송 정보</h5>
						  <hr>
						  <div class="form-group row">
						    <label for="mem_name" class="col-sm-2 col-form-label">이름</label>
						    <div class="col-sm-10">
						      <input type="text" class="form-control" id="mem_name" name="odr_name">
						    </div>
						  </div>
						  <div class="form-group row">
						    <label for="mem_phone" class="col-sm-2 col-form-label">휴대폰 번호</label>
						    <div class="col-sm-10">
						      <input type="text" class="form-control" id="mem_phone" name="odr_phone">
						    </div>
						  </div>
						  <div class="form-group row">
						    <label for="mem_phone" class="col-sm-2 col-form-label">배송지 선택</label>
						    <div class="col-sm-10">
						      <div class="form-check form-check-inline">
							  <input class="form-check-input" type="radio" name="receiveAddr" id="receiveAddr1" value="1" checked>
							  <label class="form-check-label" for="inlineRadio1">자택</label>
							</div>
						  <div class="form-check form-check-inline">
							  <input class="form-check-input" type="radio" name="receiveAddr" id="receiveAddr2" value="2">
							  <label class="form-check-label" for="inlineRadio2">타지역</label>
						  </div>
						    </div>
						  </div>
						  
						  <div class="form-group row">
						    <label for="sample2_postcode" class="col-sm-2 col-form-label">우편번호</label>
						    <div class="col-sm-10">
						      <input type="text" class="form-control" id="sample2_postcode" name="odr_zipcode" value="${sessionScope.loginStatus.mem_zipcode }">
						      <input type="button" onclick="sample2_execDaumPostcode()" value="우편번호 찾기">
						    </div>
						  </div>
						  <div class="form-group row">
						    <label for="sample2_address" class="col-sm-2 col-form-label">주소</label>
						    <div class="col-sm-10">
						      <input type="text" class="form-control" id="sample2_address" name="odr_addr" value="${sessionScope.loginStatus.mem_addr }">
						    </div>
						  </div>
						  <div class="form-group row">
						    <label for="sample2_detailAddress" class="col-sm-2 col-form-label">상세주소</label>
						    <div class="col-sm-10">
						      <input type="text" class="form-control" id="sample2_detailAddress" name="odr_addr_d" value="${sessionScope.loginStatus.mem_addr_d }">
						      <input type="hidden" id="sample2_extraAddress" placeholder="참고항목">
						    </div>
						  </div>
						  <div class="form-group row">
						    <label for="mem_phone" class="col-sm-2 col-form-label">배송지 메세지(100자 이내)</label>
						    <div class="col-sm-10">
						      <textarea name="odr_message" class="form-control" rows="5"></textarea>
						      <input type="hidden" name="odr_total_price" value="${sum_price }">
						    </div>
						  </div>
						  <div class="form-group">
						    <label for="pay_method">결제방법</label>
						    <select name="pay_method" id="pay_method" class="form-control" >
						      <option value="">결제방법을 선택하세요</option>
						      <option value="무통장입금">무통장입금</option>
						      <option value="카카오페이">카카오페이</option>
						      <option value="휴대폰">휴대폰</option>
						      <option value="신용카드">신용카드</option>
						    </select>
						    <select name="pay_nobank" id="bank" class="form-control" >
						      <option value="">입금은행을 선택하세요</option>
						      <option value="국민은행00000000000000">국민은행(00000000000000)</option>
						      <option value="하나은행11111111111111">하나은행(11111111111111)</option>
						      <option value="신한은행22222222222222">신한은행(22222222222222)</option>
						      <option value="우리은행33333333333333">우리은행(33333333333333)</option>
						    </select>
						    <!--  <input type="hidden" name="pay_nobank" id="pay_nobank" value="">-->
						    <input type="hidden" name="pay_nobank_price" id="pay_nobank_price" value="${sum_price }">
						  </div>
						  <div class="form-group row">
						    <label for="mem_name" class="col-sm-2 col-form-label">입금자명</label>
						    <div class="col-sm-10">
						      <input type="text" class="form-control" id="pay_nobank_user" name="pay_nobank_user">
						    </div>
						  </div>
						  
					 </form>
					</div>
			</div>
			<div class="box-footer text-center">
     				<button type="button" id="btnOrder" class="btn btn-primary">주문하기</button>
     				<input type="image" src="/image/payment_icon_yellow_medium.png" id="kakao_pay" class="btn btn-link" disabled>
     				<button type="button" id="btnCancel" class="btn btn-primary">주문취소</button>
     		</div>

		</div>
  	
  	</div>
  </div>

  <%@include file="/WEB-INF/views/include/footer.jsp" %>
</div>


  <script>
  
  
    $(document).ready(function(){

      //주문자와 배송지 정보를 동일하게 하고자 할때 사용.
      $("#same").on("click", function() {

        if($("#same").is(":checked") == true) {
          $("#mem_name").val($("#s_mem_name").val());
          $("#mem_phone").val($("#s_mem_phone").val());
        }else {
          $("#mem_name").val("");
          $("#mem_phone").val("");
        }
      });

      // 자택또는 타지역 라디오버튼 클릭
      $("input[name='receiveAddr']").on("click", function() {

        if($("input[name='receiveAddr']:checked").val() == 1) { //자택체크
          $("#sample2_postcode").val($("#s_mem_zipcode").val());
          $("#sample2_address").val($("#s_mem_addr").val());
          $("#sample2_detailAddress").val($("#s_mem_addr_d").val());
        }else if($("input[name='receiveAddr']:checked").val() == 2) { // 타지역체크
          $("#sample2_postcode").val("");
          $("#sample2_address").val("");
          $("#sample2_detailAddress").val("");

        }
      });

      //결제방법선택
      $("#pay_method").on("change", function() {

        /*
        if($("#pay_method option:selected").val() == "") {
          alert("결제 방법을 선택하세요");
          return;
        }
        */
        $("#btnOrder").attr("disabled", false);  // 주문하기버튼 활성화
        $("input#kakao_pay").attr("disabled", true);  // 카카오페이 버튼. 비활성화

        if($("#pay_method option:selected").val() == "카카오페이") {
          $("#btnOrder").attr("disabled", true);  // 주문하기 비활성화
          $("input#kakao_pay").attr("disabled", false); // 카카오버튼 활성화

          alert("카카오페이 이미지를 클릭하면 결제가 진행됩니다.");
        }

      });

	  //카카오페이 결제버튼 클릭. ajax요청
	  $("#kakao_pay").on("click", function(e) {
		e.preventDefault(); // submit기능 비활성화


		let odr_total_price = $("input[name='odr_total_price']").val();

		$.ajax({
			url: '/order/orderPay',
			data: {	
				
				type : '${type}',
				// int totalamount
				totalamount : odr_total_price,
				// OrderVO o_vo
				odr_name : $("input[name='odr_name']").val(),
				odr_zipcode : $("input[name='odr_zipcode']").val(),
				odr_addr: $("input[name='odr_addr']").val(),
				odr_addr_d : $("input[name='odr_addr_d']").val(),
				odr_phone : $("input[name='odr_phone']").val(),
				odr_total_price : odr_total_price,
				
				// OrderDetailVO od_vo 주문상세정보
				// odr_code : ,    
				pdt_num : $("input[name='pdt_num']").val(),
				odr_amount : $("input[name='cart_amount']").val(),
				odr_price : $("input[name='sales_price']").val(),
								
				// PaymentVO p_vo
				pay_method : $("#pay_method option:selected").val(),
				pay_tot_price : odr_total_price,
			},
			dataType : 'json',  // 기본값 : xml. 또는 서버측에서 보내오는 MIME정보를 해석해서 자동으로 인식하게 해준다.
			type: 'get',
			success : function(response) {
				
				// next_redirect_pc_url : 
				// pg_token 키 쿼리스트링
				console.log("응답: " + response);
				//alert(response.tid); 
				alert(response.next_redirect_pc_url);  
				location.href = response.next_redirect_pc_url;
			}

		});
	  })


	  let orderForm = $("#orderForm");
	  // 무통장 결제
	  $("#btnOrder").on("click", function() {

		orderForm.attr("method", "post");
		orderForm.attr("action", "/order/orderSave");

		//주문상세VO
		$("#od_pdt_num").val($("#d_pdt_num").val());
		$("#od_odr_amount").val($("#d_odr_amount").val());
		$("#od_odr_price").val($("#d_odr_price").val());


		orderForm.submit();
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
  
  <!-- iOS에서는 position:fixed 버그가 있음, 적용하는 사이트에 맞게 position:absolute 등을 이용하여 top,left값 조정 필요 -->
	<div id="layer" style="display:none;position:fixed;overflow:hidden;z-index:1;-webkit-overflow-scrolling:touch;">
	<img src="//t1.daumcdn.net/postcode/resource/images/close.png" id="btnCloseLayer" style="cursor:pointer;position:absolute;right:-3px;top:-3px;z-index:1" onclick="closeDaumPostcode()" alt="닫기 버튼">
	</div>
	
	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script>
	    // 우편번호 찾기 화면을 넣을 element
	    var element_layer = document.getElementById('layer'); //  <div id="layer"> 태그가 현재 실행코드보다 앞에 작성이 되어 있어야 한다.
	
	    function closeDaumPostcode() {
	        // iframe을 넣은 element를 안보이게 한다.
	        // 태그참조변수.style.css속성명령어 = '값';
	        element_layer.style.display = 'none';
	    }
	
	    function sample2_execDaumPostcode() {
	        new daum.Postcode({
	            oncomplete: function(data) {
	                // 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
	
	                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
	                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
	                var addr = ''; // 주소 변수
	                var extraAddr = ''; // 참고항목 변수
	
	                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
	                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
	                    addr = data.roadAddress;
	                } else { // 사용자가 지번 주소를 선택했을 경우(J)
	                    addr = data.jibunAddress;
	                }
	
	                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
	                if(data.userSelectedType === 'R'){
	                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
	                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
	                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
	                        extraAddr += data.bname;
	                    }
	                    // 건물명이 있고, 공동주택일 경우 추가한다.
	                    if(data.buildingName !== '' && data.apartment === 'Y'){
	                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
	                    }
	                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
	                    if(extraAddr !== ''){
	                        extraAddr = ' (' + extraAddr + ')';
	                    }
	                    // 조합된 참고항목을 해당 필드에 넣는다.
	                    document.getElementById("sample2_extraAddress").value = extraAddr;
	                
	                } else {
	                    document.getElementById("sample2_extraAddress").value = '';
	                }
	
	                // 우편번호와 주소 정보를 해당 필드에 넣는다.
	                document.getElementById('sample2_postcode').value = data.zonecode;
	                document.getElementById("sample2_address").value = addr;
	                // 커서를 상세주소 필드로 이동한다.
	                document.getElementById("sample2_detailAddress").focus();
	
	                // iframe을 넣은 element를 안보이게 한다.
	                // (autoClose:false 기능을 이용한다면, 아래 코드를 제거해야 화면에서 사라지지 않는다.)
	                element_layer.style.display = 'none';
	            },
	            width : '100%',
	            height : '100%',
	            maxSuggestItems : 5
	        }).embed(element_layer);
	
	        // iframe을 넣은 element를 보이게 한다.
	        element_layer.style.display = 'block';
	
	        // iframe을 넣은 element의 위치를 화면의 가운데로 이동시킨다.
	        initLayerPosition();
	    }
	
	    // 브라우저의 크기 변경에 따라 레이어를 가운데로 이동시키고자 하실때에는
	    // resize이벤트나, orientationchange이벤트를 이용하여 값이 변경될때마다 아래 함수를 실행 시켜 주시거나,
	    // 직접 element_layer의 top,left값을 수정해 주시면 됩니다.
	    function initLayerPosition(){
	        var width = 300; //우편번호서비스가 들어갈 element의 width
	        var height = 400; //우편번호서비스가 들어갈 element의 height
	        var borderWidth = 5; //샘플에서 사용하는 border의 두께
	
	        // 위에서 선언한 값들을 실제 element에 넣는다.
	        element_layer.style.width = width + 'px';
	        element_layer.style.height = height + 'px';
	        element_layer.style.border = borderWidth + 'px solid';
	        // 실행되는 순간의 화면 너비와 높이 값을 가져와서 중앙에 뜰 수 있도록 위치를 계산한다.
	        element_layer.style.left = (((window.innerWidth || document.documentElement.clientWidth) - width)/2 - borderWidth) + 'px';
	        element_layer.style.top = (((window.innerHeight || document.documentElement.clientHeight) - height)/2 - borderWidth) + 'px';
	    }
	</script> 
  </body>
</html>
