package com.demo.service;

import java.util.List;

import com.demo.domain.CartVO;
import com.demo.domain.CartVOList;

public interface CartService {

	
	//장바구니 추가
	void cart_add(CartVO vo);
	
	List<CartVOList> cart_list(String mem_id);
	
	void cart_qty_change(Long cart_code, int cart_amount);
	
	void cart_delete(Long cart_code);
	
	void cart_empty(String mem_id);
	
	void cart_sel_delete(List<Long>cart_cpde_arr);
	
	int getCartProductCountByUserID(String mem_id);
	
	
}
