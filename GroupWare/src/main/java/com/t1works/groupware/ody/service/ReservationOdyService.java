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

	// 회의실 예약하기
	@Override
	public int insert_rsRoom(Map<String, String> paraMap) {
		int n = rdao.insert_rsRoom(paraMap);
		return n;
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

	// 신청 부서 알아오기
	@Override
	public String selectDepartment(String dcode) {
		String dname= rdao.selectDepartment(dcode);
		return dname;
	}

	// 사무용품 신청하기
	@Override
	public int insert_rsGoods(Map<String, String> paraMap) {
		int n = rdao.insert_rsGoods(paraMap);
		return n;
	}

	// 회의실 예약 삭제하기
	@Override
	public int delReserveRoom(String rsroomno) {
		int n =rdao.delReserveRoom(rsroomno);
		return n;
	}

	// 차량 예약하기
	@Override
	public int insert_rsCar(Map<String, String> paraMap) {
		int n =rdao.insert_rsCar(paraMap);
		return n;
	}

	// 사무용품 예약 삭제하기
	@Override
	public int delReserveGoods(String rsgno) {
		int n =rdao.delReserveGoods(rsgno);
		return n;
	}

	// 차량 예약 삭제하기
	@Override
	public int delReserveCar(String rscno) {
		int n =rdao.delReserveCar(rscno);
		return n;
	}

	
	// 나의 회의실 대여 건수
	@Override
	public int getTotalCountMyRoom(Map<String, String> paraMap) {
		int n = rdao.getTotalCountMyRoom(paraMap);
		return n;
	}

	// 나의 회의실 대여 현황
	@Override
	public List<RsRoomOdyVO> showMyRoomListSearchWithPaging(Map<String, String> paraMap) {
		List<RsRoomOdyVO> myRoomList = rdao.showMyRoomListSearchWithPaging(paraMap);
		return myRoomList;
	}


	// 나의 사무용품 대여 현황 건수
	@Override
	public int getTotalCountMyGoods(Map<String, String> paraMap) {
		int n = rdao.getTotalCountMyGoods(paraMap);
		return n;
	}

	// 나의 사무용품 대여 현황
	@Override
	public List<GoodsOdyVO> showMyGoodsListSearchWithPaging(Map<String, String> paraMap) {
		List<GoodsOdyVO> myGoodsList = rdao.showMyGoodsListSearchWithPaging(paraMap);
		return myGoodsList;
	}

	// 나의 차량 예약 총 건수(totalCount)
	@Override
	public int getTotalCountMyCar(Map<String, String> paraMap) {
		int n = rdao.getTotalCountMyCar(paraMap);
		return n;
	}

	// 나의 차량 대여 현황
	@Override
	public List<CarOdyVO> showMyCarListSearchWithPaging(Map<String, String> paraMap) {
		List<CarOdyVO> myCarList = rdao.showMyCarListSearchWithPaging(paraMap);
		return myCarList;
	}


	


}
