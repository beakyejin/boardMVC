package kr.itedu.boardmvc.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.itedu.boardmvc.ActionForward;
import kr.itedu.boardmvc.common.Utils;
import kr.itedu.boardmvc.common.Var;
import kr.itedu.boardmvc.service.BoardListService;

public class BoardListAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();//객체화
		forward.setPath(Var.TEMPLATE_1);
		
		/*-----------------db값 들고오기----------------------------*/
		BoardListService service = new BoardListService();//서비스 객체화
		
		int btype = Utils.getParamInt(request.getParameter("btype"));//utils에서 btype값 확인하여 넘겨주기
		/*getParameter 1(get방식 url) 2(포스트 메세지body,url)에서 얻어옴
		 우선순위:  1url 2메세지body */
		
		ArrayList<BoardVO> data = service.getBoardList(btype);//data들고오기
		/*--------------------------------------------------------*/
		
		request.setAttribute("title", Var.TITLES[btype]);
		request.setAttribute("content", "boardList");
		//request.setAttribute("btype", btype); 
		//btype은 request에 담지 않아도 된다.(:23번라인에서 request에 btype을 이미 담았기 때문,주소값 유지)
		request.setAttribute("data", data);
		request.setAttribute("cssName", "boardlist");
		
		return forward;
	}
	
}
