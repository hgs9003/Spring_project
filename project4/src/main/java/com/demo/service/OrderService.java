package com.demo.service;

import java.util.List;

import com.demo.domain.CartVO;
import com.demo.domain.CartVOList;
import com.demo.domain.OrderDetailProductVO;
import com.demo.domain.OrderDetailVO;
import com.demo.domain.OrderVO;
import com.demo.domain.PaymentVO;
import com.demo.dto.Criteria;

public interface OrderService {

	//장바구니 목록
	List<CartVOList> cart_list(String mem_id);
	
	//장바구니 -> 주문
	void orderSave(OrderVO o_vo, PaymentVO p_vo);
	
	// 바로구매 -> 주문
	void orderDirectSave(OrderVO o_vo, OrderDetailVO od_vo, PaymentVO p_vo); //추가 
	
	CartVOList directOrder(CartVO vo);
	
	long getOrderSequence();
	
	int getOrderProcessCount(String mem_id);
	
	//주문내역 
	List<OrderVO> getOrderList(String mem_id, Criteria cri);
	
	int getOrderTotalcount(String mem_id);
	
	List<OrderDetailProductVO> getOrderDetailList(Long odr_code);
	 
	
}
