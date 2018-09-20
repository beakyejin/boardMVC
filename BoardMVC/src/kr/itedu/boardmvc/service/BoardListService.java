package kr.itedu.boardmvc.service;

import java.sql.Connection;
import java.util.ArrayList;

import kr.itedu.boardmvc.action.BoardVO;
import kr.itedu.boardmvc.common.BoardDAO;

public class BoardListService {
	
	public ArrayList<BoardVO> getBoardList(int btype, int pmaxNum, int pminNum){
		ArrayList<BoardVO> result = null;
		BoardDAO dao = BoardDAO.getInstance();
		result = dao.getBoardList(btype, pmaxNum,pminNum);
		
		System.out.println("==========BoardList=============");
		System.out.printf("btype: %d\n", btype);
		for(BoardVO vo : result) {
			System.out.printf("bid: %d\n", vo.getBid());
			System.out.printf("btitle: %s\n", vo.getBtitle());
			System.out.printf("bcontent: %s\n", vo.getBcontent());
			System.out.printf("bregdate: %s\n", vo.getBregdate());
			System.out.println("-------------");
		}
		
		return result;
	}
	
	public int getMaxNum(int btype) {
		int max = 0;
		BoardDAO dao = BoardDAO.getInstance();
		max = dao.getMaxNum(btype);
		
		System.out.println("maxNum: " + max);
		
		return max;
	}
}
