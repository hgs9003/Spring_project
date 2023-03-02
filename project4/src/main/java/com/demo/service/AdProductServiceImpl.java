package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.domain.CategoryVO;
import com.demo.domain.ProductVO;
import com.demo.dto.Criteria;
import com.demo.mapper.AdProductMapper;

import lombok.Setter;

@Service
public class AdProductServiceImpl implements AdProductService {

	@Setter(onMethod_ = {@Autowired})
	private AdProductMapper adProductMapper;
	
	
	
	@Override
	public List<CategoryVO> getCategoryList() {
		// TODO Auto-generated method stub
		return adProductMapper.getCategoryList();
	}



	@Override
	public List<CategoryVO> getSubCategoryList(Integer cate_code) {
		// TODO Auto-generated method stub
		return adProductMapper.getSubCategoryList(cate_code);
	}



	@Override
	public void productInsert(ProductVO vo) {
		// TODO Auto-generated method stub
		adProductMapper.productInsert(vo);
		
	}



	@Override
	public List<ProductVO> getProductList(Criteria cri) {
		// TODO Auto-generated method stub
		return adProductMapper.getProductList(cri);
	}



	@Override
	public int getProductTotalCount(Criteria cri) {
		// TODO Auto-generated method stub
		return adProductMapper.getProductTotalCount(cri);
	}



	@Override
	public ProductVO getProductBynum(Integer pdt_num) {
		// TODO Auto-generated method stub
		return adProductMapper.getProductByNum(pdt_num);
	}



	@Override
	public void productModify(ProductVO vo) {
		// TODO Auto-generated method stub
		 adProductMapper.productModify(vo);
		
	}



	@Override
	public void productDelete(Integer pdt_num) {
		// TODO Auto-generated method stub
		adProductMapper.productDelete(pdt_num);
		
	}



	@Override
	public void btnSalesYN(Integer pdt_num, String pdt_buy) {
		// TODO Auto-generated method stub
		adProductMapper.btnSalesYN(pdt_num, pdt_buy);
		
	}

}
