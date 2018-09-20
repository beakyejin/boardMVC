package kr.itedu.boardmvc.service;

import kr.itedu.boardmvc.action.BoardVO;
import kr.itedu.boardmvc.common.BoardDAO;

public class WriteSubmitService {

	public void boardUpeate(BoardVO vm) {
		System.out.println("==========BoardDModify=============");
		BoardDAO dao = BoardDAO.getInstance();
		dao.boardUpdate(vm);
		
	}

	public void boardInsert(BoardVO vm) {
		System.out.println("==========BoardDInsert=============");
		BoardDAO dao = BoardDAO.getInstance();
		dao.boardInsert(vm);
	}

}
