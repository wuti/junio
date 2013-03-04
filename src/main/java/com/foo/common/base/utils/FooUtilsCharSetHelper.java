package com.foo.common.base.utils;

import java.nio.charset.Charset;

public class FooUtilsCharSetHelper {
	public static void main(String[] args) {
		System.out.println(Charset.defaultCharset().toString());
		System.out.println(Charset.availableCharsets().keySet());
	}
}
