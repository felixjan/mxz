layui.config({
	base : basePath+'static/plugins/build/js/' // 插件js路径
}).use([ 'table', 'enhanceform' ], function() {
	var $ = layui.jquery;// 引入jquery
	var form = layui.form;// 引入form
	var enhanceForm = layui.enhanceform;
	var enhance = null;
	var table = layui.table;
	// from表单查询提交
	form.on('submit(queryMain)', function(data) {
		table.reload('mainTable', {
			page : {
				curr : 1
			},// 重新从第 1 页开始
			where : {
				queryJson : JSON.stringify(data.field)
			}
		});
		return false;
	});
	$('.operateBtn').on('click', function() {
		var type = $(this).data('power');
		if (type == 'add') {
			$('#addInfoForm')[0].reset();
			$('.addInfoDiv').show();
			layer.open({
				type : 1,
				title : addInfoTitle,
				area : ['auto',baseHeight],
				content : $('#addInfoForm'),// 这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
				end : function() {
					$('.addInfoDiv').hide();
				}
			});
		} else if (type == 'edit') {
			var checkStatus = table.checkStatus('mainTable');
			var data = checkStatus.data;
			if (data.length == 1) {
				var infoForEdit = data[0];
				/* 自动给表单赋值开始 */
				enhance = new enhanceForm({
					elem : '#editInfoForm' // 表单选择器 表单id
				});
				enhance.filling(infoForEdit);
				/* 自动给表单赋值结束 */
				$('.editInfoDiv').show();
				layer.open({
					type : 1,
					title : editInfoTitle,
					area : ['auto',baseHeight],
					content : $('#editInfoForm'),// 这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
					end : function() {
						$('.editInfoDiv').hide();
					}
				});
			} else {
				layer.alert('请选中一条数据进行修改！');
			}
		}
	});
	// 监听新增提交
	form.on('submit(addInfoBtn)', function(data) {
		layer.confirm('是否确认!', {
			btn : [ '确定', '取消' ]
		}, function(index, layero) {
			$.ajax({
				url : addInfoUrl,
				type : 'post',
				dataType : 'json',
				contentType : 'application/json',
				data : JSON.stringify(data.field),
				success : function(resData) {
					getResData(resData,"添加成功!");
					if(resData.responseStatus==true){
						$("#queryMainBtn").click();
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					layer.alert('系统错误，请联系管理员！');
				}
			});
		});
		return false;
	});
	// 监听修改提交
	form.on('submit(editInfoBtn)', function(data) {
		layer.confirm('是否确认!', {
			btn : [ '确定', '取消' ]
		}, function(index, layero) {
			$.ajax({
				url : editInfoUrl,
				type : 'post',
				dataType : 'json',
				contentType : 'application/json',
				data : JSON.stringify(data.field),
				success : function(resData) {
					getResData(resData,"修改成功!");
					if(resData.responseStatus==true){
						$("#queryMainBtn").click();
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					layer.alert('系统错误，请联系管理员！');
				}
			});
		});
		return false;
	});
	// 监听取消
	form.on('submit(cancelBtn)', function(data) {
		layer.closeAll('page'); // 关闭所有页面层
		return false;
	});
});
//获取页面跳转传参
window.parseUrl = function(){
    var url=location.href;
    var i=url.indexOf('?');
    if(i==-1)return;
    var querystr=url.substr(i+1);
    var arr1=querystr.split('&');
    var arr2=new Object();
    for  (i in arr1){
        var ta=arr1[i].split('=');
        arr2[ta[0]]=ta[1];
    }
    return arr2;
}
//获取用户操作权限
window.getUserPower = function(powerId){
	$.ajax({
		url : basePath+'sys/user/getPowerList',
		type : 'post',
		dataType : 'json',
		contentType : 'application/json',
		data : JSON.stringify({
			pid : powerId,
			limitType : 1
		}),
		success : function(resData) {
			for ( var i = 0; i <resData.length; i++){
			    $("[data-power="+resData[i].powerName+"]").css('display','inline-block');
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			layer.alert('系统错误，请联系管理员！');
		}
	});
}
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