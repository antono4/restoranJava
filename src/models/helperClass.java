/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author DELL
 */
public class helperClass {
    public static String getMD5(String txt){
        String vpassword = txt;
        String encPass=null;
        String md5 = null;

            try{
                MessageDigest digest=MessageDigest.getInstance("MD5");
                digest.update(vpassword.getBytes(),0,vpassword.length());
                encPass=new BigInteger(1,digest.digest()).toString(16);
                String vmd5 = encPass.toUpperCase();
                
                md5 = vmd5;
            }
            catch(Exception e){
                e.printStackTrace();
            }
            
        return md5;
    }
    
    public static String formatRupiah(Double rp) {
        String hasil = "";
        DecimalFormat toRupiah = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatAngka = new DecimalFormatSymbols();
        formatAngka.setCurrencySymbol("Rp. ");
        formatAngka.setMonetaryDecimalSeparator(',');
        formatAngka.setGroupingSeparator('.');
        toRupiah.setDecimalFormatSymbols(formatAngka);
        
        hasil = toRupiah.format(rp);
        return hasil;
    }
    
    public static String getCurrentDateTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
        LocalDateTime now = LocalDateTime.now();
        
        return dtf.format(now);
    }
    
    public static String generateOrderNumber() {
        String huruf = "QWERTYUIOPASDFGHJKLZXCVBNM";
        RandomString gen = new RandomString(2, new SecureRandom(), huruf);
        String str = gen.nextString();
        
        String angka = "1234567890";
        RandomString tik = new RandomString(4, new SecureRandom(), angka);
        String ank = tik.nextString();
        
        return str + ank;
    }
}
