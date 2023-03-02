package com.demo.service;

import java.util.List;

import com.demo.domain.CategoryVO;
import com.demo.domain.ProductVO;
import com.demo.dto.Criteria;

public interface ProductService {

	//1차 카테고리 목록
	List<CategoryVO> getCategoryList();
	
	//2차 카테고리를 참조하는 2차 카테고리 목록
	List<CategoryVO> getSubCategoryList(Integer cate_code);
	
	//2차 카테고리를 참조하는 상품목록
	List<ProductVO> getProductListbysubCategory(Integer cate_code, Criteria cri);

	//2차 카테고리를 참조하는 상품목록의 갯수
	int getProductCountbysubCategory(Integer cate_code, Criteria cri);
	
	//상품 상세정보 
	ProductVO getProductDetail(Integer pdt_num);
	
	
		
}
