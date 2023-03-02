package com.demo.service;


import com.demo.domain.MemberVO;

public interface MemberService {
	
	
	String idCheck(String mem_id);
	
	void join(MemberVO vo);
	
	MemberVO login_ok(String mem_id);
	
	String getIDEmailExists(String mem_id, String mem_email);
	
	void changePW(String mem_id, String enc_pw);
	
	void modify(MemberVO vo);
	
	void loginTimeUpdate(String mem_id);
	
	int getOrderTotalPrice(String mem_id);
	


}
