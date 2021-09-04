package cn.hadoop.controller;

import cn.hadoop.pojo.Temp;
import cn.hadoop.service.WeatherService;
import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/TempServlet")
public class TempServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private WeatherService service = new WeatherService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String param = request.getParameter("station");

		List<Temp> list = service.getTemp(param);

		String jst = JSON.toJSONString(list);

		response.getWriter().write(jst);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

