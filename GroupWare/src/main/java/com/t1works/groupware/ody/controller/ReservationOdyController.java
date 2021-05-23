package com.t1works.groupware.ody.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.t1works.groupware.ody.model.CarOdyVO;
import com.t1works.groupware.ody.model.GoodsOdyVO;
import com.t1works.groupware.ody.model.RoomOdyVO;
import com.t1works.groupware.ody.model.RsCarOdyVO;
import com.t1works.groupware.ody.model.RsGoodsOdyVO;
import com.t1works.groupware.ody.model.RsRoomOdyVO;
import com.t1works.groupware.ody.service.InterReservationOdyService;


@Component
@Controller
public class ReservationOdyController {
	
	@Autowired
	private InterReservationOdyService service;
	
	// 회의실 대여신청 view
	@RequestMapping(value="/t1/rsRoom.tw")
	public String room(HttpServletRequest request) {
		
		List<RoomOdyVO> roomList= service.getRoomList();
		
		request.setAttribute("roomList", roomList);
		return "ody/reservation/reserveRoom.gwTiles";
	}
	
	// 회의실 대여신청 view
	@ResponseBody
	@RequestMapping(value="/t1/rsroom/reserveRoom.tw", produces="text/plain;charset=UTF-8")
	public String rsroom(HttpServletRequest request) {
		
		String roomno = request.getParameter("roomno");
		String rdate = request.getParameter("chgdate");
		
		Map<String,String> paraMap = new HashMap<>();
		paraMap.put("roomno", roomno);
		paraMap.put("rdate", rdate);
		
		List<RsRoomOdyVO> reserveRoomList = service.getReserveRoomList(paraMap);
		
		JSONArray jsonArr = new JSONArray();
		if(reserveRoomList != null) {
			for(RsRoomOdyVO rsvo : reserveRoomList) {
				JSONObject jsonObj = new JSONObject(); // {}
				 
				jsonObj.put("rsroomno", rsvo.getRsroomno());         // {"no":"101"}
				jsonObj.put("fk_roomno", rsvo.getFk_roomno());    
				jsonObj.put("fk_employeeid", rsvo.getFk_employeeid()); 
				jsonObj.put("name", rsvo.getName()); 
				jsonObj.put("rsubject", rsvo.getRsubject()); // {"no":"101", "name":"오다윤", "writeday":"2021-05-13 11:38:59"}
				jsonObj.put("rdate", rsvo.getRdate());
				jsonObj.put("rtime", rsvo.getRtime());
				jsonObj.put("rdepartment", rsvo.getRdepartment());
				
				
				jsonArr.put(jsonObj); // [{"no":"101", "name":"오다윤", "writeday":"2021-05-13 11:38:59"}] 
			} // end of for-------------------
		}

		return jsonArr.toString(); // 
	}
	
	
	
	// 사무용품 대여신청 view
	@RequestMapping(value="/t1/rsGoods.tw")
	public String goods(HttpServletRequest request) {
		
		List<GoodsOdyVO> goodsList= service.getGoodsList();
		
		request.setAttribute("goodsList", goodsList);
		return "ody/reservation/reserveGoods.gwTiles";
	}
	
	// 사무용품 대여신청 view
	@ResponseBody
	@RequestMapping(value="/t1/rsGoods/reserveGoods.tw")
	public String rsGoods(HttpServletRequest request) {
		
		String gno = request.getParameter("gno");
		String rgdate = request.getParameter("chgdate");
		
		Map<String,String> paraMap = new HashMap<>();
		paraMap.put("gno", gno);
		paraMap.put("rgdate", rgdate);
		
		
		List<RsGoodsOdyVO> reserveGoodsList = service.getReserveGoodsList(paraMap);
		
		JSONArray jsonArr = new JSONArray();
		if(reserveGoodsList != null) {
			for(RsGoodsOdyVO rsgvo : reserveGoodsList) {
				JSONObject jsonObj = new JSONObject(); // {}
				 
				jsonObj.put("rsgno", rsgvo.getRsgno());         // {"no":"101"}
				jsonObj.put("fk_roomno", rsgvo.getFk_gno());    
				jsonObj.put("fk_employeeid", rsgvo.getFk_employeeid()); 
				jsonObj.put("name", rsgvo.getName()); 
				jsonObj.put("rgsubject", rsgvo.getRgsubject()); // {"no":"101", "name":"오다윤", "writeday":"2021-05-13 11:38:59"}
				jsonObj.put("rgdate", rsgvo.getRgdate());
				jsonObj.put("rgtime", rsgvo.getRgtime());
				jsonObj.put("rgdepartment", rsgvo.getRgdepartment());
				
				
				jsonArr.put(jsonObj); // [{"no":"101", "name":"오다윤", "writeday":"2021-05-13 11:38:59"}] 
			} // end of for-------------------
		}

		return jsonArr.toString(); // 
	}
	
	
	// 차량 대여 신청
	@RequestMapping(value="/t1/rsCar.tw")
	public String car(HttpServletRequest request) {
		
		List<CarOdyVO> carList= service.getCarList();
		
		request.setAttribute("carList", carList);
		return "ody/reservation/reserveCar.gwTiles";
	}
	
	// 차량 대여 신청
	@ResponseBody
	@RequestMapping(value="/t1/rscar/reserveCar.tw")
	public String rsCar(HttpServletRequest request) {
		
		String cno = request.getParameter("cno");
		String rcdate = request.getParameter("chgdate");
		
		Map<String,String> paraMap = new HashMap<>();
		paraMap.put("cno", cno);
		paraMap.put("rcdate", rcdate);
		
		
		List<RsCarOdyVO> reserveCarList = service.getReserveCarList(paraMap);
		
		JSONArray jsonArr = new JSONArray();
		if(reserveCarList != null) {
			for(RsCarOdyVO rscvo : reserveCarList) {
				JSONObject jsonObj = new JSONObject(); // {}
				 
				jsonObj.put("rscno", rscvo.getRscno());         // {"no":"101"}
				jsonObj.put("fk_cno", rscvo.getFk_cno());    
				jsonObj.put("fk_employeeid", rscvo.getFk_employeeid()); 
				jsonObj.put("name", rscvo.getName()); 
				jsonObj.put("rcsubject", rscvo.getRcsubject()); // {"no":"101", "name":"오다윤", "writeday":"2021-05-13 11:38:59"}
				jsonObj.put("rcdate", rscvo.getRcdate());
				jsonObj.put("rctime", rscvo.getRctime());
				jsonObj.put("rdestination", rscvo.getRdestination());
				jsonObj.put("rcpeople", rscvo.getRcpeople());
				
				jsonArr.put(jsonObj); // [{"no":"101", "name":"오다윤", "writeday":"2021-05-13 11:38:59"}] 
			} // end of for-------------------
		}

		return jsonArr.toString(); // 
	}
	
	
	
	// 차량 대여신청시 지도 팝업
	@RequestMapping(value="/t1/searchMap.tw")
	public String searchMap(HttpServletRequest request) {
	
		return "ody/searchPlace";
	}
	
	
	@RequestMapping(value="/t1/schedule.tw")
	public String showSchedule(HttpServletRequest request) {
	
		return "ody/schedule/showSchedule.gwTiles";
	}
	
	
	
	
	
	
	
	
	
}
