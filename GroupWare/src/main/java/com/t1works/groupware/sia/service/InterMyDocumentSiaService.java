package com.t1works.groupware.sia.service;

import java.util.List;
import java.util.Map;

import com.t1works.groupware.sia.model.ApprovalSiaVO;

public interface InterMyDocumentSiaService {

	// 내문서함 - 수신함 - 일반결재문서에 해당하는 문서 조회하기
	List<ApprovalSiaVO> getnorm_reclist(Map<String, String> paraMap);

}
