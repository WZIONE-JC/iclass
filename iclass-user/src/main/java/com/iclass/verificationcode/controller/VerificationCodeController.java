package com.iclass.verificationcode.controller;

import com.iclass.verificationcode.service.imp.VerificationCodeImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * iclass
 * <p>
 * Created by JasonTang on 2/10/2017 12:38 AM.
 */
@RestController
@RequestMapping("/VerificationCode")
public class VerificationCodeController {

    @Autowired
    private VerificationCodeImpl verificationCode;

    /**
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/generate")
    public void generate(HttpServletRequest request, HttpServletResponse response) {

        verificationCode.genVerificationCode(request, response);

        System.out.println(verificationCode.getVerificationCode(request));

    }

    /**
     * 供后台验证使用
     * @return
     */
    @RequestMapping("/getCode")
    public String getStringCode(HttpServletRequest request) {
        return verificationCode.getVerificationCode(request);
    }
}
