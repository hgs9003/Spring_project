package com.demo.mapper;

import org.apache.ibatis.annotations.Param;

public interface AdPaymentMapper {
	void orderPayTotalPriceChange(@Param("odr_code") Long odr_code, @Param("odr_price") int odr_price);
}
