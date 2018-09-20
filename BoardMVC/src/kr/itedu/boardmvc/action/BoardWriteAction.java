package kr.itedu.boardmvc.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.itedu.boardmvc.ActionForward;
import kr.itedu.boardmvc.common.BoardDAO;
import kr.itedu.boardmvc.common.Utils;
import kr.itedu.boardmvc.common.Var;
import kr.itedu.boardmvc.service.BoardDetailService;
import kr.itedu.boardmvc.service.BoardWriteService;

public class BoardWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		forward.setPath(Var.TEMPLATE_1);
		
		int bid = Integer.parseInt(request.getParameter("bid"));
		int btype = Utils.getParamInt(request.getParameter("btype"));
		
		if(bid > 0) {
			BoardWriteService service = new BoardWriteService();
			ArrayList<BoardVO> data = service.getBoardDetail(btype, bid);
			
			request.setAttribute("data", data);
		}else {
			request.setAttribute("data", null);
		}
		
		request.setAttribute("title", Var.TITLES[btype]+"글 쓰기");
		request.setAttribute("content", "boardwrite");
		request.setAttribute("cssName", "boardwrite");
		
		return forward;
	}

}
