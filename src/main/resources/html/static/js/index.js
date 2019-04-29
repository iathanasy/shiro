layui.config({
    base: 'static/module/' //你存放新模块的目录，注意，不是layui的模块目录
}).use(['element','jquery','layer','tabrightmenu'],function(){
    element = layui.element;
    $ = layui.jquery;
    layer = layui.layer;
    rightmenu_ = layui.tabrightmenu;//右键菜单

    // 默认方式渲染全部：关闭当前（closethis）、关闭所有但固定（closeallbutpin）、关闭其它但固定（closeothersbutpin）、关闭左侧所有但固定（closeleftbutpin）、关闭右侧所有但固定（closerightbutpin）、刷新当前页（refresh）
    rightmenu_.render({
        container: '#nav',
        filter: 'tab-switch',
        //width:110,// 右键弹出框的宽度，一般100~110即可
        // navArr：对象数组，每个对象包含dataType、title、method属性
        navArr: [
            {eventName: 'closethis', title: '关闭当前', icon: 'layui-icon-close'},
            {eventName: 'refresh', title: '刷新当前页', icon: 'layui-icon-refresh'},
            {eventName: 'closeallbutpin', title: '关闭所有'},
            {eventName: 'closeothersbutpin', title: '关闭其它'},
            //{eventName: 'closeleftbutpin', title: '关闭左侧全部'},
            //{eventName: 'closerightbutpin', title: '关闭右侧全部'},
            {eventName: 'line'}// 创建分割线
        ],
        // pinTabTitles和pintabIDs作用一样，可以二选一（建议）也可以都保留，只是便于开发使用考虑加入标题和ID形式进行两种方式的过滤
        //pinTabTitles:['首页'],//表示标题为“网站设置”的标签为固定标签**/
        //pintabIDs:['0'],//表示id为["0","1"]的标签为固定标签
    });

    var i=0;
    $('#navSorH').click(function(){
        if(i==0){
            $(".layui-side").animate({width:'0px'});
            $(".layui-body").animate({left:'0px'});
            //$(".layui-layout-left").animate({left:'10px'});
            $(this).html('<i class="layui-icon layui-icon-spread-left"></i>   ');
            i++;
        }else{
            $(".layui-side").animate({width:'200px'});
            $(".layui-body").animate({left:'200px'});
            //$(".layui-layout-left").animate({left:'200px'});
            $(this).html('<i class="layui-icon layui-icon-shrink-right"></i>');
            i--;
        }

    });

    //监听左侧菜单点击
    element.on('nav(left-menu)', function(elem){
        addTab(elem[0].innerText,elem[0].attributes[1].nodeValue,elem[0].id);
    });
    //监听tab选项卡切换
    element.on('tab(tab-switch)', function(data){
        if(data.elem.context.attributes != undefined){
            var id = data.elem.context.attributes[0].nodeValue;
            layui.each($(".layui-nav-child"), function () {
                $(this).find("dd").removeClass("layui-this");
            });
            //一级菜单
            layui.each($(".layui-nav"), function () {
                $(this).find("li").removeClass("layui-this");
            });
            //一级菜单 需要layui-nav-item不然样式会乱
            $("#"+id).attr("class","layui-nav-item layui-this");
            //$("#"+id).attr("class","layui-this");
        }
    });

    //加载首页
    //addTab("首页","main.html","0");
    var height = $(".layui-body").height() - 110;
    $("#main").css("min-height",height+"px");

});

/**
 * 新增tab选项卡，如果已经存在则打开已经存在的，不存在则新增
 * @param tabTitle 选项卡标题名称
 * @param tabUrl 选项卡链接的页面URL
 * @param tabId 选项卡id
 */
function addTab(tabTitle,tabUrl,tabId){
    if ($(".layui-tab-title li[lay-id=" + tabId + "]").length > 0) {
        element.tabChange('tab-switch', tabId);
    }else{
        //height = 内容高度 - (tab + footer)
        var height = $(".layui-body").height() - 110;
        element.tabAdd('tab-switch', {
            title: tabTitle
            ,content: '<iframe src="'+tabUrl+'" width="100%" style="min-height:'+height+'px;" frameborder="0" scrolling="auto" onload="setIframeHeight(this)"></iframe>' // 选项卡内容，支持传入html
            ,id: tabId //选项卡标题的lay-id属性值
        });
        element.tabChange('tab-switch', tabId); //切换到新增的tab上
    }
}

/**
 * ifrme自适应页面高度，需要设定min-height
 * @param iframe
 */
function setIframeHeight(iframe) {
    if (iframe) {
        var iframeWin = iframe.contentWindow || iframe.contentDocument.parentWindow;
        if (iframeWin.document.body) {
            iframe.height = iframeWin.document.documentElement.scrollHeight || iframeWin.document.body.scrollHeight;
        }
    }
};
