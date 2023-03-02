<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">주문상세정보</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <h6>주문상세정보</h6>
        <table class="table table-bordered">
        	<tr>
        		<th>주문상품/이미지</th>
        		<th>수량</th>
        		<th>주문금액</th>
        		<th>배송료</th>
        		<th>비고</th>
        	</tr>
        	<c:forEach items="${odList2 }" var="orderDetailVOList">
        	<tr>
        		<td>
        		<img src="/admin/order/displayFile?folderName=${orderDetailVOList.productVO.pdt_img_folder }&fileName=s_${orderDetailVOList.productVO.pdt_img }" 
	      		alt="" style="width: 50px;height: 50px;" onerror="this.onerror=null; this.src='/image/no_images.png'">
	      	<a class="move" href="${orderDetailVOList.productVO.pdt_num }"><c:out value="${orderDetailVOList.productVO.pdt_name }" escapeXml="true" /></a>
        		<br>
        		${orderDetailVOList.orderDetailVO.odr_code }
        		</td>
        		<td>${orderDetailVOList.orderDetailVO.odr_amount }</td>
        		<td><span class="odr_price">${orderDetailVOList.orderDetailVO.odr_price }</span></td>
        		<td>0원</td>
        		<td>
					<input type="hidden" name="odr_code" value="${orderDetailVOList.orderDetailVO.odr_code }">
					<input type="hidden" name="pdt_num" value="${orderDetailVOList.orderDetailVO.pdt_num }">
					<button type="button" name="btnOrderDetailDelete" class="btn btn-link">Delete</button>
				</td>
        	</tr>
        	</c:forEach>
        </table>
        <h6>결제정보</h6>
        <table class="table table-bordered">
        	<tr>
        		<th>결제방식</th>
        		<td>${paymentVO.pay_method }</td>
        		<th>결제금액</th>
        		<td><span id="pay_tot_price">${paymentVO.pay_tot_price }</span></td>
        	</tr>
        	<tr>
        		<th>입금자명</th>
        		<td>${paymentVO.mem_id }</td>
        		<th>입금계좌</th>
        		<td>${paymentVO.pay_nobank }</td>
        	</tr>    
        </table>         
        <h6>주문정보</h6>
        <table class="table table-bordered">
        	<tr>
        		<th>수령인</th>
        		<td>${orderVO.odr_name }</td>
        		<th>전화번호</th>
        		<td>${orderVO.odr_phone }</td>
        	</tr>
        	<tr>
        		<th>주소</th>
        		<td colspan="3">
        		${orderVO.odr_addr } ${orderVO.odr_addr_d }<br> 
        		우편번호: ${orderVO.odr_zipcode }
        		</td>
        	</tr>
        	<tr>
        		<th>배송메모</th>
        		<td colspan="3">빈칸</td>
        	</tr>
        </table>
        <h6>관리자메모</h6>
        <form action="" method="post">
		<input type="hidden" name="pay_code" value="${paymentVO.pay_code }">
        <textarea name="pay_memo" class="form-control" rows="3">${paymentVO.pay_memo }</textarea>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
        <button type="button" name="btnPayMemo" class="btn btn-primary">저장하기</button>
      </div>
    </div>
  </div>

  <script>

	$(document).ready(function(){

		// 관리자 메모
		$("button[name='btnPayMemo']").on("click", function() {

			$.ajax({
				url: '/admin/order/pay_memo',
				data: {pay_code : $("input[name='pay_code']").val(), pay_memo : $("textarea[name='pay_memo']").val()},
				type: 'post',
				dataType: 'text',
				success: function(result) {
					if(result == 'success') {
						alert("관리자 메모가 처리되었습니다.");
					}
				}
			});
		});

		// 개별주문상품 삭제
		$("button[name='btnOrderDetailDelete']").on("click", function() {
			
			if(!confirm("주문상품을 삭제하겠습니까?")) return;
			
			//주문번호, 상품코드 확보.
			let odr_code = $(this).siblings("input[name='odr_code']").val();
			let pdt_num = $(this).siblings("input[name='pdt_num']").val();
			let odr_price = $(this).parent().parent().find("span.odr_price").text();

			let cur_tr = $(this).parent().parent(); // ajax()메서드 호출전에 삭제할 tr태그를 먼저 참조해야 한다.

			// console.log("주문번호: " + odr_code);
			// console.log("상품번호: " + pdt_num);

			


			$.ajax({
				url: '/admin/order/orderDetailProductDelete',
				type: 'get',
				data: {odr_code : odr_code, pdt_num : pdt_num, odr_price : odr_price},
				dataType: 'text',
				success :function(result) {
					if(result = "success") {
						alert("개별상품이 삭제되었습니다.");
						cur_tr.remove();

						let pay_tot_price = $("#pay_tot_price").text();
						$("#pay_tot_price").text(pay_tot_price - odr_price);
					}
				}

			});
		});
	});

  </script>