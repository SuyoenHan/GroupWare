package com.t1works.groupware.sia.model;

import java.util.List;

public interface InterMyDocumentSiaDAO {

	// 내문서함 - 수신함 - 일반결재문서에 해당하는 문서 조회하기
	List<ApprovalSiaVO> getnorm_reclist(String anocode);

}
