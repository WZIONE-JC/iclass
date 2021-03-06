var Login = function () {
    var b = function () {
        if ($.fn.uniform) {
            $(":radio.uniform, :checkbox.uniform").uniform()
        }
    };
    var c = function () {
    	//注册 事件
        $(".sign-up").click(function (h) {
            h.preventDefault();
            $(".login-form").slideUp(350, function () {
                $(".register-form").slideDown(350);
                $(".sign-up").hide();
                $(".forgot-password-done").hide();
                 // $(".forgot-password-form").show();
                $(".forgot-password-link").hide();
            })
        });
        //返回 事件
        $(".back").click(function (h) {
            h.preventDefault();
            $(".register-form").slideUp(350, function () {
                $(".login-form").slideDown(350);
                $(".sign-up").show()
            })
        })
    };
    //忘记密码 事件
    var g = function () {
        $(".forgot-password-link").click(function (h) {
            h.preventDefault(); //阻止默认行为
            $(".forgot-password-form").slideToggle(200);
            $(".inner-box .close").fadeToggle(200);
        });
        $(".inner-box .close").click(function () {
            $(".forgot-password-link").click()
        })
    };

    var e = function () {
        if ($.validator) {
            $.extend($.validator.defaults, {
                errorClass: "has-error",
                validClass: "has-success",
                highlight: function (k, i, j) {
                    if (k.type === "radio") {
                        this.findByName(k.name).addClass(i).removeClass(j)
                    } else {
                        $(k).addClass(i).removeClass(j)
                    }
                    $(k).closest(".form-group").addClass(i).removeClass(j)
                },
                unhighlight: function (k, i, j) {
                    if (k.type === "radio") {
                        this.findByName(k.name).removeClass(i).addClass(j)
                    } else {
                        $(k).removeClass(i).addClass(j)
                    }
                    $(k).closest(".form-group").removeClass(i).addClass(j);
                    $(k).closest(".form-group").find('label[generated="true"]').html("")
                }
            });
            var h = $.validator.prototype.resetForm;
            $.extend($.validator.prototype, {
                resetForm: function () {
                    h.call(this);
                    this.elements().closest(".form-group").removeClass(this.settings.errorClass + " " + this.settings.validClass)
                }, showLabel: function (j, k) {
                    var i = this.errorsFor(j);
                    if (i.length) {
                        i.removeClass(this.settings.validClass).addClass(this.settings.errorClass);
                        if (i.attr("generated")) {
                            i.html(k)
                        }
                    } else {
                        i = $("<" + this.settings.errorElement + "/>").attr({
                            "for": this.idOrName(j),
                            generated: true
                        }).addClass(this.settings.errorClass).addClass("help-block").html(k || "");
                        if (this.settings.wrapper) {
                            i = i.hide().show().wrap("<" + this.settings.wrapper + "/>").parent()
                        }
                        if (!this.labelContainer.append(i).length) {
                            if (this.settings.errorPlacement) {
                                this.settings.errorPlacement(i, $(j))
                            } else {
                                i.insertAfter(j)
                            }
                        }
                    }
                    if (!k && this.settings.success) {
                        i.text("");
                        if (typeof this.settings.success === "string") {
                            i.addClass(this.settings.success)
                        } else {
                            this.settings.success(i, j)
                        }
                    }
                    this.toShow = this.toShow.add(i)
                }
            })
        }
    };

    //登录
    var d = function () {
        if ($.validator) {
            $(".login-form").validate({
                invalidHandler: function (i, h) {
                    //NProgress.start();
                    //$(".login-form .alert-danger").show();
                    //NProgress.done()
                }, submitHandler: function (h) {
                    NProgress.start();
                    $.ajax({
                        type:"post",
                        url: user_url+"/user/login",
                        dataType:"jsonp",
                        timeout: 3000,
                        data:$(".login-form").serialize(),
                        success:function(responseData){
                            //登录成功
                            if(responseData.success){
                            	$(".login-form .alert-danger").hide();
                            	NProgress.done();
                                location.href="index.html";
                            } else {
                                $(".login-form .alert-danger").show();
                                $("#msg").text(responseData.data.msg);
                                NProgress.set(1); //停止掉进度条
                            }
                        },
                        error:function(responseData){
                            swal({
                                title: "Sorry!",
                                text: responseData.responseText,
                                timer: 2000,
                                type: "error"
                            });
                            NProgress.set(1); //停止掉进度条
                        }
                    });
                    //window.location.href = "index.html"
                }
            })
        }
    };

    //注册
    var a = function () {
        if ($.validator) {
            $(".register-form").validate({
                invalidHandler: function (i, h) {

                }, submitHandler: function (h) {

                    NProgress.start();
                    var numError1 = $('.register-form .error1').length;
                    var numError2 = $('.register-form .error2').length;
                    if((numError1+numError2) == 0){
                        $.ajax({
                            type:"post",
                            dataType:"jsonp",
                            url: user_url+"/user/signup",
                            timeout: 3000,
                            data:$(".register-form").serialize(),
                            success:function(responseData){
                                if(responseData.success){
                                	swal({
                                		title: "Good Job!",
                                		text: responseData.data.msg,
                                		timer: 2000,
                                		type: "success"
                                	});
                                	//设置延时2s之后执行下面函数
                                	setTimeout(function(){
                                        $(".back").click();
                                        $(".register-form")[0].reset();
                                    },2000);
                                } else {
                                    swal({
                                		title: "Sorry!",
                                		text: responseData.data.msg,
                                		timer: 2000,
                                		type: "error"
                                	});
                                }
                            },
                            error:function(response,status,xhr){
                            	 $(".login-form .alert-msg").show();
                                $("#reg_msg").show().text(response);
                                NProgress.set(1); //停止掉进度条
                            }
                        });
                    } else {
                    	NProgress.set(1); //停止掉进度条
                    	return;
                    }
                    NProgress.done();
                }
            })
        }
    };
    //重置密码
    var f = function () {
        if ($.validator) {
            $(".forgot-password-form").validate({
                //重置按钮提交
                submitHandler: function (h) {
                    //忘记密码页面上拉
                    $(".inner-box").slideUp(350, function () {
                        $(".forgot-password-form").hide();
                        $(".forgot-password-link").hide();
                        $(".inner-box .close").hide();
                        //忘密码完成页面下拉
                        $(".forgot-password-done").show();
                        $(".inner-box").slideDown(350)
                    });
                    var setTime;
                    var time=parseInt($(".resend").text());
                    setTime=setInterval(function(){
                        if(time<=1){
                            //显示“重新发送”
                            $(".resend").parent().addClass("resendEmail").on("click",function(){
                                //忘记密码完成页面上拉隐藏
                                $(".forgot-password-done").slideUp(350,function(){
                                    //忘记密码页面消失
                                    $(".forgot-password-done").hide();
                                    $(".forgot-password-done .success-icon").hide();
                                    //“之后重新发送”打开
                                    $("#sendlaterword").show();
                                    //设置时间
                                    $(".resend").text("30")
                                    //忘记密码页面下拉展开
                                    $(".forgot-password-form").slideDown(300);
                                    $(".forgot-password-link").slideDown(300);
                                    $(".inner-box .close").slideDown(300);
                                });
                            });
                            $(".resend").text("重新发送");
                            $("#sendlaterword").hide(); //隐藏"之后重新发送"
                            clearInterval(setTime);
                            return;
                        }
                        time--;
                        $(".resend").text(time);
                    },1000);
                    return false; //阻止提交
                    alert("asd");
                }
            })
        }
    };
    //验证教工号是否存在
    var h = function() {
        $("#register_userCode").blur(function () {
            if ($.trim($("#register_userCode").val()) != "") {
                $.ajax({
                    type: "post",
                    dataType:"jsonp",
                    url: user_url+"/user/validateUsercode",
                    jsonp:"callback",
                    data: {
                    	usercode: $("#register_userCode").val()
                    },
                    timeout: 3000,
                    success: function (responseData) {
                    	if (!responseData.success) {
                            $(".register-form .alert-msg").addClass("error1").css("color","red").show();
                            $("#reg_msg").text(responseData.data.msg);
                            return false;
                        } else {
                            $(".register-form .error1").removeClass("error1").hide();
                        }
                    }
                });
            }
        });
    }
    //验证用户名是否存在
    var i = function() {
        $("#register_username").blur(function () {
            if ($.trim($("#register_username").val()) != "") {
                $.ajax({
                    type: "post",
                    dataType:"jsonp",
                    url: user_url+"/user/validateUsername",
                    jsonp: "callback",
                    data: {
                    	username: $("#register_username").val()
                    },
                    timeout: 3000,
                    success: function (responseData) {
                        if (!responseData.success) {
                            $(".register-form .alert-msg").addClass("error2").css("color","red").show();
                            $("#reg_msg").text(responseData.data.msg);
                            return false;
                        } else {
                            $(".register-form .error2").removeClass("error2").hide();
                        }
                    }
                });
            }
        });
    }
    //生成验证码
    var j = function() {
        $(function(){
            $("#imgcode").attr("src", user_url+"/VerificationCode/generate");
            $("#imgcode").click();
        })
        $("#imgcode").click(function () {
            $("#imgcode").attr("src", user_url+"/VerificationCode/generate?n=" + Math.random());
        });
    }
    //获取角色信息
    var k = function() {
        $(function () {
            $.ajax({
                type: "post",
                dataType: "jsonp",
                url: user_url + "/user/getRole",
                jsonp: "callback",
                data: {
                  device: "web"
                },
                timeout: 10000,
                success: function (responseData) {
                    if(responseData.success) {
                        var size = responseData.data.length;
                        var rolenames = responseData.data;
                        var option;
                        for(var i = 0; i < size; i ++) {
                            option = new Option(rolenames[i].rolename, rolenames[i].rolename);
                            $("#rolename")[0].add(option);
                        }
                    } else {
                        swal({
                            title: "Sorry!",
                            text: responseData.message,
                            timer: 2000,
                            type: "error"
                        });
                    }
                },
                error: function() {
                    swal({
                        title: "Sorry!",
                        text: "获取角色信息失败,请稍后再试",
                        timer: 2000,
                        type: "error"
                    });
                }
            })
        })
    }
    return {
        init: function () {
            b();
            c();
            g();
            e();
            d();
            f();
            a();
            h();
            i();
            j();
            k();
        },
    }
}();
