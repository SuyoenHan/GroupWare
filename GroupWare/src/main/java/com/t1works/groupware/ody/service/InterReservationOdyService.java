package com.t1works.groupware.ody.service;

import java.util.List;
import java.util.Map;

import com.t1works.groupware.ody.model.CarOdyVO;
import com.t1works.groupware.ody.model.GoodsOdyVO;
import com.t1works.groupware.ody.model.RoomOdyVO;
import com.t1works.groupware.ody.model.RsCarOdyVO;
import com.t1works.groupware.ody.model.RsGoodsOdyVO;
import com.t1works.groupware.ody.model.RsRoomOdyVO;

public interface InterReservationOdyService {

	// 회의실 목록 리스트
	List<RoomOdyVO> getRoomList();

	// 예약된 회의실 리스트
	List<RsRoomOdyVO> getReserveRoomList(Map<String, String> paraMap);

	// 회의실 예약하기
	int insert_rsRoom(Map<String, String> paraMap);
	
	// 회의실 예약 삭제하기
	int delReserveRoom(String rsroomno);

	// 사무용품 목록 리스트
	List<GoodsOdyVO> getGoodsList();

	// 예약된 사무용품 리스트
	List<RsGoodsOdyVO> getReserveGoodsList(Map<String, String> paraMap);

	// 차량 목록 리스트
	List<CarOdyVO> getCarList();

	// 예약된 차량 리스트
	List<RsCarOdyVO> getReserveCarList(Map<String, String> paraMap);

	// 신청부서 알아오기
	String selectDepartment(String dcode);

	// 사무용품 예약하기
	int insert_rsGoods(Map<String, String> paraMap);

	// 차량 예약하기
	int insert_rsCar(Map<String, String> paraMap);

	// 사무용품 예약 삭제하기
	int delReserveGoods(String rsgno);

	// 차량 예약 삭제하기
	int delReserveCar(String rscno);

	// 사무용품 반납하기
	int returnReserveGoods(String rsgno);

	// 차량 반납하기
	int returnReserveCar(String rscno);



	
	
}
