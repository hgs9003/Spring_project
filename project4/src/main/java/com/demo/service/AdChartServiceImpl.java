package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.domain.ChartCartVO;
import com.demo.mapper.AdChartMapper;

import lombok.Setter;

@Service
public class AdChartServiceImpl implements AdChartService {
	
	@Setter(onMethod_ = {@Autowired})
	private AdChartMapper adChartMapper;

	@Override
	public List<ChartCartVO> chartCartProductList() {
		// TODO Auto-generated method stub
		return adChartMapper.chartCartProductList();
	}

}
