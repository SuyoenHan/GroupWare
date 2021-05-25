package com.t1works.groupware.jsh.model;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.ResultType;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public class ElectronPayJshDAO implements InterElectronPayJshDAO {


	@Resource
	 private SqlSessionTemplate sqlsession6;  //원격 DB에 연결
	  // 로컬DB에 연결

	
	
	
	///////////////////////////////////////////////////////////////////////
	@Override
	// 일반결재내역 목록 보여주기
		public List<ElectronPayJshVO> generalPayment_List() {
			List<ElectronPayJshVO> electronList = sqlsession6.selectList("payment_Jsh.generalPayment_List");         
			return electronList;
		}
	
	
	
	
	
}
