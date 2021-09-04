package cn.hadoop.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.zip.GZIPOutputStream;

public class GZIPFilter2 implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletResponse resp = (HttpServletResponse) response;
		MyResponse2 myresp = new MyResponse2(resp);

		chain.doFilter(request, myresp);


		ByteArrayOutputStream bout = myresp.getBout();
		byte[] src = bout.toByteArray();
		ByteArrayOutputStream baout = new ByteArrayOutputStream();
		GZIPOutputStream zip = new GZIPOutputStream(baout);
		zip.write(src);
		zip.close();

		myresp.setHeader("Content-Encoding", "gzip");

		byte[] desc = baout.toByteArray();
		ServletOutputStream out = resp.getOutputStream();
		out.write(desc);

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}

class MyResponse2 extends HttpServletResponseWrapper {
	//字符輸出流
	private PrintWriter pw;
	//字節輸出流
	private ByteArrayOutputStream bout;

	public MyResponse2(HttpServletResponse response) {
		super(response);
	}

	public ServletOutputStream getOutputStream() {
		bout = new ByteArrayOutputStream();
		return new MyByteArrayOutputStream2(bout);
	}

	public PrintWriter getWriter() throws IOException {
		bout = new ByteArrayOutputStream();
		pw = new PrintWriter(new OutputStreamWriter(bout, "utf-8"), true);
		return pw;
	}

	public ByteArrayOutputStream getBout() {
		if(pw != null) {
			pw.close();
		}

		return bout;
	}
}

class MyByteArrayOutputStream2 extends ServletOutputStream {

	private ByteArrayOutputStream bout;

	public MyByteArrayOutputStream2(ByteArrayOutputStream bout) {
		this.bout = bout;
	}

	@Override
	public boolean isReady() {
		return false;
	}

	@Override
	public void setWriteListener(WriteListener arg0) {

	}

	@Override
	public void write(int b) throws IOException {
		bout.write(b);
	}

}
