package com.t1works.groupware.bwb.service;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.t1works.groupware.bwb.model.MemberBwbVO;


@Component
@Service
public class MemberBwbService implements InterMemberBwbService {
	
	@Resource
	private InterMemberBwbDAO dao;

	@Override
	public MemberBwbVO selectMember(Map<String, String> paraMap) {
		
		MemberBwbVO mvo = dao.selectMember(paraMap);
		
		return mvo;
	}

	
	
}
