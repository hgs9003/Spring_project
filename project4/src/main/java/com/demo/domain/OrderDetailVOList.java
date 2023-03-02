package com.demo.domain;

import lombok.Data;

// 주문관리 : 주문상세정보에서 주문상세상품정보를 사용. 
// ( OrderDetailVO, ProductVO 2개의 클래스를 사용하지않고, 새로운 VO클래스를 생성 )

@Data
public class OrderDetailVOList {

	// OD.ODR_CODE, OD.PDT_NUM,  P.PDT_NAME, P.PDT_PRICE, OD.ODR_AMOUNT, OD.ODR_PRICE, P.PDT_IMG_FOLDER, P.PDT_IMG
	private Long odr_code;
	private Integer pdt_num;
	private String pdt_name;
	private int pdt_price;
	private int odr_amount;
	private int odr_price;
	private String pdt_img_folder;
	private String pdt_img;
}
