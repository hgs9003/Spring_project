package com.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.domain.MemberVO;
import com.demo.domain.ReviewVO;
import com.demo.dto.Criteria;
import com.demo.dto.PageDTO;
import com.demo.service.ReviewService;

import lombok.Setter;
@RequestMapping("/review/*")
@RestController
public class ReviewController {
	
	@Setter(onMethod_ = {@Autowired})
	private ReviewService reviewService;
	
	
	@PostMapping(value = "/new", consumes = "application/json", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> create(@RequestBody ReviewVO vo, HttpSession session){
		
		ResponseEntity<String> entity = null;
		
		String mem_id = ((MemberVO) session.getAttribute("loginStatus")).getMem_id();
		vo.setMem_id(mem_id);
		
		reviewService.create(vo);
		
		entity =new ResponseEntity<String>("success", HttpStatus.OK);
		
		return entity;
	}
	
	//댓글목록
	@GetMapping("/list/{pdt_num}/{page}")
	public ResponseEntity<Map<String, Object>> reviewList(@PathVariable("pdt_num")Integer pdt_num, @PathVariable("page")Integer page){
		
		ResponseEntity<Map<String, Object>> entity = null;
		Map<String, Object> map = new HashMap<String, Object>();
		
		//1)댓글 리스트 목록 : List<ReviewVO>
		Criteria cri = new Criteria();
		cri.setPageNum(page);
		
		List<ReviewVO> list = reviewService.list(pdt_num, cri);
		map.put("list", list);
		
		//2) 댓글페이지 정보 : PageDTO
		PageDTO pageMaker = new PageDTO(cri, reviewService.listCount(pdt_num));
		map.put("pageMaker", pageMaker);
		
		entity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		
		return entity;
				
	}
	
	//상품후기 수정
	@PatchMapping(value = "/modify", consumes = "application/json", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> modify(@RequestBody ReviewVO vo, HttpSession session){
		ResponseEntity<String> entity = null;
		
		if(reviewService.update(vo) == 1) {
			entity = new ResponseEntity<String>("success", HttpStatus.OK);
		}else {
			entity = new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	//상품후기 삭제
	
	@DeleteMapping(value = "/delete/{rv_num}")
	public ResponseEntity<String> delete(@PathVariable("rv_num") Long rv_num) {
		
		ResponseEntity<String> entity = null;
		
		if(reviewService.delete(rv_num) == 1) {
			entity = new ResponseEntity<String>("success", HttpStatus.OK);
		}else {
			entity = new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	

}
