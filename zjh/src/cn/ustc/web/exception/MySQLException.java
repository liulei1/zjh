package cn.ustc.web.exception;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class MySQLException extends RuntimeException {

	public MySQLException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MySQLException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public MySQLException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public MySQLException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
