package com.duan.hadoop.servlet;

import com.duan.hadoop.bean.Temp;
import com.duan.hadoop.service.WeatherService;
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
    private static WeatherService service = new WeatherService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("nihao");
        //获取请求中参数
        String stn = request.getParameter("stn");
        String wban = request.getParameter("wban");
        System.out.println(stn+"=="+wban);
        List<Temp> list = service.getTemp(stn, wban);
        System.out.println(list.size());
        //将数据返回给前台
        String jsonStr = JSON.toJSONString(list);
        response.getWriter().write(jsonStr);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
