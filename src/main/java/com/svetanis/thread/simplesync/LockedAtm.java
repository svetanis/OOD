package com.svetanis.thread.simplesync;

import java.util.concurrent.locks.Lock;

public final class LockedAtm {

	private int balance;
	private Lock lock;

	public LockedAtm(int balance, Lock lock) {
		this.balance = balance;
		this.lock = lock;
	}

	public int withdraw(int value) {
		lock.lock();
		int temp = balance;
		try {
			Thread.sleep(100);
			temp = temp - value;
			Thread.sleep(100);
			balance = temp;
		} catch (InterruptedException e) {}
		lock.unlock();
		return temp;
	}

	public int deposit(int value) {
		lock.lock();
		int temp = balance;
		try {
			Thread.sleep(100);
			temp = temp + value;
			Thread.sleep(100);
			balance = temp;
		} catch (InterruptedException e) {}
		lock.unlock();
		return temp;

	}
}