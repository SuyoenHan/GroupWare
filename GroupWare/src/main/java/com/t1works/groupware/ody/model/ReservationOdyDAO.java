package com.t1works.groupware.ody.model;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public class ReservationOdyDAO implements InterReservationOdyDAO {

	@Resource
	private SqlSessionTemplate sqlsession5;

	// 회의실 조회
	@Override
	public List<RoomOdyVO> getRoomList() {
		List<RoomOdyVO> roomList = sqlsession5.selectList("reservation_ody.getRoomList");
		return roomList;
	}

	// 회의실 예약리스트 조회
	@Override
	public List<RsRoomOdyVO> getReserveRoomList(Map<String, String> paraMap) {
		List<RsRoomOdyVO> reserveRoomList = sqlsession5.selectList("reservation_ody.getReserveRoomList", paraMap);
		return reserveRoomList;
	}

	// 사무용품 목록 리스트
	@Override
	public List<GoodsOdyVO> getGoodsList() {
		List<GoodsOdyVO> goodsList = sqlsession5.selectList("reservation_ody.getGoodsList");
		return goodsList;
	}

	// 사무용품 예약 리스트
	@Override
	public List<RsGoodsOdyVO> getReserveGoodsList(Map<String, String> paraMap) {
		List<RsGoodsOdyVO> reserveGoodsList = sqlsession5.selectList("reservation_ody.getReserveGoodsList", paraMap);
		return reserveGoodsList;
	}

	// 차량 목록 리스트
	@Override
	public List<CarOdyVO> getCarList() {
		 List<CarOdyVO> carList = sqlsession5.selectList("reservation_ody.getcarList");
		return carList;
	}

	// 차량 예약 리스트
	@Override
	public List<RsCarOdyVO> getReserveCarList(Map<String, String> paraMap) {
		List<RsCarOdyVO> reserveCarList = sqlsession5.selectList("reservation_ody.getReserveCarList", paraMap);
		return reserveCarList;
	}
	
	
}
