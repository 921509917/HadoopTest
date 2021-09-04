package cn.hadoop.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hadoop.pojo.RainFall;
import cn.hadoop.service.WeatherService;
import com.alibaba.fastjson.JSON;


/**
 * Servlet implementation class RainFallServlet
 */
@WebServlet("/RainFallServlet")
public class RainFallServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private WeatherService service = new WeatherService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String params = request.getParameter("station");

		List<RainFall> list = service.getRainFall(params);

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
