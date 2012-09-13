package interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class AuthenticationInterceptor implements Interceptor {
	Logger logger = LoggerFactory.getLogger(this.getClass().getName());
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void destroy() {

	}

	public void init() {

	}

	public String intercept(ActionInvocation actionInvocation) throws Exception {
		// if (actionInvocation.getAction() instanceof FooAction) {
		// logger.info(actionInvocation.getInvocationContext().getName());
		// return Action.ERROR;
		// }
		logger.info(actionInvocation.getInvocationContext().getName());
		return Action.SUCCESS;
	}
}
