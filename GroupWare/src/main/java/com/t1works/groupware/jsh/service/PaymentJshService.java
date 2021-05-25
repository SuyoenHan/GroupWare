package com.t1works.groupware.jsh.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


import com.t1works.groupware.jsh.model.ElectronPayJshVO;
import com.t1works.groupware.jsh.model.InterElectronPayJshDAO;


@Component
@Service
public class PaymentJshService implements InterPaymentJshService {

	@Autowired
	private InterElectronPayJshDAO dao;

	// 일반결재내역 목록 보여주기
	@Override
	public List<ElectronPayJshVO> generalPayment_List() {
		List<ElectronPayJshVO>electronList = dao.generalPayment_List();
		return electronList ;
	}

	
	
}
