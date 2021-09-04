package cn.hadoop.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hadoop.service.WeatherService;
import com.alibaba.fastjson.JSON;


/**
 * Servlet implementation class DataServlet
 */
@WebServlet("/DataServlet")
public class DataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private WeatherService service = new WeatherService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String params = request.getParameter("station");
		Map<String, String> data = service.getData(params);
		String jst = JSON.toJSONString(data);
		response.getWriter().write(jst);
		
/*        ByteArrayOutputStream buf = new ByteArrayOutputStream();
//      创建GZIPOutputStream
        GZIPOutputStream gzip = new GZIPOutputStream(buf);
        gzip.write(jst.getBytes());
        //调用结束方法 把缓存内容刷新
        gzip.finish();
        //得到刷新的内容
        byte[] result = buf.toByteArray();

        System.out.println("压缩后的数据大小："+result.toString().getBytes().length);
        //设置浏览器的请求头
        response.setHeader("content-encoding", "gzip");

        response.getOutputStream().write(result);*/
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
