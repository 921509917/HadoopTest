package cn.hadoop.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.io.PrintWriter;

public class GZIPFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletResponse resp = (HttpServletResponse) response;

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}

class MyResponse extends HttpServletResponseWrapper {
	//Response對象中的字符流
	private PrintWriter pw;

	//Response對象中字節流
	private ServletOutputStream sout;

	private HttpServletResponse response;

	public MyResponse(HttpServletResponse response) {
		super(response);

		this.response = response;
	}
	
/*	public PrintWriter getWriter() {
		
	}*/

}
