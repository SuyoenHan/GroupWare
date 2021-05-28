package com.t1works.groupware.ody.model;

import java.util.List;
import java.util.Map;

public interface InterReservationOdyDAO {

	// 회의실 리스트
	List<RoomOdyVO> getRoomList();

	// 회의실 예약리스트
	List<RsRoomOdyVO> getReserveRoomList(Map<String, String> paraMap);

	// 회의실 예약하기
	int insert_rsRoom(Map<String, String> paraMap);

	// 회의실 예약 삭제하기
	int delReserveRoom(String rsroomno);
	
	// 사무용품 목록 리스트
	List<GoodsOdyVO> getGoodsList();

	// 사무용품 예약 리스트
	List<RsGoodsOdyVO> getReserveGoodsList(Map<String, String> paraMap);

	// 차량 목록 리스트
	List<CarOdyVO> getCarList();

	// 차량 예약 목록 리스트
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
