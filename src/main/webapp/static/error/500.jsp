<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>500</title>
</head>
<body>
	<div style="text-align: center; padding: 15% 0;">
		<i style="width:100px;height:100px;"><img src="<%=basePath %>static/error/error.png"/><i>
		<p style="font-size: 20px; font-weight: 300; color: #999;"><span>错误原因：</span>Web 服务器不能执行此请求，请联系管理员！</p>
	</div>
</body>
</html>