package com.younchen.utils.http.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


 

public class Excutor {
	
	private BlockingQueue<Runnable> blockingqueue;
	// 线程池
	private ThreadPoolExecutor threadPool;
	//

	public Excutor() {
		blockingqueue = new ArrayBlockingQueue<Runnable>(10);
		threadPool = new ThreadPoolExecutor(5, 5, 60, TimeUnit.SECONDS,
				blockingqueue);
	}
	
	public void execute(Runnable run){
		threadPool.execute(run);
	}


}
