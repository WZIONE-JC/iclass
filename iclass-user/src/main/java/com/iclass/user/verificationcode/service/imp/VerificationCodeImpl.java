package com.iclass.user.verificationcode.service.imp;

import com.iclass.user.verificationcode.service.api.VerificationCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * iclass
 * <p>
 * Created by JasonTang on 2/10/2017 12:36 AM.
 */
@Service
public class VerificationCodeImpl implements VerificationCode {

    private final Logger logger = LoggerFactory.getLogger(VerificationCodeImpl.class);
    /**
     * 验证码的宽度
     */
    private int width = 72;

    /**
     * 验证码的高度
     */
    private int height = 40;

    private String verificationCode;

    @Override
    public void genVerificationCode(HttpServletRequest request, HttpServletResponse response) {
        //不缓存
        response.setCharacterEncoding("utf-8");
        response.setHeader("Pragma","No-cache");
        response.setHeader("Cache-Control","no-cache");
        response.setDateHeader("Expires", 0);
        HttpSession session = request.getSession();
        //创建一个72*40的RGB图片
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = bi.getGraphics();
        Color c = new Color(80, 170, 170); //背景色
        g.setColor(c);
        g.fillRect(0, 0, width, height); //72*40的图片

        char[] ch = "ABCDEFGHIJKLMNOPQRST0123456789".toCharArray();
        Random r = new Random();
        int len = ch.length, index;
        final int CODE_MAX_LENGTH = 4;
        StringBuffer buffer = new StringBuffer();
        for(int i=0; i<CODE_MAX_LENGTH; i++) {
            index = r.nextInt(len);
            g.setColor(new Color(r.nextInt(100), r.nextInt(150), r.nextInt(200)));
            g.setFont(new Font("Consolas", Font.BOLD, 18));
            g.drawString(ch[index]+"", (i*15) + 6, (r.nextInt(10) + 20));
            buffer.append(ch[index]);
        }
        verificationCode = buffer.toString();
        logger.info("生成的验证码:" + verificationCode + "sessionId:" + session.getId());
        session.setAttribute("verificationCode", verificationCode);
        try {
            ImageIO.write(bi, "JPG", response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getVerificationCode(HttpServletRequest request) {
        return (String)request.getSession().getAttribute("verificationCode");
    }

}
