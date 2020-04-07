package examples;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

//Suppose i have a task , which will be executed 1000 times and each task is using a common function. In this example the common function is appending "000" 
//at the last of every value. Plan is to put the common function in a Thread local so that each thread of executor service will have the common function.
//for example if executor service is created with 10 thread pool , each 10 thread will have one common function.

//ThreadLocal values are stored in the Thread object, rather than in a concurrent map, so there is absolutely no locking involved, and is therefore much
//more efficient. Also note that values attached to the thread through a ThreadLocal are automatically discarded when the thread dies, which won't happen 
//with ConcurrentHashMap.

public class StudyThreadLocal {

	
	public static ThreadLocal<String> data =  new ThreadLocal<String>();
	
	static String getModifiedData(int i) { 
		data.set(i+"000"); // here i+"000" is the common functionality
		return data.get();
	}
	
	public static void main(String[] args) {
		
       ExecutorService es = Executors.newFixedThreadPool(4);

       IntStream.range(0,1000).forEach(f->{
    	   try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	   es.submit(()->{
    		   System.out.println(getModifiedData(f));
           });
       });
     

	}

}
