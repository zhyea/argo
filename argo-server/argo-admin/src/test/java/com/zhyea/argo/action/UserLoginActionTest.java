package com.zhyea.argo.action;

import com.zhyea.argo.TestBase;
import org.chobit.commons.codec.MD5;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;

public class UserLoginActionTest extends TestBase {


	@Value("${argo.password.salt}")
	private String pwdSalt;


	@Test
	public void encryptPassword() {
		String password = "123456";
		String encryptedPwd = MD5.encode(password + pwdSalt);
		System.out.println(encryptedPwd);
	}


}
