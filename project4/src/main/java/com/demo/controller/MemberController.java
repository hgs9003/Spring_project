package com.demo.controller;

import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.demo.domain.MemberVO;
import com.demo.dto.EmailDTO;
import com.demo.dto.LoginDTO;
import com.demo.service.CartService;
import com.demo.service.EmailService;
import com.demo.service.MemberService;
import com.demo.service.OrderService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@RequestMapping("/member/*")
@Controller
public class MemberController {
	
	//주입작업
	@Setter(onMethod_ = {@Autowired})
	private OrderService orderService;
	
	//주입작업
	@Setter(onMethod_ = {@Autowired})
	private CartService cartService;
	
	//주입작업
	@Setter(onMethod_ = {@Autowired})
	private PasswordEncoder passwordEncoder;
	
	
	// 이메일 작업 주입작업
	   @Setter(onMethod_ = {@Autowired})
	   private EmailService emailService;
	

	//주입작업
	@Setter(onMethod_ = {@Autowired})
	private MemberService memberService;
	
	
	

	
	//회원가입 폼
	@GetMapping("/join")
	public void join() {
		//log.info("회원가입");
	}
	
	
	//회원정보저장
	@PostMapping("/join")
	public String join(MemberVO vo, RedirectAttributes rttr) {
		
		// 평문텍스ㅡ(암호화되지 않은) 비밀번호 --> 암호화
		//1234 --> 암호화(60byte) 여러번 해도 같은 내용을 암호화되지 않는다
		String cryptEncoderPw = passwordEncoder.encode(vo.getMem_pw());
		
		vo.setMem_pw(cryptEncoderPw); // 암호화된 정보가 vo안에 들어가게 된다.
		
		if(vo.getMem_accept_e() != null && vo.getMem_accept_e().equals("on")) {
			vo.setMem_accept_e("Y");
		}else {
			vo.setMem_accept_e("N");
		}
		
		memberService.join(vo);
		
		return "redirect:/member/login";
	}
	
	   // 아이디 중복체크 
	   @ResponseBody // ajax요청일 경우 사용한다.
	   @GetMapping("/idCheck")
	   public ResponseEntity<String> idCheck(@RequestParam("mem_id") String mem_id){
	      ResponseEntity<String> entity = null;
	      
	      String isUseID = "";
	      if(memberService.idCheck(mem_id) != null) {
	         isUseID = "no"; // 아이디가 사용중이다. 중복아이디
	      }else {
	         isUseID = "yes"; // 아이디 사용이 가능하다.
	      }
	      
	      entity = new ResponseEntity<String>(isUseID, HttpStatus.OK);
	      
	      return entity;
	      
	   }
	   
		// 로그인 폼
		@GetMapping("/login")
		public void login() {
			log.info("로그인폼");
		}
	   
	   
	   
	   // 로그인 인증
	   @PostMapping("/loginPost")
	   public String loginPost(LoginDTO dto, HttpSession session, RedirectAttributes rttr) {
		   
		   MemberVO vo = memberService.login_ok(dto.getMem_id());
		   
		   String url = "";
		   String msg = "";
		   
		   if(vo !=null) { //아이디가 일치
			   String passwd = dto.getMem_pw(); // 사용자가 입력한 비밀번호
			   String db_passwd = vo.getMem_pw(); //DB에서 가져온 암호화된 비밀번호
			   
			   if(passwordEncoder.matches(passwd, db_passwd)) { //비번이 일치됨
				   session.setAttribute("loginStatus", vo);
				   
				   String dest = (String) session.getAttribute("dest");
				   url = (dest != null) ? dest : "/";
				   msg = "로그인 되었습니다.";
			   }else { //비번이 일치하지 않음 
				   url = "/member/login";
				   msg = "비번이 일치하지 않습니다.";
			   }
		   }else { // 아이디가 일치하지 않음.
			   url = "/member/login";
			   msg = "아이디가 일치하지 않습니다.";
			   
		   }
		   
		   rttr.addFlashAttribute("msg", msg);
		   
		   return "redirect:" + url;
	   }
	   
	   //로그아웃
	   @GetMapping("/logout")
	   public String logout(HttpSession session, RedirectAttributes rttr) {
		   
		   session.invalidate(); // 세션 소멸
		   rttr.addFlashAttribute("msg", "로그아웃됨");
		   
		   return "redirect:/";
				   
	   }
	   
	   //아이디 , 비번찾기 폼
	   @GetMapping("lostpass")
	   public String searchPw(@RequestParam("mem_id")String mem_id, @RequestParam("mem_email")String mem_email, RedirectAttributes rttr) {
		   
		   //1. 아이디와 이메일정보가 일치되는지 존재유무 확인
		   String db_mem_id = memberService.getIDEmailExists(mem_id, mem_email);
		   String temp_pw = "";
		   
		   String url = "";
		   String msg = "";
		   if(db_mem_id != null) {
			   //2. 임시비밀번호 생성
			   UUID uid = UUID.randomUUID();
			   
			   log.info("임시비번:" + uid);
			   
			   temp_pw = uid.toString().substring(0, 6);  //6자리.
			   
			   //3. 사용자의 회원정보 비밀번호를 암호화하여 DB에 수정작업
			   memberService.changePW(db_mem_id, passwordEncoder.encode(temp_pw));
			   
			   //4. 사용자에게 임시비밀번호 메일발송
			   EmailDTO dto = new EmailDTO("Project2", "Project2", mem_email, "Project2 임시비밀번호입니다.", "");
			   
			   try {
				   emailService.sendMail(dto, temp_pw);
				   url = "member/login";
				   msg = "메일이 발송되었습니다.";
				   
			   }catch(Exception ex) {
				   ex.printStackTrace();
			   }
		   }else {
			   url = "/member/lostpass";
			   msg = "입력하신 정보가 일치하지 않습니다. 확인해주세요.";
		   }
			   rttr.addFlashAttribute("msg", msg);
			   
			   return "redirect:" + url;
		   }
		   
		   @GetMapping("/confirmPW")
		   public void confirmPW() {
			   
		   }
		   
		   @PostMapping("/confirmPW")
		   public String confirmPW(String mem_pw, HttpSession session, RedirectAttributes rttr) {
			   
			   //로그인 상태에서 세션을 통하여, 사용자 아이디를 참조할수가 있다.
			   String mem_id = ((MemberVO)session.getAttribute("loginStatus")).getMem_id();
			   
			   MemberVO vo = memberService.login_ok(mem_id);
			   
			   String url = "";
			   String msg = "";
			   
			   if(vo != null) {
				   
				   String db_passwd = vo.getMem_pw(); //DB에서 가져온 암호화된 비밀번호
				   
				   if(passwordEncoder.matches(mem_pw, db_passwd)) { //비번이 일치됨 
					   url = "/member/modify";
				   }else { //비번이 일치되지 않음
					   url = "/member/confirmPW";
					   msg = "비밀번호가 일치하지 않습니다 ";
				   }
			   }
			   
			   rttr.addFlashAttribute("msg", msg);
			   
			   return "redirect:" + url;
			   
		   }
		   
		   @GetMapping("/modify")
		   public void modify(Model model, HttpSession session) {
			   
			   String mem_id = ((MemberVO) session.getAttribute("loginStatus")).getMem_id();
			   
			   MemberVO vo = memberService.login_ok(mem_id);
			   
			   model.addAttribute("membervo", vo);
		   }
		   
		   @PostMapping("/modify")
		   public String modify(MemberVO vo, HttpSession session, RedirectAttributes rttr) {
			   
			   String enc_mem_pw = ((MemberVO)session.getAttribute("loginStatus")).getMem_pw();
			   
			   String url = "";
			   String msg = "";
			   
			   //비밀번호를 보안관점에서 다시 확인.
			   if(passwordEncoder.matches(vo.getMem_pw(), enc_mem_pw)) {
				   memberService.modify(vo);
				   url = "/";
				   msg = "회원정보 수정됨.";
			   }else {
				   url = "/member/modify";
				   msg = "비밀번호가 일치하지 않습니다.";
			   }
			   
			   rttr.addFlashAttribute("msg", msg);
			   
			   return "redirect:" + url;
		   }
		   
			@GetMapping("/mypage")
			public void mypage(HttpSession session, Model model) {
				String mem_id = ((MemberVO) session.getAttribute("loginStatus")).getMem_id();
				
				model.addAttribute("odr_totalPrice", memberService.getOrderTotalPrice(mem_id));
				model.addAttribute("cartProductCount", cartService.getCartProductCountByUserID(mem_id));
				model.addAttribute("orderProcessCount", orderService.getOrderProcessCount(mem_id));
			}
}
