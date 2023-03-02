package com.demo.domain;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class ProductVO {
	
		private Integer pdt_num;
		private Integer cate_code; //2차 카테고리
		private Integer cate_code_prt; // 1차 카테고리 
		private String pdt_name;
		private int pdt_price;
		private int pdt_discount;
		private String pdt_company;
		private String pdt_detail;
		private String pdt_img_folder;
		private String pdt_img;
		private int pdt_amount;
		private String pdt_buy;
		private Date pdt_date_sub;
		private Date pdt_date_up;
		
		//파일 업로드 필드(상품이미지). <input type = "file" class = "from-controll" id = "uploadFile" name="uploadFile">
		private MultipartFile uploadFile;
	
	
	

}
