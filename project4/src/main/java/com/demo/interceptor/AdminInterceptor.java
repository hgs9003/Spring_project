package com.demo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.demo.domain.AdminVO;

public class AdminInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		boolean result = false;
		
		HttpSession session = request.getSession();
		
		AdminVO adminVO = (AdminVO) session.getAttribute("adminStatus");
		
		if(adminVO == null) {
			result = false;
			
			if(isAjaxRequest(request)) {
				
				response.sendError(400);
				
			}else {
				getDestination(request);
				
				//System.out.println("다시 로그인주소로");
				
				response.sendRedirect("/admin/");
			}
			
		}else {
			result = true;
		}
		
		return result;
	}

	private void getDestination(HttpServletRequest request) {
		
		String uri = request.getRequestURI();
		String query = request.getQueryString();
		
		if(query == null || query.equals("null")) {
			query = "";
			
		}else {
			query = "?" + query;
		}
		
		String dest = uri + query;
		
		if(request.getMethod().equals("GET")) {
			request.getSession().setAttribute("dest", dest);
		}
		
	}

	private boolean isAjaxRequest(HttpServletRequest request) {
		
		boolean isAjax = false;
		
		String header = request.getHeader("AJAX"); // 클라이언트에서 사용자정의로 추가된 헤더
		if(header != null && header.equals("true")) {
			isAjax = true;
		}
		
		
		return isAjax;
	}

	
}
