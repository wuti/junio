package com.foo.example.util;

import com.google.common.base.Optional;

public class AvoidNullValue {
	public static void main(String[] args) {
		String s = null;
		Optional<String> myPbje = Optional.fromNullable(s);
		System.out.println(myPbje.isPresent());
		System.out.println(myPbje.or("123"));
	}
}
