package com.stardust.express.interceptors;

import java.util.Map;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class AuthenticationInterceptor implements Interceptor {  

	private static final long serialVersionUID = 8149085439273994455L;

	@Override
	public void destroy() {
		
	}
	
	@Override
	public void init() {
		
	}
	
	@Override
	public String intercept(ActionInvocation actionInvocation) throws Exception {
		Map<String, Object> session = actionInvocation.getInvocationContext().getSession();  
		String isLoggedIn = (String)session.get("LOGGED_IN");  
	    if (isLoggedIn == null || isLoggedIn.equals("N")) {
	      return "invalid_session";
	    } else {  
	      return actionInvocation.invoke();  
	    }  
	}  
}  