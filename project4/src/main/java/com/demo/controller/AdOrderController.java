package com.demo.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.demo.domain.OrderDetailProductVO;
import com.demo.domain.OrderDetailVOList;
import com.demo.domain.OrderVO;
import com.demo.dto.Criteria;
import com.demo.dto.PageDTO;
import com.demo.service.AdOrderService;
import com.demo.util.FileUtils;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@RequestMapping("/admin/order/*")
@Controller
public class AdOrderController {
	
		@Setter(onMethod_ = {@Autowired})
		private AdOrderService adOrderService;
		
		//업로드폴더 주입 
		@Resource(name = "uploadPath")
		private String uploadPath; // "C:\\doccomsa\\upload//goods\\"
		
		//주문목록. 클라이언트에서 페이지번호 클릭, 검색클릭, 주문상태 버튼 클릭
		@GetMapping("/orderList")
		public void orderList(
				Criteria cri,
				@ModelAttribute("startDate") String startDate,
				@ModelAttribute("endDate")String endDate,
				@ModelAttribute("odr_status")String odr_status,
				Model model) {
			
			//주문목록
			List<OrderVO> orderList = adOrderService.getOrderList(cri, startDate, endDate, odr_status);
			model.addAttribute("orderList", orderList);
			
			//페이징정보
			int totalCount = adOrderService.getOrderTotalCount(cri, startDate, endDate, odr_status);
			model.addAttribute("pageMaker", new PageDTO(cri, totalCount));
			
			//진행상태 카운트
			model.addAttribute("orderStatus1", adOrderService.orderStatusCount("주문접수"));
			model.addAttribute("orderStatus2", adOrderService.orderStatusCount("결제완료"));
			model.addAttribute("orderStatus3", adOrderService.orderStatusCount("배송준비중"));
			model.addAttribute("orderStatus4", adOrderService.orderStatusCount("배송처리"));
			model.addAttribute("orderStatus5", adOrderService.orderStatusCount("배송완료"));
			model.addAttribute("orderStatus6", adOrderService.orderStatusCount("주문취소"));
			model.addAttribute("orderStatus7", adOrderService.orderStatusCount("미주문"));
			model.addAttribute("orderStatus8", adOrderService.orderStatusCount("취소요청"));
			model.addAttribute("orderStatus9", adOrderService.orderStatusCount("취소완료"));
			model.addAttribute("orderStatus10", adOrderService.orderStatusCount("교환요청"));
			model.addAttribute("orderStatus11", adOrderService.orderStatusCount("교환완료"));
			
		}
		
		//주문상태변경
		@GetMapping("/orderStatusChange")
		@ResponseBody //서버의 결과를 클라이언트로 보낼때 사용
		public ResponseEntity<String> orderStatusChange(Long odr_code, String odr_status){
			
			ResponseEntity<String> entity = null;
			
			adOrderService.orderStatusChange(odr_code, odr_status);
			
			entity = new ResponseEntity<String>("success", HttpStatus.OK);
			
			return entity;
		}
		
		@PostMapping("/orderCheckedStateChange")
		@ResponseBody
		public ResponseEntity<String> orderCheckedStateChange(@RequestParam("odr_code_arr[]") List<Long> odr_code_arr,
				@RequestParam("odr_status_arr[]") List<String> odr_status_arr){
			log.info("선택주문번호: " + odr_code_arr);
			log.info("선택주문변경: " + odr_status_arr);
			
			ResponseEntity<String> entity = null;
			
			for(int i=0; i<odr_code_arr.size(); i++) {
				adOrderService.orderStatusChange(odr_code_arr.get(i), odr_status_arr.get(i));
			}
			
			entity = new ResponseEntity<String>("success", HttpStatus.OK);
			
			return entity;
		}
		
		//클라이언트의 ajax기능의 load()메서드로부터 요청받아 jsp의 실행결과가 보내진다.
		//주문상세보기1
		@GetMapping("/orderDetail1")
		public void orderDetail1(Long odr_code, Model model) {
			
			List<OrderDetailVOList> odList = adOrderService.getOrderDetailList1(odr_code);
			
			odList.forEach(vo -> {
				vo.setPdt_img_folder(vo.getPdt_img_folder().replace("\\", "/"));
				
				
			});
			
			//주문상세정보. OrderDetailVO
			model.addAttribute("odList1", odList);
			//결제정보. PaymentVO
			model.addAttribute("paymentVO", adOrderService.getPayment(odr_code));
			//주문정보. OrderVO
			model.addAttribute("orderVO", adOrderService.getOrder(odr_code));
			//관리자메모
			log.info("주문상세내역");
		}
		
		// 주문 상세보기2. resultMap
		@GetMapping("/orderDetail2")
		public void orderDetail2(Long odr_code, Model model) {
			
			List<OrderDetailProductVO> odList = adOrderService.getOrderDetailList2(odr_code);
			
			odList.forEach(vo -> {
				vo.getProductVO().setPdt_img_folder(vo.getProductVO().getPdt_img_folder().replace("\\", "/"));
			});
			
					
			//주문상세정보. OrderDetailVO
			model.addAttribute("odList2", odList);
			//결제정보.  PaymentVO
			model.addAttribute("paymentVO", adOrderService.getPayment(odr_code));
			//주문정보.  OrderVO
			model.addAttribute("orderVO", adOrderService.getOrder(odr_code));
			//관리자메모
			
			log.info("주문상세내역");
		}
		
		
		

		
		//상품목록에서 이미지 보여주기
		@ResponseBody
		@GetMapping("/displayFile")
		public ResponseEntity<byte[]> displayFile(String folderName, String fileName) throws IOException{
			
			// C:\\doccomsa\\upload\\goods\\2022\\11\\22\\
			return FileUtils.getFile(uploadPath + folderName, fileName);
		}
		
		//관리자 메모 
		@PostMapping("/pay_memo")
		@ResponseBody
		public ResponseEntity<String> pay_memo(Integer pay_code, String pay_memo){
			ResponseEntity<String> entity = null;
			
			//작업
			adOrderService.pay_memo(pay_code, pay_memo);
			
			entity = new ResponseEntity<String>("success", HttpStatus.OK);
			return entity;
		}
		
		//주문삭제
		@GetMapping("/orderDelete")
		public String orderDelete(Criteria cri, Long odr_code, RedirectAttributes rttr) {
			
			adOrderService.orderInfoDelete(odr_code);
			
			rttr.addFlashAttribute("msg", "orderDelete");
			
			return "redirect:/admin/order/orderList" + cri.getListLink();
		}
		
		//주문상품 개별삭제
		@GetMapping("/orderDetailProductDelete")
		@ResponseBody
		public ResponseEntity<String> orderDetailProductDelete(Long odr_code, Integer pdt_num, int odr_price){
			
			ResponseEntity<String> entity = null;
			
			//작업 
			adOrderService.orderDetailProductDelete(odr_code, pdt_num, odr_price);
			
			entity = new ResponseEntity<String>("success", HttpStatus.OK);
			return entity;
		}
		
}
