package kr.itedu.boardmvc.common;

import static kr.itedu.boardmvc.common.DBConnector.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;

import kr.itedu.boardmvc.action.BoardVO;
import kr.itedu.boardmvc.action.CommentVO;

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
	public ArrayList<BoardVO> getBoardList(int btype, int pmaxNum, int pminNum) {
		ArrayList<BoardVO> result = new ArrayList<>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			 con = getConn();
			 
//			 String query = " select bid, btitle, bcontent, bregdate "
//			 		+ " from t_board"+ btype +" order by bid desc ";
			 
			 String query = "select * from ( " + 
			 		"    select rownum as rnum, z.* from ( " + 
			 		"        select * from t_board" + btype+  
			 		"        order by bid desc " + 
			 		"        ) z where rownum <= ?" + 
			 		") where rnum > ? ";
			 
			 
			 ps = con.prepareStatement(query);
			 ps.setInt(1, pmaxNum);
			 ps.setInt(2, pminNum);
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

	public int getMaxNum(int btype) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int max = 0;
		
		try {
			con = getConn();
			
			String query = " select max(bid) as max_num from t_board"+btype;
			
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				max = rs.getInt(1);
			}
			
		}catch (SQLException e) {
			//TODO: 예외처리
			e.printStackTrace();
		} catch (Exception e) {
			//TODO: 예외처리
			e.printStackTrace();
		} finally {
			close(con, ps, rs);
		}
		return max;
	}

	public void commentInsert(CommentVO vm) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			con = getConn();
			
			String query = " INSERT INTO T_COMMENT "
					+ " (CID, BID, BTYPE, T_COMMENT) "
					+ " VALUES "
					+ " ( (SELECT NVL(MAX(CID),0)+1 FROM T_COMMENT), ?, ?, ?) ";

			ps = con.prepareStatement(query);
			ps.setInt(1, vm.getBid());
			ps.setInt(2, vm.getBtype());
			ps.setString(3, vm.getT_comment());
			ps.executeQuery();
			
		}catch (SQLException e) {
			//TODO: 예외처리
		} catch (Exception e) {
			//TODO: 예외처리
		} finally {
			close(con, ps, null);
		}
	}

	public ArrayList<CommentVO> getCommentList(int _btype, int _bid) {
		ArrayList<CommentVO> result = new ArrayList<>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			con = getConn();
			
			String query = " SELECT CID, BID, BTYPE, T_COMMENT, CREGDATE "
					+ " FROM T_COMMENT "
					+ " WHERE BID=? AND BTYPE=?";
					
			ps = con.prepareStatement(query);
			ps.setInt(1, _bid);
			ps.setInt(2, _btype);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				int cid = rs.getInt("cid");
				int bid = rs.getInt("bid");
				int btype = rs.getInt("btype");
				String t_comment = rs.getString("t_comment");
				String cregdate = rs.getString("cregdate");
				
				CommentVO mo = new CommentVO();
				mo.setBid(bid);
				mo.setCid(cid);
				mo.setBtype(btype);
				mo.setT_comment(t_comment);
				mo.setCregdate(cregdate);
				
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
	
}
