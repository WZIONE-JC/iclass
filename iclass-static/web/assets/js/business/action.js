/**
 * Created by JasonTang on 3/30/2017.
 */

function addDialog(title,url,w,h){
    layer_show(title,url,'',w,h);
}

function show(title,url,id,w,h){
    layer_show(title,url,id,w,h);
}


function stop(obj,id){
    layer.confirm('确认要下架吗？',function(index){
        $(obj).parents("tr").find(".td-manage").append(" <a style='text-decoration:none' class='ml-5' onClick='start(this,"+id+")' title='发布'><i class='Hui-iconfont'>&#xe603;</i></a>");
        $(obj).parents("tr").find(".td-status").html('<span class="label label-default radius" title="已下架">已下架</span>');
        $(obj).remove();
        layer.msg('已下架!',{icon: 5,time:1000});
    });
}


function start(obj,id){
    layer.confirm('确认要发布吗？',function(index){
        $(obj).parents("tr").find(".td-manage").append(" <a style='text-decoration:none' class='ml-5' onClick='stop(this,"+id+")' title='下架'><i class='Hui-iconfont'>&#xe6de;</i></a>");
        $(obj).parents("tr").find(".td-status").html("<span class='label label-success radius' title='已发布'>已发布</span>");
        $(obj).remove();
        layer.msg('已发布!',{icon: 6,time:1000});
    });
}

function edit(title,url,id,w,h){
    layer_show(title,url,id,w,h);
}

function password(title,url,id,w,h){
    layer_show(title,url,id,w,h);
}

function del(obj,id){
    layer.confirm('确认要删除吗？',function(index){
        $(obj).parents("tr").remove();
        layer.msg('已删除!',{icon:1,time:1000});
    });
}

function datadel(obj, ids) {
    
}