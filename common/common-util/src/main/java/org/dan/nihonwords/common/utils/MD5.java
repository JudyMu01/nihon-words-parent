package org.dan.nihonwords.common.utils;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.io.UnsupportedEncodingException;

/**
 * @author mcd
 * @create 2023-07-20 16:02
 */

@Service
public class MD5 {

    public static String encrypt(String pw){
        String md5 = " ";
        try {
            md5 = DigestUtils.md5DigestAsHex(pw.getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {

            e.printStackTrace();
        }
        return md5;
    }
}
