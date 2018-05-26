var jq= jQuery.noConflict();

//点击之后恢复未填写信息而变红的输入框颜色
var commentInfo=["#name","#contact","#comment-content"];
commentInfo.forEach(function (item) {
    jq(item).click(function(){
        jq(item).css("border","1px solid LightGrey");
    });
});
jq("#submit").click(function () {
    //检查是否所有字段已经填写
    for (var i=0 ;i < commentInfo.length; i++) {
        if(!jq(commentInfo[i]).val()){
            layui .use('layer', function(){
                jq(commentInfo[i]).css("border","1px solid red");
                var layer = layui.layer;
                layer.msg('请补全评论信息', {icon: 2,time: 1000});
            });
            return;
        }
    }
    //禁用按钮防止重复提交
    jq('#submit').click(function () {
        return false;
    });
    // 异步提交数据
    jq.ajax({
        url: "/comment/",
        type: "post",
        data:JSON.stringify({
            "name":jq('#name').val(),
            "contact":jq('#contact').val(),
            "content":jq('#comment-content').val(),
            "blogId":window.location.href.split("/")[4],
        }),
        contentType: "application/json; charset=utf-8",
        success: function (result) {
            layui .use('layer', function(){
                var layer = layui.layer;
                layer.msg('评论发表成功', {icon: 1,time: 3000});
            });
            setTimeout(function () {
                window.location.reload();
            },1000);
        }
    });
});