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
    layui.jquery(".layui-body").load("/html/"+view+".html?"+new Date().getTime(),function(){});
}