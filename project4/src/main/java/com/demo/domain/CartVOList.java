package com.demo.domain;

import lombok.Data;

@Data
public class CartVOList {

	private Long cart_code;
	private Integer pdt_num;
	private String pdt_name;
	private String mem_id;
	private int pdt_price;
	private int cart_amount;
	private String pdt_img_folder;
	private String pdt_img;
	private int sales_price;  // sql: pdt_price * cart_amount
	
}
