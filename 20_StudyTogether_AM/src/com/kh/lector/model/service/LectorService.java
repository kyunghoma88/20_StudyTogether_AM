package com.kh.lector.model.service;

import static com.kh.common.JDBCTemplate.close;//static  import!!!!!!!!!
import static com.kh.common.JDBCTemplate.commit;//static  import!!!!!!!!!
import static com.kh.common.JDBCTemplate.getConnection; //static  import!!!!!!!!!
import static com.kh.common.JDBCTemplate.rollback;//static  import!!!!!!!!!

import java.sql.Connection;
import java.util.List;

import com.kh.lector.model.dao.LectorDao;
import com.kh.lector.model.vo.Lector;
import com.kh.lector.model.vo.LectorChannel;

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
	

	

}
