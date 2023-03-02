package com.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CartVO {

	private Long cart_code;
	private Integer pdt_num;
	private String mem_id;
	private int cart_amount;
}
