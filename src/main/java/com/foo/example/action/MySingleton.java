package com.foo.example.action;

public enum MySingleton {
	INSTANCE;
	public void leaveTheBuid() {

	}
}

class TestIt {
	public static void main(String[] args) {
		MySingleton.INSTANCE.leaveTheBuid();
	}
}
