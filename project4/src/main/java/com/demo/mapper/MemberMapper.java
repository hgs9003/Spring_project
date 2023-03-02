package com.demo.mapper;

import org.apache.ibatis.annotations.Param;

import com.demo.domain.MemberVO;

public interface MemberMapper {
	
	// 아이디 중복체크 
	String idCheck(String mem_id);
	
	//회원정보 저장 
	void join(MemberVO vo);
	
	//로그인 인증
	MemberVO login_ok(String mem_id);
	
	//아이디와 이메일정보 일치여부
	String getIDEmailExists(@Param("mem_id")String mem_id, @Param("mem_email")String mem_email);
	
	//임시비밀번호를 암호화하여 변경
	void changePW(@Param("mem_id")String mem_id, @Param("enc_pw")String enc_pw);
	
	//회원정보 수정
	void modify(MemberVO vo);
	
	//로그인 시간 업데이트
	void loginTimeUpdate(String mem_id);
	
	//총 주문금액(배송완료기준)
	int getOrderTotalPrice(String mem_id);
}
