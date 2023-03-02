package com.demo.domain;

import java.util.Date;

import lombok.Data;

@Data
public class MemberVO {

	private String mem_id;
	private String mem_name;
	private String mem_pw;
	private String mem_email;
	private String mem_zipcode;
	private String mem_addr;
	private String mem_addr_d;
	private String mem_phone;
	private String mem_nick;
	private String mem_accept_e; // 이메일 수신여부
	private int mem_point; // 포인트적립
	private Date mem_date_sub; // 등록날짜
	private Date mem_date_up; // 수정날짜
	private Date mem_date_last; //최신로그인 시간
	private String mem_authcode; //인증 
}
