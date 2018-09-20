package kr.itedu.boardmvc.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.itedu.boardmvc.ActionForward;
import kr.itedu.boardmvc.common.Utils;
import kr.itedu.boardmvc.common.Var;
import kr.itedu.boardmvc.service.BoardDeleteService;

public class BoardDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		
		int btype = Utils.getParamInt(request.getParameter("btype"));
		int bid = Integer.parseInt(request.getParameter("bid"));
		
		BoardDeleteService service = new BoardDeleteService();
		service.boardDelete(btype, bid);

		forward.setPath("boardList.bo?btype="+btype);
		forward.setRedirect(true);
		
		return forward;
	}

}
