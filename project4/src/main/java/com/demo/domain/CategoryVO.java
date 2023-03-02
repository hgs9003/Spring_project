package com.demo.domain;

import lombok.Data;

@Data
public class CategoryVO {
	
	/*
	 * CREATE TABLE CATEGORY_TBL (
    CATE_CODE       			NUMBER        				    PRIMARY KEY,
    CATE_CODE_PRT     		    NUMBER,                                          
    CATE_NAME           		VARCHAR2(50)         			NOT NULL
);
	 */
	// cate_code, cate_code_prt, cate_name
	

	
	private Integer cate_code;
	private Integer cate_code_prt;
	private String cate_name;
}
