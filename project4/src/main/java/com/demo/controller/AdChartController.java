package com.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.domain.ChartCartVO;
import com.demo.service.AdChartService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@RequestMapping("/admin/chart/*")
@Controller
public class AdChartController {

	@Setter(onMethod_ = {@Autowired})
	private AdChartService adChartService;
	
	@GetMapping("/cartProductPrice")
	public void chartCartProductList(Model model) {
		
		String chartCartProductData = "[";
		chartCartProductData += "['상품명', '금액'],";
		
		List<ChartCartVO> cartProductList = adChartService.chartCartProductList();
		
		int i = 0;
		for(ChartCartVO vo : cartProductList) {
			chartCartProductData += "['" + vo.getPdt_name() + "'," + vo.getPrice() + "]";
			i++;
			//마지막 요소와 데이타는 진행되지 않는다.
			if(i > cartProductList.size() ) chartCartProductData += ",";
		}
		
		chartCartProductData += "]";
		
		log.info("차트데이터: " + chartCartProductData);
		
		model.addAttribute("data", chartCartProductData);
		
	}
}
