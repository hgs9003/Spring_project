package com.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.demo.domain.AdminVO;
import com.demo.service.AdminService;

import lombok.Setter;


@RequestMapping("/admin/*")
@Controller
public class AdminController {
	//service주입
	@Setter(onMethod_ = {@Autowired})
	private AdminService adminService;
	
	//주입작업. spring-security.xml의 "bCryptPasswordEncoder" bean 주입을 받음.
	@Setter(onMethod_ = {@Autowired})
	private PasswordEncoder passwordEncoder;
	
	//관리자 로그인페이지
	@GetMapping("")
	public String adLogin() {
		
		return "/admin/adLogin";
	}
	
	@GetMapping("/admin_menu")
	public String admin_menu() {
		
		return "/admin/admin_menu";
	}
	
	@PostMapping("/admin_ok")
	public String admin_ok(AdminVO vo, HttpSession session, RedirectAttributes rttr) {
		
		AdminVO adminVO = adminService.admin_ok(vo.getAdmin_id());
		
		String url = "";
		String msg = "";
		if(adminVO != null) {
			if(passwordEncoder.matches(vo.getAdmin_pw(), adminVO.getAdmin_pw())) {
				
				// 일반회원 로그인에 사용한 세션이름과 다르게 해야 한다.
				session.setAttribute("adminStatus", adminVO);
				
				url = "/admin/admin_menu";
				msg = "관리자로그인 성공";
				

				
				
			}else {
				url = "/admin/";
				msg = "관리자 비밀번호가 일치하지 않습니다.";
			}
		}else {
			url = "/admin/";
			msg = "관리자 아이디가 일치하지 않습니다.";
		}
		
		rttr.addFlashAttribute("msg", msg);
		
		return "redirect:" + url;
	}
	
	//로그아웃
	@GetMapping("/logout")
	public String logout(HttpSession session, RedirectAttributes rttr) {
		
		
		session.invalidate();
		
		return "redirect:/admin/";
	}
}
