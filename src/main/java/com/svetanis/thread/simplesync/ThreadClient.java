package com.svetanis.thread.simplesync;

public final class ThreadClient {

	// Different references - both threads can call Foo.foo()
	// Foo 1.foo() starting
	// Foo 2.foo() starting
	// Foo 1.foo() ending
	// Foo 2.foo() ending
	public static void differentReferences() {
		Foo foo1 = new Foo();
		Foo foo2 = new Foo();
		ThreadExample thread1 = new ThreadExample("1", foo1);
		ThreadExample thread2 = new ThreadExample("2", foo2);
		thread1.start();
		thread2.start();
	}

	// Same reference to Foo. Only one will be allowed to call foo,
	// and the other will be forced to wait
	// Foo 1.foo() starting
	// Foo 1.foo() ending
	// Foo 2.foo() starting
	// Foo 2.foo() ending
	public static void sameReference() {
		Foo foo = new Foo();
		ThreadExample thread1 = new ThreadExample("1", foo);
		ThreadExample thread2 = new ThreadExample("2", foo);
		thread1.start();
		thread2.start();
	}

	public static void main(String[] args) {
		differentReferences();
		sameReference();
	}
}