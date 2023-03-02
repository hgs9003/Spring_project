package com.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.demo.domain.OrderDetailProductVO;
import com.demo.domain.OrderDetailVOList;
import com.demo.domain.OrderVO;
import com.demo.domain.PaymentVO;
import com.demo.dto.Criteria;

public interface AdOrderMapper { 

	List<OrderVO> getOrderList(@Param("cri")Criteria cri, 
							   @Param("startDate")String startDate, 
							   @Param("endDate")String endDate, 
							   @Param("odr_status")String odr_status);

	int getOrderTotalCount(@Param("cri")Criteria cri, 
							   @Param("startDate")String startDate, 
							   @Param("endDate")String endDate, 
							   @Param("odr_status")String odr_status);
	
	void orderStatusChange(@Param("odr_code")Long odr_code, @Param("odr_status")String odr_status);

	int orderStatusCount(String odr_status);    
	
	//주문상세정보1.
	List<OrderDetailVOList> getOrderDetailList1(Long odr_code);
	//주문상세정보2.
	List<OrderDetailProductVO> getOrderDetailList2(Long odr_code);
	//결제정보
	PaymentVO getPayment(Long odr_code);
	//주문정보 
	OrderVO getOrder(Long odr_code);
	
	//관리자메모
	void pay_memo(@Param("pay_code")Integer pay_code, @Param("pay_memo")String pay_memo);
	
	//주문상세
	void orderDelete(Long odr_code);
	void orderDetailDelete(Long odr_code);
	void paymentDelete(Long odr_code);
	
	
	//주문상세 개별상품 삭제기능
	
	//1)개별상품삭제기능
	void orderDetailProductDelete(@Param("odr_code")Long odr_code, @Param("pdt_num")Integer pdt_num);
	//2)주문테이블 총 주문금액 변경
	void orderTotalPriceChange(@Param("odr_code")Long odr_code, @Param("odr_price")int odr_price);
	
}
