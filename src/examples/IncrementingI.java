package examples;

//You need both volatile and synchronized keyword as i = i+1; is 3 sepereate instruction and JVM doesnot garuntee the oder of execution. so u need 
//volatile to make sure the order remains correct


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class IncrementingI {
  static volatile int i = 0;
	
    public synchronized static void counter() {
    	i = i+1;
//		System.out.println(Thread.currentThread().getId() + " : I am running :" + i  );
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
				IncrementingI.counter();
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
