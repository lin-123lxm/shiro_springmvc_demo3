package com.encryty.test;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.junit.Test;

public class PasswordGenate {


    @Test
    public  void testCreatepassword(){

        String username="hello";
        String pass = "123456";
        Object object = ByteSource.Util.bytes("hello");
        System.out.println(object);
        SimpleHash simpleHash = new SimpleHash("md5",pass,ByteSource.Util.bytes("hello"),2);
        System.out.println(simpleHash);

    }

}
