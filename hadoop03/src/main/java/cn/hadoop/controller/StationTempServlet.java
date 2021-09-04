package cn.hadoop.controller;

import cn.hadoop.service.StationTempService;
import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/StationTempServlet")
public class StationTempServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StationTempService service = new StationTempService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String stationNum = request.getParameter("stationNum");

		Map<String, String> map = service.getData(stationNum);

		String jst = JSON.toJSONString(map);

		response.getWriter().write(jst);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

