package com.comm.dataSource;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.comm.cms.pojo.SysUser;
import com.comm.util.IpAdrressUtils;
import com.comm.util.session.SessionFactory;
@Aspect  
public class LogAop{
	private static Logger logger = LogManager.getLogger(LogAop.class); 
	@Pointcut("execution(* com.comm.*.mapper.*.*(..))")
    public void pointcut() {}
	@Autowired  
	private  HttpServletRequest request;  
	
	// 方法执行完之后被调用
	@AfterReturning(pointcut = "pointcut()")
	public void afterReturning(JoinPoint joinPoint) {
		String className = joinPoint.getSignature().getDeclaringType().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        String jsonStr = JSONObject.toJSONString(args);//将java对象转换为字符串
        SysUser sysUser=SessionFactory.getSessionUser(request);
        String userStr = JSONObject.toJSONString(sysUser);//将java对象转换为字符串
		if (methodName.indexOf("add") > -1 || methodName.indexOf("create") > -1 
				|| methodName.indexOf("save") > -1 || methodName.indexOf("insert") > -1) {
			logger.info("[create]"+"["+className+"]"+"["+methodName+"]"+jsonStr+"[operator]"+userStr+"[ip]"+IpAdrressUtils.getIpAdrress(request));
		}else if(methodName.indexOf("edit") > -1 || methodName.indexOf("update") > -1
				|| methodName.indexOf("batch") > -1 || methodName.indexOf("execute") > -1) {
			logger.info("[update]"+"["+className+"]"+"["+methodName+"]"+jsonStr+"[operator]"+userStr+"[ip]"+IpAdrressUtils.getIpAdrress(request));
		}else if(methodName.indexOf("delete") > -1 || methodName.indexOf("remove") > -1){
			logger.info("[delete]"+"["+className+"]"+"["+methodName+"]"+jsonStr+"[operator]"+userStr+"[ip]"+IpAdrressUtils.getIpAdrress(request));
		} else {
			logger.debug("[select]"+"["+className+"]"+"["+methodName+"]"+jsonStr+"[operator]"+userStr+"[ip]"+IpAdrressUtils.getIpAdrress(request));
		}
	}
	// 抛出Exception之后被调用
	@AfterThrowing(pointcut = "pointcut()", throwing = "error")
	public void afterThrowing(JoinPoint joinPoint, Throwable error) {
		String className = joinPoint.getSignature().getDeclaringType().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        String jsonStr = JSONObject.toJSONString(args);//将java对象转换为字符串
		logger.error("[***]"+"["+className+"]"+"["+methodName+"]"+jsonStr,error);
	}
	public static void main(String[] args) {
        for(int i=0; i<10; i++){  
            logger.trace("===root trace");  
            logger.debug("===root debug");  
            logger.info("===root info");  
            logger.warn("===root warn");  
            logger.error("===root error");  
            logger.fatal("===root fatal");  
        }
	}
}
