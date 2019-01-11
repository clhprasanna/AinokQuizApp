package com.mjc.quizapp.action;

import java.util.Map;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class SessionInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
	
		Map<String, Object> session = invocation.getInvocationContext().getSession();
		
		// check if session is expired/empty
		if (session.isEmpty())
			return "session";
		
		return invocation.invoke();

	}

}
