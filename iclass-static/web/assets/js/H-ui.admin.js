/*H-ui.admin.js v2.3.1 date:15:42 2015.08.19 by:guojunhui*/
/*获取顶部选项卡总长度*/
function tabNavallwidth(){
	var taballwidth=0,
		$tabNav = $(".acrossTab"),
		$tabNavWp = $(".Hui-tabNav-wp"),
		$tabNavitem = $(".acrossTab li"),
		$tabNavmore =$(".Hui-tabNav-more");
	if (!$tabNav[0]){return}
	$tabNavitem.each(function(index, element) {
		taballwidth+=Number(parseFloat($(this).width()+60))});
	$tabNav.width(taballwidth+25);
	var w = $tabNavWp.width();
	if(taballwidth+25>w){
		$tabNavmore.show()}
	else{
		$tabNavmore.hide();
		$tabNav.css({left:0})}
}

/*左侧菜单响应式*/
function Huiasidedisplay(){
	if($(window).width()>=768){
		$(".Hui-aside").show()
	}
}
function getskincookie(){
	var v = getCookie("Huiskin");
	if(v==null||v==""){
		v="default";
	}
	$("#skin").attr("href","skin/"+v+"/skin.css");
}
$(function(){
	getskincookie();
	//layer.config({extend: 'extend/layer.ext.js'});
	Huiasidedisplay();
	var resizeID;
	$(window).resize(function(){
		clearTimeout(resizeID);
		resizeID = setTimeout(function(){
			Huiasidedisplay();
		},500);
	});

	$(".Hui-nav-toggle").click(function(){
		$(".Hui-aside").slideToggle();
	});
	$(".Hui-aside").on("click",".menu_dropdown dd li a",function(){
		if($(window).width()<768){
			$(".Hui-aside").slideToggle();
		}
	});
	/*左侧菜单*/
	$.Huifold(".menu_dropdown dl dt",".menu_dropdown dl dd","fast",1,"click");
	/*选项卡导航*/

	$(".Hui-aside").on("click",".menu_dropdown a",function(){
		if($(this).attr('_href')){
			var bStop=false;
			var bStopIndex=0;
			var _href=$(this).attr('_href');
			var _titleName=$(this).html();
			var topWindow=$(window.parent.document);
			var show_navLi=topWindow.find("#min_title_list li");
			show_navLi.each(function() {
				if($(this).find('span').attr("data-href")==_href){
					bStop=true;
					bStopIndex=show_navLi.index($(this));
					return false;
				}
			});
			if(!bStop){
				creatIframe(_href,_titleName);
				min_titleList();
			}
			else{
				show_navLi.removeClass("active").eq(bStopIndex).addClass("active");
				var iframe_box=topWindow.find("#iframe_box");
				iframe_box.find(".show_iframe").hide().eq(bStopIndex).show().find("iframe").attr("src",_href);
			}
		}
	});

	function min_titleList(){
		var topWindow=$(window.parent.document);
		var show_nav=topWindow.find("#min_title_list");
		var aLi=show_nav.find("li");
	};
	function creatIframe(href,titleName){
		var topWindow=$(window.parent.document);
		var show_nav=topWindow.find('#min_title_list');
		show_nav.find('li').removeClass("active");
		var iframe_box=topWindow.find('#iframe_box');
		show_nav.append('<li class="active"><span data-href="'+href+'">'+titleName+'</span><i></i><em></em></li>');
		tabNavallwidth();
		var iframeBox=iframe_box.find('.show_iframe');
		iframeBox.hide();

		iframe_box.append('<div class="show_iframe"><div class="loading"><div class="bounce1"></div> <div class="bounce2"></div> <div class="bounce3"></div></div><iframe frameborder="0" src='+href+'></iframe></div>');
		var showBox=iframe_box.find('.show_iframe:visible');
		showBox.find('iframe').attr("src",href).load(function(){
            // setTimeout(code,millisec)
            // alert(1);
            showBox.find('.loading').hide();
		});

	}

	var num=0;
	var oUl=$("#min_title_list");
	var hide_nav=$("#Hui-tabNav");
	$(document).on("click","#min_title_list li",function(){
		var bStopIndex=$(this).index();
		var iframe_box=$("#iframe_box");
		$("#min_title_list li").removeClass("active").eq(bStopIndex).addClass("active");
		iframe_box.find(".show_iframe").hide().eq(bStopIndex).show();
	});
	$(document).on("click","#min_title_list li i",function(){
		var aCloseIndex=$(this).parents("li").index();
		$(this).parent().remove();
		$('#iframe_box').find('.show_iframe').eq(aCloseIndex).remove();
		num==0?num=0:num--;
		tabNavallwidth();
	});
	$(document).on("dblclick","#min_title_list li",function(){
		var aCloseIndex=$(this).index();
		var iframe_box=$("#iframe_box");
		if(aCloseIndex>0){
			$(this).remove();
			$('#iframe_box').find('.show_iframe').eq(aCloseIndex).remove();
			num==0?num=0:num--;
			$("#min_title_list li").removeClass("active").eq(aCloseIndex-1).addClass("active");
			iframe_box.find(".show_iframe").hide().eq(aCloseIndex-1).show();
			tabNavallwidth();
		}else{
			return false;
		}
	});
	tabNavallwidth();

	$('#js-tabNav-next').click(function(){
		num==oUl.find('li').length-1?num=oUl.find('li').length-1:num++;
		toNavPos();
	});
	$('#js-tabNav-prev').click(function(){
		num==0?num=0:num--;
		toNavPos();
	});

	function toNavPos(){
		oUl.stop().animate({'left':-num*100},100);
	}

	/*换肤*/
	$("#Hui-skin .dropDown-menu a").click(function(){
		var v = $(this).attr("data-val");
		setCookie("Huiskin", v);
		$("#skin").attr("href","skin/"+v+"/skin.css");
	});
});
/*弹出层*/
/*
 参数解释：
 title	标题
 url		请求的url
 id		需要操作的数据id
 w		弹出层宽度（缺省调默认值）
 h		弹出层高度（缺省调默认值）
 */
function layer_show(title,url,id,w,h){
	if (title == null || title == '') {
		title=false;
	};
	if (url == null || url == '') {
		url="/404.html";
	};
	if (w == null || w == '') {
		w=800;
	};
	if (h == null || h == '') {
		h=($(window).height() - 100);
	};
    if(url != "/404.html" && id != null && id != "") {
		// console.log("id="+id);
        var usercode = id.split("=")[1];
        if (usercode != null && usercode != "") {
        	console.log("即将放入缓存的usercode:"+usercode);
            //将usercode放入到服务器缓存中
            $.ajax({
                type: "post",
                dataType: "jsonp",
                jsonp: "callback",
                url: user_url + "/cache/setCache",
                data: {
                    usercode: usercode
                },
                success: function (responseData) {
                    if(responseData.success) {
                        console.log("设置缓存成功");
                        console.log(responseData.data);
                    } else {
                        console.log("设置缓存失败");
                        console.log(responseData.message);
                    }
                },
                error: function (data) {
                    console.log("请求设置缓存失败");
                    console.log(data.responseText)
                }
            })
		}

    }
	layer.open({
		type: 2,
		area: [w+'px', h +'px'],
		fix: false, //不固定
		maxmin: true,
		shade:0.4,
		title: title,
		content: url,
		success: function (layero, index) {
			var $id = layer.getChildFrame('#id', index);
			$id.val(id);
			var $code = layer.getChildFrame('#code', index);
			//这里的id指的是code,比如coursecode
			$code.val(id);
			layer.getChildFrame('#loadData', index).click();

    	}
	});

}
/*弹出层*/
/*
 参数解释：
 title	标题
 url		请求的url
 w		弹出层宽度（缺省调默认值）
 h		弹出层高度（缺省调默认值）
 courseCode
 classCode
 formId
 fileType
 */
function layer_show_2(title, url , w, h, classCode, courseCode, fileType){
    if (title == null || title == '') {
        title=false;
    };
    if (url == null || url == '') {
        url="/404.html";
    };
    if (w == null || w == '') {
        w=800;
    };
    if (h == null || h == '') {
        h=($(window).height() - 100);
    };
    if(url != "/404.html" && courseCode != null && courseCode != "" && classCode != null && classCode != '') {
        console.log("courseCode:" + courseCode + ",classCode:" + classCode);
    } else {
        console.log("classCode和courseCode不能为空");
    }
    layer.open({
        type: 2,
        area: [w+'px', h +'px'],
        fix: false, //不固定
        maxmin: true,
        shade:0.4,
        title: title,
        content: url,
        success: function (layero, index) {
            var $classCode = layer.getChildFrame('#classCode', index);
            $classCode.val(classCode);
            var $courseCode = layer.getChildFrame('#courseCode', index);
            $courseCode.val(courseCode);
            var $fileType = layer.getChildFrame('#fileType', index);
            $fileType.val(fileType);
            layer.getChildFrame('#loadData', index).click();
        }
    });
}

/*关闭弹出框口*/
function layer_close(){
	var index = parent.layer.getFrameIndex(window.name);
	parent.layer.close(index);
}
