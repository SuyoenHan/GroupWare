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
	public List<CarGoodsBwbVO> selectCarGoods() {
		
		List<CarGoodsBwbVO> carList = sqlsession.selectList("productBwb.selectCarGoods");
		return carList;
	}
	
}
