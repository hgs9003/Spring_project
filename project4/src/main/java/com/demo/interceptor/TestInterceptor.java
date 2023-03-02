package com.demo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import lombok.extern.log4j.Log4j;

// 인터셉터 기능을 갖는 클래스는 HandlerInterceptorAdapter 추상클래스를 상속받아야 한다.
// 다음 클래스를 동작시키기 위해서는 servlet-context.xml에서 설정을 해야 한다.
@Log4j
public class TestInterceptor extends HandlerInterceptorAdapter {

	// 클라이언트 매핑주소 :    /test/testA 
	
	// 인터셉터가 발생이 되면
	/* preHandle() 호출 리턴값이 true -> TestController의 testA()메서드가 호출  
	   -> postHandle() 호출 -> /test/testA주소에 해당하는 jsp실행(뷰작업) -> afterCompletion() 호출
	*/
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		//log.info("첫번째 호출 메서드 : preHandle");
		System.out.println("첫번째 호출 메서드 : preHandle");
		return super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		//log.info("세번째 호출 메서드 : postHandle");
		System.out.println("세번째 호출 메서드 : postHandle");
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		//log.info("네번째 호출 메서드 : afterCompletion");
		System.out.println("네번째 호출 메서드 : afterCompletion");
		super.afterCompletion(request, response, handler, ex);
	}

	
}
