package com.zhyea.argo.tools.auth;

import org.chobit.commons.utils.JsonKit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;

import static org.chobit.commons.utils.StrKit.uuid;

/**
 * @author robin
 * @since 2025/4/21 21:57
 */
public class AesTest {


	private static final String KEY = "f1xx8w35iv8t7gi0z0mb749d102qy0s1njyl62re7bk6g94svtd";
	private static final String IV = "z3m6rqgu5c80ktj70dzin2rdyw";


	@Test
	public void encryptAndDecrypt() throws Exception {
		AuthInfo authInfo = new AuthInfo("admin", uuid());
		authInfo.setExpireTime(System.currentTimeMillis());

		String src = JsonKit.toJson(authInfo);

		System.out.println("src:" + src);

		String encrypt = AES.encrypt(src, KEY, IV);

		System.out.println(encrypt);

		String decrypt = AES.decrypt(encrypt, KEY, IV);

		System.out.println("decrypt:" + decrypt);

		Assertions.assertEquals(src, decrypt);
	}


	@Test
	public void generateKey() throws NoSuchAlgorithmException {
		String key = AES.generateKey();
		System.out.println(key);
		Assertions.assertNotNull(key);
	}


	@Test
	public void generateIv() throws NoSuchAlgorithmException {
		String iv = AES.generateIV();
		System.out.println(iv);
		Assertions.assertNotNull(iv);
	}


}
