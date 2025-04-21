package com.zhyea.argo.tools.auth;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.engines.AESEngine;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.KeyGenerator;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

public class AES {


	public static final String ALGORITHM_AES = "AES";

	private static byte[] cipherData(PaddedBufferedBlockCipher cipher, byte[] data) throws Exception {
		byte[] outputBuffer = new byte[cipher.getOutputSize(data.length)];

		int length1 = cipher.processBytes(data, 0, data.length, outputBuffer, 0);
		int length2 = cipher.doFinal(outputBuffer, length1);

		byte[] result = new byte[length1 + length2];

		System.arraycopy(outputBuffer, 0, result, 0, result.length);

		return result;
	}

	private static byte[] encrypt(byte[] plain, CipherParameters ivAndKey) throws Exception {
		PaddedBufferedBlockCipher aes = new PaddedBufferedBlockCipher(
				CBCBlockCipher.newInstance(AESEngine.newInstance())
		);

		aes.init(true, ivAndKey);

		return cipherData(aes, plain);

	}


	public static String encrypt(String src, String ky, String iv) throws Exception {
		CipherParameters ivAndKey = generateCipherParam(ky, iv);
		byte[] srcBytes = src.getBytes(StandardCharsets.UTF_8);
		byte[] bytes = encrypt(srcBytes, ivAndKey);
		return Base36.encode(bytes).toUpperCase();
	}


	private static byte[] decrypt(byte[] cipher, CipherParameters ivAndKey) throws Exception {
		PaddedBufferedBlockCipher aes = new PaddedBufferedBlockCipher(
				CBCBlockCipher.newInstance(AESEngine.newInstance())
		);
		aes.init(false, ivAndKey);

		return cipherData(aes, cipher);
	}


	public static String decrypt(String src, String ky, String iv) throws Exception {
		src = src.toLowerCase();
		CipherParameters ivAndKey = generateCipherParam(ky, iv);
		byte[] encryptedBytes = Base36.decode(src);
		byte[] bytes = decrypt(encryptedBytes, ivAndKey);
		return new String(bytes, StandardCharsets.UTF_8);
	}


	private static CipherParameters generateCipherParam(String key, String iv) {

		byte[] bytesKey = Base36.decode(key);
		byte[] byteIv = Base36.decode(iv);

		return new ParametersWithIV(new KeyParameter(bytesKey), byteIv);
	}


	/**
	 * generate key
	 *
	 * @return key
	 * @throws NoSuchAlgorithmException 异常
	 */
	public static String generateKey() throws NoSuchAlgorithmException {
		Security.addProvider(new BouncyCastleProvider());
		KeyGenerator keyGen = KeyGenerator.getInstance(ALGORITHM_AES);
		//key is 256 bits
		keyGen.init(256);
		byte[] bytes = keyGen.generateKey().getEncoded();
		return Base36.encode(bytes);
	}


	/**
	 * generate iv
	 *
	 * @return iv
	 * @throws NoSuchAlgorithmException iv
	 */
	public static String generateIV() throws NoSuchAlgorithmException {
		KeyGenerator ivGen = KeyGenerator.getInstance(ALGORITHM_AES);
		//iv is 128 bits
		ivGen.init(128);
		byte[] bytes = ivGen.generateKey().getEncoded();
		return Base36.encode(bytes);
	}

}