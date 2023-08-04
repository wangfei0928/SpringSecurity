package com.wf;

import com.wf.domain.User;
import com.wf.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 功能描述
 *
 * @author: wf
 * @date: 2023年08月03日 17:06
 */

@SpringBootTest
public class MapperTest {


    @Autowired
    private UserMapper userMapper;

    @Test
    public void TestBCryptPasswordEncoder(){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        //借用别的博客里面的一句话 : 加密后字符串的长度为固定的60位。其中：$是分割符，无意义；2a是bcrypt加密版本号；10是cost的值；而后的前22位是salt值；再然后的字符串就是密码的密文了。
        String encode = passwordEncoder.encode("123");
        String encode1 = passwordEncoder.encode("123");

        System.out.println(passwordEncoder.matches("123","$2a$10$NfdEECdqbBuTj5hHD6tfjeUbftWjZepaJYybB5cRUIB8SbdhFq5XW"));
        System.out.println(encode1);
        System.out.println(encode);
    }



    @Test
    public void test(){
        User user = userMapper.selectById(1);
        System.out.println(user);
    }


}
