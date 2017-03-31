package com.iclass.ppt_hw.component.controller;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * iclass
 * <p>
 * Created by JasonTang on 3/26/2017 4:22 PM.
 */
@Controller
public class IclassErrorController implements ErrorController {

    private final static String ERROR_PATH = "/error";

    @RequestMapping(value = ERROR_PATH)
    public String handleError() {
        return "404";
    }
    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }
}