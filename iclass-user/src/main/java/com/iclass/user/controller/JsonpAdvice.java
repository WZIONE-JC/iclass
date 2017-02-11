package com.iclass.user.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.AbstractJsonpResponseBodyAdvice;

/**
 * iclass
 * <p>
 * Created by JasonTang on 2/12/2017 12:47 AM.
 *
 * springboot 对 jsonp 的支持
 * 消除下面的错误
 * ${username}?callback=jQuery1910916…_1486832038998&_=1486832039000:1 Uncaught SyntaxError: Unexpected token :
 */
@ControllerAdvice(basePackages = "com.iclass.user.controller")
public class JsonpAdvice extends AbstractJsonpResponseBodyAdvice {

    public JsonpAdvice() {
        super("callback", "jsonp");
    }
}
