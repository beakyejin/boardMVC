package kr.itedu.boardmvc.service;

import kr.itedu.boardmvc.common.BoardDAO;

public class BoardDeleteService {

	public void boardDelete(int btype, int bid) {
		BoardDAO dao = BoardDAO.getInstance();
		dao.boardDelete(btype, bid);
	}

}
