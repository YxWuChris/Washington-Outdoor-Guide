package com.example.googlemap;
import java.math.BigInteger;
import java.security.MessageDigest;

public class MD5Encryption {
    private String input;
    public MD5Encryption(String input){
        this.input = input;
    }
    public String getEncryption(){
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1,messageDigest);
            String hashtext = number.toString(16);
            while(hashtext.length()<32){
                hashtext = "0" + hashtext;
            }
            return hashtext;

        }catch (Exception e){

        }
        return input;
    }
}
