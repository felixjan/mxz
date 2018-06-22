var basePath='./';//相对路径 
layui.config({
	base : basePath+'static/plugins/build/js/'
}).use([ 'app', 'enhanceform', 'laydate','upload' ], function() {
	var app = layui.app;
	var $ = layui.jquery;// 引入jquery
	var form = layui.form;// 引入form
	var layer = layui.layer;
	var laydate = layui.laydate;
	var upload = layui.upload;
	//添加验证规则  
    form.verify({     
    	password : function(value, item){  
            if (value.length == 0) {  
                return '请输入旧密码';  
            }  
        },  
        newPassword : function(value, item){  
            if(value.length < 6){  
                return "新密码长度不能小于6位";  
            }  
        },  
        newPasswordTwo : function(value, item){  
            if(!new RegExp($("#newPassword").val()).test(value)){  
                return "两次输入密码不一致，请重新输入！";  
            }  
        }  
    });  
	// 日期
	laydate.render({
		elem : '#birthday',
		type: 'datetime'
	});
	var enhanceForm = layui.enhanceform;
	var enhance = null;
	// 主入口
	window.exit = function() {
		$.ajax({
			url : basePath+'login/exit',
			type : 'get',
			dataType : 'json',
			success : function(resData) {
				window.location.href = basePath+"login.html";
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				layer.alert('系统错误，请联系管理员！');
			}
		});
	}
	//获取登录信息
	window.getIsLogin = function(){
		$.ajax({
			url : basePath+'sys/user/getIsLogin',
			type : 'get',
			dataType : 'json',
			success : function(resData) {
				if (resData.responseStatus == true) {
					sysUser = resData.responseData.sysUser;
					$(".realName").html(sysUser.realName);
					var picUrl = sysUser.picUrl;
					if (picUrl != "") {
						$(".userLogo").attr("src", picUrl);
					}
					app.set({
						type : 'iframe',
						jsonUrl : basePath+'sys/user/getMenuList'
					}).init();
				} else {
					window.location.href = basePath+"login.html";
				}

			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				layer.alert('系统错误，请联系管理员！');
			}
		});
	}
	var sysUser;//定义全局变量
	getIsLogin();
	$('#baseInfo').on('click', function() {
		$('#baseInfoForm')[0].reset();
		/* 自动给表单赋值开始 */
		enhance = new enhanceForm({
			elem : '#baseInfoForm' // 表单选择器 表单id
		});
		enhance.filling(sysUser);
		/* 自动给表单赋值结束 */
		$('.baseInfoDiv').show();
		layer.open({
			type : 1,
			title : '个人资料',
			area : [ 'auto', '63%' ],
			content : $('#baseInfoForm'),// 这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
			end : function() {
				$('.baseInfoDiv').hide();
			}
		});
	});
	
	$('#modifyPassword').on('click', function() {
		$('#modifyPasswordForm')[0].reset();
		$('#userName').val(sysUser.userName);
		$('#userId').val(sysUser.id);
		$('.modifyPasswordDiv').show();
		layer.open({
			type : 1,
			title : '个人资料',
			area : [ 'auto', 'auto' ],
			content : $('#modifyPasswordForm'),// 这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
			end : function() {
				$('.modifyPasswordDiv').hide();
			}
		});
	});
	// 监听取消
	form.on('submit(cancelBtn)', function(data) {
		layer.closeAll('page'); // 关闭所有页面层
		return false;
	});
	// 监听基本信息修改
	form.on('submit(baseInfoBtn)', function(data) {
		layer.confirm('是否确认!', {
			btn : [ '确定', '取消' ]
		}, function(index, layero) {
			$.ajax({
				url : basePath+'sys/user/updateBaseInfo',
				type : 'post',
				dataType : 'json',
				contentType : 'application/json',
				data : JSON.stringify(data.field),
				success : function(resData) {
					getResData(resData,"修改成功!");
					if(resData.responseStatus==true){
						getIsLogin();
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					layer.alert('系统错误，请联系管理员！');
				}
			});
		});
		return false;
	});
	// 监听密码修改
	form.on('submit(modifyPasswordBtn)', function(data) {
		layer.confirm('是否确认!', {
			btn : [ '确定', '取消' ]
		}, function(index, layero) {
			$.ajax({
				url : basePath+'sys/user/updatePassword',
				type : 'post',
				dataType : 'json',
				contentType : 'application/json',
				data : JSON.stringify(data.field),
				success : function(resData) {
					getResData(resData,"密码修改成功!");
					if(resData.responseStatus==true){
						getIsLogin();
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					layer.alert('系统错误，请联系管理员！');
				}
			});
		});
		return false;
	});
	//编辑头像
	$('#modifyPicUrl').on('click', function() {
		$('.modifyPicUrlDiv').show();
		layer.open({
			type : 1,
			title : '编辑头像',
			area : [ '240px', '280px' ],
			content : $('#modifyPicUrlDiv'),// 这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
			end : function() {
				$('.modifyPicUrlDiv').hide();
			}
		});
	});
	//普通图片上传
	var uploadInst = upload.render({
	    elem: '#choiceImgBtn',
	    url: basePath+'sys/user/updatePic',
	    size: 5120 ,//限制文件大小，单位 KB
	    accept: 'images', //图片文件
	    auto: false,//不自动上传
	    bindAction: '#uploadImgBtn',
	    choose: function(obj){
			//预读本地文件示例，不支持ie8
			obj.preview(function(index, file, result){
				 $('#imgId').attr('src', result); //图片链接（base64）
			});
		},
	    before: function(obj){
	      this.data={'id':sysUser.id};
	    },
	    done: function(resData){
	    	//如果上传失败
	    	getResData(resData,"修改头像成功!");
	    	if(resData.responseStatus==true){
	    		getIsLogin();
	    	}
	    	//上传成功
	    },
	    error: function(){
	    	layer.alert('系统错误，请联系管理员！');
	    }
	});
});
//处理返回结果集
window.getResData = function(resData,message){
	var flag=resData.responseStatus;
	if(flag==false){
		message=resData.responseMessage;
	}
	if(flag==false && resData.responseCode==1){
		toLogin();//超时重新登录
	}else{
		if(flag==true){
			layer.closeAll('page'); // 关闭所有页面层
		}
		layer.msg(message, {
			icon : 1,
			time : 1000
		});
	}
}
//超时重新登录
window.toLogin = function(){
	layer.open({
        type: 1,
        title: false, //不显示标题栏
        closeBtn: false,
        area: '300px;',
        shade: 0.8,
        id: 'LAY_layuipro', //设定一个id，防止重复弹出
        btn: ['登录'],
        btnAlign: 'c',
        moveType: 1, //拖拽模式，0或者1
        content: '<div style="padding: 50px; line-height: 22px; background-color: #393D49; color: #fff; font-weight: 300;">登录超时，请重新登录！</div>',
        success: function(layero){
          var btn = layero.find('.layui-layer-btn');
          btn.find('.layui-layer-btn0').attr({
            href: basePath+'login.html',
            target: '_top'
          });
        }
    });
}