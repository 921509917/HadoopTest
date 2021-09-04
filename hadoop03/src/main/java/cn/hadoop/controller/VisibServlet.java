package cn.hadoop.controller;

import cn.hadoop.pojo.Visib;
import cn.hadoop.service.WeatherService;
import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Servlet implementation class VisibServlet
 */
@WebServlet("/VisibServlet")
public class VisibServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private WeatherService service = new WeatherService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String params = request.getParameter("station");
		System.out.println(params+"============");
		List<Visib> list = service.getVisib(params);

		String jst = JSON.toJSONString(list);

		response.getWriter().write(jst);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
