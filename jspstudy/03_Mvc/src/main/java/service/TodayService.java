package service;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;

public class TodayService implements MyService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse repsonse) throws IOException{
		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String today = sdf.format(date);
		
		// 뷰(Jsp)로 전달할 데이터
		request.setAttribute("result", today);
		
		// 어디로 갈 것인가?
		ActionForward actionForward = new ActionForward();
		actionForward.setView("views/result.jsp");
		
		// ActionForward 반환
		return actionForward;
	}
}