package com.t1works.groupware.bwb.model;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CarGoodsBwbDAO implements InterCarGoodsBwbDAO {
	
	@Resource
    private SqlSessionTemplate sqlsession;
	
	
	// 미승인된 차량예약정보 가져오기
	@Override
	public List<CarGoodsBwbVO> selectCarRental() {
		
		List<CarGoodsBwbVO> carList = sqlsession.selectList("productBwb.selectCarRental");
		return carList;
	}

	// 미승인된 사무용품에약정보 가져오기
	@Override
	public List<CarGoodsBwbVO> selectGoodsRental() {
		
		List<CarGoodsBwbVO> goodsList = sqlsession.selectList("productBwb.selectGoodsRental");
		return goodsList;
	}
	
	// 승인버튼 클릭시 status update처리(차량)
	@Override
	public int updateCarRental(String rscno) {
		int n = sqlsession.update("productBwb.updateCarRental", rscno);
		return n;
	}
	
	// 승인버튼 클릭시 status update처리(사무용품)
	@Override
	public int updateGoodsRental(String rsgno) {
		int n = sqlsession.update("productBwb.updateGoodsRental", rsgno);
		return n;
	}
	
}
