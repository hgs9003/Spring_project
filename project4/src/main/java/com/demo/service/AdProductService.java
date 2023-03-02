package com.demo.service;

import java.util.List;

import com.demo.domain.CategoryVO;
import com.demo.domain.ProductVO;
import com.demo.dto.Criteria;

public interface AdProductService {

		List<CategoryVO> getCategoryList();
		
		List<CategoryVO> getSubCategoryList(Integer cate_code);

		void productInsert(ProductVO vo);
		
		List<ProductVO> getProductList(Criteria cri);
		
		int getProductTotalCount(Criteria cri);
		
		ProductVO getProductBynum(Integer pdt_num);
		
		void productModify(ProductVO vo);
		
		void productDelete(Integer pdt_num);
		
		void btnSalesYN(Integer pdt_num, String pdt_buy);

}
