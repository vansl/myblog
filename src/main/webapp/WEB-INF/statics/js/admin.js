layui.use(['element', 'layedit','table'], function(){
    var layer = layui.layer,
        element = layui.element,
        $ = layui.jquery,
        table = layui.table;

    i=0;    //定义一个变量i以判断动画收缩
    $('#switchNav').click(function(){
        if(i==0){
            $(".layui-side").animate({width:'toggle'});
            $(".layui-body").animate({left:'0px'});
            $(".layui-footer").animate({left:'0px'});
            //更换指示图标方向
            $("#switchNav .layui-icon").text("\ue65b");
            i++;
        }else{
            $(".layui-side").animate({width:'toggle'});
            $(".layui-body").animate({left:'200px'});
            $(".layui-footer").animate({left:'200px'});
            //更换指示图标方向
            $("#switchNav .layui-icon").text("\ue65a");
            i--;
        }
    });

    load('blog_admin');
});

function load(view) {
    layui.use(['element'],function () {
        var $ = layui.jquery,
            element = layui.element;

        //当前处于写博客页面则直接返回，防止页面切换导致未保存文章内容丢失(尚未实现)
        if($('.layui-this a').text()=="写博客"){
        }
        //首次加载页面event为空，需要排除
        if(event){
            //切换到写博客页面时侧边导航收缩
            if($(event.target).text()=="写博客"||$(event.target).text()=="编辑"){
                $('#switchNav').click();
            }
        }

        $(".layui-body").load("/html/"+view+".html?"+new Date().getTime(),function(){});
    });
    return false;
}