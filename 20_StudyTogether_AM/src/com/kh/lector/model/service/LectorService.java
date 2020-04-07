package com.kh.lector.model.service;

import static com.kh.common.JDBCTemplate.close;//static  import!!!!!!!!!
import static com.kh.common.JDBCTemplate.commit;//static  import!!!!!!!!!
import static com.kh.common.JDBCTemplate.getConnection; //static  import!!!!!!!!!
import static com.kh.common.JDBCTemplate.rollback;//static  import!!!!!!!!!

import java.sql.Connection;
import java.util.List;

import com.kh.join.model.vo.LectorJoin;
import com.kh.join.model.vo.StudyJoin;
import com.kh.lector.model.dao.LectorDao;
import com.kh.lector.model.vo.Lector;
import com.kh.lector.model.vo.LectorChannel;
import com.kh.member.model.vo.Member;
import com.kh.study.model.vo.Study;

public class LectorService {

	private LectorDao dao=new LectorDao();

	public int insertLector(Lector l) {

		Connection conn=getConnection();
		int result=dao.insertLector(conn,l);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}

	public List<Lector> searchLector(int cPage, int numPerPage) {
		
		Connection conn=getConnection();
		List<Lector> list=dao.searchLector(conn,cPage,numPerPage);
		close(conn);
		
		return list;
	}

	
	public int lectorCount() {
		Connection conn=getConnection();
		int result=dao.lectorCount(conn);
		close(conn);
		return result;
	}

	public Lector selectLector(int no) {

		Connection conn=getConnection();
		Lector l=dao.selectLector(conn,no);
		close(conn);
		return l;
		
	}

	public int deleteLector(int no) {
		Connection conn=getConnection();
		int result=dao.deleteLector(conn,no);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);

		
		return result;
	}

	public int updateLector(Lector l) {
		Connection conn=getConnection();
		int result=dao.updateLector(conn,l);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
////////////강좌 채널 내 강의 추가하기
	public int insertChannelLector(LectorChannel lc) {

		Connection conn=getConnection();
		int result=dao.insertChannelLector(conn,lc);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
//채널조회
	public List<LectorChannel> searchChannel(int no) {
		Connection conn=getConnection();
		List<LectorChannel> list=dao.searchChannel(conn,no);
		close(conn);
		return list;
	}

	public List<LectorChannel> searchChannel(int pNo, int cPage, int numPerPage) {
		Connection conn=getConnection();
		List<LectorChannel> list=dao.searchChannel(conn,pNo,cPage,numPerPage);
		close(conn);
		return list;
	}

	public int channelCount() {
		Connection conn=getConnection();
		int result=dao.channelCount(conn);
		close(conn);
		return result;
	}
//channelView 넘어가는시작
	public List<LectorChannel> searchChannel(int pNo, int cNo, int cPage, int numPerPage) {
		Connection conn=getConnection();
		List<LectorChannel> list=dao.searchChannel(conn,pNo,cNo,cPage,numPerPage);
		close(conn);
		return list;
	}
//channel no를 받아와 조회 특정 channel조회
	public LectorChannel selectChannel(int cNo) {
		Connection conn=getConnection();
		LectorChannel lc=dao.selectLectorChannel(conn,cNo);
		close(conn);
		return lc;
	}
//channel pNo,cNo 유저채널의 1개 강좌 조회
	public LectorChannel selectChannel(int pNo, int cNo) {
		Connection conn=getConnection();
		LectorChannel lc=dao.searchChannel(conn,pNo,cNo);
		close(conn);
		return lc;
	}

	public int channelCount(int no) {
		Connection conn=getConnection();
		int result=dao.channelCount(conn,no);
		close(conn);
		return result;
	}

	public LectorChannel selectChannel2(int cNo) {
		Connection conn=getConnection();
		LectorChannel lc=dao.selectChannel2(conn,cNo);
		close(conn);
		return lc;
	}

	//자식강좌 수정메서드
	public int updateChannel(LectorChannel lc) {
		Connection conn=getConnection();
		int result=dao.updateChannel(conn,lc);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}

	public int deleteChannel(int pNo, int cNo) {
		Connection conn=getConnection();
		int result=dao.deleteChannel(conn,pNo,cNo);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}

	public List<Lector> searchLectorPage(int cPage, int numPerPage, String type, String key) {
		Connection conn=getConnection();
		List<Lector> list=dao.searchLectorPage(conn,cPage,numPerPage,type,key);
		close(conn);
		return list;
	}

	public int lectorCount(String type, String key) {
		Connection conn=getConnection();
		int result=dao.lectorCount(conn,type,key);
		close(conn);
		return result;
	}

	public List<Lector> searchLectorPage(String type, String key) {
		Connection conn=getConnection();
		List<Lector> list=dao.searchLectorPage(conn,type,key);
		close(conn);
		return list;
	}

//	지현 - 리뷰
	public List<Lector> selectLectureName(String writer) {
		Connection conn = getConnection();
		List<Lector> list = dao.selectLectureName(conn,writer);
		close(conn);
		return list;
	}

	public List<LectorJoin> selectLectorJoin(int lectorNo) {
		Connection conn=getConnection();
		List<LectorJoin> list=dao.selectLectorJoin(conn,lectorNo);
		close(conn);
		return list;
	}

	public LectorJoin searchLectorJoin(int lectorNo, String userId) {
		Connection conn=getConnection();
		LectorJoin lj=dao.searchLectorJoin(conn,lectorNo,userId);
		close(conn);
		return lj;
	}
	
}
