package examples;


//If you use the volatile keyword flag will not get stored in thread local memory , instead it will be present in the main memory itself , so that even if any thread
//access the variable they will always read the latest value of the variable.

public class VolatileVisibilityProblem {

	static volatile boolean flag = true;
	
	public static void main(String[] args) {

		Thread thread1 = new Thread(()->{
					for(int i=0;i<100;i++) {	
						System.out.println(flag);
						boolean f= flag;
						if(!f) {
							break;
						}else {
							System.out.println(i);
						}
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				});
		

		Thread thread2 = new Thread(()->{	
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}				
				flag= false;
				
		});
		
		
		
		thread1.start();
		thread2.start();
		
		
	}
	
	

}
