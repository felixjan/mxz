 /**
 * Name:app.js
 * Author:zhang
 * E-mail:1411004666@qq.com
 */
var tab;
layui.define(['tab', 'navbar', 'onelevel'], function(exports) {
    var $ = layui.jquery;
    var navbar = layui.navbar;
    tab = layui.tab;
    var app = {
        config: {},
        set: function(options) {
            var that = this;
            $.extend(true, that.config, options);
            return that;
        },
        init: function() {
            var that = this,
                _config = that.config;
            if (_config.type === 'iframe') {
                tab.set({
                    elem: '#container',
                    onSwitch: function(data) { //选项卡切换时触发
                    	var n=$('.layui-show[lay-item-id]').children("iframe");
                    	n.attr("src",n.attr("src"));//刷新tab
                        //console.log(data.layId); //lay-id值
                        //console.log(data.index); //得到当前Tab的所在下标
                        //console.log(data.elem); //得到当前的Tab大容器
                    },
                    closeBefore: function(data) { //关闭选项卡之前触发
                        // console.log(data);
                        // console.log(data.icon); //显示的图标
                        // console.log(data.id); //lay-id
                        // console.log(data.title); //显示的标题
                        // console.log(data.url); //跳转的地址
                        return true; //返回true则关闭
                    }
                }).render();
                //navbar加载方式一，设置远程地址加载
                navbar.set({
                    remote: {
                        url: _config.jsonUrl
                    }
                }).render(function(data) {
                    tab.tabAdd(data);
                });
            }
            return that;
        }
    };
    //输出test接口
    exports('app', app);
});