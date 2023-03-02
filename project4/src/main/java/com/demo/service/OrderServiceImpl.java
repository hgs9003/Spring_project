package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.domain.CartVO;
import com.demo.domain.CartVOList;
import com.demo.domain.OrderDetailProductVO;
import com.demo.domain.OrderDetailVO;
import com.demo.domain.OrderVO;
import com.demo.domain.PaymentVO;
import com.demo.dto.Criteria;
import com.demo.mapper.CartMapper;
import com.demo.mapper.OrderMapper;

import lombok.Setter;

@Service
public class OrderServiceImpl implements OrderService {

	//주입 
	@Setter(onMethod_ = {@Autowired})
	private OrderMapper orderMapper;
	
	@Setter(onMethod_ = {@Autowired})
	private CartMapper cartMapper;
	
	
	
	
	@Override
	public List<CartVOList> cart_list(String mem_id) {
		// TODO Auto-generated method stub
		return cartMapper.cart_list(mem_id);
	}



	//상품리스트에서 바로구매
	@Transactional
	@Override
	public void orderDirectSave(OrderVO o_vo, OrderDetailVO od_vo, PaymentVO p_vo) {
		// TODO Auto-generated method stub
		//1)주문정보 저장하기(주문자와 배송지 정보)
		orderMapper.orderSave(o_vo);		
		
		//2)주문상세 저장하기(주문상품정보)
		orderMapper.orderDirectDetailSave(od_vo);
		
		//3)결제정보 저장하기
		p_vo.setOdr_code(o_vo.getOdr_code());
		orderMapper.paymentSave(p_vo);
		
	}




	@Override
	public CartVOList directOrder(CartVO vo) {
		// TODO Auto-generated method stub
		return orderMapper.directOrder(vo);
	}




	@Override
	public long getOrderSequence() {
		// TODO Auto-generated method stub
		return orderMapper.getOrderSequence();
	}




	@Override
	public int getOrderProcessCount(String mem_id) {
		// TODO Auto-generated method stub
		return orderMapper.getOrderProcessCount(mem_id);
	}




	@Override
	public List<OrderVO> getOrderList(String mem_id, Criteria cri) {
		// TODO Auto-generated method stub
		return orderMapper.getOrderList(mem_id, cri);
	}




	@Override
	public int getOrderTotalcount(String mem_id) {
		// TODO Auto-generated method stub
		return orderMapper.getOrderTotalCount(mem_id);
	}




	@Override
	public List<OrderDetailProductVO> getOrderDetailList(Long odr_code) {
		// TODO Auto-generated method stub
		return orderMapper.getOrderDetailList(odr_code);
	}


	//서비스의 메서드 안에서 하나라도 에러가 발생하면 정상적으로 실행되는 다른 메서드의 작업들도 롤백처리를 시키는 기능.
	//장바구니에서 주문하기
	@Transactional
	@Override
	public void orderSave(OrderVO o_vo, PaymentVO p_vo) {
		// TODO Auto-generated method stub
		//1)주문정보 저장하기(주문자와 배송지 정보)
		orderMapper.orderSave(o_vo);
		
		//2)주문상세 저장하기(주문상품정보)
		orderMapper.orderDetailSave(o_vo.getOdr_code(), o_vo.getMem_id());
		
		//3) 결제정보 저장하기
		p_vo.setOdr_code(o_vo.getOdr_code());
		orderMapper.paymentSave(p_vo);
		
		//4)장바구니 비우기
		cartMapper.cart_empty(o_vo.getMem_id());
		
		
		
	}

	
	
}
