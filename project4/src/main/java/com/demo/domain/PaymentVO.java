package com.demo.domain;

import java.util.Date;

import lombok.Data;

@Data
public class PaymentVO {

	private Integer pay_code; // 일련번호 관리
	private Long odr_code; //주문번호 
	private String mem_id; //회원ID
	private String pay_method; //결제방식선택
	private Date pay_date; // 결제일 
	private int pay_tot_price; //결제금액
	private int pay_nobank_price; //무통장 입금 금액
	private int pay_rest_price; //미지급금
	private String pay_nobank_user; // 입금자명
	private String pay_nobank; //입금은행 
	
	private String pay_memo; 
	
}
