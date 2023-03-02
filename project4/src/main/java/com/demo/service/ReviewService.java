package com.demo.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.demo.domain.ReviewVO;
import com.demo.dto.Criteria;

public interface ReviewService {
	
	void create(ReviewVO vo);
	
	List<ReviewVO> list(Integer pdt_num, Criteria cri);
	
	int listCount(Integer pdt_num);
	
	int delete(Long rv_num);
	
	int update(ReviewVO vo);

}
