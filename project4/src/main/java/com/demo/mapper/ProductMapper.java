package com.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.demo.domain.CategoryVO;
import com.demo.domain.ProductVO;
import com.demo.dto.Criteria;

public interface ProductMapper {

	//1차 카테고리 목록
	List<CategoryVO> getCategoryList();
	
	//1차카테고리를 참조하는 2차카테고리 목록
	List<CategoryVO> getSubCategoryList(Integer cate_code);
	
	//2차카테고리를 참조하는 상품목록.  Mapper Interface의 메서드가 파라미터 2개이상 일경우에는 @Param 어노테이션을 사용해야 한다.(중요)
	List<ProductVO> getProductListbysubCategory(@Param("cate_code") Integer cate_code, @Param("cri") Criteria cri);
	
	//2차카테고리를 참조하는 상품목록의 개수
	int getProductCountbysubCategory(@Param("cate_code") Integer cate_code, @Param("cri") Criteria cri);
	
	//상품상세정보
	ProductVO getProductDetail(Integer pdt_num);
	
}
