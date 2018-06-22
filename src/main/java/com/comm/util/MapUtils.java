package com.comm.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;

public class MapUtils {
	/**
	 * getPageList分页传jsonStr转map
	 * @param queryJson
	 * @return
	 */
  	public static Map<String, Object> JsonToMap(String queryJson){
  		Map<String, Object> paramMap=null;
  		int page=1;
  		int limit=10;
  		if(StringUtils.isNotBlank(queryJson)){
			paramMap=JSON.parseObject(queryJson);
		}else {
			paramMap=new HashMap<String, Object>();
		}
  		if (paramMap.containsKey("page")) {
  			String pageStr=String.valueOf(paramMap.get("page"));
			if(StringUtils.isNotBlank(pageStr)){
				page=Integer.parseInt(pageStr);
			}
		}
		if (paramMap.containsKey("limit")) {
			String limitStr=String.valueOf(paramMap.get("limit"));
			if(StringUtils.isNotBlank(limitStr)){
				limit=Integer.parseInt(limitStr);
			}
		}
		paramMap.put("limitType", 1);
		paramMap.put("firstResult", (page - 1) * limit);
		paramMap.put("maxResult", limit);
		return paramMap;
  	}
  	/**
	 * getPageList分页传jsonStr转map 添加分页查询相关数据
	 * @param queryJson
	 * @return
	 */
  	public static Map<String, Object> JsonToMap(String queryJson,int page,int limit){
  		Map<String, Object> paramMap=null;
  		if(StringUtils.isNotBlank(queryJson)){
			paramMap=JSON.parseObject(queryJson);
		}else {
			paramMap=new HashMap<String, Object>();
		}
  		paramMap.put("limitType", 1);
		paramMap.put("firstResult", (page - 1) * limit);
		paramMap.put("maxResult", limit);
		return paramMap;
  	}
  	/**
  	 * getPageList分页传map值转换
  	 * @param map
  	 * @return
  	 */
  	public static Map<String, Object> changeMap(Map<String, Object> paramMap){
  		int page=1;
  		int limit=10;
  		if(paramMap==null){
			paramMap=new HashMap<String, Object>();
		}
  		if (paramMap.containsKey("page")) {
  			String pageStr=String.valueOf(paramMap.get("page"));
			if(StringUtils.isNotBlank(pageStr)){
				page=Integer.parseInt(pageStr);
			}
		}
		if (paramMap.containsKey("limit")) {
			String limitStr=String.valueOf(paramMap.get("limit"));
			if(StringUtils.isNotBlank(limitStr)){
				limit=Integer.parseInt(limitStr);
			}
		}
		paramMap.put("limitType", 1);
		paramMap.put("firstResult", (page - 1) * limit);
		paramMap.put("maxResult", limit);
		return paramMap;
  	}
  	/**
      * 获取利用反射获取类里面的值和名称
     */
    public static Map<String, Object> beanToMap(Object bean) {
    	Class<? extends Object> clazz = bean.getClass();  
        Map<String, Object> returnMap = new HashMap<String, Object>();  
        BeanInfo beanInfo = null;  
        try {  
            beanInfo = Introspector.getBeanInfo(clazz);  
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();  
            for (int i = 0; i < propertyDescriptors.length; i++) {  
                PropertyDescriptor descriptor = propertyDescriptors[i];  
                String propertyName = descriptor.getName();  
                if (!propertyName.equals("class")) {  
                    Method readMethod = descriptor.getReadMethod();  
                    Object result = null;  
                    result = readMethod.invoke(bean, new Object[0]);  
                    if (null != propertyName) {  
                        propertyName = propertyName.toString();  
                    }  
                    returnMap.put(propertyName, result);  
                }  
            }  
        } catch (IntrospectionException e) {  
            System.out.println("分析类属性失败");  
        } catch (IllegalAccessException e) {  
            System.out.println("实例化JavaBean失败");  
        } catch (IllegalArgumentException e) {  
            System.out.println("映射错误");
        } catch (InvocationTargetException e) {  
            System.out.println("调用属性的setter方法失败");  
        }  
        return returnMap;  
    }
}
