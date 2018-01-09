/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

/**
 *
 * @author yuichi_develop
 */
public class Util {
     //*** このパターンは、勝手に変えるな！ ***//
    public static String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss.S";

    //*** 引数の文字列を、SHA256にハッシュ化した結果の文字列を返すメソッド ***//
    public static String returnSHA256(String args) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] result = md.digest(args.getBytes());	//*** 256ハッシュ化した結果をバイト配列に代入する ***//

        StringBuilder sb = new StringBuilder();
        for (byte b : result) {
            sb.append(String.format("%02x", b));
        }
        System.out.println(sb.toString());

        //*** 入力したpassをSHA256ハッシュ化した結果の文字列 ***//
        return sb.toString();
    }

    //*** 指定文字数で、ランダムな文字列を返すメソッド ***//
    public static String getRandomString(int cnt) {
        final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random rnd = new Random();
        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < cnt; i++) {
            int val = rnd.nextInt(chars.length());
            buf.append(chars.charAt(val));
        }
        return buf.toString();
    }

    //*** 引数のカレンダーインスタンスを指定パターン文字列にして返すメソッド ***//
    public static String parseCalToStr(Calendar c) {
        String str;
        if (c == null) {
            str = null;
        } else {
            str = new SimpleDateFormat(DATE_PATTERN).format(c.getTime());
        }
        return str;
    }

    //***  ***//
    public static String parseCalToStr(Date c) {
        String str;
        if (c == null) {
            str = null;
        } else {
            str = new SimpleDateFormat(DATE_PATTERN).format(c.getTime());
        }
        return str;
    }

    //***  ***//
    public static void easyLog(String args) {
        System.out.println("==========================");
        System.out.println(String.format("     %s        ", args));
        System.out.println("==========================");
    }
    
    //*** 引数の数値を日本円表記の文字列で返すメソッド ***//
    public static String convartjapanPrice(Integer price){
        return NumberFormat.getCurrencyInstance(Locale.JAPAN).format(price);
    }
}
