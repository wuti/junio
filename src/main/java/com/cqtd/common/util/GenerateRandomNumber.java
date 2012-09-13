package com.cqtd.common.util;

import java.util.Random;

public class GenerateRandomNumber {

	/**
	 * 产生min-max之间的随机整数
	 * @param max
	 * @param min
	 * @return int
	 */
	public static int generateRandomInt(int max,int min){
		Random random = new Random();
		return random.nextInt(max)%(max-min+1) + min;
	}
}
