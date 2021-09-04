package cn.hadoop.controller;

import cn.hadoop.service.CountryWeatherService;
import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Servlet implementation class CountryWeatherServlet
 */
@WebServlet("/CountryWeatherServlet")
public class CountryWeatherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private CountryWeatherService service = new CountryWeatherService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String country_code = request.getParameter("country");

		Map<String, String> map = service.getCountryData(country_code);

		String jst = JSON.toJSONString(map);
		System.out.println(map.size()+"======");
		response.getWriter().write(jst);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
