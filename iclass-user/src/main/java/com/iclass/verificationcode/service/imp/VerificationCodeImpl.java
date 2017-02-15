package com.iclass.verificationcode.service.imp;

import com.iclass.verificationcode.service.api.VerificationCode;
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
public class VerificationCodeImpl implements VerificationCode{

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
        response.setHeader("Cache-Control", "no-cache");
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
            g.setFont(new Font("宋体", Font.BOLD, 18));
            g.drawString(ch[index]+"", (i*15) + 6, (r.nextInt(10) + 20));
            buffer.append(ch[index]);
        }
        verificationCode = buffer.toString();
        session.setAttribute("verificationCode", verificationCode);
//		System.out.println("服务器:生成验证码成功:" +  session.getServletContext().getAttribute("verificationCode"));
        try {
            ImageIO.write(bi, "JPG", response.getOutputStream());
        } catch (IOException e) {
            System.out.println("验证码生成出错");
            e.printStackTrace();
        }
    }

    public String getVerificationCode(HttpServletRequest request) {
        return (String)request.getSession().getAttribute("verificationCode");
    }

}
