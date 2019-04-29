layui.define(['jquery','layer'],function(exports){
    $ = layui.jquery;
    layer = layui.layer;
    //获取token
    t = layui.data(sysToken).token;
    //全局的ajax访问，处理ajax清求时异常
    $.ajaxSetup({
        dataType: "json",
        contentType: "application/json",
        headers: { // 默认添加请求头
            token: t,
            //"Content-Type": "application/json;charset=utf-8"
            //"Content-Type": "application/x-www-form-urlencoded;charset=utf-8"
        },
        //完成请求后触发
        complete: function (XMLHttpRequest, textStatus) {
            //通过XMLHttpRequest取得响应结果
            var res = XMLHttpRequest.responseText;
            try {
                //console.log(res);
                var jsonData = JSON.parse(res);
                if (jsonData.code == 403) {
                    //清空Token
                    layui.data(sysToken, {
                        key: token
                        , remove: true
                    });
                    layui.data(sysUser, {
                        key: user
                        , remove: true
                    });
                    //如果超时就处理 ，指定要跳转的页面(比如登陆页)
                    layer.msg('Token验证失败,请重新登录', {
                        offset: '15px',
                        icon: 5,
                        time: 2000
                    }, function () {
                        location.href = "/login.html"
                    });
                } else if (jsonData.code == 500) {
                    //其他的异常情况,给个提示。
                    layer.msg(jsonData.msg);
                } else {
                    //正常情况就不统一处理了
                }
            } catch (e) {
            }
        }
        ,error: function(jqXHR, textStatus, errorThrown){
            switch (jqXHR.status){
                case(0):
                    layer.alert("网络故障");
                    break;
                case(500):
                    layer.alert("服务器系统内部错误");
                    break;
                case(403):
                    layer.alert("请求不允许");
                    break;
                case(408):
                    layer.alert("请求超时");
                    break;
                case(503):
                    layer.alert("服务器过载或暂停维修，宕机");
                    break;
                default:
                    layer.alert("未知错误");
            }
        }
    });

    exports('baseAjax',{});
});
