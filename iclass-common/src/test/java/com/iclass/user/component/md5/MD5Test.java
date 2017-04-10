package com.iclass.user.component.md5;

import org.junit.Test;

/**
 * iclass
 * <p>
 * Created by yang.tang on 2017/2/10 20:35.
 */
public class MD5Test {

    @Test
    public void testMD5() {
        String str = "361239731";
        String r = MD5.getPwd(str);
        System.out.println(r);
    }

    @Test
    public void testGetDevice() {
        String src = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.133 Safari/537.36";
        String result = src.substring(0, src.indexOf(")") + 1);
        System.out.println(result);
    }
}
