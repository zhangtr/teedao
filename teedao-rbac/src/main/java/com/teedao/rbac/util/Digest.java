package com.teedao.rbac.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Digest {
	/*
	 * 消息摘要MD5 
	 * 麻省理工学院Ronald Rivest提出的MD5 
	 * 得到32位MD5加密密码
	 */
	public static String GetMD5(String str) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return byteToHex(md.digest());
	}

	/*
	 * 消息摘要SHA 
	 * 美国国家标准技术研究所的SHA 
	 * 得取40位的SHA1加密密码
	 */
	public static String GetSHA1(String str) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-1");
			md.update(str.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return byteToHex(md.digest());
	}
	

	/*
	 * 二行制转字符串
	 */
	private static String byteToHex(byte[] hash) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < hash.length; i++) {
			if ((0xff & hash[i]) < 0x10) {
				sb.append("0" + Integer.toHexString((0xFF & hash[i])));
			} else {
				sb.append(Integer.toHexString(0xFF & hash[i]));
			}
		}
		return sb.toString();
	}

}
