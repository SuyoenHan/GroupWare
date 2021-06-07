package com.t1works.groupware.bwb.model;

import java.util.List;

public interface InterCarGoodsBwbDAO {
	
	// 미승인된 차량예약정보 가져오기
	List<CarGoodsBwbVO> selectCarRental();
	
	// 미승인된 사무용품에약정보 가져오기
	List<CarGoodsBwbVO> selectGoodsRental();
	
	// 승인버튼 클릭시 status update처리(차량)
	int updateCarRental(String rscno);
	
	// 승인버튼 클릭시 status update처리(사무용품)
	int updateGoodsRental(String rsgno);

}
