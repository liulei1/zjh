package cn.ustc.web.interceptor;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * 登录拦截器
 * @author liu
 *
 */
public class LoginInterceptor extends AbstractInterceptor{
	// don't intercept
	private String exclude;
	public void setExclude(String exclude) {
		this.exclude = exclude;
	}
	
	@Override
	public String intercept(ActionInvocation action) throws Exception {
		//如果user对象不为空，拦截器放行
		if(ServletActionContext.getServletContext().getAttribute("user") != null){
			// have login
			return action.invoke();
		}else {
			
			if(exclude != null){
				String[] methods = exclude.split(",");
				//谁也不拦截，放行
				if("none".equals(methods[0])){
					return action.invoke();
				}
				
				// 判断methods（放行数组） 是否包含当前 访问
				String currentMethod = ActionContext.getContext().getName(); // user_login
				for (String method : methods) {
					if (method.equals(currentMethod)) {
						// 当前method 被配置放行
						return action.invoke();
					}
				}
			}
			ActionSupport as = (ActionSupport) action.getAction();
			as.addActionError("请先登录吧！");
			return "login";
		}
	}
}
