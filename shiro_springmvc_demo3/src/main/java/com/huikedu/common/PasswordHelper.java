package com.huikedu.common;

import com.huikedu.domain.AdminUser;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class PasswordHelper {

    public static void encryptPassword(AdminUser  user) {
//        user.setPasswordSalt( ByteSource.Util.bytes(user.getAccount()).toString());
        ByteSource salt = ByteSource.Util.bytes(user.getAccount());
        String newPassword = new Md5Hash(user.getPassword(),salt,2).toHex();
        user.setPasswordSalt(salt.toString());

       /* String newPassword = new SimpleHash(
                "md5",
                user.getPassword(),
                ByteSource.Util.bytes(user.getPasswordSalt()),
                2).toString();
*/
        user.setPassword(newPassword);
    }
}
