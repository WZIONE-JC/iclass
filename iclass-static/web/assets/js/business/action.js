/**
 * Created by JasonTang on 3/30/2017.
 */

function add(title,url,w,h){
    layer_show(title,url,w,h);
}

function show(title,url,id,w,h){
    layer_show(title,url,id,w,h);
}

function stop(obj,id){
    layer.confirm('确认要下架吗？',function(index){
        $(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" onClick="start(this,id)" href="javascript:;" title="恢复"><i class="Hui-iconfont">&#xe6e1;</i></a>');
        $(obj).parents("tr").find(".td-status").html('<span class="label label-defaunt radius">已下架</span>');
        $(obj).remove();
        layer.msg('已下架!',{icon: 5,time:1000});
    });
}


function start(obj,id){
    layer.confirm('确认要恢复吗？',function(index){
        $(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" onClick="stop(this,id)" href="javascript:;" title="下架"><i class="Hui-iconfont">&#xe631;</i></a>');
        $(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">已恢复</span>');
        $(obj).remove();
        layer.msg('已恢复!',{icon: 6,time:1000});
    });
}

function edit(title,url,id,w,h){
    layer_show(title,url,w,h);
}

function password(title,url,id,w,h){
    layer_show(title,url,w,h);
}

function del(obj,id){
    layer.confirm('确认要删除吗？',function(index){
        $(obj).parents("tr").remove();
        layer.msg('已删除!',{icon:1,time:1000});
    });
}

function datadel(obj, ids) {
    
}