package com.comm.util;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;

public class HttpUtils {

    /**
	 * http get请求
	 * @param url
	 * @return
	 */
	public String httpGet(String url) {
		String responseContent = null;
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			HttpGet httpGet = new HttpGet(url);
			CloseableHttpResponse response = httpclient.execute(httpGet);
			httpGet.addHeader("Content-type", "application/json; charset=utf-8");
			try {
				if(response.getStatusLine().getStatusCode()==HttpStatus.SC_OK){
					HttpEntity entity = response.getEntity();
					responseContent=EntityUtils.toString(entity, "UTF-8");
					EntityUtils.consume(entity);
				}else{
					System.out.println("接口调用错误代码:"+response.getStatusLine().getStatusCode());
				}
			} finally {
				response.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("responseContent = " + responseContent);
		return responseContent;
	}
	/**
	 * http post请求
	 * @param url
	 * @return
	 */
    public String httpPost(String url, Map<String, Object> paramsMap) {
		String responseContent = null;
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			HttpPost httpPost = new HttpPost(url);
			httpPost.addHeader("Content-type", "application/json; charset=utf-8");
			httpPost.setEntity(new StringEntity(JSON.toJSONString(paramsMap), Charset.forName("UTF-8")));
			CloseableHttpResponse response = httpclient.execute(httpPost);
			try {
				if(response.getStatusLine().getStatusCode()==HttpStatus.SC_OK){
					HttpEntity entity = response.getEntity();
					responseContent=EntityUtils.toString(entity, "UTF-8");
					EntityUtils.consume(entity);
				}else{
					System.out.println("接口调用错误代码:"+response.getStatusLine().getStatusCode());
				}
			} finally {
				response.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("responseContent = " + responseContent);
		return responseContent;
	}
    
    /**
     * 获取请求url地址
     * @param request
     * @return
     */
    public static String getUrl(HttpServletRequest request){
    	String contextPath=request.getContextPath();//应用名称 服务器部署的都没有应用名称
    	String requestUrl="";
    	if(StringUtils.isNotBlank(contextPath)) {
    		requestUrl = request.getScheme() //当前链接使用的协议
	    		    +"://" + request.getServerName()//服务器地址 
	    		    + ":" + request.getServerPort() //端口号 
	    		    + request.getContextPath(); //应用名称
    	}else {
    		requestUrl = request.getScheme() //当前链接使用的协议
        		    +"://" + request.getServerName();//服务器地址 
    	}
		return requestUrl;
    }
	
}
