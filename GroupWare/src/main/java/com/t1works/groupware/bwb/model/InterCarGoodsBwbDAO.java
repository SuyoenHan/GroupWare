package com.t1works.groupware.bwb.model;

import java.util.List;

public interface InterCarGoodsBwbDAO {
	
	// 미승인된 차량예약정보 가져오기
	List<CarGoodsBwbVO> selectCarGoods();

}
