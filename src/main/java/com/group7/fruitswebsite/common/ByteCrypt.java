package com.group7.fruitswebsite.common;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class ByteCrypt {
	public static void main(String[] args) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(4);
		String result = encoder.encode("minh");
		System.out.println(result);
	}
}
