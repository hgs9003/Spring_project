package com.demo.domain;

import lombok.Data;

@Data
public class OrderDetailVO {
	
	
	private Long odr_code;
	private Integer pdt_num;
	private int odr_amount;
	private int odr_price;
}
