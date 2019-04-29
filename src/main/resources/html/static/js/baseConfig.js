/**系统配置后台 全局js*/
var baseUrl = "http://127.0.0.1:8081/";
var sysToken = "sys_token";
var token = "token";
var sysUser = "sys_user";
var user = "user";

//获取用户登录信息
var tuser = layui.data(sysUser).user;

/**
 * 退出登录
 */
function logout(msg){
    //清空Token
    layui.data(sysToken, {
        key: token
        ,remove: true
    });
    layui.data(sysUser, {
        key: user
        ,remove: true
    });

    layer.msg(msg, {
        offset: '15px',
        icon: 1,
        time: 1000
    }, function () {
        location.href ="/login.html"
    });
}

//判断字符是否为空的方法
function isEmpty(obj){
    if(typeof obj == "undefined" || obj == null || obj == ""){
        return true;
    }else{
        return false;
    }
}
