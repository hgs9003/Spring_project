package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.domain.OrderDetailProductVO;
import com.demo.domain.OrderDetailVOList;
import com.demo.domain.OrderVO;
import com.demo.domain.PaymentVO;
import com.demo.dto.Criteria;
import com.demo.mapper.AdOrderMapper;
import com.demo.mapper.AdPaymentMapper;

import lombok.Setter;

@Service
public class AdOrderServiceImpl implements AdOrderService {
	
	@Setter(onMethod_ = {@Autowired})
	private AdPaymentMapper adPaymentMapper;
	
	@Setter(onMethod_ = {@Autowired})
	private AdOrderMapper adOrderMapper;

	@Override
	public List<OrderVO> getOrderList(Criteria cri, String startDate, String endDate, String odr_status) {
		// TODO Auto-generated method stub
		return adOrderMapper.getOrderList(cri, startDate, endDate, odr_status);
	}

	@Override
	public int getOrderTotalCount(Criteria cri, String startDate, String endDate, String odr_status) {
		// TODO Auto-generated method stub
		return adOrderMapper.getOrderTotalCount(cri, startDate, endDate, odr_status);
	}

	@Override
	public void orderStatusChange(Long odr_code, String odr_status) {
		// TODO Auto-generated method stub
		adOrderMapper.orderStatusChange(odr_code, odr_status);
	}

	@Override
	public int orderStatusCount(String odr_status) {
		// TODO Auto-generated method stub
		return adOrderMapper.orderStatusCount(odr_status);
	}



	@Override
	public List<OrderDetailProductVO> getOrderDetailList2(Long odr_code) {
		// TODO Auto-generated method stub
		return adOrderMapper.getOrderDetailList2(odr_code);
	}

	@Override
	public PaymentVO getPayment(Long odr_code) {
		// TODO Auto-generated method stub
		return adOrderMapper.getPayment(odr_code);
	}

	@Override
	public OrderVO getOrder(Long odr_code) {
		// TODO Auto-generated method stub
		return adOrderMapper.getOrder(odr_code);
	}

	@Override
	public void pay_memo(Integer pay_code, String pay_memo) {
		// TODO Auto-generated method stub
		adOrderMapper.pay_memo(pay_code, pay_memo);
		
	}

	//주문정보 삭제
	@Transactional
	@Override
	public void orderInfoDelete(Long odr_code) {
		// TODO Auto-generated method stub
		
		adOrderMapper.orderDetailDelete(odr_code);
		adOrderMapper.paymentDelete(odr_code);
		
		adOrderMapper.orderDelete(odr_code);
		
	}

	
	// 주문 개별상품삭제 : 1)ORDER_DETAIL_TBL 상품삭제 2)ORDER_TBL 총주문금액 변경(차감) 3)PAYMENT_TBL 총주문금액 변경(차감)
	@Transactional
	@Override
	public void orderDetailProductDelete(Long odr_code, Integer pdt_num, int odr_price) {
		// TODO Auto-generated method stub
		adOrderMapper.orderDetailProductDelete(odr_code, pdt_num);
		adOrderMapper.orderTotalPriceChange(odr_code, odr_price);
		
		adPaymentMapper.orderPayTotalPriceChange(odr_code, odr_price);
	}

	@Override
	public List<OrderDetailVOList> getOrderDetailList1(Long odr_code) {
		// TODO Auto-generated method stub
		return adOrderMapper.getOrderDetailList1(odr_code);
	}



}
