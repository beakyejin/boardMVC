package kr.itedu.boardmvc;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.itedu.boardmvc.action.Action;
import kr.itedu.boardmvc.action.BoardDeleteAction;
import kr.itedu.boardmvc.action.BoardDetailAction;
import kr.itedu.boardmvc.action.BoardListAction;
import kr.itedu.boardmvc.action.BoardWriteAction;
import kr.itedu.boardmvc.action.CommentWriteAction;
import kr.itedu.boardmvc.action.WriteSubmitAction;

@WebServlet("*.bo")
public class BoardFrontController extends HttpServlet {
       
	protected void doProc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String reqURI = request.getRequestURI(); //전체URI(localhost:8088.........jsp)
		System.out.println("reqURI: "+ reqURI);
		String ctxPath = request.getContextPath(); //루트URI(localhost:8088/BoardMVC)
		System.out.println("ctxPath: " + ctxPath);
		//전체주소-루트주소
		String comd = reqURI.substring(ctxPath.length());
		System.out.println("URI: " + comd);
		
		ActionForward forward = null;
		Action action = null;
		
		if(comd.equals("/boardList.bo")) {
			action = new BoardListAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				//TODO: 예외처리
				e.printStackTrace();
			}
		}else if(comd.equals("/boardDetail.bo")) {
			action = new BoardDetailAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				//TODO: 예외처리
				e.printStackTrace();
			}
		}else if(comd.equals("/boardWrite.bo")) {
			action = new BoardWriteAction();
			
			try {
				forward = action.execute(request, response);
			}catch (Exception e) {
				// TODO: handle exception
			}
		}else if(comd.equals("/writeSubmit.bo")) {
			action = new WriteSubmitAction();
			
			try {
				forward = action.execute(request, response);
			}catch (Exception e) {
				// TODO: handle exception
			}
		}else if(comd.equals("/boardDelete.bo")) {
			action = new BoardDeleteAction();
			
			try {
				forward = action.execute(request, response);
			}catch (Exception e) {
				// TODO: handle exception
			}
		}else if(comd.equals("/commentWrite.bo")) {
			action = new CommentWriteAction();
			
			try {
				forward = action.execute(request, response);
			}catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		
		if(forward != null) {
			//Redirect로 보낼건지 아닌지 판단. isRedirect()의 default값은 false.
			if(forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			}else {
				RequestDispatcher rd = request.getRequestDispatcher(forward.getPath());
				rd.forward(request, response);
			}
		}else {
			//TODO 없는 주소값 에러페이지 디스플레이 처리
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//최초로 req,res생성
		doProc(request, response);
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProc(request, response);
	}

}
