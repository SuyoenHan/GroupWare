package com.t1works.groupware.ody.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.t1works.groupware.ody.model.CarOdyVO;
import com.t1works.groupware.ody.model.GoodsOdyVO;
import com.t1works.groupware.ody.model.InterReservationOdyDAO;
import com.t1works.groupware.ody.model.RoomOdyVO;
import com.t1works.groupware.ody.model.RsCarOdyVO;
import com.t1works.groupware.ody.model.RsGoodsOdyVO;
import com.t1works.groupware.ody.model.RsRoomOdyVO;

@Component
@Service
public class ReservationOdyService implements InterReservationOdyService {

	@Autowired 
	private InterReservationOdyDAO rdao;
	
	// 회의실 리스트 조회
	@Override
	public List<RoomOdyVO> getRoomList() {
		List<RoomOdyVO> roomList = rdao.getRoomList();
		return roomList;
	}

	// 회의실 예약 리스트 조회
	@Override
	public List<RsRoomOdyVO> getReserveRoomList(Map<String, String> paraMap) {
		List<RsRoomOdyVO> reserveRoomList = rdao.getReserveRoomList(paraMap);
		return reserveRoomList;
	}

	// 사무용품 목록 리스트
	@Override
	public List<GoodsOdyVO> getGoodsList() {
		List<GoodsOdyVO> goodsList = rdao.getGoodsList();
		return goodsList;
	}

	// 예약된 사무용품  리스트
	@Override
	public List<RsGoodsOdyVO> getReserveGoodsList(Map<String, String> paraMap) {
		List<RsGoodsOdyVO> reserveGoodsList = rdao.getReserveGoodsList(paraMap);
		return reserveGoodsList;
	}

	// 차량 목록 리스트
	@Override
	public List<CarOdyVO> getCarList() {
		List<CarOdyVO> carList = rdao.getCarList();
		return carList;
	}

	// 예약된 차량 리스트
	@Override
	public List<RsCarOdyVO> getReserveCarList(Map<String, String> paraMap) {
		List<RsCarOdyVO> reserveCarList = rdao.getReserveCarList(paraMap);
		return reserveCarList;
	}

	


}
