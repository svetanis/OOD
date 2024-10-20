package com.svetanis.thread;

// 1195. Fizz Buzz Multithreaded
// given 4 functions:
// printFizz, printBuzz, printFizzBuzz, printNumber
// given an instance of the class FizzBuzz 
// that has 4 function: fizz, buzz, fizzbuzz, number
// the same instance of FizzBuzz will be passed 
// to 4 different threads:
// Thread A: calls fizz() that should output "fizz"
// Thread B: calls buzz() that should output "buzz"
// Thread C: calls fizzbuzz() that should output "fizzbuzz"
// Thread D: calls number() that should only output integers

public final class FooMultithreaded {

	public static void main(String[] args) {
		Foo foo = new Foo();
		Runnable first = () -> System.out.println("first");
		Runnable second = () -> System.out.println("second");
		Runnable third = () -> System.out.println("third");

		Thread ft = new Thread(() -> first(foo, first));
		Thread st = new Thread(() -> second(foo, second));
		Thread tt = new Thread(() -> third(foo, third));

		ft.start();
		st.start();
		tt.start();
	}

	private static void third(Foo foo, Runnable task) {
		try {
			foo.third(task);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private static void second(Foo foo, Runnable task) {
		try {
			foo.second(task);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private static void first(Foo foo, Runnable task) {
		try {
			foo.first(task);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
