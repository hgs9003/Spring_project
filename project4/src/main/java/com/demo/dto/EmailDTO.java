package com.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EmailDTO {
	
	private String senderName;  //발신자이름
	private String senderMail; 	//발신자 메일주소 
	private String receiverMail; //수신자 메일주소 . 회원메일 주소로 사용
	private String subject; // 제목
	private String message; //본문 
	
	//생성자 메소드
	public EmailDTO() { 
		this.senderName = "mall";
		this.senderMail = "mall";
		this.subject = "mall 회원가입 메일인증코드입니다";
		this.message = "메일 인증을 위한 인증코드를 확인하시고, 회원가입시 인증코드 입력란에 입력하세요";
	}

}
