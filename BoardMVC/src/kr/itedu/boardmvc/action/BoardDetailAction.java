package kr.itedu.boardmvc.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.itedu.boardmvc.ActionForward;
import kr.itedu.boardmvc.common.Utils;
import kr.itedu.boardmvc.common.Var;
import kr.itedu.boardmvc.service.BoardDetailService;
import kr.itedu.boardmvc.service.BoardListService;

public class BoardDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();//객체화
		forward.setPath(Var.TEMPLATE_1);
		
		BoardDetailService service = new BoardDetailService();
		int btype = Utils.getParamInt(request.getParameter("btype"));//utils에서 btype값 확인하여 넘겨주기
		int bid = Integer.parseInt(request.getParameter("bid"));
		
		//글보기
		ArrayList<BoardVO> data = service.getBoardDetail(btype, bid);
		//댓글 불러오기
		ArrayList<CommentVO> cmtlist = service.getCommentList(btype, bid);
		
		request.setAttribute("cmtlist", cmtlist);
		request.setAttribute("cmtlsize", cmtlist.size());
		request.setAttribute("title", Var.TITLES[btype]+"글 보기");
		request.setAttribute("content", "boarddetail");
		request.setAttribute("data", data);
		request.setAttribute("cssName", "boarddetail");
		
		return forward;
	}

}
