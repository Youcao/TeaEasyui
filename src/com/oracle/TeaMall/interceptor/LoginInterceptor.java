package com.oracle.TeaMall.interceptor;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.oracle.TeaMall.bean.User;

public class LoginInterceptor implements Interceptor{

	@Override
	public String intercept(ActionInvocation invacation) throws Exception {
		System.out.println("进入拦截器了");
		//1.先获取用户的请求路径和参数
		String path=ServletActionContext.getRequest().getRequestURL().toString();
		System.out.println(path);
		//2.判断路径中是否含有需要登录才能访问的
		if(path.contains("UserAction!index.action")) {
			System.out.println("用户正在访问主页");
			if(ServletActionContext.getRequest().getSession().getAttribute("user")==null) {
				System.out.println("用户是非法访问");
				return "illegalVisit";
			}else {
				System.out.println("用户正常访问");
				return invacation.invoke();
			}
		}else {
			System.out.println("action中正常访问，不用拦截");
			return invacation.invoke();
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	/*@Override
	protected String doIntercept(ActionInvocation invacation) throws Exception {
		User u=(User) ServletActionContext.getRequest().getSession().getAttribute("user");
		if(null == u) {
			return "illegalVisit";
		}else {
			return invacation.invoke();
		}
	}
*/
}
