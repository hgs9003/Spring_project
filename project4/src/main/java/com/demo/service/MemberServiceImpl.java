package com.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.domain.MemberVO;
import com.demo.mapper.MemberMapper;

import lombok.Setter;

@Service
public class MemberServiceImpl implements MemberService {

	//주입작업
	//@Autowired  롬복사용 안할 경우
	@Setter(onMethod_ = {@Autowired})
	private MemberMapper memberMapper;

	@Override
	public String idCheck(String mem_id) {
		// TODO Auto-generated method stub
		return memberMapper.idCheck(mem_id);
	}

	@Override
	public void join(MemberVO vo) {
		memberMapper.join(vo);
	}

	@Override
	public MemberVO login_ok(String mem_id) {
		// TODO Auto-generated method stub
		return memberMapper.login_ok(mem_id);
	}

	@Override
	public String getIDEmailExists(String mem_id, String mem_email) {
		// TODO Auto-generated method stub
		return memberMapper.getIDEmailExists(mem_id, mem_email);
	}

	@Override
	public void changePW(String mem_id, String enc_pw) {
		// TODO Auto-generated method stub
		memberMapper.changePW(mem_id, enc_pw);
		
	}

	@Override
	public void modify(MemberVO vo) {
		// TODO Auto-generated method stub
		memberMapper.modify(vo);
		
	}

	@Override
	public void loginTimeUpdate(String mem_id) {
		// TODO Auto-generated method stub
		memberMapper.loginTimeUpdate(mem_id);
		
	}

	@Override
	public int getOrderTotalPrice(String mem_id) {
		// TODO Auto-generated method stub
		return memberMapper.getOrderTotalPrice(mem_id);
	}



}
