package com.demo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.demo.domain.MemberVO;

// 설정된 매핑주소의 요청이 발생되면, 아래 인터셉터 클래스는 동작한다.
// 매핑주소의 성격은 로그인 인증만 사용이 가능한 주소.
public class UserInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		boolean result = false;
		
		// 클라이언트의 요청이 인증이 되었는 지 여부를 체크(세션객체를 확인)
		HttpSession session = request.getSession();
		MemberVO user = (MemberVO) session.getAttribute("loginStatus");
		
		if(user == null) {  // 인증정보가 존재하지 않을 때.
			result = false;
			
			// 클라이언트 요청이 ajax(비동기요청) 인지 동기요청인지 체크
			if(isAjaxRequest(request)) {  // ajax요청 인 경우
				
				//  ajax요청은 콜백기능을 가지고 있어, 다시 클라이언트의 콜백으로 제어가 동작되어지는 특징을 가지고 있으므로
				// 스프링의 작업이 진행되지 않는다.
				//response.sendRedirect("/member/login");
				System.out.println("Ajax요청");
				response.sendError(400);
			}else {  // 동기요청 인 경우. 로그인이 필요한 주소를 요청
				// 1)로그인을 안한 경우 2)세션소멸된 경우
				getDestination(request);
				
				
				response.sendRedirect("/member/login");  // 
			}
		}else {  // 인증정보가 존재
			result = true; 
		}
		
		/*
		 1) return true;   // 컨트롤러의 요청주소로 제어 
		 2) return false;  // 스프링에서 진행이 끝남. 
		 */
		
		
		return result;
	}

	// 인증되지 않은 상태에서 인증된 사용자만 접근하는 주소의 정보를 저장하는 목적.
	private void getDestination(HttpServletRequest request) {
		
		// 요청주소: /cart/cart_list?pdt_num=10
		String uri = request.getRequestURI();   //  /cart/cart_list
		String query = request.getQueryString();   //  ?물음표 뒤에 파라미터=값     pdt_num=10
		
		if(query == null || query.equals("null")) {
			query = "";
		}else {
			query = "?" + query;
		}
		
		String destination = uri + query; //  /cart/cart_list?pdt_num=10
		
		if(request.getMethod().equals("GET")) {
			request.getSession().setAttribute("dest", destination);
		}
	}

	private boolean isAjaxRequest(HttpServletRequest request) {
		
		boolean isAjax = false;
		
		String header = request.getHeader("AJAX");
		if(header != null && header.equals("true")) {
			isAjax = true;
		}
		
		
		return isAjax;
	}

	
}
