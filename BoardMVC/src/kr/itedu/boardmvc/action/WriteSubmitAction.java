package kr.itedu.boardmvc.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.itedu.boardmvc.ActionForward;
import kr.itedu.boardmvc.common.Utils;
import kr.itedu.boardmvc.common.Var;
import kr.itedu.boardmvc.service.BoardWriteService;
import kr.itedu.boardmvc.service.WriteSubmitService;

public class WriteSubmitAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		forward.setRedirect(true);
		
		WriteSubmitService service = new WriteSubmitService();
		
		int bid = Integer.parseInt(request.getParameter("bid"));
		int btype = Utils.getParamInt(request.getParameter("btype"));
		int page = Integer.parseInt(request.getParameter("page"));
		String title = request.getParameter("btitle");
		String content = request.getParameter("bcontent");
		
		BoardVO vm = new BoardVO();
		vm.setBid(bid);
		vm.setBtype(btype);
		vm.setBtitle(title);
		vm.setBcontent(content);

		if(bid > 0) {
			service.boardUpeate(vm);
			forward.setPath("boardDetail.bo?btype="+btype+"&bid="+bid+"&page="+page);
		}else {
			service.boardInsert(vm);
			forward.setPath("boardList.bo?btype="+btype+"&page="+page);
		}
		
		return forward;
	}

}
