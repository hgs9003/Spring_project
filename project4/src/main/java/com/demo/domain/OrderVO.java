package com.demo.domain;

import java.util.Date;

import lombok.Data;

@Data
public class OrderVO {
	
	private Long odr_code;
	private String mem_id;
	private String odr_name;
	private String odr_zipcode;
	private String odr_addr;
	private String odr_addr_d;
	private String odr_phone;
	private int odr_total_price;
	private Date odr_date;
	
	private String odr_status; //주문상태
	private String payment_status; //결제상태 

}


//orderPay?type=direct&
//totalamount=39900
//&odr_name=강식&
//odr_zipcode=111%20%20&
//odr_addr=11&
//odr_addr_d=11&
//odr_phone=11&
//odr_total_price=39900&
//pdt_num=4&
//odr_amount=1&
//odr_price=39900&
//pay_method=카카오페이&
//pay_tot_price=39900
