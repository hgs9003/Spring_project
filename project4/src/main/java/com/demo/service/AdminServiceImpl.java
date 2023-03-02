package com.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.domain.AdminVO;
import com.demo.mapper.AdminMapper;

import lombok.Setter;

@Service
public class AdminServiceImpl implements AdminService {

	
	//mapper 주입
	@Setter(onMethod_ = {@Autowired})
	private AdminMapper adminMapper;
	
	@Override
	public AdminVO admin_ok(String admin_id) {
		// TODO Auto-generated method stub
		return adminMapper.admin_ok(admin_id);
	}

	@Override
	public void login_update(String admin_id) {
		// TODO Auto-generated method stub
		adminMapper.login_update(admin_id);
		
	}

}
