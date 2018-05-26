//修改请求路径
UE.Editor.prototype._bkGetActionUrl = UE.Editor.prototype.getActionUrl;
UE.Editor.prototype.getActionUrl = function(action) {
    if (action == 'uploadimage' ){
        return '/ued/image';
    } else if(action == 'uploadscrawl') {
        return '/ued/scrawl';
    } else if(action == 'uploadvideo'){
        return '/ued/video';
    }else {
        return this._bkGetActionUrl.call(this, action);
    }
}

layui.use(['jquery', 'form'], function() {
    var $ = layui.jquery,
        form = layui.form,
        $form = $('#typeForm'),
        ue = UE.getEditor('container'),
        createTime;

    /*
    *  如果editBlogInfo为null则新建博客，否则编辑博客
    *
    *  新建博客时POST标题+内容+分类+published+userId（是否发表）
    *  编辑博客时PUT标题+内容+分类+published+userId到对应博客id
    * */

    function submit(published) {
        if(!$('#title').val()||!categoryId){
            layer.msg('请补全博客信息', {icon: 2});
            return;
        }
        if(/^写下文字，记录生活创建于：\d{4}年\s\d{2}月\s\d{2}日\s\d{2}:\d{2}:\d{2}$/.test(ue.getContentTxt())||!ue.getContentTxt()) {
            layer.msg('尚未编写博客内容', {icon: 2});
            return;            }
        if(window.editBlogInfo){
            var url="/blog/"+window.editBlogInfo.blogId;
            var type="PUT";
        }else{
            var url="/blog";
            var type="POST";
        }
        $.ajax({
            url: url,
            data:JSON.stringify({
                "title":$('#title').val(),
                "content":ue.getContent(),
                "text":ue.getContentTxt(),
                "typeId":typeId?typeId:categoryId,
                "userId":1,
                "published":published
            }),
            contentType: "application/json; charset=utf-8",
            type: type,

            success: function (result) {
                layer.msg('上传成功', {icon: 1});
                //清空内容后（防止未保存提示）刷新页面并置空editBlogInfo
                ue.execCommand('cleardoc');
                window.editBlogInfo=null;
                setTimeout(function () {
                    window.location.reload();
                },3000);

            },
            error:function(result){
                layer.msg(result.responseText, {icon: 2});
            }
        });
    }

    $("#publish").click(function () {
        submit(0)
    });

    $("#draft").click(function () {
        submit(1)
    });


    //如果editBlogInfo为null则新建博客
    if(window.editBlogInfo==null){
        //初始化分类选择面板
        initCategoty();

        //富文本编辑器初始化
        ue.ready(function() {
            //自定义上传请求参数
            ue.execCommand('serverparam', {
                'userId':1,
                //'blogId': "temp"
            });

            $.ajax({
                url: "/getTime",
                data:"format=yyyy年 MM月 dd日 HH:mm:ss",
                type: "get",
                success: function (result) {
                    createTime=result;
                    ue.setContent("<p>写下文字，记录生活</p><p>创建于："+result+"</p>");
                }
            });
            ue.addListener('focus',function(){
                if(/^写下文字，记录生活创建于：\d{4}年\s\d{2}月\s\d{2}日\s\d{2}:\d{2}:\d{2}$/.test(ue.getContentTxt())){
                    ue.setContent("<p></p><p>创建于："+createTime+"</p>");
                    ue.focus(false);
                }
            })
            window.onbeforeunload=function(e){
                if(!/^写下文字，记录生活创建于：\d{4}年\s\d{2}月\s\d{2}日\s\d{2}:\d{2}:\d{2}$/.test(ue.getContentTxt())&&ue.getContentTxt()) {
                    return (e || window.event).returnValue = '内容可能尚未保存，确定离开吗';
                }
            }
        });

    //如果editBlogInfo不为null则编辑博客
    }else{
        //初始化分类选择面板
        layui.jquery.ajax({
            url: "/type/"+window.editBlogInfo.typeId,
            type: "get",
            dataType: "json",
            success: function (result) {
                initCategoty(result.id,result.children?result.children[0].id:null);
            }
        });

        //标题初始化
        $('#title').val(window.editBlogInfo.title);
        //富文本编辑器初始化
        ue.ready(function() {
            //自定义上传请求参数
            ue.execCommand('serverparam', {
                'userId':1,
                //'blogId': window.editBlogInfo.blogId
            });

            $.ajax({
                url: "/blog/"+window.editBlogInfo.blogId,
                type: "get",
                success: function (result) {
                    ue.setContent(result);
                },
                error:function (result) {
                    //置空editBlogInfo
                    window.editBlogInfo=null;
                }

            });
            window.onbeforeunload=function(e){
                if(ue.getContentTxt()) {
                    return (e || window.event).returnValue = '内容可能尚未保存，确定离开吗';
                }
            }
        });
    }

});