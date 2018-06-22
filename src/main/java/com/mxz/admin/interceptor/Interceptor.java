package com.mxz.admin.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.comm.util.ResMsgUtils;
import com.comm.util.ResponseObject;
import com.comm.util.session.SysConstant;

/**
 * 后台系统登录拦截
 */
public class Interceptor implements HandlerInterceptor{
	private static Logger logger = LogManager.getLogger(Interceptor.class); 
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String requestUri = request.getRequestURI();
		String webName=request.getContextPath();//系统应用名称
        //请求controller中的方法名
        HandlerMethod handlerMethod = (HandlerMethod)handler;
        //解析HandlerMethod
        String methodName = handlerMethod.getMethod().getName();
        String className = handlerMethod.getBean().getClass().getSimpleName();
        System.out.println("webName:"+webName+"===methodName:"+methodName+"===className:"+className); 
        if (request.getSession().getAttribute(SysConstant.SESSION_USER)!= null){
        	if(methodName.startsWith("get")||methodName.startsWith("find")||methodName.startsWith("query")){//放行查询接口
            	return true;
            }
        	return true;
        	/*String userPower=(String) request.getSession().getAttribute(SysConstant.SESSION_USERPOWER);
        	if(StringUtils.isNotBlank(userPower)) {
	        	String[] actionStr=userPower.split("|");
	        	for (String str : actionStr) {
	        		if(requestUri.indexOf(str)>-1) {
	        			return true;
	            	}
				}
	        	ResponseObject responseObject = new ResponseObject(Boolean.FALSE);
	            responseObject.setResponseMessage("没有操作权限！");
	            System.out.println("===没有操作权限！===");
	            ResMsgUtils.sendJsonMessage(response, responseObject);
	        	return false;
        	}else {
        		ResponseObject responseObject = new ResponseObject(Boolean.FALSE);
	            responseObject.setResponseMessage("没有操作权限！");
	            System.out.println("=====没有操作权限！=====");
	            ResMsgUtils.sendJsonMessage(response, responseObject);
        		return false;
        	}*/
        }
        System.out.println("========没有登录，或session已失效========");
        ResponseObject responseObject = new ResponseObject(Boolean.FALSE);
        responseObject.setResponseMessage("登录已失效，请重新登录！");
        responseObject.setResponseCode(ResponseObject.CODE_1);
        ResMsgUtils.sendJsonMessage(response, responseObject);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    	if(ex != null) {
    		//请求controller中的方法名
            HandlerMethod handlerMethod = (HandlerMethod)handler;
            //解析HandlerMethod
    		String methodName = handlerMethod.getMethod().getName();
            String className = handlerMethod.getBean().getClass().getSimpleName();
    		logger.error("[**]"+"["+className+"]"+"["+methodName+"]",ex);
    	}
    }
}
