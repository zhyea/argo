package com.zhyea.argo.tools;


import org.bouncycastle.crypto.engines.AESEngine;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.paddings.PKCS7Padding;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.SecureRandom;
import java.security.Security;

public class SM4Example {
	static {
		Security.addProvider(new BouncyCastleProvider());
	}

	public static void main(String[] args) throws Exception {
		// 密钥和初始化向量
		byte[] keyBytes = new byte[32]; // 256位密钥
		byte[] ivBytes = new byte[16]; // 初始化向量
		SecureRandom random = new SecureRandom();
		random.nextBytes(keyBytes);
		random.nextBytes(ivBytes);

		KeyParameter key = new KeyParameter(keyBytes);
		ParametersWithIV params = new ParametersWithIV(key, ivBytes);

		// 初始化加密器
		CBCBlockCipher cipher = new CBCBlockCipher(new AESEngine());
		cipher.init(true, params);

		// 使用PKCS7进行填充
		PKCS7Padding padding = new PKCS7Padding();
		padding.init(cipher);

		// 假设待加密数据
		byte[] plainText = "This is a secret message".getBytes();
		byte[] cipherText = new byte[plainText.length];

		// 加密过程
		int offset = 0;
		int len = cipher.processBlocks(plainText, 0, plainText.length, cipherText, offset);
		cipher.doFinal(cipherText, offset + len);

		// 输出加密后的数据
		System.out.println("Cipher Text: " + bytesToHex(cipherText));

		// 解密过程（略）

	}
}

