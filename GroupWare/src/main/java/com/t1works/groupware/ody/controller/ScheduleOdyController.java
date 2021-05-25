package com.t1works.groupware.ody.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.t1works.groupware.ody.model.ScalCategoryOdyVO;
import com.t1works.groupware.ody.model.ScheduleOdyVO;
import com.t1works.groupware.ody.service.InterScheduleOdyService;


@Component
@Controller
public class ScheduleOdyController {
	
	@Autowired
	private  InterScheduleOdyService service;
	
	@RequestMapping(value="/t1/schedule.tw")
	public String requiredLogin_showSchedule(HttpServletRequest request, HttpServletResponse response) {
	
		return "ody/schedule/showSchedule.gwTiles";
	}
	
	// 전체 캘린더를 불러오는것(추후 내 캘린더, 사내캘린더 나눠서 불러올 수 있도록 해야함)
	@ResponseBody
	@RequestMapping(value="/t1/selectSchedule.tw", produces="text/plain;charset=UTF-8")
	public String selectSchedule(HttpServletRequest request) {
		// 등록된 일정 가져오기
		
				String fk_employeeid = request.getParameter("fk_employeeid");
				String fk_bcno = request.getParameter("fk_bcno");
				
				Map<String,String> paraMap = new HashMap<>();
				paraMap.put("fk_employeeid",fk_employeeid);
				paraMap.put("fk_bcno",fk_bcno);
				
			//	System.out.println(fk_employeeid);
			//	System.out.println(fk_bcno);
				List<ScheduleOdyVO> scheduleList = service.getScheduleList(paraMap);
				JSONArray jsArr = new JSONArray();
				if(scheduleList!=null) {
					for(ScheduleOdyVO svo : scheduleList) {
						JSONObject jsObj = new JSONObject();
						jsObj.put("subject", svo.getSubject());
						jsObj.put("startdate", svo.getStartdate());
						jsObj.put("enddate", svo.getEnddate());
						jsObj.put("color", svo.getColor());
						jsObj.put("sdno", svo.getSdno());
						jsArr.put(jsObj);
					}
				}
				
				return jsArr.toString();
	}
	
	@RequestMapping(value="/t1/insertSchedule.tw", method = {RequestMethod.POST})
	public String requiredLogin_insertSchedule(HttpServletRequest request, HttpServletResponse response) {
		
		// form에서 받아온 날짜
		String chooseDate = request.getParameter("chooseDate");
		request.setAttribute("chooseDate", chooseDate);
		return "ody/schedule/insertSchedule.gwTiles";
	}
	
	@ResponseBody
	@RequestMapping(value="/t1/selectCategory.tw", produces="text/plain;charset=UTF-8")
	public String getSmallCategory(HttpServletRequest request) {
		
		String fk_bcno = request.getParameter("fk_bcno");
		String fk_employeeid= request.getParameter("fk_employeeid");
		
	//	System.out.println("대분류"+fk_bcno);
	//	System.out.println("사번"+fk_employeeid);
		
		Map<String,String> paraMap = new HashMap<>();
		paraMap.put("fk_bcno", fk_bcno);
		paraMap.put("fk_employeeid", fk_employeeid);
		
		List<ScalCategoryOdyVO> scategoryList = service.getSmallCategory(paraMap);
	//	System.out.println("개수"+scategoryList.size());
		
		JSONArray jsArr = new JSONArray();
		if(scategoryList!=null) {
			for(ScalCategoryOdyVO scvo : scategoryList) {
				JSONObject jsObj = new JSONObject();
				jsObj.put("scno", scvo.getScno());
				jsObj.put("scname", scvo.getScname());
				
				jsArr.put(jsObj);
			}
		}
		
		return jsArr.toString();
	}
	
	@RequestMapping(value="/t1/schedule/registerSchedule.tw", method = {RequestMethod.POST})
	public ModelAndView registerSchedule(ModelAndView mav, HttpServletRequest request, ScheduleOdyVO svo) {

		int n = service.registerSchedule(svo);
		
		if(n == 0) {
			mav.addObject("message", "일정 등록에 실패하였습니다.");
		}
		else {
			mav.addObject("message", "일정 등록에 성공하였습니다.");
		}
		
		mav.addObject("loc", request.getContextPath()+"/t1/schedule.tw");
		mav.setViewName("ody/schedule/showSchedule.gwTiles");
		
		return mav;
	}
}
