package org.chobit.tiger.service;

import org.chobit.tiger.TestBase;
import org.chobit.tiger.utils.MD5;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author robin
 */
public class UserServiceTest extends TestBase {


    @Value("${calf.passoword-salt}")
    private String passwordSalt;

    @Test
    public void encode() {
        System.out.println(MD5.encode(passwordSalt, "admin"));
    }


}
