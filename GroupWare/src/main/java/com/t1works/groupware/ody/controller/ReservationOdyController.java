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


import com.t1works.groupware.ody.model.*;
import com.t1works.groupware.ody.service.InterReservationOdyService;


@Component
@Controller
public class ReservationOdyController {
	
	@Autowired
	private InterReservationOdyService service;
	
	// 회의실 대여신청 view
	@RequestMapping(value="/t1/rsRoom.tw")
	public String requiredLogin_room(HttpServletRequest request, HttpServletResponse response) {
		
	
		List<RoomOdyVO> roomList= service.getRoomList();
		
	
		request.setAttribute("roomList", roomList);
		return "ody/reservation/reserveRoom.gwTiles";
	}
	
	// 회의실 대여신청 view
	@ResponseBody
	@RequestMapping(value="/t1/rsroom/reserveRoom.tw", produces="text/plain;charset=UTF-8")
	public String requiredLogin_rsroom(HttpServletRequest request, HttpServletResponse response) {
		
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
				jsonObj.put("rdname", rsvo.getRdname());
				
				
				jsonArr.put(jsonObj); // [{"no":"101", "name":"오다윤", "writeday":"2021-05-13 11:38:59"}] 
			} // end of for-------------------
		}

		return jsonArr.toString(); // 
	}
	
	// 회의실 예약하기
	@RequestMapping(value="/t1/addReserveRoom.tw" , method = {RequestMethod.POST})
	public ModelAndView addReserveRoom(HttpServletRequest request, HttpServletResponse response, ModelAndView mav) {
		
		String rtimeArr = request.getParameter("rtime");
		
		String[] rArr = rtimeArr.split(",");
		
		String fk_roomno = request.getParameter("fk_roomno");
		String rdate= request.getParameter("rdate");
		String fk_employeeid= request.getParameter("fk_employeeid");
		String rdepartment= request.getParameter("rdepartment");
		String rsubject= request.getParameter("rsubject");

		
		int n=0;
		for(int i=0;i<rArr.length;i++) {
			String rtime= rArr[i];
		//	System.out.println(rtime);
			Map<String,String> paraMap = new HashMap<>();
			paraMap.put("fk_roomno", fk_roomno);
			paraMap.put("rdate", rdate);
			paraMap.put("fk_employeeid", fk_employeeid);
			paraMap.put("rdepartment", rdepartment);
			paraMap.put("rsubject", rsubject);
			paraMap.put("rtime", rtime);
			n = service.insert_rsRoom(paraMap);
		}
	

		if(n>0) {
			mav.addObject("message","해당 회의실이 예약되었습니다.");
			mav.addObject("loc",request.getContextPath()+"/t1/rsRoom.tw");
		}
		else {
			mav.addObject("message","회의실 예약을 실패하였습니다.");
			mav.addObject("loc",request.getContextPath()+"/t1/rsRoom.tw");
		}
		
		mav.setViewName("msg");

		return mav;
	}
	
	
	// 사무용품 대여신청 view
	@RequestMapping(value="/t1/rsGoods.tw")
	public String requiredLogin_goods(HttpServletRequest request, HttpServletResponse response) {
		
		
		List<GoodsOdyVO> goodsList= service.getGoodsList();
		
		request.setAttribute("goodsList", goodsList);
		return "ody/reservation/reserveGoods.gwTiles";
	}
	
	// 사무용품 대여신청 view
	@ResponseBody
	@RequestMapping(value="/t1/rsGoods/reserveGoods.tw", produces="text/plain;charset=UTF-8")
	public String requiredLogin_rsGoods(HttpServletRequest request, HttpServletResponse response) {
		
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
				jsonObj.put("rdname", rsgvo.getRdname());
				jsonObj.put("gstatus", rsgvo.getGstatus());
				
				jsonArr.put(jsonObj); // [{"no":"101", "name":"오다윤", "writeday":"2021-05-13 11:38:59"}] 
			} // end of for-------------------
		}

		return jsonArr.toString(); // 
	}
	
	
	// 차량 대여 신청
	@RequestMapping(value="/t1/rsCar.tw")
	public String requiredLogin_car(HttpServletRequest request, HttpServletResponse response) {
		
		List<CarOdyVO> carList= service.getCarList();
		
		request.setAttribute("carList", carList);
		return "ody/reservation/reserveCar.gwTiles";
	}
	
	// 차량 대여 신청
	@ResponseBody
	@RequestMapping(value="/t1/rscar/reserveCar.tw", produces="text/plain;charset=UTF-8")
	public String requiredLogin_rsCar(HttpServletRequest request, HttpServletResponse response) {
		
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
				jsonObj.put("cstatus", rscvo.getCstatus());
				
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
	
	
	// 사무용품 예약하기
		@RequestMapping(value="/t1/addReserveGoods.tw" , method = {RequestMethod.POST})
		public ModelAndView addReserveGoods(HttpServletRequest request, HttpServletResponse response, ModelAndView mav) {
			
			String rgtimeArr = request.getParameter("rgtime");
			
			String[] rgArr = rgtimeArr.split(",");
			
			String gno = request.getParameter("gno");
			String rgdate= request.getParameter("rgdate");
			String fk_employeeid= request.getParameter("fk_employeeid");
			String rgdepartment= request.getParameter("rgdepartment");
			String rgsubject= request.getParameter("rgsubject");

			int n=0;
			
			for(int i=0;i<rgArr.length;i++) {
				String rgtime= rgArr[i];
			//	System.out.println(rtime);
				Map<String,String> paraMap = new HashMap<>();
				paraMap.put("gno", gno);
				paraMap.put("rgdate", rgdate);
				paraMap.put("fk_employeeid", fk_employeeid);
				paraMap.put("rgdepartment", rgdepartment);
				paraMap.put("rgsubject", rgsubject);
				paraMap.put("rgtime", rgtime);
				n = service.insert_rsGoods(paraMap);
			}
			
			
			if(n>0) {
				mav.addObject("message","해당 사무용품이 예약되었습니다.");
				mav.addObject("loc",request.getContextPath()+"/t1/rsGoods.tw");
			}
			else {
				mav.addObject("message","사무용품 예약을 실패하였습니다.");
				mav.addObject("loc",request.getContextPath()+"/t1/rsGoods.tw");
			}
			
			mav.setViewName("msg");
			return mav;
		}
		

	// 회의실 예약 삭제하기
	@ResponseBody
	@RequestMapping(value="/t1/rsroom/delReserveRoom.tw", method= {RequestMethod.POST})
	public String delReserveRoom(HttpServletRequest request) {
		
		String rsroomno = request.getParameter("rsroomno");
		
		int n =service.delReserveRoom(rsroomno);
		System.out.println(n);
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("n", n);
		return jsonObj.toString();
	}

	// 사무용품 예약 삭제
	@ResponseBody
	@RequestMapping(value="/t1/rsGoods/delReserveGoods.tw", method= {RequestMethod.POST})
	public String delReserveGoods(HttpServletRequest request) {
		
		String rsgno = request.getParameter("rsgno");
		
		int n =service.delReserveGoods(rsgno);
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("n", n);
		return jsonObj.toString();
	}
	
	// 차량 예약하기
	@RequestMapping(value="/t1/addReserveCar.tw", method= {RequestMethod.POST})
	public ModelAndView addReserveCar(HttpServletRequest request, HttpServletResponse response, ModelAndView mav) {
		
		String rctimeArr = request.getParameter("rctime");
		
		String[] rcArr = rctimeArr.split(",");
		
		String cno = request.getParameter("cno");
		String rcdate= request.getParameter("rcdate");
		String fk_employeeid= request.getParameter("fk_employeeid");
		String rdestination= request.getParameter("rdestination");
		String rcpeople= request.getParameter("rcpeople");
		String rcsubject= request.getParameter("rcsubject");

		int n=0;
		
		for(int i=0;i<rcArr.length;i++) {
			String rctime= rcArr[i];
		//	System.out.println(rtime);
			Map<String,String> paraMap = new HashMap<>();
			paraMap.put("cno", cno);
			paraMap.put("rcdate", rcdate);
			paraMap.put("fk_employeeid", fk_employeeid);
			paraMap.put("rdestination", rdestination);
			paraMap.put("rcpeople", rcpeople);
			paraMap.put("rcsubject", rcsubject);
			paraMap.put("rctime", rctime);
			n = service.insert_rsCar(paraMap);
		}
	
		if(n>0) {
			mav.addObject("message","해당 차량이 예약되었습니다.");
			mav.addObject("loc",request.getContextPath()+"/t1/rsCar.tw");
		}
		else {
			mav.addObject("message","차량을 실패하였습니다.");
			mav.addObject("loc",request.getContextPath()+"/t1/rsCar.tw");
		}
		
		mav.setViewName("msg");

		return mav;
	}
	
	// 차량 예약 삭제
	@ResponseBody
	@RequestMapping(value="/t1/rsCar/delReserveCar.tw", method= {RequestMethod.POST})
	public String delReserveCar(HttpServletRequest request) {
			
		String rscno = request.getParameter("rscno");
			
		int n =service.delReserveCar(rscno);
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("n", n);
		return jsonObj.toString();
	}
		
	// 사무용품 반납하기
	@ResponseBody
	@RequestMapping(value="/t1/rsGoods/returnReserveGoods.tw",method= {RequestMethod.POST})
	public String returnReserveGoods(HttpServletRequest request) {
		String rsgno = request.getParameter("rsgno");
		
		int n =service.returnReserveGoods(rsgno);
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("n", n);
		return jsonObj.toString();
	}
	
	// 차량 반납하기
	@ResponseBody
	@RequestMapping(value="/t1/rsCar/returnReserveCar.tw",method= {RequestMethod.POST})
	public String returnReserveCar(HttpServletRequest request) {
		String rscno = request.getParameter("rscno");
		
		int n =service.returnReserveCar(rscno);
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("n", n);
		return jsonObj.toString();
	}
	
	
	
	
}
