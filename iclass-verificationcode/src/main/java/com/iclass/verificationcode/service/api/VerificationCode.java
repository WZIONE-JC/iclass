package com.iclass.verificationcode.service.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * iclass
 * <p>
 * Created by JasonTang on 2/10/2017 12:32 AM.
 */
public interface VerificationCode {

    public void genVerificationCode(HttpServletRequest request,
                                    HttpServletResponse response);
}
