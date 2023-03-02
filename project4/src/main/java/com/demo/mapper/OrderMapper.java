package com.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.demo.domain.CartVO;
import com.demo.domain.CartVOList;
import com.demo.domain.OrderDetailProductVO;
import com.demo.domain.OrderDetailVO;
import com.demo.domain.OrderVO;
import com.demo.domain.PaymentVO;
import com.demo.dto.Criteria;

public interface OrderMapper {
	
	//1)주문정보 저장하기
	void orderSave(OrderVO vo);  // odr_code NULL상태
	
	//2-1)주문상세 저장하기.  장바구니테이블의 데이타를 참조해서, 주문상세테이블에 저장한다.
	void orderDetailSave(@Param("odr_code") Long odr_code, @Param("mem_id") String mem_id);
	
	//2-2)주문상세 저장하기.  바로구매에서 주문상세저장하기(장바구니 사용안함)
	void orderDirectDetailSave(OrderDetailVO vo);
	//3)결제정보 저장하기
	void paymentSave(PaymentVO vo);
	
	//바로구매에서 보여줄 주문내역
	CartVOList directOrder(CartVO vo);
	
	//시퀀스 가져오기
	long getOrderSequence();
	
	// 진행 주문건수
	int getOrderProcessCount(String mem_id);
	
	// 주문내역
	List<OrderVO> getOrderList(@Param("mem_id") String mem_id, @Param("cri") Criteria cri);
	
	int getOrderTotalCount(String mem_id);
	
	List<OrderDetailProductVO> getOrderDetailList(Long odr_code);
}
