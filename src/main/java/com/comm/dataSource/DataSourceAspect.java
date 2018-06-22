package com.comm.dataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
public class DataSourceAspect{
	private static Logger logger = LogManager.getLogger(DataSourceAspect.class); 
	public void before(JoinPoint joinPoint) {
		String className = joinPoint.getSignature().getDeclaringType().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
		try {
			if (methodName.indexOf("add") > -1 || methodName.indexOf("create") > -1 
					|| methodName.indexOf("save") > -1 || methodName.indexOf("insert") > -1 
					|| methodName.indexOf("edit") > -1 || methodName.indexOf("update") > -1
					|| methodName.indexOf("batch") > -1 || methodName.indexOf("execute") > -1
					|| methodName.indexOf("delete") > -1 || methodName.indexOf("remove") > -1){
				if(className.startsWith("Log")) {
					System.out.println(className + "======" + methodName + " 切换到: logMaster");
					DataSourceSwitcher.setLogMaster();
				}else {
					System.out.println(className + "======" + methodName + " 切换到: master");
					DataSourceSwitcher.setMaster();
				}
			} else {
				if(className.startsWith("Log")) {
					System.out.println(className + "======" + methodName + " 切换到: logSlave");
					DataSourceSwitcher.setLogSlave();
				}else {
					System.out.println(className + "======" + methodName + " 切换到: slave");
					DataSourceSwitcher.setSlave();
				}
			}
		} catch (Exception ex) {
			logger.error("[*]"+"["+className+"]"+"["+methodName+"]",ex);
			ex.printStackTrace();
		}
	}
	
}
