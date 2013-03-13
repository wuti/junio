package com.foo.common.base.utils;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

public class FooUtilsEventListener {
	// Class is typically registered by the container.
	@Subscribe
	public void recordCustomerChange(String e) {
		System.out.println(e);
	}

	// somewhere during initialization

	public void changeCustomer() {
	}

	public static void main(String[] args) {
		EventBus myBus = new EventBus();
		myBus.register(new FooUtilsEventListener());
		myBus.post("a");
	}
}
