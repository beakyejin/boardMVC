package kr.itedu.boardmvc.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.itedu.boardmvc.ActionForward;

public interface Action {
	ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception;
}
