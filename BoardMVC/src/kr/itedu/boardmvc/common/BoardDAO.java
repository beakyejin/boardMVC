package kr.itedu.boardmvc.common;

import static kr.itedu.boardmvc.common.DBConnector.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;

import kr.itedu.boardmvc.action.BoardVO;

public class BoardDAO {
	private static BoardDAO dao;
	
	private BoardDAO() {} //private 생성자
	
	public static BoardDAO getInstance() { //싱글톤 패턴
		if(dao == null) {
			dao = new BoardDAO();
		}
		return dao;
	}
	
	//모든 데이터 들고오기
	public ArrayList<BoardVO> getBoardList(int btype) {
		ArrayList<BoardVO> result = new ArrayList<>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			 con = getConn();
			 
			 String query = " select bid, btitle, bcontent, bregdate "
			 		+ " from t_board"+ btype +" order by bid desc ";
			 

			 ps = con.prepareStatement(query);
			 rs = ps.executeQuery();
			 
			 while(rs.next()) {
				 int bid = rs.getInt("bid");
				 String btitle = rs.getString("btitle");
				 String bcontent = rs.getString("bcontent");
				 String bregdate = rs.getString("bregdate");
				 
				 BoardVO mo = new BoardVO();
				 mo.setBid(bid);
				 mo.setBtitle(btitle);
				 mo.setBcontent(bcontent);
				 mo.setBregdate(bregdate);
				 
				 result.add(mo);
			 }
			 
			 
		} catch (SQLException e) {
			//TODO: 예외처리
		} catch (Exception e) {
			//TODO: 예외처리
		} finally {
			close(con, null, null);
		}
		
		return result;
	}

	public ArrayList<BoardVO> getBoardDetail(int btype, int idx) {
		ArrayList<BoardVO> result = new ArrayList<>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			con = getConn();
			
			String query = " select bid, btitle, bcontent, bregdate "
					+ " from t_board" + btype + " where bid=? ";
					
			ps = con.prepareStatement(query);
			ps.setInt(1, idx);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				int bid = rs.getInt("bid");
				String btitle = rs.getString("btitle");
				String bcontent = rs.getString("bcontent");
				String bregdate = rs.getString("bregdate");
				
				BoardVO mo = new BoardVO();
				mo.setBid(bid);
				mo.setBtitle(btitle);
				mo.setBcontent(bcontent);
				mo.setBregdate(bregdate);
				
				result.add(mo);
			}
			
		}catch (SQLException e) {
			//TODO: 예외처리
		} catch (Exception e) {
			//TODO: 예외처리
		} finally {
			close(con, ps, rs);
		}
		
		return result;
	}

	public void boardUpdate(BoardVO vm) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			con = getConn();
			
			String query = " update t_board" + vm.getBtype() 
						+" set btitle=?, bcontent=? "
						+ " where bid=? ";
			
			ps = con.prepareStatement(query);
			ps.setString(1, vm.getBtitle());
			ps.setString(2, vm.getBcontent());
			ps.setInt(3, vm.getBid());
			
			ps.executeQuery();
			
		}catch (SQLException e) {
			//TODO: 예외처리
			e.printStackTrace();
		} catch (Exception e) {
			//TODO: 예외처리
			e.printStackTrace();
		} finally {
			close(con, ps, null);
		}
		
	}

	public void boardInsert(BoardVO vm) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			con = getConn();
			
			String query = " insert into t_board" + vm.getBtype()
						+ " (bid, btitle, bcontent)"
						+ " values"
						+ " ((select nvl(max(bid),1)+1 from t_board"
						+ vm.getBtype()
						+ "), ?, ?) ";

			ps = con.prepareStatement(query);
			ps.setString(1, vm.getBtitle());
			ps.setString(2, vm.getBcontent());
			
			ps.executeQuery();
			
		}catch (SQLException e) {
			//TODO: 예외처리
		} catch (Exception e) {
			//TODO: 예외처리
		} finally {
			close(con, ps, null);
		}
	}

	public void boardDelete(int btype, int bid) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			con = getConn();
			
			String query = " delete from t_board" + btype
					+ " where bid=? ";
			
			ps = con.prepareStatement(query);
			ps.setInt(1, bid);
			
			ps.executeQuery();
			
		}catch (SQLException e) {
			//TODO: 예외처리
			e.printStackTrace();
		} catch (Exception e) {
			//TODO: 예외처리
			e.printStackTrace();
		} finally {
			close(con, ps, null);
		}
	}
	
}
