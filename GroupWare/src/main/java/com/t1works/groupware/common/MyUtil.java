package com.t1works.groupware.common;

import javax.servlet.http.HttpServletRequest;

public class MyUtil {
	
	// *** ? 다음의 데이터까지 포함한 현재 URL 주소를 알려주는 메소드를 생성 *** //
	public static String getCurrentURL(HttpServletRequest request) {

		String currentURL = request.getRequestURL().toString();
		
		// getQueryString() 물음표뒤의 값을 읽어온다.(Post방식일 경우에는 null)
		String queryString = request.getQueryString();
		
		if(queryString != null) { // Get방식일 경우
			
			currentURL +="?"+queryString;
		}
			
			String ctxPath = request.getContextPath();
			// /groupware
			
			int beginIndex = currentURL.indexOf(ctxPath) + ctxPath.length();
			
			currentURL =  currentURL.substring(beginIndex+1);
			
			return currentURL;
			
	}// end of public static String getCurrentURL
	
}
	

