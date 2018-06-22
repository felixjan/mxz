layui.use(['form','layer'],function(){
	var form = layui.form;
	var $ = layui.jquery;
	//登录按钮事件
	form.on("submit(login)",function(data){
		$.ajax({
			url : './login/tologin',
			type : 'post',
			dataType : 'json',
			contentType : 'application/json',
			data : JSON.stringify(data.field),
			success : function(resData) {
				if(resData.responseStatus==false){
					layer.msg(resData.responseMessage, {
						icon : 1,
						time : 1000
					});
				}else{
					window.location.href="./index.html";
				}
				
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				layer.alert('系统错误，请联系管理员！');
			}
		});
		return false;
	});
	window.getCode = function(){
		$("#imgCode").attr("src","./login/getVerifyCode?"+Math.random());
	}
})