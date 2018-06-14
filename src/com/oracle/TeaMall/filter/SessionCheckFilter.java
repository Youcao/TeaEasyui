package com.oracle.TeaMall.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 这是对未登录后台却要访问后台信息页面的用户进行拦截
 * Servlet Filter implementation class SessionCheckFilter
 */
public class SessionCheckFilter implements Filter {

	public SessionCheckFilter() {
    }

	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("进入后台拦截页面非法请求");
		//1.先获取用户要访问的URL地址和请求的参数
		HttpServletRequest r=(HttpServletRequest)request;
		HttpServletResponse rs=(HttpServletResponse)response;
		String url=r.getRequestURI().toString();
		System.out.println(url);
		/*String param=r.getQueryString();
		if(param==null) {//如果请求的参数为空，则说明请求方式为post，登录和修改个人信息，这两种情况都可以直接放行
			chain.doFilter(request, response);
			return ;
		}*/
		if(url.contains("index.jsp"))
		{
			//先判断session中有没有已登录的用户
			if(r.getSession().getAttribute("user")!=null) {
				System.out.println("session中有该用户的登录信息，放行");
				chain.doFilter(request, response);//有的话就放行
			}else {//没有则跳转到登录页面
				System.out.println("session中没有该用户的登录信息，拦截");
				rs.sendRedirect("login.jsp");
			}
		}else {//如果上述的拦截没有调用就放行
			System.out.println("jsp中不涉及到非法请求，放行");
			chain.doFilter(request, response);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
