package com.t1works.groupware.kdn.model;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class EmailKdnDAO implements InterEmailKdnDAO {
	
	@Resource
	private SqlSessionTemplate sqlsession3; // 원격DB에 연결

}
