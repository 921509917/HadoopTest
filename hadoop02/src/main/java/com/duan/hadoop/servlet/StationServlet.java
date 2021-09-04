package com.duan.hadoop.servlet;

import com.alibaba.fastjson.JSON;
import com.duan.hadoop.bean.Station;
import com.duan.hadoop.service.StationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/StationServlet")
public class StationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static StationService service = new StationService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("====================");
        List<Station> list = service.getStation();
        //将list对象转变为JSON字符串
        String jsonStr = JSON.toJSONString(list);
        System.out.println(list.size());
        //将站点信息返回给前台
        response.getWriter().write(jsonStr);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
