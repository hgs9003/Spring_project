package com.demo.domain;

import lombok.Data;

@Data
public class OrderDetailProductVO {
	
	
	//기존 클래스 
	private OrderDetailVO orderDetailVO;  //주문상세 클래스
	private ProductVO productVO; // 상품클래스 

}
