package com.demo.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demo.domain.CartVO;
import com.demo.domain.CartVOList;
import com.demo.domain.MemberVO;
import com.demo.service.CartService;
import com.demo.util.FileUtils;

import lombok.Setter;



@RequestMapping("/cart/*")
@Controller
public class CartController {

	@Setter(onMethod_ = {@Autowired})
	private CartService cartService;
	
	//업로드폴더 주입
	@Resource(name = "uploadPath") // servlet-context.xml 설정.
	private String uploadPath; // "C:\\doccomsa\\upload\\goods\\"
	
	//장바구니 담기. 
	//장바구니테이블에 사용자가 상품을 장바구니에 추가할 경우, 1) 데이터가 존재를 하지 않으면 데이터 삽입하고  2)존재하면 수량 변경(수정)
	@ResponseBody
	@GetMapping("/cart_add")
	public ResponseEntity<String> cart_add(CartVO vo, HttpSession session){
		
		ResponseEntity<String> entity = null;
		
		//상품코드, 수량
		//세션: 로그인사용자정보
		String mem_id = ((MemberVO)session.getAttribute("loginStatus")).getMem_id();
		vo.setMem_id(mem_id);
		
		cartService.cart_add(vo);
		
		entity = new ResponseEntity<String>("success", HttpStatus.OK);
		
		return entity;
	}
	
	//장바구니 목록
	@GetMapping("/cart_list")
	public String cart_list(HttpSession session, Model model) {
		
		String mem_id = ((MemberVO)session.getAttribute("loginStatus")).getMem_id();
		
		List<CartVOList> cartList = cartService.cart_list(mem_id);
		
		cartList.forEach(vo -> {
			
			vo.setPdt_img_folder(vo.getPdt_img_folder().replace("//", "/"));
		});
		
		model.addAttribute("cartList", cartList);
		
		return "/cart/cartList";
		
	}
	
	//상품목록에서 이미지 보여주기.
	@ResponseBody
	@GetMapping("/displayFile")
	public ResponseEntity<byte[]> displayFile(String folderName, String fileName) throws IOException {
		
		// C:\\doccomsa\\upload\\goods\\2022\\11\\22\\
		return FileUtils.getFile(uploadPath + folderName, fileName);
	}
	
	//장바구니 수량변경
	@ResponseBody
	@GetMapping("/cart_qty_change")
	public ResponseEntity<String> cart_qty_change(@RequestParam("cart_code") Long cart_code,@RequestParam("cart_amount")  int cart_amount) {
		
		ResponseEntity<String> entity = null;
		
		cartService.cart_qty_change(cart_code, cart_amount);
		
		entity = new ResponseEntity<String>("success", HttpStatus.OK);
		
		return entity;
	}
	
	//장바구니 삭제
	@ResponseBody
	@GetMapping("/cart_delete")
	public ResponseEntity<String> cart_delete(@RequestParam("cart_code") Long cart_code) {
		
		ResponseEntity<String> entity = null;
		
		cartService.cart_delete(cart_code);
		entity = new ResponseEntity<String>("success", HttpStatus.OK);
		
		return entity;
	}
	
	//장바구니 비우기
	@GetMapping("/cart_empty")
	public String cart_empty(HttpSession session) {
		
		String mem_id = ((MemberVO)session.getAttribute("loginStatus")).getMem_id();
		
		cartService.cart_empty(mem_id);
		
		return "redirect:/cart/cart_list";
	}
	
	//선택 장바구니삭제
	@ResponseBody
	@PostMapping("/cart_sel_delete")
	public ResponseEntity<String> cart_sel_delete(@RequestParam("cart_code_arr[]") List<Long> cart_code_arr) {
		ResponseEntity<String> entity = null;
		
		//방법1. 하나씩 반복적으로 삭제.
		/*
		for(int i=0; i<cart_code_arr.size(); i++) {
			cartService.cart_delete(cart_code_arr.get(i));
		}
		*/
		
		//방법2. mybatis foreach : https://java119.tistory.com/85
		cartService.cart_sel_delete(cart_code_arr);
		
		entity = new ResponseEntity<String>("success", HttpStatus.OK);
		
		return entity;
	}
	
	
}
