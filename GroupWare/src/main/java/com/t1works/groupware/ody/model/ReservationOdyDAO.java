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

	// 회의실 예약하기
	@Override
	public int insert_rsRoom(Map<String, String> paraMap) {
		int n = sqlsession5.insert("reservation_ody.insert_rsRoom",paraMap);
		return n;
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

	// 신청부서 알아오기
	@Override
	public String selectDepartment(String dcode) {
		String dname = sqlsession5.selectOne("reservation_ody.selectDepartment",dcode);
		return dname;
	}

	// 사무용품 예약하기
	@Override
	public int insert_rsGoods(Map<String, String> paraMap) {
		int n = sqlsession5.insert("reservation_ody.insert_rsGoods",paraMap);
		return n;
	}

	// 회의실 예약 삭제하기
	@Override
	public int delReserveRoom(String rsroomno) {
		int n = sqlsession5.delete("reservation_ody.delReserveRoom",rsroomno);
		return n;
	}

	// 차량 예약하기
	@Override
	public int insert_rsCar(Map<String, String> paraMap) {
		int n = sqlsession5.insert("reservation_ody.insert_rsCar",paraMap);
		return n;
	}

	// 사무용품 예약 삭제하기
	@Override
	public int delReserveGoods(String rsgno) {
		int n = sqlsession5.delete("reservation_ody.delReserveGoods",rsgno);
		return n;
	}

	// 차량 예약 삭제하기
	@Override
	public int delReserveCar(String rscno) {
		int n = sqlsession5.delete("reservation_ody.delReserveCar",rscno);
		return n;
	}

	// 나의 회의실 대여 건수
	@Override
	public int getTotalCountMyRoom(Map<String, String> paraMap) {
		int n = sqlsession5.selectOne("reservation_ody.getTotalCountMyRoom",paraMap);
		return n;
	}

	// 나의 회의실 대여 현황
	@Override
	public List<RsRoomOdyVO> showMyRoomListSearchWithPaging(Map<String, String> paraMap) {
		List<RsRoomOdyVO> myRoomList  = sqlsession5.selectList("reservation_ody.showMyRoomListSearchWithPaging",paraMap);
		return myRoomList;
	}



	// 나의 사무용품 대여 현황 건수
	@Override
	public int getTotalCountMyGoods(Map<String, String> paraMap) {
		int n = sqlsession5.selectOne("reservation_ody.getTotalCountMyGoods",paraMap);
		return n;
	}

	// 나의 사무용품 대여 현황
	@Override
	public List<GoodsOdyVO> showMyGoodsListSearchWithPaging(Map<String, String> paraMap) {
		List<GoodsOdyVO> myGoodsList = sqlsession5.selectList("reservation_ody.showMyGoodsListSearchWithPaging",paraMap);
		return myGoodsList;
	}

	// 나의 차량 대여 총 건수(totalCount)
	@Override
	public int getTotalCountMyCar(Map<String, String> paraMap) {
		int n = sqlsession5.selectOne("reservation_ody.getTotalCountMyCar",paraMap);
		return n;
	}

	// 나의 차량 대여 현황
	@Override
	public List<CarOdyVO> showMyCarListSearchWithPaging(Map<String, String> paraMap) {
		List<CarOdyVO> myCarList = sqlsession5.selectList("reservation_ody.showMyCarListSearchWithPaging",paraMap);
		return myCarList;
	}

	// 회의실 시간 확인 
	@Override
	public List<RsRoomOdyVO> checkTimeRoom(Map<String, String> paraMap) {
		 List<RsRoomOdyVO> roomTimeList =  sqlsession5.selectList("reservation_ody.checkTimeRoom",paraMap);
		 return roomTimeList;
	}

	// 회의실 예약 변경하기
	@Override
	public int edit_rsRoom(Map<String, String> paraMap) {
		int n = sqlsession5.insert("reservation_ody.edit_rsRoom",paraMap);
		return n;
	}

	// 사무용품 예약 변경 시간 확인
	@Override
	public List<RsGoodsOdyVO> checkTimeGoods(Map<String, String> paraMap) {
		List<RsGoodsOdyVO> goodsTimeList = sqlsession5.selectList("reservation_ody.checkTimeGoods",paraMap);
		return  goodsTimeList;
	}


	
}
