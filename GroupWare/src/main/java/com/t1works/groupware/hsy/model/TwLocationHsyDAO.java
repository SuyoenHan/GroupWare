package com.t1works.groupware.hsy.model;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public class TwLocationHsyDAO implements InterTwLocationHsyDAO {

	@Autowired 
	private SqlSessionTemplate sqlsession2;

	// 회사위치테이블에 있는 정보 가져오기  
	@Override
	public List<TwLocationHsyVO> twLocationAjax() {

		List<TwLocationHsyVO> twLocationList= sqlsession2.selectList("twLocationHsy.twLocationAjax");
		return twLocationList;
	}

	
}
