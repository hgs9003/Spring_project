package com.demo.service;

import com.demo.dto.EmailDTO;

public interface EmailService {

	
	void sendMail(EmailDTO dto, String message);
	
}
