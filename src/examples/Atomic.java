package examples;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Atomic {
 static AtomicInteger i = new AtomicInteger(0);
	
    public static void counter() {
    	i.incrementAndGet();
//		System.out.println(Thread.currentThread().getId() + " : I am running :" + i.incrementAndGet() );
//		try {
//			Thread.sleep(500);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        ExecutorService es = Executors.newFixedThreadPool(4);
        for(int i = 0 ;i<1000;i++) {
		es.submit(() ->{
			Atomic.counter();
			});
        }
        es.shutdown();
        try {
        	es.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
          
        }
        System.out.println(i);
	}

}
