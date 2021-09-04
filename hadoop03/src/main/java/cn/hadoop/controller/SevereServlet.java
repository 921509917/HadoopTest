package cn.hadoop.controller;

import cn.hadoop.pojo.Severe;
import cn.hadoop.service.WeatherService;
import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class SevereServlet
 */
@WebServlet("/SevereServlet")
public class SevereServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private WeatherService service = new WeatherService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String params = request.getParameter("station");

		Severe s = service.getSevere(params);

		String jst = JSON.toJSONString(s);

		response.getWriter().write(jst);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
